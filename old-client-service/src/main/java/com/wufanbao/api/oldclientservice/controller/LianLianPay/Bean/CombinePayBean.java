package com.wufanbao.api.oldclientservice.controller.LianLianPay.Bean;

/**
 * User:wangshihao
 * Date:2017-11-24
 * Time:14:46
 */
public class CombinePayBean {
    private String ver_app;//接口版本 默认 1.0
    private String sign_type;//签名方式 RSA
    private String sign;//签名
    private String oid_partner;//用户所属商户号
    private String user_id;//商户用户唯一编号
    private String col_oidpartner;//收款方商户号
    private String wechat_app_id;//微信 AppID
    private String busi_partner;//商户业务类型 业务类型编码 虚拟商品销售：101001 实物商品销售：109001
    private String no_order;//商户唯一订单号
    private String dt_order;//商户订单时间
    private String money_order;//商户订单金额
    private String name_goods;//商品名称
    private String notify_url;//异步通知地址
    private String risk_item;//风险控制参数
    private String pay_type;//支付方式
    private String info_order;//订单描述
    private String valid_order;//订单有效时间
    private String card_no;

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getValid_order() {
        return valid_order;
    }

    public void setValid_order(String valid_order) {
        this.valid_order = valid_order;
    }

    public String getInfo_order() {
        return info_order;
    }

    public void setInfo_order(String info_order) {
        this.info_order = info_order;
    }

    public String getVer_app() {
        return ver_app;
    }

    public void setVer_app(String ver_app) {
        this.ver_app = ver_app;
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

    public String getOid_partner() {
        return oid_partner;
    }

    public void setOid_partner(String oid_partner) {
        this.oid_partner = oid_partner;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCol_oidpartner() {
        return col_oidpartner;
    }

    public void setCol_oidpartner(String col_oidpartner) {
        this.col_oidpartner = col_oidpartner;
    }

    public String getWechat_app_id() {
        return wechat_app_id;
    }

    public void setWechat_app_id(String wechat_app_id) {
        this.wechat_app_id = wechat_app_id;
    }

    public String getBusi_partner() {
        return busi_partner;
    }

    public void setBusi_partner(String busi_partner) {
        this.busi_partner = busi_partner;
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

    public String getMoney_order() {
        return money_order;
    }

    public void setMoney_order(String money_order) {
        this.money_order = money_order;
    }

    public String getName_goods() {
        return name_goods;
    }

    public void setName_goods(String name_goods) {
        this.name_goods = name_goods;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getRisk_item() {
        return risk_item;
    }

    public void setRisk_item(String risk_item) {
        this.risk_item = risk_item;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }
}
