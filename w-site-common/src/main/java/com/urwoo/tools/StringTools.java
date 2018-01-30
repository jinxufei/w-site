package com.urwoo.tools;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StringTools {

    public static boolean isNullOrEmpty(final String str) {
        return StringUtils.isBlank(str);
    }

    public static boolean nonNullAndEmpty(final String str) {
        return !isNullOrEmpty(str);
    }

    public static boolean equals(final String a, final String b) {
        return (a == b) || (a != null && a.equals(b));
    }

    public static boolean nonEquals(final String a, final String b) {
        return !equals(a, b);
    }

    public static String[] str2Array(String str) {
        return str2Array(str, ",");
    }

    public static Long[] str2LongArray(String str){
        return str2LongArray(str, ",");
    }

    public static String[] str2Array(String str, String split) {
        if (nonNullAndEmpty(str)) {
            String[] strings = str.split(split);
            List<String> tempList = new ArrayList<>();
            for (String s : strings){
                if (nonNullAndEmpty(s)) {
                    tempList.add(s);
                }
            }
            String[] temp = new String[tempList.size()];
            int i = 0;
            for (String s : tempList) {
                temp[i++] = s;
            }
            return temp;
        }
        return null;
    }

    public static Long[] str2LongArray(String str, String split) {
        if (nonNullAndEmpty(str)) {
            String[] strings = str.split(split);
            List<Long> tempList = new ArrayList<>();
            for (String s : strings){
                if (nonNullAndEmpty(s)) {
                    tempList.add(Long.parseLong(s));
                }
            }
            Long[] temp = new Long[tempList.size()];
            int i = 0;
            for (Long s : tempList) {
                temp[i++] = s;
            }
            return temp;
        }
        return null;
    }

}
