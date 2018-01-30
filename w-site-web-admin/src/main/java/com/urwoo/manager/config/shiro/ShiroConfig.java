package com.urwoo.manager.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明
     *   1、一个URL可以配置多个Filter，使用逗号分隔
     *   2、当设置多个过滤器时，全部验证通过，才视为通过
     *   3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new NonLoginFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/verify/image", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "anon");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");

//        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        //未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置SecurityManager
     */
    @Bean
    public SecurityManager securityManager(DefaultWebSessionManager webSessionManager,
                                           WRealm wRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(wRealm);
        securityManager.setSessionManager(webSessionManager);
        return securityManager;
    }

    @Bean
    public RedisSessionDao redisSessionDao() {
        RedisSessionDao redisSessionDao = new RedisSessionDao();
        redisSessionDao.setExpireTime(30 * 60 * 60);
        return redisSessionDao;
    }

    @Bean
    public DefaultWebSessionManager configWebSessionManager(RedisSessionDao redisSessionDao){
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        //manager.setCacheManager(cacheManager);// 加入缓存管理器
        manager.setSessionDAO(redisSessionDao);// 设置SessionDao
        manager.setDeleteInvalidSessions(true);// 删除过期的session
        manager.setGlobalSessionTimeout(redisSessionDao.getExpireTime());// 设置全局session超时时间
        manager.setSessionValidationSchedulerEnabled(true);// 是否定时检查session

        return manager;
    }

    /*  ========================身份认证配置 START======================== */
    /**
     * 自定义密码匹配器
     */
    @Bean
    public WCredentialsMatcher wCredentialsMatcher(){
        return new WCredentialsMatcher();
    }

    @Bean
    public WRealm wRealm(WCredentialsMatcher wCredentialsMatcher) {
        WRealm realm = new WRealm();
        realm.setCredentialsMatcher(wCredentialsMatcher);
        return realm;
    }
    /*  ========================身份认证配置 END======================== */

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /*  ========================权限控制配置 START======================== */
    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启shiro aop注解支持。
     * 使用代理方式、所以需要开启代码支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /*  ========================权限控制配置 END======================== */
}
