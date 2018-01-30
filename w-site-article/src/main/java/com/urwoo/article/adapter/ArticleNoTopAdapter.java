package com.urwoo.article.adapter;

import com.urwoo.article.vo.ArticleNoTop;
import com.urwoo.enums.Status;
import com.urwoo.po.ArticleNoTopPo;
import com.urwoo.tools.ObjectTools;

public class ArticleNoTopAdapter {

    public static ArticleNoTopPo articleNoTop2Po(ArticleNoTop noTop) {
        if (ObjectTools.isNull(noTop)) {
            return null;
        }
        ArticleNoTopPo po = new ArticleNoTopPo();
        po.setArticleId(noTop.getArticleId());
        po.setId(noTop.getId());
        po.setUserId(noTop.getUserId());

        if (ObjectTools.nonNull(noTop.getStatus())) {
            po.setStatus(noTop.getStatus().code());
        } else {
            po.setStatus(Status.ON.code());
        }
        po.setCreateTime(noTop.getCreateTime());
        po.setModifyTime(noTop.getModifyTime());
        return po;
    }
}
