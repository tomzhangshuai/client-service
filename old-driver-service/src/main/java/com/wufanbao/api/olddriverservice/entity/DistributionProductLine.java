package com.wufanbao.api.olddriverservice.entity;

/**
 * User:Wangshihao
 * Date:2017-09-07
 * Time:15:47
 */
public class DistributionProductLine {
    private long distributionOrderId;//用户订单号
    private long productGlobalId;//商品id
    private Integer quantity;//配送发货数量
    private Integer planQuantity;//补货单计划配送总数量
    private Integer ExtraQuantity;//额外配送发货数量
    private Integer actualQuantity;//实际上架数量
    private Integer surplusQuantity;//返仓剩
    private Integer exceptionQuantity;//异常数量

    public Integer getExceptionQuantity() {
        return exceptionQuantity;
    }

    public void setExceptionQuantity(Integer exceptionQuantity) {
        this.exceptionQuantity = exceptionQuantity;
    }

    public Integer getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(Integer planQuantity) {
        this.planQuantity = planQuantity;
    }

    public Integer getExtraQuantity() {
        return ExtraQuantity;
    }

    public void setExtraQuantity(Integer extraQuantity) {
        ExtraQuantity = extraQuantity;
    }

    public long getDistributionOrderId() {
        return distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(Integer actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public Integer getSurplusQuantity() {
        return surplusQuantity;
    }

    public void setSurplusQuantity(Integer surplusQuantity) {
        this.surplusQuantity = surplusQuantity;
    }
}
