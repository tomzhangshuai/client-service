package com.wufanbao.api.clientservice.common.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.wufanbao.api.clientservice.common.DateUtils;
import com.wufanbao.api.clientservice.common.JsonUtils;
import com.wufanbao.api.clientservice.common.StringUtils;
import com.wufanbao.api.clientservice.common.wechat.AccessToken;
import com.wufanbao.api.clientservice.common.wechat.UserInfo;
import com.wufanbao.api.clientservice.service.ApiServiceException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

//https://docs.open.alipay.com/20180417160701241302/dhqxdb/
@Component
public class AliPay {
    private static final String url = "https://openapi.alipay.com/gateway.do";
    //应用私钥
    private static final String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuRd0jvrlwvLEP2z3WtIBX0gqen/zV7ynWjntxeavWDG6HT77hY2B2bQGKwl6ALPsnH4Yved13Aj1JZc7N9KacmbzRo08ttIwGnqcDRD9y29Hanj3LO46bDzsz3BnKDI52teinYhV4TixW04ECckdwB9iRWb2+/xV3Ks03i0wE0toq0FTNaN52UBxMW08umkK/iqfO+n/x0PxpMOM40AKpTFkjcMdnk9A/chCij50ksJGBYT2ncjQrrs59CSC1XN2ac2lRgI7Wec2MSogdGN9T4miALgS4krkqvqhOGZlrGsPOpcgKV4NFBXhroJzcDQKwjwqqFmO0Pw+bZJtsvYGXAgMBAAECggEAJwy2kZTh/pX5YuEwiRSnEYaaJJMa12Eg0dfZZ0LpMyxT8/P1hJF9AuxIBSyqejQaDtkseyTmcA1LaE/suA+S6dI1EhBy5H3pRm50Y/H9+aGx8ze0Bv8QZERUcrll+fU4UvdN63u57dwuaUI5TTAIO902NNOUBeEokSxBEaof3ZB254SpoS5p701GoJ0VSEuxzMEIMRi+Rczzgq8yU2sq3OIfDx5UFSpbimhaN5yf2WCcgNC3vT21Cqo/I3qYK1T1Yhoy/tOrQcG5wDag7THDRJ8nZZ/Pm2nrwb/lcuHmpT2CI7PjQ450ntGrWBV0k0qK7Ri2iGhiFteE/K2DXEjwSQKBgQDaDIIJ2it6uMmqyYVgDLhTlBGva42FQzznI7g8lw6SoMwRQBEYFBKfa7fNKLwb3yXQXeEDF/eFO2G5RW52k/Tr14Knm6oJO7x8QWfOFlsqeHr4XQ79KdbfxMSgtqRPI++fWcuVfPQiXyBNAEwbMseg4kMzfuvl3Mg7R2O5D7cUZQKBgQDMmtpcxOcc5gkEFm0qQfWP1vYjbXfVWVQQiThXXzBSjbR+L+Eem7iLe0IbLAbNMPac7GRhBJE4Duhyb1dhWZtktkYs69m6Yicx+/7uMC8iIuNv2D5wdsWgQm/1cq+3LY6f4G3sV8Uae3sJQV96Ri5ibBqU/eUDbMhUnMJRQbPoSwKBgEPDZVpIegCuksXH0PJToakfuAtLInUKu0+d0zDJRRceb6zguy8/dbYjlMBma2dCBw9lTXtXpG28UZdxU234d0If/JO4FbS+4sm+zdLHMdfWD2lGClV0/jkWfoDvocqSgXKFRnaTmY9g/rnPYNeHBXEjP/0t8YPDBFsfyYcGsj/dAoGBALgEwkZTdcVhA1Tg0rgB8Ni4rlG4v+Fr4RaqQ7kZTL3C40XmzCMaTQVuC0ui8k8ULYl/dtxnHjD7/4YEq+NLk14mLcFzMjTW2Ie1LFiPj6S1GjdalvXRbPcxKb7osi8e0zrpwBOj8QtbaZIucK/pdQhiUBoWrCXDbf3jq0UnwaY3AoGBAL11+FVGHGiXDfL/CXQfV3iFbsRaAUb5TAtuv8jt0Z4nB/C/Csxz1g57iWBFoffcVMsvQlny+GId56NYs6MC2eR8cVV292STh82kUKF09T5Ltg9v0syeF6UwGwZGWtD6dPPEjOYUuTJBrOXF8+UHhyGwKIy9bqOKD+qXvQr6kU7e";
    //支付宝公钥
    private static final String alpay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkF4G7slkYzLn1nVXRJZ3PZXBddyiwE7cjRhJg9WbA4RvR+Rvo+QUIIM/FIC/PHTj2Izjc0a1+hXu0FQUKU3AvQTXgnVA8xL7B6VNZqMTYxJ/FeAMccrwaBZ5xci67y3WWIRO9J1kCV6s6PILnm/uFOI3XF0qXnL7jtMCpOThoeyHQ08YtWIvluJLyyEMTXQIC7Rflv1w4c7a91dspU1YVpljl9WXg4gv///bhmoCl5/8JHF9IgFm3yIo1VXzWCA/LNrxjg7A0Zmg3ei024IzOu6K3e4neD5oE2kJQWzO5CbB/+nAfv2KY9uO3szbM63Iq28HUTegqf5SaD7mA1rLKQIDAQAB";
    //支付宝分配给开发者的应用ID
    private static final String app_id = "2017100909215193";

