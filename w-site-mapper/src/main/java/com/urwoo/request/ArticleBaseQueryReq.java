package com.urwoo.request;

import lombok.Data;

@Data
public class ArticleBaseQueryReq extends BaseQueryReq{
    protected Long userId;
    protected Long articleId;
}
