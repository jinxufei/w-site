package com.urwoo.user.adapter;

import com.urwoo.request.UserQueryReq;
import com.urwoo.tools.ObjectTools;
import com.urwoo.user.vo.query.UserQueryParam;

public class UserQueryParamAdapter {

    public static UserQueryReq param2Req(UserQueryParam param) {

        if (ObjectTools.isNull(param))
            return null;

        UserQueryReq req = new UserQueryReq();

        if (ObjectTools.nonNull(param.getLevel()))
            req.setLevel(param.getLevel().code());
        if (ObjectTools.nonNull(param.getStatus()))
            req.setStatus(param.getStatus().code());
        req.setNames(param.getNames());
        req.setStartDate(param.getStartDate());
        req.setEndDate(param.getEndDate());

        return req;
    }
}
