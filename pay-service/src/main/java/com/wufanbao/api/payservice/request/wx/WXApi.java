package com.wufanbao.api.payservice.request.wx;

import com.wufanbao.api.payservice.common.Global;
import com.wufanbao.api.payservice.common.Helper;
import com.wufanbao.api.payservice.common.RequestData;

public class WXApi {
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
     * @return String tradeNo,double totalAmount,String buyerId,String buyerLogonId,String notifyUrl,String attach,String body,String timeoutExpress
     */
    public static RequestData jspay() {
        WxJSPay wxJsPay = new WxJSPay();
        wxJsPay.setTotal_amount(0.01);
        wxJsPay.setTrade_no(System.currentTimeMillis() + "");
        wxJsPay.setMch_create_ip("");
        wxJsPay.setLimit_credit_pay("");
        wxJsPay.setTime_expire("20201227091010");
        wxJsPay.setAttach("");
        wxJsPay.setBody("测试微信支付");
        wxJsPay.setGoods_tag("");
        wxJsPay.setOpenid("");
        wxJsPay.setNotify_url("http://www.baidu.com");
        wxJsPay.setCallback_url("");
        wxJsPay.setType(0);
        return jspay(wxJsPay);
    }

    /**
     * JSAPI 线上支付（含小程序及公众号）
     * 应用场景：为前端设备通过js api 方式调起微信支付时，使用该接口预先获取参数
     *
     * @param openId         微信用户opengid,使用微信js 接口获取得到，测试帐户不需要填写，正式签约帐户需要填写绑定公众号下的opengid
     * @param tradeNo        商户订单号
     * @param totalAmount    订单金额
     * @param notifyUrl
     * @param attach
     * @param body
     * @param timeoutExpress
     * @return
     */
    public static RequestData jspay(String openId, String subAppId, String tradeNo, double totalAmount, int type, String notifyUrl, String callbackUrl, String goodTag, String attach, String body, String timeoutExpress, String limitCreaitPay, String mchCreateIp) {
        WxJSPay wxJsPay = new WxJSPay();
        wxJsPay.setTotal_amount(totalAmount);
        wxJsPay.setTrade_no(tradeNo);
        wxJsPay.setTime_expire(timeoutExpress);
        wxJsPay.setAttach(attach);
        wxJsPay.setBody(body);
        wxJsPay.setGoods_tag(goodTag);
        wxJsPay.setOpenid(openId);
        wxJsPay.setSub_appid(subAppId);
        wxJsPay.setType(type);
        wxJsPay.setNotify_url(notifyUrl);
        wxJsPay.setCallback_url(callbackUrl);
        wxJsPay.setLimit_credit_pay(limitCreaitPay);
        //wxJsPay.setMch_create_ip(mchCreateIp);
        return jspay(wxJsPay);
    }

    public static RequestData jspay(WxJSPay wxJSPay) {
        data.setBiz_type("wx.jspay");
        data.setBiz_content(wxJSPay);
        return data;
    }

    /**
     * App支付预下单
     *
     * @return
     */
    public static RequestData appPay() {
        WxAppPay appPay = new WxAppPay();
        appPay.setTotal_amount(0.01);
        appPay.setAttach("");
        appPay.setBody("测试微信App支付");
        //appPay.setMch_create_ip("192.168.0.1");
        appPay.setNotify_url("http://www.baidu.com");
        appPay.setTime_expire("20201227091010");
        appPay.setSub_appid("557799");
        appPay.setTrade_no(System.currentTimeMillis() + "");
        return appPay(appPay);
    }

    public static RequestData appPay(String tradeNo, double totalAmount, String notifyUrl, String callbackUrl, String subAppId, String body, String attach, String timeExpire, String mchCreateIp) {
        WxAppPay appPay = new WxAppPay();
        appPay.setTrade_no(tradeNo);
        appPay.setTotal_amount(totalAmount);
        appPay.setAttach(attach);
        appPay.setBody(body);
        appPay.setMch_create_ip(mchCreateIp);
        appPay.setNotify_url(notifyUrl);
        appPay.setCallback_url(callbackUrl);
        appPay.setTime_expire(timeExpire);
        appPay.setSub_appid(subAppId);
        return appPay(appPay);
    }

    public static RequestData appPay(WxAppPay appPay) {
        data.setBiz_type("wx.apppay");
        data.setBiz_content(appPay);
        return data;
    }

    /**
     * Wap 支付预下单
     *
     * @return
     */
    public static RequestData wapPay() {
        WxWapPay wapPay = new WxWapPay();
        wapPay.setTrade_no(System.currentTimeMillis() + "");
        wapPay.setTotal_amount(0.01);
        //wapPay.setAttach("");
        wapPay.setBody("测试Wap支付");
        //wapPay.setMch_create_ip("192.168.0.1");
        wapPay.setNotify_url("http://www.baidu.com");
        wapPay.setMch_app_name("伍饭宝");
        wapPay.setMch_app_id("com.wufanbao.clien");
        wapPay.setDevice_info("test");
        wapPay.setTime_expire("20201227091010");
        //wxAppPay.setSub_appid("557799");
        return wapPay(wapPay);
    }

    public static RequestData wapPay(String tradeNo, double totalAmount, String notifyUrl, String callbackUrl, String subAppId, String body, String attach, String timeExpire, String mchAppName, String mchAppId, String mchCreateIp, String deviceInfo) {
        WxWapPay wapPay = new WxWapPay();
        wapPay.setTrade_no(tradeNo);
        wapPay.setTotal_amount(totalAmount);
        wapPay.setAttach(attach);
        wapPay.setBody(body);
        wapPay.setMch_create_ip(mchCreateIp);
        wapPay.setNotify_url(notifyUrl);
        wapPay.setCallback_url(callbackUrl);
        wapPay.setMch_app_name(mchAppName);
        wapPay.setMch_app_id(mchAppId);
        wapPay.setDevice_info(deviceInfo);
        wapPay.setTime_expire(timeExpire);
        wapPay.setSub_appid(subAppId);
        return wapPay(wapPay);
    }

