package com.wufanbao.api.clientservice.common.wechat;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.wufanbao.api.clientservice.common.Data;
import com.wufanbao.api.clientservice.common.DateUtils;
import com.wufanbao.api.clientservice.common.XMLUtil;
import com.wufanbao.api.clientservice.service.ApiServiceException;

import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

//微信支付
//https://blog.csdn.net/qq_29423883/article/details/77745425
public class WechatPay {
    private static final String ALGORITHM = "AES";

    private WXPay wxpay;
    private WechatPayConfig config;

    public WechatPayConfig getConfig() {
        return config;
    }

    public WechatPay(String appId) throws Exception {
        config = new WechatPayConfig(appId);
        wxpay = new WXPay(config, WXPayConstants.SignType.MD5);
    }

    public WechatPay() throws Exception {
        config = new WechatPayConfig();
        wxpay = new WXPay(config, WXPayConstants.SignType.MD5);
    }

    //验证签名
    public boolean checkSign(Map<String, String> map) throws Exception {
        String sign = map.get("sign").toString();
        Map<String, String> data = new HashMap<>();
        for (String keyValue : map.keySet()) {
            if (keyValue.equals("sign")) {
                continue;
            }
            data.put(keyValue.toString(), map.get(keyValue));
        }
        String createSign = WXPayUtil.generateSignature(data, config.getKey(), WXPayConstants.SignType.MD5);
        if (createSign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }

    //对退款信息进行解密
    public Map<String, String> decodeData(String reqInfo) throws Exception {
        //2、对商户key做md5，得到32位小写key*
        SecretKeySpec key = new SecretKeySpec(MD5Util.MD5Encode(config.getKey(), "UTF-8").toLowerCase().getBytes(), ALGORITHM);
        //3、用key*对加密串B做AES-256-ECB解密
        return XMLUtil.doXMLParse(AESUtil.decryptData(reqInfo, key));
    }

    public String notifySuccess(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }

    //统一下单
    public Map<String, String> payOrder(String tradeNo, String tradeType, String openId, BigDecimal amount, String body, String ip, String callback) throws UnsupportedEncodingException, ApiServiceException {
        Data data = new Data();
        data.put("out_trade_no", tradeNo);
        BigDecimal b = amount.multiply(BigDecimal.valueOf(100));
        data.put("total_fee", String.valueOf(b.intValue()));
        data.put("fee_type", "CNY");
        //data.put("device_info", "");
        data.put("trade_type", tradeType);
        if (tradeType == "JSAPI") {
            data.put("openid", openId);
        }
        data.put("spbill_create_ip", ip);
        data.put("time_start", DateUtils.DateToString(new Date(),"yyyyMMddHHmmss"));
        String time=DateUtils.DateToString(DateUtils.getAfterTime(2),"yyyyMMddHHmmss");
        data.put("time_expire", time);
        data.put("notify_url", callback);
        data.put("body", new String(body.getBytes("UTF-8"), "UTF-8"));//new String(body.getBytes("ISO-8859-1") ,"UTF-8"));//new String(body.getBytes(),"UTF-8")

        Map<String, String> newMap = new HashMap<>();
        try {
            String str = config.getAppID();
            Map<String, String> map = wxpay.unifiedOrder(data);
            if (map.get("return_code").equals("FAIL")) {
                throw new ApiServiceException(map.get("return_msg"));
            }
            newMap.put("appid", map.get("appid"));
            newMap.put("prepayid", map.get("prepay_id"));
            newMap.put("partnerid", map.get("mch_id"));
            newMap.put("noncestr", map.get("nonce_str"));
            newMap.put("package", "Sign=WXPay");
            newMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
            newMap.put("sign", WXPayUtil.generateSignature(newMap, config.getKey(), WXPayConstants.SignType.MD5));
        } catch (Exception e) {
            throw new ApiServiceException(e.getMessage());
        }
        return newMap;
    }

    //查询支付订单
    //https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4
    public Map<String, String> queryOrder(String tradeNo) throws ApiServiceException {
        Data data = new Data();
        data.put("out_trade_no", tradeNo);
        try {
            Map<String, String> map = wxpay.orderQuery(data);
            return map;
        } catch (Exception e) {
            throw new ApiServiceException(e.getMessage());
        }
    }

    //查询退款订单
    public Map<String, String> refundQuery(String tradeNo) throws ApiServiceException {
        Data data = new Data();
        data.put("out_trade_no", tradeNo);
        try {
            Map<String, String> map = wxpay.refundQuery(data);
            return map;
        } catch (Exception e) {
            throw new ApiServiceException(e.getMessage());
        }
    }

    /***
     * 退款
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
     * @param tradeNo
     * @param refundTradeNo
     * @param amount
     * @param refundAmount
     * @return
     */
    public Map<String, String> refund(String tradeNo, String refundTradeNo, BigDecimal amount, BigDecimal refundAmount, String callback) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", tradeNo);//商户订单号
        data.put("out_refund_no", refundTradeNo);//商户退款单号
        BigDecimal b = amount.multiply(BigDecimal.valueOf(100));
        data.put("total_fee", String.valueOf(b.intValue()));//订单金额
        BigDecimal a = refundAmount.multiply(BigDecimal.valueOf(100));
        data.put("refund_fee", String.valueOf(a.intValue()));//退款金额
        data.put("notify_url", callback);
        Map<String, String> map = new HashMap<>();
        try {
            map = wxpay.refund(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
