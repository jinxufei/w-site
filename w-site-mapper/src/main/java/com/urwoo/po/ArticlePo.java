package com.urwoo.po;

import lombok.Data;
import java.util.Date;

@Data
public class ArticlePo {
    protected Long id;
    protected String title;
    protected String content;
    protected Integer types;
    protected Integer checkStatus;
    protected Integer status;
    protected Date createTime;
    protected Date modifyTime;
    protected Long articleCateId;
    protected Long userId;
}
