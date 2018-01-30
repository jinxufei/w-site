package com.urwoo.tools;

public class ObjectTools {

    public static boolean isNull(final Object obj){
        return obj == null;
    }

    public static boolean nonNull(final Object obj){
        return !isNull(obj);
    }

    public static boolean equals(final Object o1 , final Object o2){
        return (o1 == o2) || (o1 != null && o1.equals(o2));
    }

    public static boolean nonEquals(final Object o1 , final Object o2){
        return !equals(o1, o2);
    }

}
