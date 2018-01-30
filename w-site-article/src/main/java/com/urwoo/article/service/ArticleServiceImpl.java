package com.urwoo.article.service;

import com.urwoo.article.adapter.ArticleAdapter;
import com.urwoo.article.adapter.ArticleQueryParamAdapter;
import com.urwoo.article.codes.ArticleBizCode;
import com.urwoo.article.vo.Article;
import com.urwoo.article.vo.query.ArticleQueryParam;
import com.urwoo.enums.CheckStatus;
import com.urwoo.enums.Status;
import com.urwoo.mapper.ArticleMapper;
import com.urwoo.model.WResult;
import com.urwoo.po.ArticleInfoPo;
import com.urwoo.po.ArticlePo;
import com.urwoo.request.ArticleQueryReq;
import com.urwoo.tools.JsonTools;
import com.urwoo.tools.ObjectTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/article/")
@Slf4j
public class ArticleServiceImpl implements ArticleBizCode{

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping(path = "save", method = RequestMethod.POST)
    @Transactional
    public WResult save(@RequestBody(required = false) Article article){
        Assert.notNull(article, "articlePo must not be null!");
        Assert.hasText(article.getTitle(), "article title must be not null or empty!");
        Assert.hasText(article.getContent(), "article content must be not null or empty!");
        if (checkArticleTitleIsExist(article.getUserId(), article.getTitle())){
            return WResult.build(ARTICLE_TITLE_IS_EXIST);
        }
        article.setStatus(Status.ON);
        article.setCheckStatus(CheckStatus.CHECKING);
        articleMapper.save(ArticleAdapter.article2Po(article));
        return WResult.ok();
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    @Transactional
    public WResult update(@RequestBody(required = false) Article article){
        Assert.notNull(article, "articlePo must not be null!");
        Assert.notNull(article.getId(), "article id must not be null!");
        Assert.hasText(article.getTitle(), "article title must be not null or empty!");
        Assert.hasText(article.getContent(), "article content must be not null or empty!");
        articleMapper.update(ArticleAdapter.article2Po(article));
        return WResult.ok();
    }

    @RequestMapping(path = "pages", method = RequestMethod.POST)
    public WResult pages(@RequestBody(required = false) ArticleQueryParam articleQueryParam,
                         @RequestParam(name = "start", defaultValue = "0") Long start,
                         @RequestParam(name = "limit", defaultValue = "0") Integer limit) {

        ArticleQueryReq req = ArticleQueryParamAdapter.param2Req(articleQueryParam);
        List<ArticleInfoPo> articles = articleMapper.query(req, start, limit);
        long count = articleMapper.count(req);

        log.debug("pages() : articles={}, count={}", JsonTools.transformJsonStr(articles), count);
        return WResult.page(articles, count, start, limit);
    }

    @RequestMapping(path = "get/{id}", method = RequestMethod.GET)
    public WResult get(@PathVariable("id") Long id){
        ArticlePo articlePo = articleMapper.getById(id);
        if (ObjectTools.isNull(articlePo)){
            return WResult.build(ARTICLE_NOT_EXIST);
        }
        if (ObjectTools.equals(articlePo.getStatus(), Status.DEL.code())){
            return WResult.build(ARTICLE_DELETED);
        }
        return WResult.ok(articlePo);
    }

    @RequestMapping(path = "getInfo/{id}", method = RequestMethod.GET)
    public WResult getInfo(@PathVariable("id") Long id){
        ArticleInfoPo articleInfo = articleMapper.getInfo(id);
        if (ObjectTools.isNull(articleInfo)){
            return WResult.build(ARTICLE_NOT_EXIST);
        }
        if (ObjectTools.equals(articleInfo.getStatus(), Status.DEL.code())){
            return WResult.build(ARTICLE_DELETED);
        }
        return WResult.ok(articleInfo);
    }

    @DeleteMapping(path = "del")
    @Transactional
    public WResult batchDelete(@RequestParam("ids") Long[] ids) {
        Assert.notEmpty(ids, "ids must be not null!");
        log.info("batchDelete() : id={}", ids);
        articleMapper.batchUpdateStatus(Arrays.asList(ids), Status.DEL.code());
        return WResult.ok();
    }

    @PutMapping(path = "startup")
    @Transactional
    public WResult batchStartUp(@RequestParam("ids") Long[] ids) {
        Assert.notEmpty(ids, "ids must be not null!");
        log.info("batchStartUp() : id={}", ids);
        articleMapper.batchUpdateStatus(Arrays.asList(ids), Status.ON.code());
        return WResult.ok();
    }

    @PutMapping(path = "pause")
    @Transactional
    public WResult batchPause(@RequestParam("ids") Long[] ids) {
        Assert.notEmpty(ids, "ids must be not null!");
        log.info("batchPause() : id={}", ids);
        articleMapper.batchUpdateStatus(Arrays.asList(ids), Status.OFF.code());
        return WResult.ok();
    }

    //
    private boolean checkArticleTitleIsExist(Long userId, String title) {
        return ObjectTools.nonNull(articleMapper.getByTitle(userId, title));
    }
}
