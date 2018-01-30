package com.urwoo.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysPermissionPo {
    private Long id;
    private String name;
    private String perCode;
    private Integer status;
    private Date createTime;
    private Date modifyTime;
}
