package com.urwoo.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysRolePo {
    private Long id;
    private String name;
    private Integer status;
    private Date createTime;
    private Date modifyTime;
}
