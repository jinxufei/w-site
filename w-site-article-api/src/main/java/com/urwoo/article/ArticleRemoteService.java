package com.urwoo.article;

import com.urwoo.article.hystrix.ArticleServiceHystrix;
import com.urwoo.article.vo.Article;
import com.urwoo.article.vo.query.ArticleQueryParam;
import com.urwoo.model.WResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "w-article-service", fallback = ArticleServiceHystrix.class)
public interface ArticleRemoteService {

    @RequestMapping(path = "/article/save", method = RequestMethod.POST)
    WResult save(@RequestBody(required = false) Article article);

    @RequestMapping(path = "/article/update", method = RequestMethod.POST)
    WResult update(@RequestBody(required = false) Article article);

    @RequestMapping(path = "/article/pages", method = RequestMethod.POST)
    WResult pages(@RequestBody(required = false) ArticleQueryParam articleQueryParam,
                  @RequestParam(name = "start", defaultValue = "0") Long start,
                  @RequestParam(name = "limit", defaultValue = "0") Integer limit);

    @RequestMapping(path = "/article/get/{id}", method = RequestMethod.GET)
    WResult get(@PathVariable("id") Long id);

    @RequestMapping(path = "/article/getInfo/{id}", method = RequestMethod.GET)
    WResult getInfo(@PathVariable("id") Long id);

    @DeleteMapping(path = "/article/del")
    WResult batchDelete(@RequestParam("ids") Long[] ids);

    @PutMapping(path = "/article/startup")
    WResult batchStartUp(@RequestParam("ids") Long[] ids);

    @PutMapping(path = "/article/pause")
    WResult batchPause(@RequestParam("ids") Long[] ids);
}
