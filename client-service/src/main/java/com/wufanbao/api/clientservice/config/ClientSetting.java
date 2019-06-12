package com.wufanbao.api.clientservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClientSetting {
    //版本
    @Value("${com.wufanbao.version}")
    private String version;
    //数据请求的token，目前一对一token
    @Value("${com.wufanbao.token}")
    private String token;
    //资源地址
    @Value("${com.wufanbao.resource}")
    private String resource;
    @Value("${com.wufanbao.userOrderInvalidTime}")
    private int userOrderInvalidTime;
    //文件上传地址
    @Value("${com.wufanbao.uploadUrl}")
    private String uploadUrl;
    //支付回调地址
    @Value("${com.wufanbao.payCallback}")
    private String payCallback;

    @Value("${com.wufanbao.wechat}")
    private String wechat;

    @Value("${com.wufanbao.alipay}")
    private String alipay;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getUserOrderInvalidTime() {
        return userOrderInvalidTime;
    }

    public void setUserOrderInvalidTime(int userOrderInvalidTime) {
        this.userOrderInvalidTime = userOrderInvalidTime;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }


    public void setWechat(String mUrl) {
        this.wechat = wechat;
    }

    public String getWechat() {
        return wechat;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getPayCallback() {
        return payCallback;
    }

    public void setPayCallback(String payCallback) {
        this.payCallback = payCallback;
    }
}