    public static RequestData wapPay(WxWapPay wapPay) {
        data.setBiz_type("wx.wappay");
        data.setBiz_content(wapPay);
        return data;
    }


    /**
     * 扫码－B扫C支付
     *
     * @return
     */
    public static RequestData tradePay() {
        WxTradePay tradePay = new WxTradePay();
        tradePay.setTrade_no(System.currentTimeMillis() + "");
        //传入收款码信息
        tradePay.setAuth_code("234234234");
        tradePay.setTotal_amount(0.01);
        tradePay.setAttach("");
        tradePay.setBody("");
        tradePay.setTime_expire(60);
        return tradePay(tradePay);
    }

    public static RequestData tradePay(String tradeNo, double totalAmount, String authCode, String body, String attach, int timeExpire) {
        WxTradePay tradePay = new WxTradePay();
        tradePay.setTrade_no(tradeNo);
        //传入收款码信息
        tradePay.setAuth_code(authCode);
        tradePay.setTotal_amount(totalAmount);
        tradePay.setAttach(attach);
        tradePay.setBody(body);
        tradePay.setTime_expire(timeExpire);
        return tradePay(tradePay);
    }

    public static RequestData tradePay(WxTradePay tradePay) {
        data.setBiz_type("wx.tradepay");
        data.setBiz_content(tradePay);
        return data;
    }


    /**
     * 扫码－C扫B预下单
     *
     * @return
     */
    public static RequestData prePay() {
        WxPrePay prePay = new WxPrePay();
        prePay.setTrade_no(System.currentTimeMillis() + "");
        prePay.setTotal_amount(0.01);
        prePay.setAttach("");
        prePay.setBody("");
        prePay.setTime_expire("20201227091010");
        prePay.setNotify_url("www.baidu.com");
        return prePay(prePay);
    }

    public static RequestData prePay(String tradeNo, double totalAmount, String notifyUrl, String body, String attach, String timeExpire) {
        WxPrePay prePay = new WxPrePay();
        prePay.setTrade_no(tradeNo);
        prePay.setTotal_amount(totalAmount);
        prePay.setAttach(attach);
        prePay.setBody(body);
        prePay.setTime_expire(timeExpire);
        prePay.setNotify_url(notifyUrl);
        return prePay(prePay);
    }

    public static RequestData prePay(WxPrePay prePay) {
        data.setBiz_type("wx.prepay");
        data.setBiz_content(prePay);
        return data;
    }

    /**
     * 预下单－查询支付结果
     *
     * @return
     */
    public static RequestData prePayQuery() {
        data.setBiz_type("wx.prepayquery");
        WxPrePayQuery prePayQuery = new WxPrePayQuery();
        prePayQuery.setTrade_no("1511763380717");
        data.setBiz_content(prePayQuery);
        return data;
    }

    public static RequestData prePayQuery(String tradeNo) {
        WxPrePayQuery prePayQuery = new WxPrePayQuery();
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
        data.setBiz_type("wx.query");
        WxQuery query = new WxQuery();
        query.setTrade_no("1111");
        query.setWx_trade_no("");
        data.setBiz_content(query);
        return data;
    }

    public static RequestData query(String tradeNo, String WXTradeNo) {
        data.setBiz_type("wx.query");
        WxQuery query = new WxQuery();
        query.setTrade_no(tradeNo);
        query.setWx_trade_no(WXTradeNo);
        data.setBiz_content(query);
        return data;
    }


    /**
     * 退款
     *
     * @return
     */
    public static RequestData refund() {
        data.setBiz_type("wx.refund");
        WxRefund refund = new WxRefund();
        refund.setTrade_no("1111");
        refund.setWx_trade_no("");
        refund.setRefund_amount("0.01");
        refund.setOut_refund_no("");
        data.setBiz_content(refund);
        return data;
    }

    public static RequestData refund(String tradeNo, String WXTradeNo, String refundAmount, String outRefundNo) {
        WxRefund refund = new WxRefund();
        refund.setTrade_no(tradeNo);
        refund.setWx_trade_no(WXTradeNo);
        refund.setRefund_amount(refundAmount);
        refund.setOut_refund_no(outRefundNo);
        return refund(refund);
    }

    public static RequestData refund(WxRefund refund) {
        data.setBiz_type("wx.refund");
        data.setBiz_content(refund);
        return data;
    }

    /**
     * 退款查询
     *
     * @return
     */
    public static RequestData refundQuery() {
        data.setBiz_type("wx.refundquery");
        WxRefundQuery refundQuery = new WxRefundQuery();
        refundQuery.setTrade_no("");
        refundQuery.setWx_trade_no("");
        refundQuery.setRefund_id("1");
        data.setBiz_content(refundQuery);
        return data;
    }

    public static RequestData refundQuery(String tradeNo, String WXTradeNo, String refundId) {
        data.setBiz_type("wx.refundquery");
        WxRefundQuery refundQuery = new WxRefundQuery();
        refundQuery.setTrade_no(tradeNo);
        refundQuery.setWx_trade_no(WXTradeNo);
        refundQuery.setRefund_id(refundId);
        data.setBiz_content(refundQuery);
        return data;
    }
}
