package com.urwoo.article;

import com.urwoo.article.hystrix.ArticleCategoryServiceHystrix;
import com.urwoo.article.vo.ArticleCategory;
import com.urwoo.article.vo.query.ArticleCategoryQueryParam;
import com.urwoo.enums.Status;
import com.urwoo.model.WResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "w-article-service", fallback = ArticleCategoryServiceHystrix.class)
public interface ArticleCategoryRemoteService {

    @RequestMapping(path = "/articleCate", method = RequestMethod.POST)
    WResult save(@RequestBody(required = false) ArticleCategory articleCategory);

    @RequestMapping(path = "/articleCate", method = RequestMethod.PUT)
    WResult update(@RequestBody(required = false) ArticleCategory articleCategory);

    @RequestMapping(path = "/articleCate/pages", method = RequestMethod.POST)
    WResult pages(@RequestBody(required = false) ArticleCategoryQueryParam articleCategoryQueryParam,
                  @RequestParam(name = "start", defaultValue = "0") Long start,
                  @RequestParam(name = "limit", defaultValue = "0") Integer limit);

    @RequestMapping(path = "/articleCate/del", method = RequestMethod.DELETE)
    WResult batchDelete(@RequestParam("ids") Long[] ids);

    @RequestMapping(path = "/articleCate/start", method = RequestMethod.PUT)
    WResult batchStartUp(@RequestParam("ids") Long[] ids);

    @RequestMapping(path = "/articleCate/pause", method = RequestMethod.PUT)
    WResult batchPause(@RequestParam("ids") Long[] ids);

    @RequestMapping(path = "/articleCate/{id}", method = RequestMethod.GET)
    WResult get(@RequestParam("id") Long id);

    @RequestMapping(path = "/articleCate/status_{status}", method = RequestMethod.GET)
    WResult listByStatus(@PathVariable("status") Status status);
}
