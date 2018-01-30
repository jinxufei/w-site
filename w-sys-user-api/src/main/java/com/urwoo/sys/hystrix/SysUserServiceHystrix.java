package com.urwoo.sys.hystrix;

import com.urwoo.model.WResult;
import com.urwoo.sys.SysUserRemoteService;
import com.urwoo.sys.vo.SysUser;
import com.urwoo.sys.vo.query.SysUserQueryParam;
import org.springframework.stereotype.Component;

@Component
public class SysUserServiceHystrix implements SysUserRemoteService {

    @Override
    public WResult page(SysUserQueryParam param, Long start, Integer limit) {
        return null;
    }

    @Override
    public WResult save(SysUser sysUser) {
        return null;
    }

    @Override
    public WResult update(SysUser sysUser) {
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

    @Override
    public WResult getById(Long id) {
        return null;
    }

    @Override
    public WResult getByUsername(String username) {
        return null;
    }
}
