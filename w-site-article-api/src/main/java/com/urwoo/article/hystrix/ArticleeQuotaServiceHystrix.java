package com.urwoo.article.hystrix;

import com.urwoo.article.ArticleQuotaRemoteService;
import com.urwoo.article.vo.ArticleNoTop;
import com.urwoo.article.vo.ArticleShare;
import com.urwoo.article.vo.ArticleStar;
import com.urwoo.article.vo.ArticleTop;
import com.urwoo.model.WResult;
import org.springframework.stereotype.Component;

@Component
public class ArticleeQuotaServiceHystrix implements ArticleQuotaRemoteService {

    @Override
    public WResult starCount(Long articleId) {
        return null;
    }

    @Override
    public WResult topCount(Long articleId) {
        return null;
    }

    @Override
    public WResult noTopCount(Long articleId) {
        return null;
    }

    @Override
    public WResult shareCount(Long articleId) {
        return null;
    }

    @Override
    public WResult commentCount(Long articleId) {
        return null;
    }

    @Override
    public WResult star(ArticleStar articleStar) {
        return null;
    }

    @Override
    public WResult top(ArticleTop articleTop) {
        return null;
    }

    @Override
    public WResult noTop(ArticleNoTop articleNoTop) {
        return null;
    }

    @Override
    public WResult share(ArticleShare articleShare) {
        return null;
    }
}
