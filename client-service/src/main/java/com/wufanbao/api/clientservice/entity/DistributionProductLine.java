package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// DistributionProductLine,配送单商品明细表
public class DistributionProductLine {
    //DistributionOrderId,
    private long distributionOrderId;
    //ProductGlobalId,
    private long productGlobalId;
    //配送发货总数量,
    private int quantity;
    //补货单计划配送总数量,
    private int planQuantity;
    //额外配送发货数量,
    private int extraQuantity;
    //实际上架数量,
    private int actualQuantity;
    //剩余数量,
    private int surplusQuantity;
    //实际入库数量,
    private int storeQuantity;
    //备注,
    private String remark;
    //异常数,
    private int exceptionQuantity;

    public long getDistributionOrderId() {
        return this.distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPlanQuantity() {
        return this.planQuantity;
    }

    public void setPlanQuantity(int planQuantity) {
        this.planQuantity = planQuantity;
    }

    public int getExtraQuantity() {
        return this.extraQuantity;
    }

    public void setExtraQuantity(int extraQuantity) {
        this.extraQuantity = extraQuantity;
    }

    public int getActualQuantity() {
        return this.actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public int getSurplusQuantity() {
        return this.surplusQuantity;
    }

    public void setSurplusQuantity(int surplusQuantity) {
        this.surplusQuantity = surplusQuantity;
    }

    public int getStoreQuantity() {
        return this.storeQuantity;
    }

    public void setStoreQuantity(int storeQuantity) {
        this.storeQuantity = storeQuantity;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getExceptionQuantity() {
        return this.exceptionQuantity;
    }

    public void setExceptionQuantity(int exceptionQuantity) {
        this.exceptionQuantity = exceptionQuantity;
    }

}
