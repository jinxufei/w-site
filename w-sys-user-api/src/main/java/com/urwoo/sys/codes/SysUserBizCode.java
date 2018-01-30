package com.urwoo.sys.codes;

public interface SysUserBizCode {

    /**
     * 系统用户不存在
     */
    Integer SYS_USER_NOT_EXIST = 10000;

    /**
     * 系统用户名称已经存在
     */
    Integer SYS_USERNAME_EXIST = 10001;

    /**
     * 系统用户名不能修改
     */
    Integer SYS_USERNAME_NOT_MODIFIED = 10002;
}
