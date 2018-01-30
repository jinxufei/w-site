package com.urwoo.enums;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    FEMALE(0, "女"),
    MALE(1, "男"),
    UNKNOWN(2, "未知");

    int code;
    String des;

    Gender(int code, String des){
        this.code = code;
        this.des = des;
    }

    public int code() {
        return code;
    }

    public String des() {
        return des;
    }

    static Map<Integer, Gender> map = new HashMap<>();

    static {
        for (Gender gender : Gender.values()) {
            map.put(gender.code, gender);
        }
    }

    public static Gender gender(Integer code) {
        return map.get(code);
    }
}
