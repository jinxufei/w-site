package com.urwoo.po;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ArticleCategoryPo {

    private Long  id;
    private String name;
    private Long sort;
    private Integer status;
    private String remark;
    private Date createTime;
    private Date modifyTime;
}
