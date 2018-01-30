package com.urwoo.manager.controller;

import com.urwoo.article.ArticleCategoryRemoteService;
import com.urwoo.enums.Status;
import com.urwoo.manager.adapter.ArticleCategoryAdapter;
import com.urwoo.manager.adapter.ParamAdapter;
import com.urwoo.manager.responses.WResponses;
import com.urwoo.model.WResult;
import com.urwoo.sys.vo.SysUser;
import com.urwoo.tools.JsonTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/cate/")
@Slf4j
public class ArticleCategoryController extends BaseController {

    @Autowired
    private ArticleCategoryRemoteService articleCategoryRemoteService;

    @RequestMapping(path = "page", method = RequestMethod.GET)
    public WResponses page(@RequestParam Map<String, Object> params) {
        try {
            WResult wResult = articleCategoryRemoteService.pages(
                    ArticleCategoryAdapter.param2CategoryQueryParam(params),
                    ArticleCategoryAdapter.start(params), ArticleCategoryAdapter.limit(params));

            log.info("page() :param={}, res={}", params, JsonTools.transformJsonStr(wResult));

            return articleCateCode(wResult);
        } catch (Exception e) {
            log.error("query article category cause error!", e);
        }
        return WResponses.error();
    }

    @RequestMapping(path = "save", method = RequestMethod.POST)
    public WResponses save(@RequestParam Map<String, Object> params) {
        WResult wResult = articleCategoryRemoteService.save(ArticleCategoryAdapter.param2Category(params));
        log.info("save() : params={}, res={}", params, JsonTools.transformJsonStr(wResult));
        return articleCateCode(wResult);
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    public WResponses update(@RequestParam Map<String, Object> params) {
        WResult wResult = articleCategoryRemoteService.update(ArticleCategoryAdapter.param2Category(params));
        log.info("update() : params={}, res={}", params, JsonTools.transformJsonStr(wResult));
        return articleCateCode(wResult);
    }

    @RequestMapping(path = "get", method = RequestMethod.GET)
    public WResponses get(@RequestParam(name = "id") Long id) {
        WResult wResult = articleCategoryRemoteService.get(id);
        log.info("get() : id={}, res={}", id, JsonTools.transformJsonStr(wResult));
        return articleCateCode(wResult);
    }

    @RequestMapping(path = "batchStartup", method = RequestMethod.POST)
    public WResponses batchStartup(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = articleCategoryRemoteService.batchStartUp(ids);
        log.info("batchStartup() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCateCode(wResult);
    }

    @RequestMapping(path = "batchPause", method = RequestMethod.POST)
    public WResponses batchPause(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = articleCategoryRemoteService.batchPause(ids);
        log.info("batchPause() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCateCode(wResult);
    }

    @RequestMapping(path = "batchDelete", method = RequestMethod.POST)
    public WResponses batchDelete(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = articleCategoryRemoteService.batchDelete(ids);
        log.info("batchDelete() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCateCode(wResult);
    }

    @RequestMapping(path = "normal/list")
    public WResponses normalCateList() {
        WResult wResult = articleCategoryRemoteService.listByStatus(Status.ON);
        log.info("normalCateList() : res={}", JsonTools.transformJsonStr(wResult));
        return articleCateCode(wResult);
    }
}
