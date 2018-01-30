package com.urwoo.po;

import lombok.Data;

import java.util.Date;

@Data
public class UserPo {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private Integer status;
    private Integer loginType;
    private String avatar;
    private Integer gender;
    private String meta;
    private Integer level;
    private String md5;
    private Date createTime;
    private Date modifyTime;
}
