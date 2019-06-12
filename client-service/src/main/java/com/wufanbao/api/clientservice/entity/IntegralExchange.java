package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// IntegralExchange,用户积分兑换表
public class IntegralExchange {
    //IntegralExchangeId,
    private long integralExchangeId;
    //IntegralCommodityId,
    private long integralCommodityId;
    //UserId,
    private long userId;
    //Amount,
    private BigDecimal amount;
    //Description,
    private String description;
    //CreateTime,
    private Date createTime;

    public long getIntegralExchangeId() {
        return this.integralExchangeId;
    }

    public void setIntegralExchangeId(long integralExchangeId) {
        this.integralExchangeId = integralExchangeId;
    }

    public long getIntegralCommodityId() {
        return this.integralCommodityId;
    }

    public void setIntegralCommodityId(long integralCommodityId) {
        this.integralCommodityId = integralCommodityId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
