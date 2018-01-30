package com.urwoo.article.vo;

import com.urwoo.enums.Status;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleNoTop {
    private Long id;
    private Long userId;
    private Long articleId;
    private Status status;
    private Date createTime;
    private Date modifyTime;
}
