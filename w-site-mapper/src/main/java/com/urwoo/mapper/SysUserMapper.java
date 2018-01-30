package com.urwoo.mapper;

import com.urwoo.po.SysUserPo;
import com.urwoo.request.SysUserQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {

    int save(SysUserPo sysUserPo);

    int update(SysUserPo sysUserPo);

    int batchUpdateStatus(@Param("ids") List<Long> ids,
                          @Param("status") Integer status);

    SysUserPo getById(@Param("id") Long id);

    SysUserPo getByUsername(@Param("username") String username);

    List<SysUserPo> query(@Param("param") SysUserQueryReq param,
                          @Param("start") Long start,
                          @Param("limit") Integer limit);

    Long count(@Param("param") SysUserQueryReq param);
}
