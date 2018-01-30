package com.urwoo.article.hystrix;

import com.urwoo.article.ArticleRemoteService;
import com.urwoo.article.vo.Article;
import com.urwoo.article.vo.query.ArticleQueryParam;
import com.urwoo.model.WResult;
import org.springframework.stereotype.Component;

@Component
public class ArticleServiceHystrix implements ArticleRemoteService {

    @Override
    public WResult save(Article article) {
        return null;
    }

    @Override
    public WResult update(Article article) {
        return null;
    }

    @Override
    public WResult pages(ArticleQueryParam articleQueryParam, Long start, Integer limit) {
        return null;
    }

    @Override
    public WResult get(Long id) {
        return null;
    }

    @Override
    public WResult getInfo(Long id) {
        return null;
    }

    @Override
    public WResult batchDelete(Long[] ids) {
        return null;
    }

    @Override
    public WResult batchStartUp(Long[] ids) {
        return null;
    }

    @Override
    public WResult batchPause(Long[] ids) {
        return null;
    }
}
