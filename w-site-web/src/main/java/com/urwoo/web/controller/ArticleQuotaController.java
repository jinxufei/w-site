package com.urwoo.web.controller;

import com.urwoo.article.ArticleQuotaRemoteService;
import com.urwoo.article.vo.ArticleNoTop;
import com.urwoo.article.vo.ArticleShare;
import com.urwoo.article.vo.ArticleStar;
import com.urwoo.article.vo.ArticleTop;
import com.urwoo.model.WResult;
import com.urwoo.tools.ObjectTools;
import com.urwoo.web.adapter.ArticleQuotaAdapter;
import com.urwoo.web.responses.WResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quota/")
@Slf4j
public class ArticleQuotaController {

    @Autowired
    private ArticleQuotaRemoteService articleQuotaRemoteService;

    @RequestMapping(path = "stars", method = RequestMethod.GET)
    public WResponses starCount(@RequestParam("articleIds") List<Long> articleIds) {
        try {
            Map<Long, Long> stars = new HashMap<>();
            articleIds.parallelStream().forEach(id -> {
                WResult wResult = articleQuotaRemoteService.starCount(id);
                long count = count(wResult.getData());
                stars.put(id, count);
            });
            log.info("starCount() : articleIds={}, res={}", articleIds, stars);
            return WResponses.ok().put("data", stars);
        } catch (Exception e) {
            log.error("get article star num quota cause error", e);
            return WResponses.error();
        }
    }

    @RequestMapping(path = "tops", method = RequestMethod.GET)
    public WResponses topCount(@RequestParam("articleIds") List<Long> articleIds) {
        try {
            Map<Long, Long> tops = new HashMap<>();
            articleIds.parallelStream().forEach(id -> {
                WResult wResult = articleQuotaRemoteService.topCount(id);
                long count = count(wResult.getData());
                tops.put(id, count);
            });
            log.info("topCount() : articleIds={}, res={}", articleIds, tops);
            return WResponses.ok().put("data", tops);
        } catch (Exception e) {
            log.error("get article top num quota cause error", e);
            return WResponses.error();
        }
    }

    @RequestMapping(path = "notops", method = RequestMethod.GET)
    public WResponses noTopCount(@RequestParam("articleIds") List<Long> articleIds) {
        try {
            Map<Long, Long> noTops = new HashMap<>();
            articleIds.parallelStream().forEach(id -> {
                WResult wResult = articleQuotaRemoteService.noTopCount(id);
                long count = count(wResult.getData());
                noTops.put(id, count);
            });
            log.info("noTopCount() : articleIds={}, res={}", articleIds, noTops);
            return WResponses.ok().put("data", noTops);
        } catch (Exception e) {
            log.error("get article noTop num quota cause error", e);
            return WResponses.error();
        }
    }

    @RequestMapping(path = "shares", method = RequestMethod.GET)
    public WResponses shareCount(@RequestParam("articleIds") List<Long> articleIds) {
        try {
            Map<Long, Long> shares = new HashMap<>();
            articleIds.parallelStream().forEach(id -> {
                WResult wResult = articleQuotaRemoteService.shareCount(id);
                long count = count(wResult.getData());
                shares.put(id, count);
            });
            log.info("shareCount() : articleIds={}, res={}", articleIds, shares);
            return WResponses.ok().put("data", shares);
        } catch (Exception e) {
            log.error("get article share num quota cause error", e);
            return WResponses.error();
        }
    }

    @RequestMapping(path = "comments", method = RequestMethod.GET)
    public WResponses commentCount(@RequestParam("articleIds") List<Long> articleIds) {
        try {
            Map<Long, Long> comments = new HashMap<>();
            articleIds.parallelStream().forEach(id -> {
                WResult wResult = articleQuotaRemoteService.commentCount(id);
                long count = count(wResult.getData());
                comments.put(id, count);
            });
            log.info("commentCount() : articleIds={}, res={}", articleIds, comments);
            return WResponses.ok().put("data", comments);
        } catch (Exception e) {
            log.error("get article comment num quota cause error", e);
            return WResponses.error();
        }
    }

    @RequestMapping(path = "star", method = RequestMethod.POST)
    public WResponses star(@RequestParam Map<String, Object> param){
        try {
            ArticleStar articleStar = ArticleQuotaAdapter.param2Star(param);
            articleQuotaRemoteService.star(articleStar);
            log.info("star() : param={}", param);
            return WResponses.ok();
        }catch (Exception e){
            log.error("save article star quota cause error", e);
            return WResponses.error();
        }
    }

    @RequestMapping(path = "top", method = RequestMethod.POST)
    public WResponses top(@RequestParam Map<String, Object> param){
        try {
            ArticleTop articleTop = ArticleQuotaAdapter.param2Top(param);
            articleQuotaRemoteService.top(articleTop);
            log.info("top() : param={}", param);
            return WResponses.ok();
        }catch (Exception e){
            log.error("save article top quota cause error", e);
            return WResponses.error();
        }
    }

    @RequestMapping(path = "noTop", method = RequestMethod.POST)
    public WResponses noTop(@RequestParam Map<String, Object> param){
        try {
            ArticleNoTop articleNoTop = ArticleQuotaAdapter.param2NoTop(param);
            articleQuotaRemoteService.noTop(articleNoTop);
            log.info("noTop() : param={}", param);
            return WResponses.ok();
        }catch (Exception e){
            log.error("save article noTop quota cause error", e);
            return WResponses.error();
        }
    }

    @RequestMapping(path = "share", method = RequestMethod.POST)
    public WResponses share(@RequestParam Map<String, Object> param){
        try {
            ArticleShare articleShare = ArticleQuotaAdapter.param2Share(param);
            articleQuotaRemoteService.share(articleShare);
            log.info("share() : param={}", param);
            return WResponses.ok();
        }catch (Exception e){
            log.error("save article share quota cause error", e);
            return WResponses.error();
        }
    }

    //
    private Long count(Object o) {
        if (ObjectTools.nonNull(o) &&
                o instanceof HashMap) {
            Map<String, Object> map = (HashMap) o;
            Object obj;
            if ( ObjectTools.nonNull(obj = map.get("count"))){
                return obj instanceof Integer ?
                        ((Integer) obj).longValue() : (Long) obj;
            }
        }
        return 0L;
    }
}
