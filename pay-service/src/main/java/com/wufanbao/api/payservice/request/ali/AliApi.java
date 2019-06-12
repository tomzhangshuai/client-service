package com.wufanbao.api.payservice.request.ali;

import com.wufanbao.api.payservice.common.Global;
import com.wufanbao.api.payservice.common.Helper;
import com.wufanbao.api.payservice.common.RequestData;
import com.wufanbao.api.payservice.request.wx.WxPrePay;

public class AliApi {
    private static RequestData data;

    static {
        initData();
    }

    private static void initData() {
        data = new RequestData();
        data.setVersion(Global.VERSION);
        data.setMerchant_id(Global.MERCHANT_ID);
        data.setTerm_id(Global.TERM_ID);
        data.setTimestamp(Helper.getTimestamp());
        data.setSign_type(Global.SIGN_TYPE);
    }

    /**
     * JSAPI线上支付
     *
     * @return
     */
    public static RequestData jspay(String tradeNo, double totalAmount, String buyerId, String notifyUrl, String body, String timeoutExpress) {
        return jspay(tradeNo, totalAmount, buyerId, "", notifyUrl, "", body, timeoutExpress);
    }

    public static RequestData jspay(String tradeNo, double totalAmount, String buyerId, String buyerLogonId, String notifyUrl, String attach, String body, String timeoutExpress) {
        AliJSPay aliJsPay = new AliJSPay();
        aliJsPay.setTotal_amount(totalAmount);
        aliJsPay.setTrade_no(tradeNo);//System.currentTimeMillis() + ""
        aliJsPay.setAttach(attach);
        aliJsPay.setBody(body);
        aliJsPay.setNotify_url(notifyUrl);
        aliJsPay.setBuyer_id(buyerId);
        aliJsPay.setBuyer_logon_id(buyerLogonId);
        aliJsPay.setQr_code_timeout_express(timeoutExpress);
        return jspay(aliJsPay);
    }

    public static RequestData jspay(AliJSPay jsPay) {
        data.setBiz_type("ali.jspay");
        data.setBiz_content(jsPay);
        return data;
    }

    /**
     * 扫码－B扫C支付
     *
     * @return
     */
    public static RequestData tradePay() {
        AliTradePay tradePay = new AliTradePay();
        tradePay.setTrade_no(System.currentTimeMillis() + "");
        //传入收款码信息
        tradePay.setAuth_code("sfasd33");
        tradePay.setTotal_amount(0.01);
        tradePay.setAttach("");
        tradePay.setBody("阿里 B扫C支付");
        tradePay.setTime_expire(60);
        return tradePay(tradePay);
    }

    public static RequestData tradePay(String tradeNo, String authCode, double totalAmount, String body, String attach, int timeExpire) {
        AliTradePay tradePay = new AliTradePay();
        tradePay.setTrade_no(tradeNo);
        //传入收款码信息
        tradePay.setAuth_code(authCode);
        tradePay.setTotal_amount(totalAmount);
        tradePay.setAttach(attach);
        tradePay.setBody(body);
        tradePay.setTime_expire(timeExpire);
        return tradePay(tradePay);
    }

    public static RequestData tradePay(AliTradePay tradePay) {
        data.setBiz_type("ali.tradepay");
        data.setBiz_content(tradePay);
        return data;
    }

    /**
     * 扫码－C扫B预下单
     *
     * @return
     */
    public static RequestData prePay() {
        data.setBiz_type("ali.prepay");
        AliPrePay tradePay = new AliPrePay();
        tradePay.setTrade_no(System.currentTimeMillis() + "");
        tradePay.setTotal_amount(0.01);
        tradePay.setAttach("");
        tradePay.setBody("阿里 C扫B预下单");
        tradePay.setQr_code_time_expire("20201227091010");
        tradePay.setNotify_url("www.baidu.com");
        data.setBiz_content(tradePay);
        return data;
    }

    public static RequestData prePay(String tradeNo, double totalAmount, String body, String attach, String notifyUrl, String QRCodeTimeOutExpress) {
        AliPrePay prePay = new AliPrePay();
        prePay.setTrade_no(tradeNo);
        prePay.setTotal_amount(totalAmount);
        prePay.setAttach(attach);
        prePay.setBody(body);
        prePay.setQr_code_time_expire(QRCodeTimeOutExpress);
        prePay.setNotify_url(notifyUrl);
        return prePay(prePay);
    }

    public static RequestData prePay(AliPrePay prePay) {
        data.setBiz_type("ali.prepay");
        data.setBiz_content(prePay);
        return data;
    }

    /**
     * 预下单－查询支付结果
     *
     * @return
     */
    public static RequestData prePayQuery() {
        data.setBiz_type("ali.prepayquery");
        AliPrePayQuery prePayQuery = new AliPrePayQuery();
        prePayQuery.setTrade_no("1511763380717");
        data.setBiz_content(prePayQuery);
        return data;
    }

    public static RequestData prePayQuery(String tradeNo) {
        data.setBiz_type("ali.prepayquery");
        AliPrePayQuery prePayQuery = new AliPrePayQuery();
        prePayQuery.setTrade_no(tradeNo);
        data.setBiz_content(prePayQuery);
        return data;
    }

    /**
     * 查询订单
     *
     * @return
     */
    public static RequestData query() {
        data.setBiz_type("ali.query");
        AliQuery query = new AliQuery();
        query.setTrade_no("1111");
        query.setAli_trade_no("");
        data.setBiz_content(query);
        return data;
    }

    public static RequestData query(String tradeNo, String aliTradeNo) {
        data.setBiz_type("ali.query");
        AliQuery query = new AliQuery();
        query.setTrade_no(tradeNo);
        query.setAli_trade_no(aliTradeNo);
        data.setBiz_content(query);
        return data;
    }

    /**
     * 退款
     *
     * @return
     */
    public static RequestData refund() {
        data.setBiz_type("ali.refund");
        AliRefund refund = new AliRefund();
        refund.setTrade_no("1111");
        refund.setAli_trade_no("");
        refund.setRefund_amount("0.01");
        refund.setOut_refund_no("");
        data.setBiz_content(refund);
        return data;
    }

    public static RequestData refund(String tradeNo, String aliTradeNo, String refundAmount, String outRefundNo) {
        data.setBiz_type("ali.refund");
        AliRefund refund = new AliRefund();
        refund.setTrade_no(tradeNo);
        refund.setAli_trade_no(aliTradeNo);
        refund.setRefund_amount(refundAmount);
        refund.setOut_refund_no(outRefundNo);
        data.setBiz_content(refund);
        return data;
    }

    /**
     * 退款查询
     *
     * @return
     */
    public static RequestData refundQuery() {
        data.setBiz_type("ali.refundquery");
        AliRefundQuery refundQuery = new AliRefundQuery();
        refundQuery.setTrade_no("");
        refundQuery.setAli_trade_no("");
        refundQuery.setRefund_id("1");
        data.setBiz_content(refundQuery);
        return data;
    }

    public static RequestData refundQuery(String tradeNo, String aliTradeNo, String refundId) {
        data.setBiz_type("ali.refundquery");
        AliRefundQuery refundQuery = new AliRefundQuery();
        refundQuery.setTrade_no(tradeNo);
        refundQuery.setAli_trade_no(aliTradeNo);
        refundQuery.setRefund_id(refundId);
        data.setBiz_content(refundQuery);
        return data;
    }
}
