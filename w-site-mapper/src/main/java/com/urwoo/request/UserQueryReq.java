package com.urwoo.request;

import lombok.Data;

@Data
public class UserQueryReq extends BaseQueryReq{
    private Integer level;
    private String[] names;
}
