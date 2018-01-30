package com.urwoo.manager.controller;

import com.urwoo.manager.responses.Message;
import com.urwoo.manager.responses.WResponses;
import com.urwoo.redis.JedisClient;
import com.urwoo.sys.vo.SysUser;
import com.urwoo.tools.StringTools;
import com.urwoo.tools.VerifyCodeTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class LoginController extends BaseController{

    @Value("${redisKey.prefix.verifycode}")
    private String VERIFYCODE;
    @Value("${redisKey.expire_time}")
    private Integer EXPIRE_TIME;
    @Value("${redisKey.wsession]")
    private String W_SESSION;

    @Autowired
    private Message message;
    @Autowired
    private JedisClient jedisClient;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public WResponses login(@RequestParam("uuid") String uuid,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("code") String code) {
        try {
            String key = VERIFYCODE + uuid;
            String sessionCode = jedisClient.get(key);
            if (StringTools.nonNullAndEmpty(sessionCode) &&
                    sessionCode.equalsIgnoreCase(code)) {
                Subject subject = SecurityUtils.getSubject();
                //令牌
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                //登录
                try {
                    subject.login(token);
                    if (subject.isAuthenticated()) {
                        SysUser sysUser = (SysUser) subject.getPrincipal();
                        return WResponses.ok().put("data", sysUser);
                    }
                } catch (AuthenticationException e) {
                    log.error("auth cause error", e);
                    return WResponses.error(message.getUsernameOrPasswordError());
                }
            } else { // 验证码 error
                return WResponses.error(message.getVerifyCodeError());
            }
        } catch (Exception e) {
            log.error("login fail ", e);
        }
        return WResponses.error();
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public WResponses logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return WResponses.ok();
    }

    @RequestMapping(path = "/currentUser", method = RequestMethod.GET)
    public WResponses currentUser(){
        SysUser sysUser = currentSysUser();
        return WResponses.ok().put("data", sysUser);
    }

    /**
     * 验证码
     */
    @RequestMapping(value = "/verify/image", method = RequestMethod.GET)
    public void verifyImage(String uid, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeTools.generateVerifyCode(4);

        //存入Redis
        String key = VERIFYCODE + uid;
        //
        jedisClient.set(key, verifyCode);
        jedisClient.expire(key, EXPIRE_TIME);

        //生成图片
        int w = 100, h = 36;
        try {
            VerifyCodeTools.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            log.error("验证码生成失败", e);
        }
    }
}
