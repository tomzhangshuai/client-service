package com.wufanbao.api.payservice.common;

import com.wufanbao.api.payservice.request.PayRequest;

public class RequestData {
    /**
     * 名称：版本号
     * 必填：否
     * 描述：
     */
    private String version;
    /**
     * 名称：商户Id
     * 必填：否
     */
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
    /**
     * 名称：交易类型
     * 必填：是
     */
    private String biz_type;
    /**
     * 名称：请求的业务数据
     * 必填：是
     */
    private PayRequest biz_content;
    /**
     * 名称：签名加密方式
     * 必填：是
     */
    private String sign_type;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public PayRequest getBiz_content() {
        return biz_content;
    }

    public void setBiz_content(PayRequest biz_content) {
        this.biz_content = biz_content;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }
}
