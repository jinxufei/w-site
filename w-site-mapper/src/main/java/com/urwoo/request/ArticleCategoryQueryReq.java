package com.urwoo.request;

import lombok.Data;

@Data
public class ArticleCategoryQueryReq extends BaseQueryReq{
    private String[] names;
}
