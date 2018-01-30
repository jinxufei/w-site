package com.urwoo.manager.adapter;

import com.urwoo.article.vo.Article;
import com.urwoo.article.vo.query.ArticleQueryParam;
import com.urwoo.enums.Status;
import com.urwoo.tools.MapTools;
import com.urwoo.tools.StringTools;

import java.util.Map;

public class ArticleAdapter extends ParamAdapter{

    public static ArticleQueryParam param2ArticleQueryParam(Map<String, Object> param) {

        ArticleQueryParam queryParam = new ArticleQueryParam();

        if (MapTools.isNull(param))
            return queryParam;


        if (checkValueNonNull(param, "status")) {
            queryParam.setStatus(Status.status(
                    Integer.parseInt((String) param.get("status"))));
        }
        if (checkValueNonNull(param, "startDate")) {
            queryParam.setStartDate((String) param.get("startDate"));
        }
        if (checkValueNonNull(param, "endDate")) {
            queryParam.setEndDate((String) param.get("endDate"));
        }
        if (checkValueNonNull(param, "title")) {
            queryParam.setTile((String) param.get("title"));
        }
        if (checkValueNonNull(param, "cateIds")) {
            queryParam.setCateIds(StringTools.str2LongArray((String)param.get("cateIds")));
        }
        return queryParam;
    }


    public static Article param2Article(Map<String, Object> param) {

        Article article = new Article();
        if (MapTools.isNull(param))
            return article;

        if (checkValueNonNull(param, "id")) {
            article.setId(Long.parseLong((String) param.get("id")));
        }
        if (checkValueNonNull(param, "title")) {
            article.setTitle((String) param.get("title"));
        }
        if (checkValueNonNull(param, "content")) {
            article.setTitle((String) param.get("content"));
        }
        if (checkValueNonNull(param, "cateId")) {
            article.setArticleCateId(Long.parseLong((String) param.get("cateId")));
        }

        return article;
    }
}
