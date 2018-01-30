package com.urwoo.sys.adapter;

import com.urwoo.request.SysUserQueryReq;
import com.urwoo.sys.vo.query.SysUserQueryParam;
import com.urwoo.tools.ObjectTools;

public class SysUserQueryParamAdapter {

    public static SysUserQueryReq param2Req(SysUserQueryParam param) {

        SysUserQueryReq req = new SysUserQueryReq();
        if (ObjectTools.isNull(param)) {
            return req;
        }

        req.setNames(param.getNames());
        if (ObjectTools.nonNull(param.getStatus()))
            req.setStatus(param.getStatus().code());
        req.setStartDate(param.getStartDate());
        req.setEndDate(param.getEndDate());
        return req;
    }
}
