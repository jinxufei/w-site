package com.urwoo.article.vo;

import com.urwoo.enums.CheckStatus;
import com.urwoo.enums.Status;
import com.urwoo.enums.Type;
import lombok.Data;

import java.util.Date;

@Data
public class Article {

    private Long id;
    private String title;
    private String content;
    private Type types;
    private CheckStatus checkStatus;
    private Status status;
    private Date createTime;
    private Date modifyTime;
    private Long articleCateId;
    private Long userId;
}
