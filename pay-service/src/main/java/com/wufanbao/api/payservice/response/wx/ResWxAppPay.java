package com.wufanbao.api.payservice.response.wx;

import com.google.gson.annotations.SerializedName;
import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.PayResponse;

public class ResWxAppPay extends PayResponse {
    private String trade_no;
    private String attach;
    private String pay_info;

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getPay_info() {
        return pay_info;
    }

    public void setPay_info(String pay_info) {
        this.pay_info = pay_info;
    }

    public static class PayInfo {
        private String appId;
        private String timeStamp;
        private String status;
        private String signType;

        @SerializedName("package")
        private String packageX;

        private String callback_url;
        private String nonceStr;
        private String paySign;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public String getCallback_url() {
            return callback_url;
        }

        public void setCallback_url(String callback_url) {
            this.callback_url = callback_url;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }
    }

    public static class Data {
        private ResponseBody responseBody;
        private ResponseData<ResWxAppPay> responseData;
        private ResWxAppPay.PayInfo payInfo;

        public ResponseBody getResponseBody() {
            return responseBody;
        }

        public void setResponseBody(ResponseBody responseBody) {
            this.responseBody = responseBody;
        }

        public ResponseData<ResWxAppPay> getResponseData() {
            return responseData;
        }

        public void setResponseData(ResponseData<ResWxAppPay> responseData) {
            this.responseData = responseData;
        }

        public ResWxAppPay.PayInfo getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(ResWxAppPay.PayInfo payInfo) {
            this.payInfo = payInfo;
        }
    }
}
