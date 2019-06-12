package com.wufanbao.api.payservice.response.ali;

import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.PayResponse;

public class ResAliPrePay extends PayResponse {
    private String trade_no;
    private String attach;
    private String qr_code;

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

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public static class Data {
        private ResponseBody responseBody;
        private ResponseData<ResAliPrePay> responseData;


        public ResponseBody getResponseBody() {
            return responseBody;
        }

        public void setResponseBody(ResponseBody responseBody) {
            this.responseBody = responseBody;
        }

        public ResponseData<ResAliPrePay> getResponseData() {
            return responseData;
        }

        public void setResponseData(ResponseData<ResAliPrePay> responseData) {
            this.responseData = responseData;
        }

    }
}
