package com.urwoo.article;

import com.urwoo.article.hystrix.ArticleeQuotaServiceHystrix;
import com.urwoo.article.vo.ArticleNoTop;
import com.urwoo.article.vo.ArticleShare;
import com.urwoo.article.vo.ArticleStar;
import com.urwoo.article.vo.ArticleTop;
import com.urwoo.model.WResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "w-article-service", fallback = ArticleeQuotaServiceHystrix.class)
public interface ArticleQuotaRemoteService {

    @RequestMapping(path = "/article/stars/{articleId}", method = RequestMethod.GET)
    WResult starCount(@PathVariable("articleId") Long articleId);

    @RequestMapping(path = "/article/tops/{articleId}", method = RequestMethod.GET)
    WResult topCount(@PathVariable("articleId") Long articleId);

    @RequestMapping(path = "/article/notops/{articleId}", method = RequestMethod.GET)
    WResult noTopCount(@PathVariable("articleId") Long articleId);

    @RequestMapping(path = "/article/shares/{articleId}", method = RequestMethod.GET)
    WResult shareCount(@PathVariable("articleId") Long articleId);

    @RequestMapping(path = "/article/comments/{articleId}", method = RequestMethod.GET)
    WResult commentCount(@PathVariable("articleId") Long articleId);

    @RequestMapping(path = "/article/star", method = RequestMethod.GET)
    WResult star(@RequestBody(required = false) ArticleStar articleStar);

    @RequestMapping(path = "/article/top", method = RequestMethod.GET)
    WResult top(@RequestBody(required = false) ArticleTop articleTop);

    @RequestMapping(path = "/article/notop", method = RequestMethod.GET)
    WResult noTop(@RequestBody(required = false) ArticleNoTop articleNoTop);

    @RequestMapping(path = "/article/share", method = RequestMethod.GET)
    WResult share(@RequestBody(required = false) ArticleShare articleShare);
}
