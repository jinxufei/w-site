package com.urwoo.request;

import lombok.Data;

@Data
public class ArticleCommentQueryReq extends BaseQueryReq{
    private Long articleId;
    private Long userId;
}
