package com.urwoo.manager.adapter;

import com.urwoo.tools.MapTools;
import com.urwoo.user.vo.query.UserQueryParam;

import java.util.Map;

public class UserAdapter extends ParamAdapter{

    public static UserQueryParam param2UserQueryParam(Map<String, Object> param){

        UserQueryParam queryParam = new UserQueryParam();

        if (MapTools.isNull(param))
            return queryParam;

        return queryParam;
    }
}
