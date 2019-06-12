package com.wufanbao.api.payservice.demo;

import com.wufanbao.api.payservice.common.Helper;
import com.wufanbao.api.payservice.common.RequestData;
import com.wufanbao.api.payservice.request.ali.AliApi;
import com.wufanbao.api.payservice.request.wx.WXApi;
import com.wufanbao.api.payservice.response.ali.*;
import com.wufanbao.api.payservice.response.wx.*;

public class Inzpay {
    public static void main(String args[]) {
        //JSAPI线上支付
        //RequestData data = WXApi.jspay();
        //App支付预下单
//        RequestData data = WXApi.appPay();
        //扫码－B扫C支付
//        RequestData data = WXApi.tradePay();
        //扫码－C扫B预下单
//        RequestData data = WXApi.prePay();
        //预下单－查询支付结果
//        RequestData data = WXApi.prePayQuery();
        //查询订单
//        RequestData data = WXApi.query();
        //退款
//        RequestData data = WXApi.refund();
        //退款查询
        //RequestData data = WXApi.refundQuery();
        try {


            //微信 JSAPI +
            RequestData wxJsrequestData = WXApi.jspay();
            WxJSPayHandle handle = new WxJSPayHandle();
            Helper.request(wxJsrequestData, handle);
            ResWxJSPay.Data wxJsData = handle.getData();


            //微信 AppPay
            RequestData wxAppPayRequesData = WXApi.appPay();
            WxAppPayHandle wxAppPayHandle = new WxAppPayHandle();
            Helper.request(wxAppPayRequesData, wxAppPayHandle);
            ResWxAppPay.Data wxAppPayData = wxAppPayHandle.getData();


            //微信 WapPay -
            RequestData wxWapPayRequestData = WXApi.wapPay();
            WxWapPayHandle wxWapPayHandle = new WxWapPayHandle();
            Helper.request(wxWapPayRequestData, wxWapPayHandle);
            ResWxWapPay.Data wxWapPayData = wxWapPayHandle.getData();


            //微信 TradePay B扫C 支付 +
            RequestData wxTradePayRequestData = WXApi.tradePay();
            WxTradePayHandle wxTradePayHandle = new WxTradePayHandle();
            Helper.request(wxTradePayRequestData, wxTradePayHandle);
            ResWxTradePay.Data wxTradePayData = wxTradePayHandle.getData();


            //微信 PrePay C 扫 B 支付 +
            RequestData wxPrePayRequestData = WXApi.prePay();
            WxPrePayHandle wxPrePayHandle = new WxPrePayHandle();
            Helper.request(wxPrePayRequestData, wxPrePayHandle);
            ResWxPrePay.Data wxPrePayData = wxPrePayHandle.getData();


            //微信 预下单-查询支付结果 +
            RequestData wxPrePayQueryRequestData = WXApi.prePayQuery();
            WxPrePayQueryHandle wxPrePayQueryHandle = new WxPrePayQueryHandle();
            Helper.request(wxPrePayQueryRequestData, wxPrePayQueryHandle);
            ResWxPrePayQuery.Data wxPrePayQueryData = wxPrePayQueryHandle.getData();


            //微信 查询订单 +
            RequestData wxQueryRequestData = WXApi.query();
            WxQueryHandle wxQueryHandle = new WxQueryHandle();
            Helper.request(wxQueryRequestData, wxQueryHandle);
            ResWxQuery.Data wxQueryData = wxQueryHandle.getData();


            //微信 申请退款 +
            RequestData wxRefundRequestData = WXApi.refund();
            WxRefundHandle wxRefundHandle = new WxRefundHandle();
            Helper.request(wxRefundRequestData, wxRefundHandle);
            ResWxRefund.Data wxRefundData = wxRefundHandle.getData();


            //支付宝 JSAPI +
            RequestData aliJsRequestData = AliApi.jspay(System.currentTimeMillis() + "", 0.01, "557799", "http://www.baidu.com", "", "60");
            AliJSPayHandle aliJSPayHandle = new AliJSPayHandle();
            Helper.request(aliJsRequestData, aliJSPayHandle);
            ResAliJSPay.Data alJsData = aliJSPayHandle.getData();


            //微信 TradePay B扫C 支付
            RequestData aliTradePayRequestData = AliApi.tradePay();
            AliTradePayHandle aliTradePayHandle = new AliTradePayHandle();
            Helper.request(aliTradePayRequestData, aliTradePayHandle);
            ResAliTradePay.Data aliTradePayData = aliTradePayHandle.getData();

            //微信 PrePay C 扫 B 支付
            RequestData aliPrePayRequestData = AliApi.prePay();
            AliPrePayHandle aliPrePayHandle = new AliPrePayHandle();
            Helper.request(aliPrePayRequestData, aliPrePayHandle);
            ResAliPrePay.Data aliPrePayData = aliPrePayHandle.getData();

            //微信 预下单-查询支付结果
            RequestData aliPrePayQueryRequestData = AliApi.prePayQuery();
            AliPrePayQueryHandle aliPrePayQueryHandle = new AliPrePayQueryHandle();
            Helper.request(aliPrePayQueryRequestData, aliPrePayQueryHandle);
            ResAliPrePayQuery.Data aliPrePayQueryData = aliPrePayQueryHandle.getData();

            //微信 查询订单
            RequestData aliQueryRequestData = AliApi.query();
            AliQueryHandle aliQueryHandle = new AliQueryHandle();
            Helper.request(aliQueryRequestData, aliQueryHandle);
            ResAliQuery.Data aliQueryData = aliQueryHandle.getData();

            //微信 申请退款
            RequestData aliRefundRequestData = AliApi.refund();
            AliRefundHandle aliRefundHandle = new AliRefundHandle();
            Helper.request(aliRefundRequestData, aliRefundHandle);
            ResAliRefund.Data aliRefundData = aliRefundHandle.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
