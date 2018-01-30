package com.urwoo.article.codes;

import com.urwoo.model.BaseBizCode;

public interface ArticleBizCode extends BaseBizCode {

    /**
     * 段子被删除
     */
    Integer ARTICLE_DELETED = 2000;

    /**
     * 段子不存在
     */
    Integer ARTICLE_NOT_EXIST = 2001;

    /**
     * 段子标题存在
     */
    Integer ARTICLE_TITLE_IS_EXIST = 2002;
}
