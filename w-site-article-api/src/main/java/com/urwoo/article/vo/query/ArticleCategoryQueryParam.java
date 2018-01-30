package com.urwoo.article.vo.query;

import com.urwoo.query.BaseQueryParam;
import lombok.Data;

import java.util.Arrays;

@Data
public class ArticleCategoryQueryParam extends BaseQueryParam {

    private String[] names;

    @Override
    public String toString() {
        return "ArticleCategoryQueryParam{" +
                "status=" + status +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", names=" + Arrays.toString(names) +
                '}';
    }
}
