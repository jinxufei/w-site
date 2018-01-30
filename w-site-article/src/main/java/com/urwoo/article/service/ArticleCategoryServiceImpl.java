package com.urwoo.article.service;

import com.urwoo.article.adapter.ArticleCategoryAdapter;
import com.urwoo.article.adapter.ArticleCategoryQueryParamAdapter;
import com.urwoo.article.codes.ArticleCategoryBizCode;
import com.urwoo.article.vo.ArticleCategory;
import com.urwoo.article.vo.query.ArticleCategoryQueryParam;
import com.urwoo.enums.Status;
import com.urwoo.mapper.ArticleCategoryMapper;
import com.urwoo.model.WResult;
import com.urwoo.po.ArticleCategoryPo;
import com.urwoo.request.ArticleCategoryQueryReq;
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
@Slf4j
public class ArticleCategoryServiceImpl implements ArticleCategoryBizCode {

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @PostMapping(path = "/articleCate")
    @Transactional
    public WResult save(@RequestBody(required = false) ArticleCategory articleCategory) {

        Assert.notNull(articleCategory, "articleCategory must not be null!");
        Assert.hasText(articleCategory.getName(), "articleCategory name must not be null!");
        if (checkArticleCategoryNameIsExist(articleCategory.getName())) {
            log.error("articleCategory name is exist !: articleCategory={}", articleCategory.toString());
            return WResult.build(ARTICLE_CATEGORY_NAME_IS_EXIST);
        }
        if (ObjectTools.isNull(articleCategory.getStatus()))
            articleCategory.setStatus(Status.ON);
        articleCategoryMapper.save(
                ArticleCategoryAdapter.articleCategory2Po(articleCategory));
        return WResult.ok();
    }

    @PutMapping(path = "/articleCate")
    @Transactional
    public WResult update(@RequestBody(required = false) ArticleCategory articleCategory) {

        Assert.notNull(articleCategory, "articleCategory must not be null!");
        Assert.notNull(articleCategory.getId(), "articleCategory id must not be null!");
        Assert.hasText(articleCategory.getName(), "articleCategory name must not be null!");

        ArticleCategoryPo articleCategoryInDB = articleCategoryMapper.getById(articleCategory.getId());
        if (ObjectTools.isNull(articleCategoryInDB)) {
            log.error("article category [id={}] is not exist!", articleCategory.getId());
            return WResult.build(ARTICLE_CATEGORY_NOT_EXIST);
        }

        if (checkArticleCategoryNameIsModify(articleCategory.getName(), articleCategoryInDB.getName())){
            if (checkArticleCategoryNameIsExist(articleCategory.getName())) {
                log.error("articleCategory name [name={}] is exist !", articleCategory.getName());
                return WResult.build(ARTICLE_CATEGORY_NAME_IS_EXIST);
            }
        }
        articleCategoryMapper.update(
                ArticleCategoryAdapter.articleCategory2Po(articleCategory));
        return WResult.ok();
    }

    @PostMapping(path = "/articleCate/pages")
    public WResult pages(@RequestBody(required = false) ArticleCategoryQueryParam articleCategoryQueryParam,
                         @RequestParam(name = "start", defaultValue = "0") Long start,
                         @RequestParam(name = "limit", defaultValue = "0") Integer limit) {

        ArticleCategoryQueryReq req = ArticleCategoryQueryParamAdapter.param2Req(articleCategoryQueryParam);
        List<ArticleCategoryPo> userPoList = articleCategoryMapper.query(req, start, limit);
        long count = articleCategoryMapper.count(req);

        log.debug("pages() : userList={}, count={}", JsonTools.transformJsonStr(userPoList), count);
        return WResult.page(userPoList, count, start, limit);
    }

    @DeleteMapping(path = "/articleCate/del")
    @Transactional
    public WResult batchDelete(@RequestParam("ids") Long[] ids) {
        log.info("batchDelete() : id={}", ids);
        articleCategoryMapper.batchUpdateStatus(Arrays.asList(ids), Status.DEL.code());
        return WResult.ok();
    }

    @PutMapping(path = "/articleCate/start")
    @Transactional
    public WResult batchStartUp(@RequestParam("ids") Long[] ids) {
        log.info("batchStartUp() : id={}", ids);
        articleCategoryMapper.batchUpdateStatus(Arrays.asList(ids), Status.ON.code());
        return WResult.ok();
    }

    @PutMapping(path = "/articleCate/pause")
    @Transactional
    public WResult batchPause(@RequestParam("ids") Long[] ids) {
        log.info("batchPause() : id={}", ids);
        articleCategoryMapper.batchUpdateStatus(Arrays.asList(ids), Status.OFF.code());
        return WResult.ok();
    }

    @GetMapping(path = "/articleCate/{id}")
    public WResult get(@RequestParam("id") Long id) {
        log.info("get() : id={}", id);
        ArticleCategoryPo articleCategoryPo = articleCategoryMapper.getById(id);
        if (ObjectTools.isNull(articleCategoryPo)){
            return WResult.build(ARTICLE_CATEGORY_NOT_EXIST);
        }
        log.debug("get(): id={}, res={}", id, articleCategoryPo.toString());
        if (articleCategoryPo.getStatus() == Status.DEL.code()){
            return WResult.build(ARTICLE_CATEGORY_DELETED);
        }
        return WResult.ok(articleCategoryPo);
    }

    @RequestMapping(path = "/articleCate/status_{status}", method = RequestMethod.GET)
    public WResult listByStatus(@PathVariable("status") Status status){

        List<ArticleCategoryPo> categorys = articleCategoryMapper.listByStatus(status.code());
        log.debug("listByStatus(): status={}, res={}", status, JsonTools.transformJsonStr(categorys));
        return WResult.ok(categorys);
    }

    //
    private boolean checkArticleCategoryNameIsModify(String name, String nameInDB){
        return ObjectTools.nonEquals(name, nameInDB);
    }

    private boolean checkArticleCategoryNameIsExist(String name) {
        return ObjectTools.nonNull(articleCategoryMapper.getByName(name));
    }
}
