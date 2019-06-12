package com.wufanbao.api.oldclientservice.Tool;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * AES，256位带向量加密提供类
 */
public class AES_256 {

    /**
     * 使用AES，256位带向量加密
     *
     * @param keyString 加密字符串
     * @param content   待加密内容
     * @return 加密后结果
     * @throws Exception
     */
    public static byte[] Encrypt(String keyString, String content) throws Exception {

        byte[] aesKey = new byte[32];

        byte[] aesIv = new byte[16];

        getKeyAndIV(keyString, aesKey, aesIv);
        SecretKeySpec secretKey = new SecretKeySpec(aesKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        IvParameterSpec iv = new IvParameterSpec(aesIv);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes("UTF-8"));

        return encrypted;
    }


    /**
     * 使用AES，256位带向量解密
     *
     * @param keyString 加密字符串
     * @param content   待解密内容
     * @return 解密后结果
     * @throws Exception
     */
    public static byte[] Decrypt(String keyString, byte[] content) throws Exception {

        byte[] aesKey = new byte[32];

        byte[] aesIv = new byte[16];

        getKeyAndIV(keyString, aesKey, aesIv);
        SecretKeySpec secretKey = new SecretKeySpec(aesKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        IvParameterSpec iv = new IvParameterSpec(aesIv);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] decrypted = cipher.doFinal(content);

        return decrypted;
    }


    /**
     * 获取AES key iv
     *
     * @param keyString 加密字符串
     * @param key       aes 256位key
     * @param iv        aes 128位向量
     */
    private static void getKeyAndIV(String keyString, byte[] key, byte[] iv) {


        byte[] sKey = null;

        try {
            sKey = keyString.getBytes("utf-8");
        } catch (Exception ex) {

        }

        for (int i = 0; i < sKey.length && i < key.length; i++) {
            key[i] = sKey[i];
        }

        if (sKey.length > iv.length) {
            for (int i = 0, j = sKey.length - iv.length; i < iv.length; i++, j++) {
                iv[i] = sKey[j];
            }

        } else {
            for (int i = 0; i < sKey.length; i++) {
                iv[i] = sKey[i];
            }
        }

    }
}