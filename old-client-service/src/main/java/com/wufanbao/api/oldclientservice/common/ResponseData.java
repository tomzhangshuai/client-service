package com.wufanbao.api.oldclientservice.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * 数据返回输出
 */
@Component
public class ResponseData extends HashMap {
    @Autowired
    private SignUtils signUtils;

    private void putCommon() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        this.put("timestamp", format.format(timestamp));
        this.put("signType", "MD5");
    }

    public ResponseData() {

    }

    //成功，返回数据
    public ResponseData success() {
        this.put("code", 100);
        this.put("message", "success");
        putCommon();
        return this;
    }

    //错误（异常），返回数据
    public ResponseData error(@Null String message) {
        this.put("code", 101);
        this.put("message", !StringUtils.isNullOrEmpty(message) ? message : "error");
        putCommon();
        return this;
    }

    //失败，返回数据
    public ResponseData fail(@Null String message) {
        this.put("code", 102);
        this.put("message", !StringUtils.isNullOrEmpty(message) ? message : "fail");
        putCommon();
        return this;
    }


    //其他情况的消息返回
    public ResponseData(int code, String message) {
        this.put("code", code);
        this.put("message", message);
        putCommon();
    }

    //签名输出数据
    public <T> ResponseData sign(@Null T data) {
        this.put("data", JsonUtils.GsonString(data));
        String dataStr = JsonUtils.GsonString(this);

        ResponseData responseData = new ResponseData();
        responseData.put("data", dataStr);
        responseData.put("sign", signUtils.getSign(dataStr, (String) this.get("timestamp")));
        return responseData;
    }
}
