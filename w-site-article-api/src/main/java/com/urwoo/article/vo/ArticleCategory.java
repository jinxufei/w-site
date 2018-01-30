package com.urwoo.article.vo;

import com.urwoo.enums.Status;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ArticleCategory {

    private Long  id;
    private String name;
    private Long sort;
    private Status status;
    private String remark;
    private Date createTime;
    private Date modifyTime;
}
