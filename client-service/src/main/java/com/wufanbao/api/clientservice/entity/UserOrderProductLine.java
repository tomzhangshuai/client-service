package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserOrderProductLine,
public class UserOrderProductLine {
    //UserOrderProductLineId,
    private long userOrderProductLineId;
    //订单ID,
    private long userOrderId;
    //商品ID,
    private long productGlobalId;
    //下架id,
    private long productOffId;
    //关联时间,
    private Date relationTime;

    public long getUserOrderProductLineId() {
        return this.userOrderProductLineId;
    }

    public void setUserOrderProductLineId(long userOrderProductLineId) {
        this.userOrderProductLineId = userOrderProductLineId;
    }

    public long getUserOrderId() {
        return this.userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public long getProductOffId() {
        return this.productOffId;
    }

    public void setProductOffId(long productOffId) {
        this.productOffId = productOffId;
    }

    public Date getRelationTime() {
        return this.relationTime;
    }

    public void setRelationTime(Date relationTime) {
        this.relationTime = relationTime;
    }

}
