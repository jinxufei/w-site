package com.urwoo.enums;

import java.util.HashMap;
import java.util.Map;

public enum Type {

    TEXT(0, "文字"),
    IMAGE(1, "图片");

    int code;
    String des;

    Type(int code, String des){
        this.code = code;
        this.des = des;
    }

    public int code() {
        return code;
    }

    public String des() {
        return des;
    }

    static Map<Integer, Type> map = new HashMap<>();

    static {
        for (Type type : Type.values()) {
            map.put(type.code, type);
        }
    }

    public static Type type(Integer code) {
        return map.get(code);
    }
}
