package com.wufanbao.api.payservice.request.ali;

import com.wufanbao.api.payservice.request.PayRequest;

public class AliPrePay extends PayRequest {
    private String trade_no;
    private double total_amount;
    private String attach;
    private String body;
    private String qr_code_time_expire;
    private String notify_url;

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
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

    public String getQr_code_time_expire() {
        return qr_code_time_expire;
    }

    public void setQr_code_time_expire(String qr_code_time_expire) {
        this.qr_code_time_expire = qr_code_time_expire;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
}
