package com.wufanbao.api.payservice.request.ali;

import com.wufanbao.api.payservice.request.PayRequest;

public class AliQuery extends PayRequest {
    private String trade_no;
    private String ali_trade_no;

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
}
