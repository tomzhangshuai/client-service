package com.wufanbao.api.payservice.request.ali;

import com.wufanbao.api.payservice.request.PayRequest;

public class AliRefundQuery extends PayRequest {
    private String trade_no;
    private String ali_trade_no;
    private String refund_id;

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getAli_trade_no() {
        return ali_trade_no;
    }

    public void setAli_trade_no(String ali_trade_no) {
        this.ali_trade_no = ali_trade_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }
}
