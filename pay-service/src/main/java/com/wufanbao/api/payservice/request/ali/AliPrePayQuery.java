package com.wufanbao.api.payservice.request.ali;

import com.wufanbao.api.payservice.request.PayRequest;

public class AliPrePayQuery extends PayRequest {
    private String trade_no;

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }
}
