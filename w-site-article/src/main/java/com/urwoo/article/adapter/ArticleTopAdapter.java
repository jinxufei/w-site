package com.urwoo.article.adapter;

import com.urwoo.article.vo.ArticleTop;
import com.urwoo.enums.Status;
import com.urwoo.po.ArticleTopPo;
import com.urwoo.tools.ObjectTools;

public class ArticleTopAdapter {

    public static ArticleTopPo articleTop2Po(ArticleTop top) {
        if (ObjectTools.isNull(top)) {
            return null;
        }
        ArticleTopPo po = new ArticleTopPo();
        po.setArticleId(top.getArticleId());
        po.setId(top.getId());
        po.setUserId(top.getUserId());

        if (ObjectTools.nonNull(top.getStatus())) {
            po.setStatus(top.getStatus().code());
        } else {
            po.setStatus(Status.ON.code());
        }
        po.setCreateTime(top.getCreateTime());
        po.setModifyTime(top.getModifyTime());
        return po;
    }
}
