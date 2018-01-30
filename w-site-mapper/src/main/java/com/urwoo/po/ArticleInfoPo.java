package com.urwoo.po;

import lombok.Data;

@Data
public class ArticleInfoPo extends ArticlePo{
    private String username;
    private String nickname;
    private String articleCategoryName;
}
