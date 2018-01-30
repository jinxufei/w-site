package com.urwoo.enums;

import java.util.HashMap;
import java.util.Map;

public enum SharePlatform {

    QQ(1, "qq空间"),
    WEIBO(2, "微博"),
    WEIXIN(3, "微信");

    int code;
    String des;

    SharePlatform(int code, String des){
        this.code = code;
        this.des = des;
    }

    public int code() {
        return code;
    }

    public String des() {
        return des;
    }

    static Map<Integer, SharePlatform> map = new HashMap<>();

    static {
        for (SharePlatform sharePlatform : SharePlatform.values()) {
            map.put(sharePlatform.code, sharePlatform);
        }
    }

    public static SharePlatform sharePlatform(Integer code) {
        return map.get(code);
    }
}
