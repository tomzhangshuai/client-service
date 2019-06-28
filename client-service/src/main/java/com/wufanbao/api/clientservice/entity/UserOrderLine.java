package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserOrderLine,用户订单明细
public class UserOrderLine {
    //UserOrderId,
    private long userOrderId;
    //商品,
    private long productGlobalId;
    //购买数量,
    private int quantity;
    //取餐数量,
    private int actualQuantity;
    //下架数量
    private int offQuantity;
    //单价,
    private BigDecimal price;
    //备注,
    private String remark;
    //美味程度,
    private int tastyDegree;
    //评价,
    private String evaluation;

    public int getOffQuantity() {
        return offQuantity;
    }

    public void setOffQuantity(int offQuantity) {
        this.offQuantity = offQuantity;
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

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getActualQuantity() {
        return this.actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTastyDegree() {
        return this.tastyDegree;
    }

    public void setTastyDegree(int tastyDegree) {
        this.tastyDegree = tastyDegree;
    }

    public String getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

}
