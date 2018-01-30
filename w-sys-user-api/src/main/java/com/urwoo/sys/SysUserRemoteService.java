package com.urwoo.sys;

import com.urwoo.model.WResult;
import com.urwoo.sys.hystrix.SysUserServiceHystrix;
import com.urwoo.sys.vo.SysUser;
import com.urwoo.sys.vo.query.SysUserQueryParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "w-sys-user-service", fallback = SysUserServiceHystrix.class)
public interface SysUserRemoteService {

    @RequestMapping(path = "/sysuser/page", method = RequestMethod.GET)
    WResult page(@RequestBody(required = false) SysUserQueryParam param,
                 @RequestParam(name = "start", defaultValue = "0") Long start,
                 @RequestParam(name = "limit", defaultValue = "20") Integer limit);


    @RequestMapping(path = "/sysuser/save", method = RequestMethod.POST)
    WResult save(@RequestBody(required = false) SysUser sysUser);

    @RequestMapping(path = "/sysuser/update", method = RequestMethod.PUT)
    WResult update(@RequestBody(required = false) SysUser sysUser);

    @RequestMapping(path = "/sysuser/del", method = RequestMethod.PUT)
    WResult batchDelete(@RequestParam("ids") Long[] ids);

    @RequestMapping(path = "/sysuser/start", method = RequestMethod.PUT)
    WResult batchStartUp(@RequestParam("ids") Long[] ids);

    @RequestMapping(path = "/sysuser/pause", method = RequestMethod.PUT)
    WResult batchPause(@RequestParam("ids") Long[] ids);

    @RequestMapping(path = "/sysuser/{id}", method = RequestMethod.GET)
    WResult getById(@PathVariable("id") Long id);

    @RequestMapping(path = "/sysuser/u_{username}", method = RequestMethod.GET)
    WResult getByUsername(@PathVariable("username") String username);
}
