package com.urwoo.manager.config.shiro;

import com.urwoo.manager.responses.WResponses;
import com.urwoo.tools.JsonTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 对没有登录的请求进行拦截, 全部返回json信息, 覆盖掉shiro原本的跳转login.jsp的拦截方式
 */
@Component
@Slf4j
public class NonLoginFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return true;
        }
        WResponses wResponses = WResponses.error(2, "未登录！");
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(JsonTools.transformJsonStr(wResponses));
        } catch (Exception e) {
            log.error("out json error!", e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }
}