package com.urwoo.po;

import lombok.Data;
import java.util.Date;

@Data
public class ArticleCommonPo {
    private Long id;
    private Long userId;
    private Long articleId;
    private String content;
    private Integer status;
    private Date createTime;
    private Date modifyTime;
}
