package com.wufanbao.api.payservice.response.wx;

import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.PayResponse;

public class ResWxRefund extends PayResponse {
    private String trade_no;
    private String wx_trade_no;
    private String refund_id;
    private String refund_fee;
    private String total_amount;
    private String refund_channel;

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getWx_trade_no() {
        return wx_trade_no;
    }

    public void setWx_trade_no(String wx_trade_no) {
        this.wx_trade_no = wx_trade_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public static class Data {
        private ResponseBody responseBody;
        private ResponseData<ResWxRefund> responseData;


        public ResponseBody getResponseBody() {
            return responseBody;
        }

        public void setResponseBody(ResponseBody responseBody) {
            this.responseBody = responseBody;
        }

        public ResponseData<ResWxRefund> getResponseData() {
            return responseData;
        }

        public void setResponseData(ResponseData<ResWxRefund> responseData) {
            this.responseData = responseData;
        }

    }
}
