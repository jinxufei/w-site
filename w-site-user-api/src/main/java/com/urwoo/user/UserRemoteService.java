package com.urwoo.user;

import com.urwoo.model.WResult;
import com.urwoo.user.hystrix.UserServiceHystrix;
import com.urwoo.user.vo.User;
import com.urwoo.user.vo.query.UserQueryParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "w-user-service", fallback = UserServiceHystrix.class)
public interface UserRemoteService {

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    WResult saveUser(@RequestBody(required = false) User user);

    @RequestMapping(path = "/user", method = RequestMethod.PUT)
    WResult update(@RequestBody(required = false) User user);

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    WResult get(@PathVariable("id") Long id);

    @RequestMapping(path = "/user/u/{username}", method = RequestMethod.GET)
    WResult username(@PathVariable("username") String username);

    @RequestMapping(path = "/user/p/{phone}", method = RequestMethod.GET)
    WResult phone(@PathVariable("phone") String phone);

    @RequestMapping(path = "/user/e/{email}", method = RequestMethod.GET)
    WResult email(@PathVariable("email") String email);

    @RequestMapping(path = "/user/pages", method = RequestMethod.POST)
    WResult pages(@RequestBody(required = false) UserQueryParam userQueryParam,
                  @RequestParam(name = "start", defaultValue = "0") Long start,
                  @RequestParam(name = "limit", defaultValue = "0") Integer limit);

    @RequestMapping(path = "/user/pwd/{id}", method = RequestMethod.PUT)
    WResult updatePwd(@PathVariable("id") Long id,
                      @RequestParam("pwd") String pwd);

    @RequestMapping(path = "/user/delete", method = RequestMethod.PUT)
    WResult batchDelete(@RequestParam("ids") Long[] ids);

    @RequestMapping(path = "/user/start", method = RequestMethod.PUT)
    WResult batchStartUp(@RequestParam("ids") Long[] ids);

    @RequestMapping(path = "/user/pause", method = RequestMethod.PUT)
    WResult batchPause(@RequestParam("ids") Long[] ids);
}
