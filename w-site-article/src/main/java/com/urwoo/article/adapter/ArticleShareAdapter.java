package com.urwoo.article.adapter;

import com.urwoo.article.vo.ArticleShare;
import com.urwoo.enums.Status;
import com.urwoo.po.ArticleSharePo;
import com.urwoo.tools.ObjectTools;

public class ArticleShareAdapter {

    public static ArticleSharePo articleShare2Po(ArticleShare share) {
        if (ObjectTools.isNull(share)) {
            return null;
        }
        ArticleSharePo po = new ArticleSharePo();
        po.setArticleId(share.getArticleId());
        po.setId(share.getId());
        po.setUserId(share.getUserId());

        if (ObjectTools.nonNull(share.getStatus())) {
            po.setStatus(share.getStatus().code());
        } else {
            po.setStatus(Status.ON.code());
        }
        po.setSharePlatform(share.getSharePlatform().code());
        po.setCreateTime(share.getCreateTime());
        po.setModifyTime(share.getModifyTime());
        return po;
    }
}
