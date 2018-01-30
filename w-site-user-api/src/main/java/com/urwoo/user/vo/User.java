package com.urwoo.user.vo;

import com.urwoo.enums.Gender;
import com.urwoo.enums.Level;
import com.urwoo.enums.LoginType;
import com.urwoo.enums.Status;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class User {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private Status status;
    private LoginType loginType;
    private String avatar;
    private Gender gender;
    private String meta;
    private Level level;
    private String md5;
    private Date createTime;
    private Date modifyTime;
}
