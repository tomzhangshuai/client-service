package com.wufanbao.api.oldclientservice.controller.LianLianPay.Bean;

import java.io.Serializable;

/**
 * 微信支付类
 *
 * @author <a href="zhuqf@yintong.com.cn">zhuqf</a>
 * @version 1.0
 * @package com.yintong.llwallet.domain
 * @description TODO
 * @date 2017-3-9 下午4:06:15
 */
public class WeChatPayBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String correlationID;
    private String no_order;
    private String oid_partner;
    private String busi_partner;
    private String money_order;
    /**
     * 付款方userid
     */
    private String user_id;
    /**
     * 付款方姓名
     */
    private String name_user;
    /**
     * 付款方证件类型(默认是身份证：0)
     */
    private String id_type = "0";
    /**
     * 付款方证件号
     */
    private String id_no;
    /**
     * 收款方用户标识（与col_oidpartner二选一传）
     */
    private String col_userid;
    /**
     * 收款方商户（与col_userid二选一传）
     */
    private String col_oidpartner;
    private String name_goods;
    private String dt_order;
    private String notify_url;
    private String return_url;
    private String info_order;
    private String pay_type;
    private String risk_item;
    private String shareing_data;
    private String sign_type;
    private String sign;
    /**
     * 担保商户号
     */
    private String secured_partner;
    /**
     * 买方确认收货有效期(单位：分钟。默认10080分钟（7天）
     */
    private String buyer_confirm_valid;
    /**
     * 卖方发货有效期(单位：分钟。默认10080分钟（7天）)
     */
    private String seller_send_valid = "10080";
    private String oid_paybill;
    /**
     * app支付
     */
    private String appid;
    private String openid;
    /**
     * 二维码
     */
    private String pay_token;
    private String pay_status;
    private String ret_code;
    private String ret_msg;

    public String getCorrelationID() {
        return correlationID;
    }

    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }

    public String getNo_order() {
        return no_order;
    }

    public void setNo_order(String no_order) {
        this.no_order = no_order;
    }

    public String getOid_partner() {
        return oid_partner;
    }

    public void setOid_partner(String oid_partner) {
        this.oid_partner = oid_partner;
    }

    public String getBusi_partner() {
        return busi_partner;
    }

    public void setBusi_partner(String busi_partner) {
        this.busi_partner = busi_partner;
    }

    public String getMoney_order() {
        return money_order;
    }

    public void setMoney_order(String money_order) {
        this.money_order = money_order;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getCol_userid() {
        return col_userid;
    }

    public void setCol_userid(String col_userid) {
        this.col_userid = col_userid;
    }

    public String getCol_oidpartner() {
        return col_oidpartner;
    }

    public void setCol_oidpartner(String col_oidpartner) {
        this.col_oidpartner = col_oidpartner;
    }

    public String getName_goods() {
        return name_goods;
    }

    public void setName_goods(String name_goods) {
        this.name_goods = name_goods;
    }

    public String getDt_order() {
        return dt_order;
    }

    public void setDt_order(String dt_order) {
        this.dt_order = dt_order;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getInfo_order() {
        return info_order;
    }

    public void setInfo_order(String info_order) {
        this.info_order = info_order;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getRisk_item() {
        return risk_item;
    }

    public void setRisk_item(String risk_item) {
        this.risk_item = risk_item;
    }

    public String getShareing_data() {
        return shareing_data;
    }

    public void setShareing_data(String shareing_data) {
        this.shareing_data = shareing_data;
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

    public String getSecured_partner() {
        return secured_partner;
    }

    public void setSecured_partner(String secured_partner) {
        this.secured_partner = secured_partner;
    }

    public String getBuyer_confirm_valid() {
        return buyer_confirm_valid;
    }

    public void setBuyer_confirm_valid(String buyer_confirm_valid) {
        this.buyer_confirm_valid = buyer_confirm_valid;
    }

    public String getSeller_send_valid() {
        return seller_send_valid;
    }

    public void setSeller_send_valid(String seller_send_valid) {
        this.seller_send_valid = seller_send_valid;
    }

    public String getOid_paybill() {
        return oid_paybill;
    }

    public void setOid_paybill(String oid_paybill) {
        this.oid_paybill = oid_paybill;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPay_token() {
        return pay_token;
    }

    public void setPay_token(String pay_token) {
        this.pay_token = pay_token;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }


}
