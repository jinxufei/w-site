package com.urwoo.enums;

import java.util.HashMap;
import java.util.Map;

public enum CheckStatus {
    CHECKING(0, "待审核"),
    PASS(1, "通过"),
    REFUSE(2, "拒绝");

    int code;
    String des;

    CheckStatus(int code, String des){
        this.code = code;
        this.des = des;
    }

    public int code() {
        return code;
    }

    public String des() {
        return des;
    }

    static Map<Integer, CheckStatus> map = new HashMap<>();

    static {
        for (CheckStatus status : CheckStatus.values()) {
            map.put(status.code, status);
        }
    }

    public static CheckStatus status(Integer code) {
        return map.get(code);
    }
}
