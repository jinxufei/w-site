package com.urwoo.article.vo;

import com.urwoo.enums.SharePlatform;
import com.urwoo.enums.Status;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleShare {
    private Long id;
    private Long userId;
    private Long articleId;
    private Status status;
    private SharePlatform sharePlatform;
    private Date createTime;
    private Date modifyTime;
}
