package com.wufanbao.api.oldclientservice.controller.LianLianPay.Util;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User:wangshihao
 * Date:2017-11-25
 * Time:16:11
 */
public class Sign {


    private static String TRADER_MD5_KEY = "201408071000001543test_20140812";
    private final static String SERVER = "https://traderapi.lianlianpay.com/refund.htm";
    private final static String prikeyvalue = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOe8Nd6pAW++sKtbyEGE7cNiKISDQ7qv1u7bisnIXLNGDy7aNC8NAP5EEIoFoaeObU9aBqpjgrC+SK1q3hW6z9ZWQIK+h+adn7wm+ZkSr5WaTZlkyg3i2ZeDuRqW39NAT5w3SAEwkcVDY1IZSTNqB/vItVFSW35Yzg+m1E0dHz/5AgMBAAECgYB2HHSsfyFvThcHz+j6lAEHc7FJEQplTZhT9+v+OrQc35l/fNHaJVubPR/c6U/icfJ+3ljFYaDL6sBqOyzkkGm7UaIv5wc0QrQUCXwCYXxqzsrKtkcLdAWRhIzpGasb/tlpejY69kz4egv+BFA4HcuAxrnT/f2gSOqvLLoxdwgLgQJBAPuc6VtRtgOGJvSzFrnddej5rA0XbKIZmkpcCoXVFObOkxTZEEbAUbHBJR2mK9dpwGO7gQyO/1ugvK2ucy0S+ekCQQDrxpLPaTTuzLcNMlyRQJMqD8Zul4ovD7K2xBekTcSE+outkUop/TzLVP+5jGrj4djRi/2o/yUrrLbRAj40LzuRAkBZ+QstH3PxoKgPenbW8c03CQiwCb9L29HjYzRUFRULT7UikFgUpLLR2Fo4VvJ4bhVg1GzaG8xObJTrmmUPKij5AkBt8yJPev5mRi0vCEdGrVhzivu8Ywrbu3RZ6sQjqOh2Iui0PkbZofCR2cePXb4TGyUjQyc9gg5xeY+flG4xo70hAkEAlUze0OYb6TEUI7I0juXqb+kcrLogg1AFfCg3T3T2lYGu5vMpyrdz64MZbmnzm5gr/ElAb+J/frcyl8nhIu8Fig==";


    public String genSign(JSONObject reqObj) {
        String sign = reqObj.getString("sign");
        String sign_type = reqObj.getString("sign_type");
        // // 生成待签名串
        String sign_src = genSignData(reqObj);
        System.out.println("商户[" + reqObj.getString("oid_partner") + "]待签名原串"
                + sign_src);
        System.out.println("商户[" + reqObj.getString("oid_partner") + "]签名串"
                + sign);

        if ("MD5".equals(sign_type)) {
            sign_src += "&key=" + TRADER_MD5_KEY;
            return signMD5(sign_src);
        }
        if ("RSA".equals(sign_type)) {
            return getSignRSA(sign_src);
        }
        return null;
    }

    public String signMD5(String signSrc) {

        try {
            return Md5Algorithm.getInstance().md5Digest(
                    signSrc.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * RSA签名验证
     *
     * @param //reqObj
     * @return
     */
    public String getSignRSA(String sign_src) {
        return TraderRSAUtil.sign(prikeyvalue, sign_src);

    }


    /**
     * 生成待签名串
     *
     * @param //paramMap
     * @return
     */
    public String genSignData(JSONObject jsonObject) {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            // sign 和ip_client 不参与签名
            if ("sign".equals(key)) {
                continue;
            }
            String value = (String) jsonObject.getString(key);
            // 空串不参与签名
            if (null == value) {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }

}
