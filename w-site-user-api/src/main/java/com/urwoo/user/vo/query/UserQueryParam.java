package com.urwoo.user.vo.query;

import com.urwoo.enums.Level;
import com.urwoo.query.BaseQueryParam;
import lombok.Data;

import java.util.Arrays;

@Data
public class UserQueryParam extends BaseQueryParam {

    private Level level;
    private String[] names;

    @Override
    public String toString() {
        return "UserQueryParam{" +
                "level=" + level +
                ", status=" + status +
                ", names=" + Arrays.toString(names) +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
