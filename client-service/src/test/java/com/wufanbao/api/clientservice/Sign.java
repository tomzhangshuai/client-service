package com.wufanbao.api.clientservice;

import com.wufanbao.api.clientservice.common.JsonUtils;
import com.wufanbao.api.clientservice.common.RequestData;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class Sign {

    public static String getSign(RequestData data) {
        return new String(Hex.encodeHex(DigestUtils.md5(JsonUtils.GsonString(data) + "&" + data.getTimestamp() + Config.Token)));
    }

    public static String getSign(String jsonData, String timestamp) {
        return new String(Hex.encodeHex(DigestUtils.md5(jsonData + "&" + timestamp + Config.Token)));

    }
}
