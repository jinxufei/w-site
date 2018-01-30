package com.urwoo.user.hystrix;

import com.urwoo.model.WResult;
import com.urwoo.user.UserRemoteService;
import com.urwoo.user.vo.User;
import com.urwoo.user.vo.query.UserQueryParam;
import org.springframework.stereotype.Component;

/**
 * 熔断处理
 */
@Component
public class UserServiceHystrix implements UserRemoteService {

    @Override
    public WResult saveUser(User user) {
        return null;
    }

    @Override
    public WResult update(User user) {
        return null;
    }

    @Override
    public WResult get(Long id) {
        return null;
    }

    @Override
    public WResult username(String username) {
        return null;
    }

    @Override
    public WResult phone(String phone) {
        return null;
    }

    @Override
    public WResult email(String email) {
        return null;
    }

    @Override
    public WResult pages(UserQueryParam userQueryParam, Long start, Integer limit) {
        return null;
    }

    @Override
    public WResult updatePwd(Long id, String pwd) {
        return null;
    }

    @Override
    public WResult batchDelete(Long[] ids) {
        return null;
    }

    @Override
    public WResult batchStartUp(Long[] ids) {
        return null;
    }

    @Override
    public WResult batchPause(Long[] ids) {
        return null;
    }
}
