package com.wufanbao.api.payservice.common;

import com.wufanbao.api.payservice.response.PayResponse;

public class ResponseData<T extends PayResponse> {
    private String merchant_id;
    /**
     * 名称：终端id
     * 必填：否
     */
    private String term_id;
    /**
     * 名称：请求时间
     * 必填：否
     */
    private String timestamp;
    private String biz_channel;
    /**
     * 名称：交易类型
     * 必填：是
     */
    private String biz_type;

    private String code;
    private String message;
    private T biz_content;
    private String sign_type;

    public String getBiz_channel() {
        return biz_channel;
    }

    public void setBiz_channel(String biz_channel) {
        this.biz_channel = biz_channel;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getTerm_id() {
        return term_id;
    }

    public void setTerm_id(String term_id) {
        this.term_id = term_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBiz_type() {
        return biz_type;
    }

    public void setBiz_type(String biz_type) {
        this.biz_type = biz_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBiz_content() {
        return biz_content;
    }

    public void setBiz_content(T biz_content) {
        this.biz_content = biz_content;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

}
