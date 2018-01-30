package com.urwoo.mapper;

import com.urwoo.po.UserPo;
import com.urwoo.request.UserQueryReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int save(UserPo user);

    int update(UserPo user);

    int updatePwd(@Param("id") Long id, @Param("password") String password);

    int batchUpdateStatus(@Param("ids") List<Long> ids, @Param("status") Integer status);

    UserPo getById(@Param("id") Long id);

    UserPo getByUsername(@Param("username") String username);

    UserPo getByPhone(@Param("phone") String phone);

    UserPo getByEmail(@Param("email") String email);

    List<UserPo> query(@Param("param") UserQueryReq param,
                       @Param("start") Long start,
                       @Param("limit") Integer limit);

    Long count(@Param("param") UserQueryReq param);

}
