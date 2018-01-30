package com.urwoo.request;

import lombok.Data;

import java.util.Arrays;

@Data
public class SysUserQueryReq extends BaseQueryReq{
    private String[] names;

    @Override
    public String toString() {
        return "SysUserQueryReq{" +
                "status=" + status +
                ", names=" + Arrays.toString(names) +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
