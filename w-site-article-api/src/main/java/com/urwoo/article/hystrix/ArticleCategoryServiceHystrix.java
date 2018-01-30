package com.urwoo.article.hystrix;

import com.urwoo.article.ArticleCategoryRemoteService;
import com.urwoo.article.vo.ArticleCategory;
import com.urwoo.article.vo.query.ArticleCategoryQueryParam;
import com.urwoo.enums.Status;
import com.urwoo.model.WResult;
import org.springframework.stereotype.Component;

/**
 * 熔断处理
 */
@Component
public class ArticleCategoryServiceHystrix implements ArticleCategoryRemoteService {

    @Override
    public WResult save(ArticleCategory articleCategory) {
        return null;
    }

    @Override
    public WResult update(ArticleCategory articleCategory) {
        return null;
    }

    @Override
    public WResult pages(ArticleCategoryQueryParam articleCategoryQueryParam, Long start, Integer limit) {
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

    @Override
    public WResult get(Long id) {
        return null;
    }

    @Override
    public WResult listByStatus(Status status) {
        return null;
    }
}
