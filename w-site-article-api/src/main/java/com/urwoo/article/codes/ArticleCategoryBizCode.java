package com.urwoo.article.codes;

import com.urwoo.model.BaseBizCode;

public interface ArticleCategoryBizCode extends BaseBizCode {
    /**
     * 段子种类被删除
     */
    Integer ARTICLE_CATEGORY_DELETED = 1000;

    /**
     * 段子种类不存在
     */
    Integer ARTICLE_CATEGORY_NOT_EXIST = 1001;

    /**
     * 段子种类名称已经存在
     */
    Integer ARTICLE_CATEGORY_NAME_IS_EXIST = 1002;
}
