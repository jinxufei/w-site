package com.urwoo.web.adapter;

import com.urwoo.article.vo.ArticleNoTop;
import com.urwoo.article.vo.ArticleShare;
import com.urwoo.article.vo.ArticleStar;
import com.urwoo.article.vo.ArticleTop;
import com.urwoo.enums.SharePlatform;
import com.urwoo.tools.MapTools;

import java.util.Map;

public class ArticleQuotaAdapter extends AbstractReqParamAdapter{

    public static ArticleTop param2Top(Map<String, Object> param){
        ArticleTop articleTop = new ArticleTop();
        if (MapTools.nonNull(param)){
            articleTop.setArticleId(getArticleId(param));
            articleTop.setUserId(getUserId(param));
        }
        return articleTop;
    }

    public static ArticleNoTop param2NoTop(Map<String, Object> param){
        ArticleNoTop articleNoTop = new ArticleNoTop();
        if (MapTools.nonNull(param)){
            articleNoTop.setArticleId(getArticleId(param));
            articleNoTop.setUserId(getUserId(param));
        }
        return articleNoTop;
    }

    public static ArticleStar param2Star(Map<String, Object> param){
        ArticleStar articleStar = new ArticleStar();
        if (MapTools.nonNull(param)){
            articleStar.setArticleId(getArticleId(param));
            articleStar.setUserId(getUserId(param));
        }
        return articleStar;
    }

    public static ArticleShare param2Share(Map<String, Object> param){
        ArticleShare articleShare = new ArticleShare();
        if (MapTools.nonNull(param)){
            articleShare.setArticleId(getArticleId(param));
            articleShare.setUserId(getUserId(param));
            articleShare.setSharePlatform(SharePlatform
                    .sharePlatform(getSharePlatform(param)));
        }
        return articleShare;
    }

    //
    private static Long getUserId(Map<String, Object> param){
        return checkValueNonNull(param, "userId") ?
                Long.parseLong((String)param.get("userId")) : 0L;
    }

    private static Long getArticleId(Map<String, Object> param){
        return checkValueNonNull(param, "article") ?
                Long.parseLong((String)param.get("article")) : 0L;
    }

    private static Integer getSharePlatform(Map<String, Object> param){
        return checkValueNonNull(param, "sharePlatform") ?
                Integer.parseInt((String)param.get("sharePlatform")) : 0;
    }
}
