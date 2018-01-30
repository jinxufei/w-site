package com.urwoo.manager.controller;

import com.urwoo.manager.adapter.SysUserAdapter;
import com.urwoo.manager.common.Constants;
import com.urwoo.manager.responses.WResponses;
import com.urwoo.model.WResult;
import com.urwoo.sys.SysUserRemoteService;
import com.urwoo.sys.vo.SysUser;
import com.urwoo.tools.JsonTools;
import com.urwoo.tools.Md5Tools;
import com.urwoo.tools.ObjectTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/sysuser/")
@Slf4j
public class SysUserController extends BaseController{

    @Autowired
    private SysUserRemoteService sysUserRemoteService;

    @RequestMapping(path = "page", method = RequestMethod.GET)
    public WResponses page(@RequestParam Map<String, Object> params){

        try {
            WResult wResult = sysUserRemoteService.page(
                    SysUserAdapter.map2Query(params),
                    SysUserAdapter.start(params), SysUserAdapter.limit(params));

            log.info("page() :param={}, res={}", params, JsonTools.transformJsonStr(wResult));

            return sysUserCode(wResult);
        } catch (Exception e) {
            log.error("query article category cause error!", e);
        }
        return WResponses.error();
    }

    @RequestMapping(path = "save", method = RequestMethod.POST)
    public WResponses save(@RequestParam Map<String, Object> param){
        try {
            SysUser sysUser = SysUserAdapter.map2SysUser(param);
            sysUser.setPassword(Md5Tools.getInstance().getEncryptedPwd(Constants.SYS_PASSWORD));
            WResult wResult = sysUserRemoteService.save(sysUser);
            log.info("save() :param={}, res={}", param, JsonTools.transformJsonStr(wResult));
            return sysUserCode(wResult);
        }catch (Exception e){
            log.error("save SysUser cause error!", e);
        }
        return WResponses.error();
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    public WResponses update(@RequestParam Map<String, Object> param){
        try {
            SysUser sysUser = SysUserAdapter.map2SysUser(param);
            if (ObjectTools.isNull(sysUser.getId())){
                log.error("update() : id is null");
                return WResponses.error(message.getSysUserNotSelectId());
            }
            WResult wResult = sysUserRemoteService.save(SysUserAdapter.map2SysUser(param));
            log.info("update() :param={}, res={}", param, JsonTools.transformJsonStr(wResult));
            return sysUserCode(wResult);
        }catch (Exception e){
            log.error("update SysUser cause error!", e);
        }
        return WResponses.error();
    }

    @RequestMapping(path = "get", method = RequestMethod.GET)
    public WResponses get(@RequestParam(name = "id") Long id) {
        WResult wResult = sysUserRemoteService.getById(id);
        log.info("get() : id={}, res={}", id, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }

    @RequestMapping(path = "batchStartup", method = RequestMethod.POST)
    public WResponses batchStartup(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = sysUserRemoteService.batchStartUp(ids);
        log.info("batchStartup() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }

    @RequestMapping(path = "batchPause", method = RequestMethod.POST)
    public WResponses batchPause(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = sysUserRemoteService.batchPause(ids);
        log.info("batchPause() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }

    @RequestMapping(path = "batchDelete", method = RequestMethod.POST)
    public WResponses batchDelete(@RequestParam(name = "ids") Long[] ids) {
        WResult wResult = sysUserRemoteService.batchDelete(ids);
        log.info("batchDelete() : ids={}, res={}", ids, JsonTools.transformJsonStr(wResult));
        return articleCode(wResult);
    }
}
