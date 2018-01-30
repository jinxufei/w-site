package com.urwoo.web.adapter;

import com.urwoo.article.vo.query.ArticleQueryParam;
import com.urwoo.tools.MapTools;

import java.util.Map;

public class ArticleReqAdapter extends AbstractReqParamAdapter{

    public static ArticleQueryParam req2param(Map<String, Object> param){

        ArticleQueryParam req = new ArticleQueryParam();
        if (MapTools.isNull(param))
            return req;
        return req;
    }
}
