package com.urwoo.sys.service;

import com.urwoo.enums.Status;
import com.urwoo.mapper.SysUserMapper;
import com.urwoo.model.WResult;
import com.urwoo.po.SysUserPo;
import com.urwoo.request.SysUserQueryReq;
import com.urwoo.sys.adapter.SysUserAdapter;
import com.urwoo.sys.adapter.SysUserQueryParamAdapter;
import com.urwoo.sys.codes.SysUserBizCode;
import com.urwoo.sys.vo.SysUser;
import com.urwoo.sys.vo.query.SysUserQueryParam;
import com.urwoo.tools.JsonTools;
import com.urwoo.tools.ObjectTools;
import com.urwoo.tools.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class SysUserServiceImpl implements SysUserBizCode {

    @Autowired
    private SysUserMapper sysUserMapper;

    @RequestMapping(path = "/sysuser/page", method = RequestMethod.GET)
    public WResult page(@RequestBody(required = false) SysUserQueryParam param,
                        @RequestParam(name = "start", defaultValue = "0") Long start,
                        @RequestParam(name = "limit", defaultValue = "20") Integer limit) {
        SysUserQueryReq req = SysUserQueryParamAdapter.param2Req(param);
        List<SysUserPo> sysUsers = sysUserMapper.query(req, start, limit);
        Long count = sysUserMapper.count(req);
        log.info("page() : req = {}, start={}, limit={}, syaUsers={}, count={}",
                req.toString(), start, limit, JsonTools.transformJsonStr(sysUsers), count);
        return WResult.page(sysUsers, count, start, limit);
    }

    @RequestMapping(path = "/sysuser/save", method = RequestMethod.POST)
    @Transactional
    public WResult save(@RequestBody(required = false) SysUser sysUser) {

        Assert.hasText(sysUser.getName(), "name must not be null!");
        Assert.hasText(sysUser.getUsername(), "username must not be null!");
        Assert.hasText(sysUser.getPassword(), "password must not be null!");
        if (checkSysUserNameIsExist(sysUser.getUsername())){
            return WResult.build(SYS_USERNAME_EXIST);
        }
        SysUserPo sysUserPo = SysUserAdapter.sysUser2po(sysUser);
        sysUserMapper.save(sysUserPo);
        return WResult.ok();
    }

    @RequestMapping(path = "/sysuser/update", method = RequestMethod.PUT)
    @Transactional
    public WResult update(@RequestBody(required = false) SysUser sysUser) {

        Assert.notNull(sysUser.getId(), "id must not be null!");
        Assert.hasText(sysUser.getName(), "name must not be null!");
        Assert.notNull(sysUser.getStatus(), "status must not be null!");
        Assert.hasText(sysUser.getUsername(), "username must not be null!");
        Assert.hasText(sysUser.getPassword(), "password must not be null!");

        SysUserPo sysUserInDB = sysUserMapper.getById(sysUser.getId());
        if (StringTools.equals(sysUserInDB.getUsername(), sysUser.getUsername())){
            return WResult.build(SYS_USERNAME_NOT_MODIFIED);
        }
        sysUser.setStatus(Status.status(sysUserInDB.getStatus()));
        sysUser.setCreateTime(sysUserInDB.getCreateTime());
        SysUserPo sysUserPo = SysUserAdapter.sysUser2po(sysUser);
        sysUserMapper.save(sysUserPo);
        return WResult.ok();
    }

    @RequestMapping(path = "/sysuser/del", method = RequestMethod.PUT)
    @Transactional
    public WResult batchDelete(@RequestParam("ids") Long[] ids) {
        log.info("batchDelete() : id={}", ids);
        sysUserMapper.batchUpdateStatus(Arrays.asList(ids), Status.DEL.code());
        return WResult.ok();
    }

    @RequestMapping(path = "/sysuser/start", method = RequestMethod.PUT)
    @Transactional
    public WResult batchStartUp(@RequestParam("ids") Long[] ids) {
        log.info("batchStartUp() : id={}", ids);
        sysUserMapper.batchUpdateStatus(Arrays.asList(ids), Status.ON.code());
        return WResult.ok();
    }

    @RequestMapping(path = "/sysuser/pause", method = RequestMethod.PUT)
    @Transactional
    public WResult batchPause(@RequestParam("ids") Long[] ids) {
        log.info("batchPause() : id={}", ids);
        sysUserMapper.batchUpdateStatus(Arrays.asList(ids), Status.OFF.code());
        return WResult.ok();
    }

    @RequestMapping(path = "/sysuser/{id}", method = RequestMethod.GET)
    public WResult getById(@PathVariable("id") Long id) {
        SysUserPo sysUser = sysUserMapper.getById(id);
        if (ObjectTools.isNull(sysUser)) {
            return WResult.build(SYS_USER_NOT_EXIST);
        }
        return WResult.ok(sysUser);
    }

    @RequestMapping(path = "/sysuser/u_{username}", method = RequestMethod.GET)
    public WResult getByUsername(@PathVariable("username") String username) {
        SysUserPo sysUser = sysUserMapper.getByUsername(username);
        if (ObjectTools.isNull(sysUser)) {
            return WResult.build(SYS_USER_NOT_EXIST);
        }
        return WResult.ok(sysUser);
    }

    //
    private boolean checkSysUserNameIsExist(String username){
        return ObjectTools.nonNull(sysUserMapper.getByUsername(username));
    }
}
