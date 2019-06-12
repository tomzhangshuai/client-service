package com.wufanbao.api.payservice.request.ali;

import com.wufanbao.api.payservice.request.PayRequest;

public class AliJSPay extends PayRequest {
    private double total_amount;
    private String trade_no;
    private String attach;
    private String body;
    /**
     * 买家支付宝账号
     */
    private String buyer_logon_id;
    /**
     * 买家支付宝用户Id
     */
    private String buyer_id;
    private String notify_url;
    private String qr_code_timeout_express;

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getQr_code_timeout_express() {
        return qr_code_timeout_express;
    }

    public void setQr_code_timeout_express(String qr_code_timeout_express) {
        this.qr_code_timeout_express = qr_code_timeout_express;
    }
}
