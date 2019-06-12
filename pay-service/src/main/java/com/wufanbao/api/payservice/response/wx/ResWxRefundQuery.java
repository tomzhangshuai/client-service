package com.wufanbao.api.payservice.response.wx;

import com.wufanbao.api.payservice.response.PayResponse;

public class ResWxRefundQuery extends PayResponse {
    private String trade_no;
    private int refund_count;
    private String refund_id_$n;
    private String refund_channel_$n;
    private int refund_fee_$n;
    private int coupon_refund_fee_$n;
    private String refund_time_$n;
    private String refund_status_$n;
}
