package com.urwoo.po;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleSharePo {
    private Long id;
    private Long userId;
    private Long articleId;
    private Integer sharePlatform;
    private Integer status;
    private Date createTime;
    private Date modifyTime;
}
