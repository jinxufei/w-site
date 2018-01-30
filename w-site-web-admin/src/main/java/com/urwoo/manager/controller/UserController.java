package com.urwoo.manager.controller;

import com.urwoo.manager.adapter.ParamAdapter;
import com.urwoo.manager.adapter.UserAdapter;
import com.urwoo.manager.responses.WResponses;
import com.urwoo.model.WResult;
import com.urwoo.tools.JsonTools;
import com.urwoo.user.UserRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController extends BaseController{

    @Autowired
    private UserRemoteService userRemoteService;

    @RequestMapping(path = "page", method = RequestMethod.GET)
    public WResponses page(@RequestParam Map<String, Object> params){
        try {
            WResult wResult = userRemoteService.pages(
                    UserAdapter.param2UserQueryParam(params),
                    UserAdapter.start(params), UserAdapter.limit(params));

            log.info("page() :param={}, res={}", params, JsonTools.transformJsonStr(wResult));

            return userCode(wResult);
        } catch (Exception e) {
            log.error("query user cause error!", e);
        }
        return WResponses.error();
    }

    @RequestMapping(path = "batchStartup", method = RequestMethod.POST)
    public WResponses batchStartup(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = userRemoteService.batchStartUp(ids);
        log.info("batchStartup() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }

    @RequestMapping(path = "batchPause", method = RequestMethod.POST)
    public WResponses batchPause(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = userRemoteService.batchPause(ids);
        log.info("batchPause() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }

    @RequestMapping(path = "batchDelete", method = RequestMethod.POST)
    public WResponses batchDelete(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = userRemoteService.batchDelete(ids);
        log.info("batchDelete() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }
}
