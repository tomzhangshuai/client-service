package com.wufanbao.api.olddriverservice.Tool;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * User:Wangshihao
 * Date:2017-09-11
 * Time:14:27
 */
public class AES_256 {
    public static boolean initialized = false;
    public static final String ALGORITHM = "AES/ECB/PKCS7Padding";

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

    public static String encryptionPassword(String password) {
        String data = password;
        int[] buf = new int[16];
        for (int i = 0; i < data.length(); i = i + 2) {
            if (data.charAt(i) != '0') {
                buf[i / 2] = Integer.parseInt(data.substring(i, i + 2), 16);
            } else {
                buf[i / 2] = Integer.parseInt(data.substring(i + 1, i + 2), 16);
            }
        }
        char[] res2 = new char[32];
        int y = 0;
        for (int i = 0; i < 16; i++) {
            int b = ((buf[i] + i) & 0xf);
            b = b > 9 ? b + 0x57 : b + 0x30;
            res2[y] = (char) b;
            y++;
            b = ((buf[i] + i) >> 4);
            b = b > 9 ? b + 0x57 : b + 0x30;
            res2[y] = (char) b;
            y++;
        }
        String result = new String(res2);
        return result;
    }
}
