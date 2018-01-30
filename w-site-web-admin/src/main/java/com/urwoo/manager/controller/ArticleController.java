package com.urwoo.manager.controller;

import com.urwoo.article.ArticleRemoteService;
import com.urwoo.manager.adapter.ArticleAdapter;
import com.urwoo.manager.adapter.ParamAdapter;
import com.urwoo.manager.responses.WResponses;
import com.urwoo.model.WResult;
import com.urwoo.tools.JsonTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article/")
@Slf4j
public class ArticleController extends BaseController {

    @Autowired
    private ArticleRemoteService articleRemoteService;

    @RequestMapping(path = "page", method = RequestMethod.GET)
    public WResponses page(@RequestParam Map<String, Object> params) {
        try {
            WResult wResult = articleRemoteService.pages(
                    ArticleAdapter.param2ArticleQueryParam(params),
                    ArticleAdapter.start(params), ArticleAdapter.limit(params));

            log.info("page() :param={}, res={}", params, JsonTools.transformJsonStr(wResult));

            return articleCode(wResult);
        } catch (Exception e) {
            log.error("query article cause error!", e);
        }
        return WResponses.error();
    }

    @RequestMapping(path = "save", method = RequestMethod.POST)
    public WResponses save(@RequestParam Map<String, Object> params) {
        try {
            WResult wResult = articleRemoteService.save(ArticleAdapter.param2Article(params));
            log.info("save() : params={}, res={}", params, JsonTools.transformJsonStr(wResult));
            return articleCode(wResult);
        }catch (Exception e){
            log.error("save article cause error!", e);
        }
        return WResponses.error();
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    public WResponses update(@RequestParam Map<String, Object> params) {
        try {
            WResult wResult = articleRemoteService.update(ArticleAdapter.param2Article(params));
            log.info("update() : params={}, res={}", params, JsonTools.transformJsonStr(wResult));
            return articleCode(wResult);
        }catch (Exception e){
            log.error("update article cause!", e);
        }
        return WResponses.error();
    }

    @RequestMapping(path = "get", method = RequestMethod.GET)
    public WResponses get(@RequestParam(name = "id") Long id) {
        WResult wResult = articleRemoteService.get(id);
        log.info("get() : id={}, res={}", id, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }

    @RequestMapping(path = "batchStartup", method = RequestMethod.POST)
    public WResponses batchStartup(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = articleRemoteService.batchStartUp(ids);
        log.info("batchStartup() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }

    @RequestMapping(path = "batchPause", method = RequestMethod.POST)
    public WResponses batchPause(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = articleRemoteService.batchPause(ids);
        log.info("batchPause() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }

    @RequestMapping(path = "batchDelete", method = RequestMethod.POST)
    public WResponses batchDelete(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = articleRemoteService.batchDelete(ids);
        log.info("batchDelete() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }
}
