package com.urwoo.tools;

import java.util.Map;

public class MapTools {

    public static <K, V> boolean isNull(final Map<K,V> map) {
        return map == null || map.isEmpty();
    }

    public static <K, V> boolean nonNull(final Map<K,V> map){
        return !isNull(map);
    }

}
