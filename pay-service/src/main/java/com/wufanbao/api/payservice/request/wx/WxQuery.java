package com.wufanbao.api.payservice.request.wx;

import com.wufanbao.api.payservice.request.PayRequest;

public class WxQuery extends PayRequest {
    private String trade_no;
    private String wx_trade_no;

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
}
