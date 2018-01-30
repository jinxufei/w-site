package com.urwoo.enums;

public enum LoginType {

    SELF(0, "本站注册"),
    THIRD(1, "第三方登录");

    int code;
    String des;

    LoginType(int code, String des){
        this.code = code;
        this.des = des;
    }

    public int code() {
        return code;
    }

    public String des() {
        return des;
    }
}
