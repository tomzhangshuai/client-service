package com.wufanbao.api.clientservice.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {
    //判断字符串是否为空
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static String md5(String str) {
        String digest = null;
        StringBuffer buffer = new StringBuffer();
        try {
            MessageDigest digester = MessageDigest.getInstance("md5");
            byte[] digestArray = digester.digest(str.getBytes("utf-8"));
            for (int i = 0; i < digestArray.length; i++) {
                buffer.append(String.format("%02x", digestArray[i]));
            }
            digest = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return digest;
    }

    public static int getWordCount(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255)
                length++;
            else
                length += 2;
        }
        return length;

    }
}
