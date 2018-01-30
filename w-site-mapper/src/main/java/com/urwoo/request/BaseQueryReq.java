package com.urwoo.request;

import lombok.Data;

@Data
public class BaseQueryReq {
    protected Integer status;
    protected String startDate;
    protected String endDate;
}
