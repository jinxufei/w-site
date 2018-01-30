package com.urwoo.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

public class JsonTools {

    public static <T> T transformObject(String json , TypeReference<T> tTypeReference){
        return JSON.parseObject(json, tTypeReference);
    }

    public static <T> T transformObject(String json ,Class<T> clazz){
        return JSONObject.toJavaObject(JSONObject.parseObject(json), clazz);
    }

    @SuppressWarnings("unchecked")
    public static <K,V> Map<K,V> json2Map(String json){
        return transformObject(json, Map.class);
    }

    public static <T> List<T> transformList(String json, Class<T> clazz){
        return JSONObject.parseArray(json, clazz);
    }

    public static String transformJsonStr(Object o){
        return JSONObject.toJSONString(o);
    }
}
