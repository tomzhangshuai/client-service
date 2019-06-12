package com.wufanbao.api.oldclientservice.controller.LianLianPay.Bean;

/**
 * User:wangshihao
 * Date:2017-11-22
 * Time:11:34
 */
public class BankPayBean extends BankCardPayBean {
    private String oid_partner;//商户编号
    private String sign_type;//签名方式
    private String sign;//签名
    private String no_refund;//商户退款流水号
    private String dt_refund;//商户退款时间1
    private String money_refund;//退款金额1
    private String notify_url;//服务器异步通知地址
    private String no_order;//原商户订单号1
    private String dt_order;//原商户订单时间

    public String getOid_partner() {
        return oid_partner;
    }

    public void setOid_partner(String oid_partner) {
        this.oid_partner = oid_partner;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNo_refund() {
        return no_refund;
    }

    public void setNo_refund(String no_refund) {
        this.no_refund = no_refund;
    }

    public String getDt_refund() {
        return dt_refund;
    }

    public void setDt_refund(String dt_refund) {
        this.dt_refund = dt_refund;
    }

    public String getMoney_refund() {
        return money_refund;
    }

    public void setMoney_refund(String money_refund) {
        this.money_refund = money_refund;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getNo_order() {
        return no_order;
    }

    public void setNo_order(String no_order) {
        this.no_order = no_order;
    }

    public String getDt_order() {
        return dt_order;
    }

    public void setDt_order(String dt_order) {
        this.dt_order = dt_order;
    }
}
