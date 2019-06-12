package com.wufanbao.api.oldclientservice.common;


import com.wufanbao.api.oldclientservice.config.ClientSetting;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUtils {
    @Autowired
    private ClientSetting clientSetting;

    public String getSign(RequestData data) {
        return DigestUtils.md5Hex(JsonUtils.GsonString(data) + "&" + data.getTimestamp() + clientSetting.getTokenKey()).toLowerCase();
    }

    public String getSign(String jsonData, String timestamp) {
        return DigestUtils.md5Hex(jsonData + "&" + timestamp + clientSetting.getTokenKey()).toLowerCase();
    }
}
