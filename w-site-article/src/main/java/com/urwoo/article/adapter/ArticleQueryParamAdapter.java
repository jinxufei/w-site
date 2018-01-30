package com.urwoo.article.adapter;

import com.urwoo.article.vo.query.ArticleQueryParam;
import com.urwoo.request.ArticleQueryReq;
import com.urwoo.tools.ObjectTools;

public class ArticleQueryParamAdapter {

    public static ArticleQueryReq param2Req(ArticleQueryParam articleQueryParam) {
        if (ObjectTools.isNull(articleQueryParam))
            return null;

        ArticleQueryReq req = new ArticleQueryReq();

        req.setTitle(articleQueryParam.getTile());
        if (ObjectTools.nonNull(articleQueryParam.getStatus())) {
            req.setStatus(articleQueryParam.getStatus().code());
        }
        req.setStartDate(articleQueryParam.getStartDate());
        req.setEndDate(articleQueryParam.getEndDate());
        req.setArticleCateIds(articleQueryParam.getCateIds());

        return req;
    }
}
