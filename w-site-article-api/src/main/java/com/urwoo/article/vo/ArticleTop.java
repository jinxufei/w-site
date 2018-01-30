package com.urwoo.article.vo;

import com.urwoo.enums.Status;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ArticleTop {
    private Long id;
    private Long userId;
    private Long articleId;
    private Status status;
    private Date createTime;
    private Date modifyTime;
}
