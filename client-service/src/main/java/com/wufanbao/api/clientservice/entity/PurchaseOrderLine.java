package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// PurchaseOrderLine,
public class PurchaseOrderLine {
    //PurchaseOrderLineId,
    private long purchaseOrderLineId;
    //PurchaseOrderId,
    private long purchaseOrderId;
    //商品,
    private long productGlobalId;
    //买家,
    private long companyId;
    //数量,
    private int quantity;
    //单价,
    private int price;
    //备注,
    private String remark;
    //实收数量,
    private int actualQuantity;
    //商品名称,
    private String productName;
    //箱内份数,
    private int boxNum;
    //箱数,
    private int boxQuantity;

    public long getPurchaseOrderLineId() {
        return this.purchaseOrderLineId;
    }

    public void setPurchaseOrderLineId(long purchaseOrderLineId) {
        this.purchaseOrderLineId = purchaseOrderLineId;
    }

    public long getPurchaseOrderId() {
        return this.purchaseOrderId;
    }

    public void setPurchaseOrderId(long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getActualQuantity() {
        return this.actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBoxNum() {
        return this.boxNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
    }

    public int getBoxQuantity() {
        return this.boxQuantity;
    }

    public void setBoxQuantity(int boxQuantity) {
        this.boxQuantity = boxQuantity;
    }

}
