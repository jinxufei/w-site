package com.urwoo.article.adapter;

import com.urwoo.article.vo.query.ArticleCategoryQueryParam;
import com.urwoo.request.ArticleCategoryQueryReq;
import com.urwoo.tools.ObjectTools;

public class ArticleCategoryQueryParamAdapter {

    public static ArticleCategoryQueryReq param2Req(ArticleCategoryQueryParam articleCategoryQueryParam) {
        if (ObjectTools.isNull(articleCategoryQueryParam))
            return null;

        ArticleCategoryQueryReq req = new ArticleCategoryQueryReq();

        req.setNames(articleCategoryQueryParam.getNames());
        if (ObjectTools.nonNull(articleCategoryQueryParam.getStatus())) {
            req.setStatus(articleCategoryQueryParam.getStatus().code());
        }
        req.setStartDate(articleCategoryQueryParam.getStartDate());
        req.setEndDate(articleCategoryQueryParam.getEndDate());

        return req;
    }
}
