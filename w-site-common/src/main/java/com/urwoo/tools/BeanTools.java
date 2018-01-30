package com.urwoo.tools;

import com.alibaba.fastjson.util.TypeUtils;

/**
 * Bean 工具类
 */
public class BeanTools {

    public static <T> T mapToObject(Object object, Class<T> beanClass) {
        if (ObjectTools.isNull(object))
            return null;
        return TypeUtils.castToJavaBean(object, beanClass);
    }
}
