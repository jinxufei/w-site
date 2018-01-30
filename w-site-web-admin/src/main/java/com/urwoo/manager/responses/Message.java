package com.urwoo.manager.responses;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "message")
@PropertySource(value = "classpath:message.properties", encoding = "UTF-8")
public class Message {

    private String noLogin;
    private String usernameOrPasswordError;
    private String verifyCodeError;

    private String articleCateDeleted;
    private String articleCateNotExist;
    private String articleCateNameIsExist;

    private String articleDeleted;
    private String articleNotExist;
    private String articleTitleIsExist;

    private String usernameIsExist;
    private String userPhoneIsExist;
    private String userEmailIsExist;
    private String userNotExist;

    private String sysUsernameIsExist;
    private String sysUserNotExist;
    private String sysUserUsernameNotModified;
    private String sysUserNotSelectId;
}

