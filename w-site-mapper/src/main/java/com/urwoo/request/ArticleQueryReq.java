package com.urwoo.request;

import lombok.Data;

@Data
public class ArticleQueryReq extends BaseQueryReq{
    private Long[] articleCateIds;
    private String title;
}
