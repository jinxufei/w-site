package com.urwoo.web.adapter;

import com.urwoo.tools.MapTools;
import com.urwoo.tools.ObjectTools;
import com.urwoo.tools.StringTools;

import java.util.Map;

public abstract class AbstractReqParamAdapter {

    public static Integer limit(Map<String, Object> map) {
        if (MapTools.nonNull(map)) {
            if (checkValueNonNull(map, "limit")) {
                return Integer.parseInt((String) map.get("limit"));
            }
        }
        return 20;
    }

    public static Long start(Map<String, Object> map) {
        if (MapTools.nonNull(map)) {
            if (checkValueNonNull(map, "start")) {
                return Long.parseLong((String) map.get("start"));
            }
        }
        return 0L;
    }

    //
    protected static boolean checkValueNonNull(Map<String, Object> param, String key) {
        Object obj;
        return ObjectTools.nonNull(obj = param.get(key)) &&
                StringTools.nonNullAndEmpty((String) obj);
    }
}
