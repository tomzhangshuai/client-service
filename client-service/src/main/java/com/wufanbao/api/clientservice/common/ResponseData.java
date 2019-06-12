package com.wufanbao.api.clientservice.common;

import com.wufanbao.api.clientservice.service.ApiServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 数据返回输出
 */
@Component
public class ResponseData extends HashMap {
    @Autowired
    private SignUtils signUtils;
    @Autowired
    private CommonFun commonFun;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private void putCommon() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        this.put("timestamp", format.format(timestamp));
        this.put("signType", "MD5");
    }

    public ResponseData() {

    }
    public static void main(String[] args){
        ResponseData responseData=new ResponseData();
        responseData.error("12");
        System.out.println(responseData.toString());

    }
    //成功，返回数据
    public ResponseData success() {
        this.put("code", 100);
        this.put("message", "success");
        putCommon();
        return this;
    }

    public ResponseData success(int code) {
        this.put("code", code);
        this.put("message", "success");
        putCommon();
        return this;
    }

    //错误（异常），返回数据
    public ResponseData error(@Null String message) {
        this.put("code", 101);
        if (!StringUtils.isNullOrEmpty(message)) {
            this.put("message", message);
        } else {
            this.put("message", "系统异常");
        }
        putCommon();
        return this;
    }

    public ResponseData error(@Null Exception ex) {
        this.put("code", 101);
        if (!StringUtils.isNullOrEmpty(ex.getMessage())) {
            this.put("message", ex.getMessage());
        } else {
            this.put("message", "系统异常:" + commonFun.getStackTraceInfo(ex));
        }
        logger.error(commonFun.getStackTraceInfo(ex));
        putCommon();
        return this;
    }


    public ResponseData error(ApiServiceException ex) {
        this.put("code", ex.getCode() > 0 ? ex.getCode() : 101);
        this.put("message", !StringUtils.isNullOrEmpty(ex.getMessage()) ? ex.getMessage() : "error");
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

    //签名错误
    public ResponseData signError() {
        this.put("code", 103);
        this.put("message", "签名错误");
        putCommon();
        return this;
    }

    //登录失效
    public ResponseData autoError() {
        this.put("code", 104);
        this.put("message", "登录已失效，请重新登录");
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
        if (data instanceof String) {
            this.put("data", data);
        } else {
            String jsonStr = JsonUtils.GsonString(data);
            System.out.println("返回：" + jsonStr);
            this.put("data", jsonStr);
        }
        String dataStr = JsonUtils.GsonString(this);
        ResponseData responseData = new ResponseData();
        responseData.put("data", dataStr);
        responseData.put("sign", signUtils.getSign(dataStr, (String) this.get("timestamp")));
        return responseData;
    }
}
