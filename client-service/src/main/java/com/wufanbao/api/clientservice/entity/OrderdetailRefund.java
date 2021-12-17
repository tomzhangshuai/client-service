package com.wufanbao.api.clientservice.entity;

import java.math.BigDecimal;

public class OrderdetailRefund {
    private long orderDetailRefundId;// '订单明细退款Id'
    private long orderRefundId;// '订单退款Id',
    private long orderId;// '订单Id',
    private long productGlobalId;// '产品Id',
    private int quantity;// '购买数量',
    private int actualRefundQuantity;// '实际退款数量',
    private BigDecimal price;          // '价格',
    private String remark;//  '备注'

    public long getOrderDetailRefundId() {
        return orderDetailRefundId;
    }

    public void setOrderDetailRefundId(long orderDetailRefundId) {
        this.orderDetailRefundId = orderDetailRefundId;
    }

    public long getOrderRefundId() {
        return orderRefundId;
    }

    public void setOrderRefundId(long orderRefundId) {
        this.orderRefundId = orderRefundId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getActualRefundQuantity() {
        return actualRefundQuantity;
    }

    public void setActualRefundQuantity(int actualRefundQuantity) {
        this.actualRefundQuantity = actualRefundQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
