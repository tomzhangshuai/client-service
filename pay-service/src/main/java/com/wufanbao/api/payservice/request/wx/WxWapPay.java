package com.wufanbao.api.payservice.request.wx;

import com.wufanbao.api.payservice.request.PayRequest;

public class WxWapPay extends PayRequest {
    private String trade_no;
    private String sub_appid;
    private double total_amount;
    private String attach;
    private String body;
    private String mch_create_ip;
    private String callback_url;
    private String notify_url;
    private String mch_app_name;
    private String mch_app_id;
    private String device_info;
    private String time_expire;

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getSub_appid() {
        return sub_appid;
    }

    public void setSub_appid(String sub_appid) {
        this.sub_appid = sub_appid;
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

    public String getMch_create_ip() {
        return mch_create_ip;
    }

    public void setMch_create_ip(String mch_create_ip) {
        this.mch_create_ip = mch_create_ip;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getMch_app_name() {
        return mch_app_name;
    }

    public void setMch_app_name(String mch_app_name) {
        this.mch_app_name = mch_app_name;
    }

    public String getMch_app_id() {
        return mch_app_id;
    }

    public void setMch_app_id(String mch_app_id) {
        this.mch_app_id = mch_app_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }
}
