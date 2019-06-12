package com.wufanbao.api.oldclientservice.common;

public class StringUtils {
    //判断字符串是否为空
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() <= 0;
    }
}
