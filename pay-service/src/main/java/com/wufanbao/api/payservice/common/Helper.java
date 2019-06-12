package com.wufanbao.api.payservice.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.payservice.response.wx.ResWxJSPay;
import com.wufanbao.api.payservice.response.IResponseHandle;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYmmddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * 发送请求
     *
     * @param data
     * @param sign
     */
    public static String request(RequestData data, String sign) {
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(createSSLSocketFactory())
                    .build();
            FormBody.Builder builder = new FormBody.Builder();
            String dataJson = new Gson().toJson(data);
            //System.out.println("data: " + dataJson);
            //System.out.println("sign: " + sign);
            builder.add("data", dataJson);
            builder.add("sign", sign);
            RequestBody body = builder.build();
            Request request = new Request.Builder()
                    .url("https://allpaytest.visastandards.com:9902/gclients")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                ResponseBody responseBody = new Gson().fromJson(result, ResponseBody.class);
                ResponseData responseData = new Gson().fromJson(responseBody.getData(), new TypeToken<ResponseData<ResWxJSPay>>() {
                }.getType());

                ResWxJSPay jsPay = (ResWxJSPay) responseData.getBiz_content();
                ResWxJSPay.PayInfo payInfo = new Gson().fromJson(jsPay.getPay_info(), ResWxJSPay.PayInfo.class);

                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void request(RequestData data, IResponseHandle responseHandle) throws Exception {
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(createSSLSocketFactory())
                    .build();
            FormBody.Builder builder = new FormBody.Builder();
            String dataJson = new Gson().toJson(data);
            String sign = Sign.getSign(data);
            System.out.println("data: " + dataJson);
            System.out.println("sign: " + sign);
            builder.add("data", dataJson);
            builder.add("sign", sign);
            RequestBody body = builder.build();
            Request request = new Request.Builder()
                    .url("https://allpaytest.visastandards.com:9902/gclients")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                System.out.println("输出：" + result);
                ResponseBody responseBody = new Gson().fromJson(result, ResponseBody.class);
                responseHandle.decodeData(responseBody);
                if (!checkSign(responseBody, responseHandle.GetResponseDataStr(), responseHandle.getTimestamp())) {
                    throw new Exception("签名验证失败");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    /**
     * 签名对
     *
     * @param responseDataStr
     * @return
     */
    public static boolean checkSign(ResponseBody responseBody, String responseDataStr, String timestamp) {
        String sign = Sign.getSign(responseDataStr, timestamp);
        if (sign.equals(responseBody.getSign())) {
            System.out.println("签名验证成功");
            return true;
        } else {
            System.out.println("签名验证失败");
            return false;
        }
    }


}
