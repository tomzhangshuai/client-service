package com.wufanbao.api.distributionservice.tools;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

/**
 * User:Wangshihao
 * Date:2017-09-11
 * Time:14:27
 */
public class AES256 {

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
    public static byte[] encrypt(String keyString, String content) throws Exception {

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
     * 使用AES，256位带向量加密 并返回base64字符串
     * @param keyString
     * @param content
     * @return
     * @throws Exception
     */
    public static String encryptToBase64(String keyString, String content) throws Exception{
        byte[] bytes=encrypt(keyString,content);
        Base64.Encoder encoder=Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }


    /**
     * 使用AES，256位带向量解密
     *
     * @param keyString 加密字符串
     * @param content   待解密内容
     * @return 解密后结果
     * @throws Exception
     */
    public static byte[] decrypt(String keyString, byte[] content) throws Exception {

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
     * 从base64字符串解码
     * @param keyString
     * @param content
     * @return
     * @throws Exception
     */
    public static String decryptFromBase64(String keyString,String content) throws  Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(content);
        byte[] result=decrypt(keyString,bytes);

       return new String(result, Charset.forName("UTF-8"));

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

    public static void main(String[] args) throws Exception {
        String testStr="dfasdfsdf234234ssdfsdfsdfsdfsdfsd23_+342342sdfsdfsdfsdfewerwererdfsdfsdfsdfwerwerweddddddddsssdddddddeeedddffffdd3ddd";
        String key="aaddddfdddfwe3333fffdsdwe23saombsdofisdferweddd";
        System.out.println("testStr:"+testStr);
        System.out.println("key:"+key);
        String result=AES256.encryptToBase64(key,testStr);

        System.out.println("result:"+result);

        String decryptStr=AES256.decryptFromBase64(key,result);

        System.out.println("decryptStr:"+decryptStr);

        System.out.println(testStr.equals(decryptStr));

        byte[]  a="中".getBytes();
        System.out.println(Arrays.toString(a));
        byte[] b="中".getBytes();
        System.out.println(Arrays.toString(b));
        byte[] c="中".getBytes("ISO8859-1");
        System.out.println(Arrays.toString(c));

    }

}
