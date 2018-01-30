package com.urwoo.query;

import com.urwoo.enums.Status;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseQueryParam {
    protected Status status;
    protected String startDate;
    protected String endDate;
}