    private static final String format = "json";

    //请求使用的编码格式，如utf-8,gbk,gb2312等
    private static final String charset = "utf-8";

    //商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
    private static final String sign_type = "RSA2";

    private static AlipayClient alipayClient;

    public AliPay() {
        //实例化客户端
        alipayClient = new DefaultAlipayClient(url, app_id, private_key, format, charset, alpay_public_key, sign_type);
    }

    //验证签名
    public boolean checkSign(Map<String, String> param) throws AlipayApiException {
        return AlipaySignature.rsaCheckV1(param, alpay_public_key, AlipayConstants.CHARSET_UTF8, sign_type); // 校验签名是否正确
    }

    //订单付款
    public String appPayOrder(String tradeNo, BigDecimal amount, String subject, String body, String callback) throws AlipayApiException {
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setPassbackParams(body);  //描述信息  添加附加数据
        model.setSubject(subject);//商品标题
        model.setOutTradeNo(tradeNo); //商家订单编号
        model.setTimeoutExpress("30m"); //超时关闭该订单时间
        model.setTotalAmount(String.valueOf(amount.doubleValue()));  //订单总金额
        model.setProductCode("QUICK_MSECURITY_PAY"); //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY

        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizModel(model);

        request.setNotifyUrl(callback);  //回调地址

        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        String result = response.getBody();
        return result;//可以直接给客户端请求，无需再做处理
    }


    //查询支付订单状态
    public AlipayTradeQueryResponse queryOrder(String tradeNo) throws AlipayApiException, ApiServiceException {
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(tradeNo); //商家订单编号
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model);
        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        return response;
    }

    //查询退款订单状态
    public AlipayTradeFastpayRefundQueryResponse refundQuery(String tradeNo) throws AlipayApiException, ApiServiceException {
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(tradeNo); //商家订单编号
        model.setOutRequestNo(tradeNo);//请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();

        request.setBizModel(model);
        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        return response;
    }


    /**
     * 退款
     * https://docs.open.alipay.com/api_1/alipay.trade.refund
     *
     * @param tradeNo
     * @param refundAmount
     * @return
     * @throws AlipayApiException
     */
    public String refund(String tradeNo, BigDecimal refundAmount) throws AlipayApiException {
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(tradeNo); //与预授权转支付商户订单号相同，代表对该笔交易退款
        model.setRefundAmount(String.valueOf(refundAmount.doubleValue()));
        model.setRefundReason("正常退款");//退款原因

        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(model);
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        return response.getBody();
    }

    //通过code获取access_token
    public AlipaySystemOauthTokenResponse getAccessTokenByCode(String code) throws ApiServiceException {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(code);
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response;
            } else {
                throw new ApiServiceException(response.getSubCode() + ":" + response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            throw new ApiServiceException(e.getErrCode() + ":" + e.getErrMsg());
        }
    }

    //刷新(暂时每用上)
    public AlipaySystemOauthTokenResponse refreshToken(String code, String refreshToken) throws ApiServiceException {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(code);
        request.setRefreshToken(refreshToken);
        try {
            AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response;
            } else {
                throw new ApiServiceException(response.getSubCode() + ":" + response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            throw new ApiServiceException(e.getErrCode() + ":" + e.getErrMsg());
        }
    }


    //获取用户信息
    public AlipayUserInfoShareResponse getUserInfo(String token) throws ApiServiceException {
        try {
            AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
            AlipayUserInfoShareResponse response = alipayClient.execute(request, token);
            if (response.isSuccess()) {
                return response;
            } else {
                throw new ApiServiceException(response.getSubCode() + ":" + response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            throw new ApiServiceException(e.getErrCode() + ":" + e.getErrMsg());
        }
    }

    //H5 支付
    public String wapPayOrder(String tradeNo, BigDecimal amount, String subject, String body, String notifyUrl, String returnUrl) throws AlipayApiException {
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setPassbackParams(body);  //描述信息  添加附加数据
        model.setOutTradeNo(tradeNo); //商家订单编号
        model.setTotalAmount(String.valueOf(amount.doubleValue()));  //订单总金额
        model.setSubject(subject);//商品标题
        model.setTimeoutExpress("30m"); //超时关闭该订单时间
        model.setProductCode("QUICK_WAP_WAY"); //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY

        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();//创建API对应的request
        request.setBizModel(model);
        request.setReturnUrl(returnUrl);
        request.setNotifyUrl(notifyUrl);//在公共参数中设置回跳和通知地址

        String form = alipayClient.pageExecute(request).getBody(); //调用SDK生成表单
        return form;
    }


}
