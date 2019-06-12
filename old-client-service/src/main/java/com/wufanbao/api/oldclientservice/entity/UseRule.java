package com.wufanbao.api.oldclientservice.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

public class UseRule {
    //用户商品Id
    private long productGlobalId;
    //早中晚的开始时间
    @JSONField(format = "mm:ss")
    private Date startTime;
    //早中晚的结束时间
    @JSONField(format = "mm:ss")
    private Date endTime;
    //满多少进而
    private BigDecimal qualifiedMoney;
    //区域id
    private long areaId;

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getQualifiedMoney() {
        return qualifiedMoney;
    }

    public void setQualifiedMoney(BigDecimal qualifiedMoney) {
        this.qualifiedMoney = qualifiedMoney;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
}
