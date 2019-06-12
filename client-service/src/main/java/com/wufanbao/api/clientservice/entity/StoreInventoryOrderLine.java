package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// 盘存单行,
public class StoreInventoryOrderLine {
    //SalesOrderLineId,
    private long inventoryOrderLineId;
    //盘存单号,
    private long inventoryOrderId;
    //所属公司,
    private long companyId;
    //商品,
    private long productGlobalId;
    //批号,
    private String lotNo;
    //盈亏数,
    private int quantity;
    //系统数量,
    private int systemQuantity;
    //实盘数量,
    private int checkQuantity;
    //盈亏原因,
    private String remark;

    public long getInventoryOrderLineId() {
        return this.inventoryOrderLineId;
    }

    public void setInventoryOrderLineId(long inventoryOrderLineId) {
        this.inventoryOrderLineId = inventoryOrderLineId;
    }

    public long getInventoryOrderId() {
        return this.inventoryOrderId;
    }

    public void setInventoryOrderId(long inventoryOrderId) {
        this.inventoryOrderId = inventoryOrderId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public String getLotNo() {
        return this.lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSystemQuantity() {
        return this.systemQuantity;
    }

    public void setSystemQuantity(int systemQuantity) {
        this.systemQuantity = systemQuantity;
    }

    public int getCheckQuantity() {
        return this.checkQuantity;
    }

    public void setCheckQuantity(int checkQuantity) {
        this.checkQuantity = checkQuantity;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
