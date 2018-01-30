package com.urwoo.manager.config.shiro;

import com.urwoo.manager.common.Constants;
import com.urwoo.model.WResult;
import com.urwoo.sys.SysUserRemoteService;
import com.urwoo.sys.vo.SysUser;
import com.urwoo.tools.BeanTools;
import com.urwoo.tools.JsonTools;
import com.urwoo.tools.ObjectTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

@Slf4j
public class WRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "WRealm";
    }

    @Autowired
    private SysUserRemoteService sysUserRemoteService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号.
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        WResult wResult = sysUserRemoteService.getByUsername(username);
        Object obj;
        if (ObjectTools.nonNull(wResult) && ObjectTools.nonNull(obj = wResult.getData())) {
            SysUser sysUser = BeanTools.mapToObject(obj, SysUser.class);
            log.info("WRealm # doGetAuthenticationInfo # user is {}", JsonTools.transformJsonStr(sysUser));
            if (ObjectTools.nonNull(sysUser)) {
                SimpleAuthenticationInfo authenticationInfo = new
                        SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), getName());
                SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
                return authenticationInfo;
            }
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        SysUser sysUser  = (SysUser) principals.getPrimaryPrincipal();
//
//        for(SysRole role:userInfo.getRoleList()){
//            authorizationInfo.addRole(role.getRole());
//            for(SysPermission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
//        return authorizationInfo;
        return authorizationInfo;
    }
}
