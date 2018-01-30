package com.urwoo.sys.vo;

import com.urwoo.enums.Status;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable{
    private static final long serialVersionUID = -2670125576780574968L;
    private Long id;
    private String name;
    private String username;
    private String password;
    private String ssoId;
    private Status status;
    private Date createTime;
    private Date modifyTime;
}
