package com.urwoo.manager.adapter;

import com.urwoo.article.vo.ArticleCategory;
import com.urwoo.article.vo.query.ArticleCategoryQueryParam;
import com.urwoo.enums.Status;
import com.urwoo.tools.MapTools;
import com.urwoo.tools.StringTools;

import java.util.Map;

public class ArticleCategoryAdapter extends ParamAdapter{

    public static ArticleCategoryQueryParam param2CategoryQueryParam(Map<String, Object> param) {

        ArticleCategoryQueryParam queryParam = new ArticleCategoryQueryParam();
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
        if (checkValueNonNull(param, "names")) {
            queryParam.setNames(StringTools.str2Array((String) param.get("names")));
        }

        return queryParam;
    }

    public static ArticleCategory param2Category(Map<String, Object> param) {

        ArticleCategory articleCategory = new ArticleCategory();
        if (MapTools.isNull(param))
            return articleCategory;

        if (checkValueNonNull(param, "id")) {
            articleCategory.setId(Long.parseLong((String) param.get("id")));
        }
        if (checkValueNonNull(param, "name")) {
            articleCategory.setName((String) param.get("name"));
        }
        if (checkValueNonNull(param, "sort")) {
            articleCategory.setStatus(Status.status(
                    Integer.parseInt((String) param.get("sort"))));
        }
        if (checkValueNonNull(param, "remark")) {
            articleCategory.setName((String) param.get("remark"));
        }

        return articleCategory;
    }
}
