package com.wufanbao.api.payservice.request.ali;

import com.wufanbao.api.payservice.request.PayRequest;

public class AliRefund extends PayRequest {
    private String trade_no;
    private String ali_trade_no;
    private String refund_amount;
    private String out_refund_no;

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

    public String getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(String refund_amount) {
        this.refund_amount = refund_amount;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }
}
