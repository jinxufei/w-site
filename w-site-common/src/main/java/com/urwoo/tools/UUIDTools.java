package com.urwoo.tools;

import java.util.UUID;

public class UUIDTools {

    private static class Singleton {
        private static UUIDTools instance;
        static {
            instance = new UUIDTools();
        }
        static UUIDTools getInstance() {
            return instance;
        }
    }

    public static UUIDTools getInstance() {
        return Singleton.getInstance();
    }

    public String genUUID(){

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
