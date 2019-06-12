package com.wufanbao.api.oldclientservice.entity;

public class ResponseInfo<T> {
    private int code;//返回码
    private String message;//消息
    private T data;//返回数据

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
