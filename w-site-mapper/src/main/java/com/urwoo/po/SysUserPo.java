package com.urwoo.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserPo {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String ssoId;
    private Integer status;
    private Date createTime;
    private Date modifyTime;
}
