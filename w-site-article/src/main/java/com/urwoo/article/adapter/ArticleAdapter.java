package com.urwoo.article.adapter;

import com.urwoo.article.vo.Article;
import com.urwoo.po.ArticlePo;
import com.urwoo.tools.ObjectTools;

public class ArticleAdapter {

    public static ArticlePo article2Po(Article article){

        if (ObjectTools.isNull(article))
            return null;

        ArticlePo articlePo = new ArticlePo();

        articlePo.setId(article.getId());
        articlePo.setTitle(article.getTitle());
        articlePo.setTypes(0);
        articlePo.setStatus(article.getStatus().code());
        articlePo.setContent(article.getContent());
        articlePo.setCreateTime(article.getCreateTime());
        articlePo.setModifyTime(article.getModifyTime());
        articlePo.setArticleCateId(article.getArticleCateId());
        articlePo.setUserId(article.getUserId());
        return articlePo;
    }
}
