package com.urwoo.po;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleStarPo {
    private Long id;
    private Long userId;
    private Long articleId;
    private Integer status;
    private Date createTime;
    private Date modifyTime;
}
