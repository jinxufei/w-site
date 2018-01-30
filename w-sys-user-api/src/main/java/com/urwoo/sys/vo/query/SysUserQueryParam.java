package com.urwoo.sys.vo.query;

import com.urwoo.query.BaseQueryParam;
import lombok.Data;
import java.util.Arrays;

@Data
public class SysUserQueryParam  extends BaseQueryParam {

    private String[] names;

    @Override
    public String toString() {
        return "SysUserQueryParam{" +
                "status=" + status +
                ", names=" + Arrays.toString(names) +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
