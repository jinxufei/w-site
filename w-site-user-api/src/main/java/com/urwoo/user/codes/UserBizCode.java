package com.urwoo.user.codes;

import com.urwoo.model.BaseBizCode;

public interface UserBizCode extends BaseBizCode{

    /**
     * 用户名已经存在
     */
    Integer USERNAME_IS_EXIST = 4000;

    /**
     * 用户名称不存在
     */
    Integer USERNAME_NOT_EXIST = 4010;

    /**
     * 用户手机号已经存在
     */
    Integer USER_PHONE_IS_EXIST = 4001;

    /**
     * 用户手机号不存在
     */
    Integer USER_PHONE_NOT_EXIST = 4011;

    /**
     * 用户邮箱已经存在
     */
    Integer USER_EMAIL_IS_EXIST = 4002;

    /**
     * 用户邮箱不存在
     */
    Integer USER_EMAIL_NOT_EXIST = 4012;

    /**
     * 用户对象不存在
     */
    Integer USER_NOT_EXIST = 4003;
}
