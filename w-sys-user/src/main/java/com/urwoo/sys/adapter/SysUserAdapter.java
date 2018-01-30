package com.urwoo.sys.adapter;

import com.urwoo.enums.Status;
import com.urwoo.po.SysUserPo;
import com.urwoo.sys.vo.SysUser;
import com.urwoo.tools.ObjectTools;

public class SysUserAdapter {

    public static SysUserPo sysUser2po(SysUser sysUser) {

        if (ObjectTools.isNull(sysUser))
            return null;

        SysUserPo sysUserPo = new SysUserPo();
        sysUserPo.setId(sysUser.getId());
        sysUserPo.setName(sysUser.getName());
        sysUserPo.setUsername(sysUser.getUsername());
        sysUserPo.setPassword(sysUser.getPassword());
        if (ObjectTools.isNull(sysUser.getStatus())) {
            sysUserPo.setStatus(Status.ON.code());
        } else {
            sysUserPo.setStatus(sysUser.getStatus().code());
        }
        sysUserPo.setCreateTime(sysUser.getCreateTime());
        sysUserPo.setModifyTime(sysUser.getModifyTime());
        return sysUserPo;
    }
}
