package com.urwoo.article.service;

import com.urwoo.article.adapter.ArticleNoTopAdapter;
import com.urwoo.article.adapter.ArticleShareAdapter;
import com.urwoo.article.adapter.ArticleStarAdapter;
import com.urwoo.article.adapter.ArticleTopAdapter;
import com.urwoo.article.vo.ArticleNoTop;
import com.urwoo.article.vo.ArticleShare;
import com.urwoo.article.vo.ArticleStar;
import com.urwoo.article.vo.ArticleTop;
import com.urwoo.enums.Status;
import com.urwoo.mapper.*;
import com.urwoo.model.WResult;
import com.urwoo.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ArticleQuotaServiceImpl {

    @Autowired
    private ArticleTopMapper articleTopMapper;
    @Autowired
    private ArticleNoTopMapper articleNoTopMapper;
    @Autowired
    private ArticleShareMapper articleShareMapper;
    @Autowired
    private ArticleStarMapper articleStarMapper;
    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @RequestMapping(path = "/article/stars/{articleId}", method = RequestMethod.GET)
    public WResult starCount(@PathVariable("articleId") Long articleId) {
        ArticleStarQueryReq req = new ArticleStarQueryReq();
        req.setArticleId(articleId);
        req.setStatus(Status.ON.code());
        long count = articleStarMapper.count(req);
        log.info("starCount() : articleId={}, count={}", articleId, count);
        return WResult.count(count);
    }

    @RequestMapping(path = "/article/tops/{articleId}", method = RequestMethod.GET)
    public WResult topCount(@PathVariable("articleId") Long articleId) {
        ArticleTopQueryReq req = new ArticleTopQueryReq();
        req.setArticleId(articleId);
        req.setStatus(Status.ON.code());
        long count = articleTopMapper.count(req);
        log.info("topCount() : articleId={}, count={}", articleId, count);
        return WResult.count(count);
    }

    @RequestMapping(path = "/article/notops/{articleId}", method = RequestMethod.GET)
    public WResult noTopCount(@PathVariable("articleId") Long articleId) {
        ArticleNoTopQueryReq req = new ArticleNoTopQueryReq();
        req.setArticleId(articleId);
        req.setStatus(Status.ON.code());
        long count = articleNoTopMapper.count(req);
        log.info("noTopCount() : articleId={}, count={}", articleId, count);
        return WResult.count(count);
    }

    @RequestMapping(path = "/article/shares/{articleId}", method = RequestMethod.GET)
    public WResult shareCount(@PathVariable("articleId") Long articleId) {
        ArticleShareQueryReq req = new ArticleShareQueryReq();
        req.setArticleId(articleId);
        req.setStatus(Status.ON.code());
        long count = articleShareMapper.count(req);
        log.info("shareCount() : articleId={}, count={}", articleId, count);
        return WResult.count(count);
    }

    @RequestMapping(path = "/article/comments/{articleId}", method = RequestMethod.GET)
    public WResult commentCount(@PathVariable("articleId") Long articleId) {
        ArticleCommentQueryReq req = new ArticleCommentQueryReq();
        req.setArticleId(articleId);
        req.setStatus(Status.ON.code());
        long count = articleCommentMapper.count(req);
        log.info("commentCount() : articleId={}, count={}", articleId, count);
        return WResult.count(count);
    }

    @RequestMapping(path = "/article/star", method = RequestMethod.GET)
    public WResult star(@RequestBody(required = false) ArticleStar articleStar) {

        Assert.notNull(articleStar.getArticleId(), "article id must not be null!");
        Assert.notNull(articleStar.getUserId(), "user id must not be null!");
        articleStarMapper.save(ArticleStarAdapter.articleStar2Po(articleStar));
        log.info("star() : articleStar={}", articleStar.toString());
        return WResult.ok();
    }

    @RequestMapping(path = "/article/top", method = RequestMethod.GET)
    public WResult top(@RequestBody(required = false) ArticleTop articleTop) {

        Assert.notNull(articleTop.getArticleId(), "article id must not be null!");
        Assert.notNull(articleTop.getUserId(), "user id must not be null!");
        articleTopMapper.save(ArticleTopAdapter.articleTop2Po(articleTop));
        log.info("top() : articleTop={}", articleTop.toString());
        return WResult.ok();
    }

    @RequestMapping(path = "/article/notop", method = RequestMethod.GET)
    public WResult noTop(@RequestBody(required = false) ArticleNoTop articleNoTop) {

        Assert.notNull(articleNoTop.getArticleId(), "article id must not be null!");
        Assert.notNull(articleNoTop.getUserId(), "user id must not be null!");
        articleNoTopMapper.save(ArticleNoTopAdapter.articleNoTop2Po(articleNoTop));
        log.info("noTop() : articleNoTop={}", articleNoTop.toString());
        return WResult.ok();
    }

    @RequestMapping(path = "/article/share", method = RequestMethod.GET)
    public WResult share(@RequestBody(required = false) ArticleShare articleShare) {

        Assert.notNull(articleShare.getArticleId(), "article id must not be null!");
        Assert.notNull(articleShare.getUserId(), "user id must not be null!");
        Assert.notNull(articleShare.getSharePlatform(), "share platform must not be null!");
        articleShareMapper.save(ArticleShareAdapter.articleShare2Po(articleShare));
        log.info("share() : articleShare={}", articleShare.toString());
        return WResult.ok();
    }
}
