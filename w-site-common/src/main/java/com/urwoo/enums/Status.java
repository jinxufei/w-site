package com.urwoo.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    OFF(0, "暂停"),
    ON(1, "启动"),
    DEL(2, "删除");

    int code;
    String des;

    Status(int code, String des){
        this.code = code;
        this.des = des;
    }

    public int code() {
        return code;
    }

    public String des() {
        return des;
    }

    static Map<Integer, Status> map = new HashMap<>();

    static {
        for (Status status : Status.values()) {
            map.put(status.code, status);
        }
    }

    public static Status status(Integer code) {
        return map.get(code);
    }
}
