package com.wufanbao.api.olddriverservice.entity;

import org.springframework.stereotype.Component;

@Component
public class ResponseInfo<T> {
    private int code;//返回码
    private String message;//消息
    private String responseTime;//返回时间
    private T data;//返回数据
    private String digital;//数字摘要

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDigital() {
        return digital;
    }

    public void setDigital(String digital) {
        this.digital = digital;
    }
}
