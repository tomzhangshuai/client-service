package com.wufanbao.api.oldclientservice.Tool;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class OutPutJson implements Serializable {

    /**
     * 返回客户端统一格式，包括状态码，提示信息，以及业务数据
     */
    private static final long serialVersionUID = 1L;
    //状态码
    private int code;
    //必要的提示信息
    private String message;
    //业务数据
    private Object data;

    public OutPutJson(int status, String message, Object data) {
        this.code = status;
        this.message = message;
        this.data = data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        if (null == this.data) {
            this.setData(new Object());
        }
        return JSON.toJSONString(this);
    }
}