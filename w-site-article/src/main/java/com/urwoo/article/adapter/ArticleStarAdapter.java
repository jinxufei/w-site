package com.urwoo.article.adapter;

import com.urwoo.article.vo.ArticleStar;
import com.urwoo.enums.Status;
import com.urwoo.po.ArticleStarPo;
import com.urwoo.tools.ObjectTools;

public class ArticleStarAdapter {

    public static ArticleStarPo articleStar2Po(ArticleStar star) {
        if (ObjectTools.isNull(star)) {
            return null;
        }
        ArticleStarPo po = new ArticleStarPo();
        po.setArticleId(star.getArticleId());
        po.setId(star.getId());
        po.setUserId(star.getUserId());

        if (ObjectTools.nonNull(star.getStatus())) {
            po.setStatus(star.getStatus().code());
        } else {
            po.setStatus(Status.ON.code());
        }
        po.setCreateTime(star.getCreateTime());
        po.setModifyTime(star.getModifyTime());
        return po;
    }
}
