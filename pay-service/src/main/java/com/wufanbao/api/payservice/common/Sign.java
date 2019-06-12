package com.wufanbao.api.payservice.common;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;

public class Sign {
    public static String getSign(RequestData data) {
        String sign = "";
        Gson gson = new Gson();
        //sign = new EncryptUtilMd5().getMD5ofStr(gson.toJson(data) + "&" + data.getTimestamp() + Global.TOKEN).toLowerCase();
        sign = DigestUtils.md5Hex(gson.toJson(data) + "&" + data.getTimestamp() + Global.TOKEN).toLowerCase();
        return sign;
    }

    public static String getSign(String dataStr, String timestamp) {
        String a = dataStr + "&" + timestamp + Global.TOKEN;
        String sign = DigestUtils.md5Hex(dataStr + "&" + timestamp + Global.TOKEN).toLowerCase();
        return sign;
    }
}
