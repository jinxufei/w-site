package com.urwoo.article.adapter;

import com.urwoo.article.vo.ArticleCategory;
import com.urwoo.po.ArticleCategoryPo;
import com.urwoo.tools.ObjectTools;

public class ArticleCategoryAdapter {

    public static ArticleCategoryPo articleCategory2Po(ArticleCategory articleCategory) {

        if (ObjectTools.isNull(articleCategory))
            return null;

        ArticleCategoryPo po = new ArticleCategoryPo();
        po.setId(articleCategory.getId());
        po.setName(articleCategory.getName());
        po.setSort(articleCategory.getSort());
        po.setStatus(articleCategory.getStatus().code());
        po.setRemark(articleCategory.getRemark());
        po.setCreateTime(articleCategory.getCreateTime());
        po.setModifyTime(articleCategory.getModifyTime());

        return po;
    }
}
