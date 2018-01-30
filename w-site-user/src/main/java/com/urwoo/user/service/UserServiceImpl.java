package com.urwoo.user.service;

import com.urwoo.mapper.UserMapper;
import com.urwoo.model.WResult;
import com.urwoo.po.UserPo;
import com.urwoo.request.UserQueryReq;
import com.urwoo.tools.JsonTools;
import com.urwoo.tools.Md5Tools;
import com.urwoo.tools.ObjectTools;
import com.urwoo.user.adapter.UserAdapter;
import com.urwoo.user.adapter.UserQueryParamAdapter;
import com.urwoo.user.codes.UserBizCode;
import com.urwoo.user.vo.User;
import com.urwoo.enums.Level;
import com.urwoo.enums.Status;
import com.urwoo.user.vo.query.UserQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class UserServiceImpl implements UserBizCode {

    @Autowired
    private UserMapper userMapper;

    @PostMapping(path = "/user")
    @Transactional
    public WResult saveUser(@RequestBody(required = false) User user) {
        Assert.notNull(user, "user object must not be null!");
        log.info("saveUser() : user={}", user.toString());
        try {
            user.setPassword(Md5Tools.getInstance().getEncryptedPwd(user.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error("saveUser() : password MD5 cause error!", e);
        }

        // 下面的判断可以直接在数据库新增唯一索引
        if (ObjectTools.nonNull(user.getUsername()) &&
                checkUsername(user.getUsername()))
            return WResult.build(USERNAME_IS_EXIST);

        if (ObjectTools.nonNull(user.getPhone()) &&
                checkUsername(user.getPhone()))
            return WResult.build(USER_PHONE_IS_EXIST);

        if (ObjectTools.nonNull(user.getEmail()) &&
                checkUsername(user.getEmail()))
            return WResult.build(USER_EMAIL_IS_EXIST);

        user.setStatus(Status.ON);
        user.setLevel(Level.LEVEL_1);
        userMapper.save(UserAdapter.user2Po(user));
        return WResult.ok();
    }

    @PutMapping(path = "/user")
    @Transactional
    public WResult update(@RequestBody(required = false) User user) {

        Assert.notNull(user, "user object must not be null!");
        Assert.notNull(user.getId(), "user id must not be null!");
        UserPo userPo = getUserById(user.getId());

        userPo.setEmail(user.getEmail());
        userPo.setAvatar(user.getAvatar());
        userPo.setPhone(user.getPhone());
        userPo.setGender(user.getGender().code());
        userPo.setUsername(user.getUsername());
        userPo.setNickname(user.getNickname());
        userPo.setMeta(user.getMeta());
        userMapper.update(userPo);

        return WResult.ok();
    }

    @GetMapping(path = "/user/{id}")
    public WResult get(@PathVariable("id") Long id) {

        UserPo userPo = getUserById(id);
        if (ObjectTools.isNull(userPo))
            return WResult.build(USER_NOT_EXIST);

        log.info("get() : id={}, res={}", id, userPo.toString());
        return WResult.ok(UserAdapter.po2User(userPo));
    }

    @GetMapping(path = "/user/u/{username}")
    public WResult username(@PathVariable("username") String username) {

        boolean isExist = checkUsername(username);
        log.info("username() : res={}", isExist);
        if (isExist)
            return WResult.build(USERNAME_IS_EXIST);
        else
            return WResult.build(USERNAME_NOT_EXIST);
    }

    @GetMapping(path = "/user/p/{phone}")
    public WResult phone(@PathVariable("phone") String phone) {

        boolean isExist = checkPhone(phone);
        log.info("phone() : res={}", isExist);
        if (isExist)
            return WResult.build(USER_PHONE_IS_EXIST);
        else
            return WResult.build(USER_PHONE_NOT_EXIST);
    }

    @GetMapping(path = "user/e/{email}")
    public WResult email(@PathVariable("email") String email) {

        boolean isExist = checkEmail(email);
        log.info("email() : res={}", isExist);
        if (isExist)
            return WResult.build(USER_EMAIL_IS_EXIST);
        else
            return WResult.build(USER_EMAIL_NOT_EXIST);
    }

    @PostMapping(path = "/user/pages")
    public WResult pages(@RequestBody(required = false) UserQueryParam userQueryParam,
                         @RequestParam(name = "start", defaultValue = "0") Long start,
                         @RequestParam(name = "limit", defaultValue = "0") Integer limit) {

        UserQueryReq req = UserQueryParamAdapter.param2Req(userQueryParam);
        List<UserPo> userPoList = userMapper.query(req, start, limit);
        long count = userMapper.count(req);

        log.debug("pages() : userList={}, count={}", JsonTools.transformJsonStr(userPoList), count);
        return WResult.page(userPoList, count, start, limit);
    }

    @PutMapping(path = "/user/pwd/{id}")
    @Transactional
    public WResult updatePwd(@PathVariable("id") Long id,
                             @RequestParam("pwd") String pwd) {
        log.info("updatePwd() : id={}, pwd={}", id, pwd);
        userMapper.updatePwd(id, pwd);
        return WResult.ok();
    }

    @PutMapping(path = "/user/delete")
    @Transactional
    public WResult batchDelete(@RequestParam("ids") Long[] ids) {
        log.info("batchDelete() : id={}", ids);
        userMapper.batchUpdateStatus(Arrays.asList(ids), Status.DEL.code());
        return WResult.ok();
    }

    @PutMapping(path = "/user/start")
    public WResult batchStartUp(@RequestParam("ids") Long[] ids) {
        log.info("batchStartUp() : id={}", ids);
        userMapper.batchUpdateStatus(Arrays.asList(ids), Status.ON.code());
        return WResult.ok();
    }

    @PutMapping(path = "/user/pause")
    @Transactional
    public WResult batchPause(@RequestParam("ids") Long[] ids) {
        log.info("batchPause() : id={}", ids);
        userMapper.batchUpdateStatus(Arrays.asList(ids), Status.OFF.code());
        return WResult.ok();
    }

    //
    private boolean checkUsername(String username) {
        return ObjectTools.nonNull(getUserByUsername(username));
    }

    private boolean checkEmail(String email) {
        return ObjectTools.nonNull(userMapper.getByEmail(email));
    }

    private boolean checkPhone(String phone) {
        return ObjectTools.nonNull(userMapper.getByPhone(phone));
    }

    private UserPo getUserById(Long id) {
        return userMapper.getById(id);
    }

    private UserPo getUserByUsername(String username) {
        return userMapper.getByUsername(username);
    }
}
