package com.urwoo.manager.adapter;

import com.urwoo.enums.Status;
import com.urwoo.sys.vo.SysUser;
import com.urwoo.sys.vo.query.SysUserQueryParam;
import com.urwoo.tools.MapTools;
import com.urwoo.tools.StringTools;

import java.util.Map;

public class SysUserAdapter extends ParamAdapter{

    public static SysUserQueryParam map2Query(Map<String, Object> param){

        SysUserQueryParam queryParam = new SysUserQueryParam();
        if (MapTools.isNull(param))
            return queryParam;

        if (checkValueNonNull(param, "status")) {
            queryParam.setStatus(Status.status(
                    Integer.parseInt((String) param.get("status"))));
        }
        if (checkValueNonNull(param, "startDate")) {
            queryParam.setStartDate((String) param.get("startDate"));
        }
        if (checkValueNonNull(param, "endDate")) {
            queryParam.setEndDate((String) param.get("endDate"));
        }
        if (checkValueNonNull(param, "names")) {
            queryParam.setNames(StringTools.str2Array((String) param.get("names")));
        }

        return queryParam;
    }

    public static SysUser map2SysUser(Map<String, Object> param){

        SysUser sysUser = new SysUser();

        if (MapTools.isNull(param))
            return sysUser;

        if (checkValueNonNull(param, "id")) {
            sysUser.setName((String) param.get("id"));
        }
        if (checkValueNonNull(param, "name")) {
            sysUser.setName((String) param.get("name"));
        }
        if (checkValueNonNull(param, "username")) {
            sysUser.setName((String) param.get("username"));
        }
        return sysUser;
    }
}
