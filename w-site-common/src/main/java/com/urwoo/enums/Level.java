package com.urwoo.enums;

import java.util.HashMap;
import java.util.Map;

public enum Level {

    LEVEL_1(1, ""),
    LEVEL_2(2, ""),
    LEVEL_3(3, ""),
    LEVEL_4(4, ""),
    LEVEL_5(5, ""),
    LEVEL_6(6, ""),
    LEVEL_7(7, ""),
    LEVEL_8(8, ""),
    LEVEL_9(9, ""),
    LEVEL_10(10, ""),
    LEVEL_11(11, ""),
    LEVEL_12(12, "");

    int code;
    String des;

    Level(int code, String des){
        this.code = code;
        this.des = des;
    }

    public int code() {
        return code;
    }

    public String des() {
        return des;
    }

    static Map<Integer, Level> map = new HashMap<>();

    static {
        for (Level level : Level.values()) {
            map.put(level.code, level);
        }
    }

    public static Level level(Integer code) {
        return map.get(code);
    }
}
