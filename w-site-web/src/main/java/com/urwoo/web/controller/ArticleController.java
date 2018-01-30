package com.urwoo.web.controller;

import com.urwoo.article.ArticleRemoteService;
import com.urwoo.article.codes.ArticleBizCode;
import com.urwoo.article.vo.query.ArticleQueryParam;
import com.urwoo.enums.Status;
import com.urwoo.model.WResult;
import com.urwoo.tools.JsonTools;
import com.urwoo.tools.ObjectTools;
import com.urwoo.web.adapter.ArticleReqAdapter;
import com.urwoo.web.responses.WResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article/")
@Slf4j
public class ArticleController implements ArticleBizCode {

    @Autowired
    private ArticleRemoteService articleRemoteService;

    @RequestMapping(path = "query", method = RequestMethod.GET)
    public WResponses list(@RequestParam Map<String, Object> param) {

        ArticleQueryParam req = ArticleReqAdapter.req2param(param);
        req.setStatus(Status.ON);
        try {
            WResult wResult = articleRemoteService.pages(req,
                    ArticleReqAdapter.start(param), ArticleReqAdapter.limit(param));
            log.info("list() : param={}, res={}", param, JsonTools.transformJsonStr(wResult));
            return WResponses.page(wResult);
        } catch (Exception e) {
            log.error("get article list cause error", e);
            return WResponses.error();
        }
    }

    @RequestMapping(path = "get", method = RequestMethod.GET)
    public WResponses get(@RequestParam("id") Long id) {
        try {
            WResult wResult = articleRemoteService.getInfo(id);
            log.info("get() : id={}, res={}", id, JsonTools.transformJsonStr(wResult));
            if (ObjectTools.equals(wResult.getCode(), ARTICLE_NOT_EXIST) ||
                    ObjectTools.equals(wResult.getCode(), ARTICLE_DELETED)) {
                log.error("article is null or article status is deleteStatus");
                return WResponses.error();
            }
            return WResponses.page(wResult);
        } catch (Exception e) {
            log.error("get article cause error", e);
            return WResponses.error();
        }
    }
}
