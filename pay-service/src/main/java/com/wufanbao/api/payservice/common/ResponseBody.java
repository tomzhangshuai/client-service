package com.wufanbao.api.payservice.common;

public class ResponseBody {
    private String data;
    private String sign;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
