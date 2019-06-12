package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StoreOrderInLine,仓库入库明细
public class StoreOrderInLine {
    //StoreOrderInLineId,
    private long storeOrderInLineId;
    //批号,
    private String lotNo;
    //商品ID,
    private long productGlobalId;
    //公司ID,
    private long companyId;
    //仓库id,
    private long storeId;
    //数量,
    private int quantity;
    //StoreOrderInId,
    private long storeOrderInId;
    //备注,
    private String remark;

    public long getStoreOrderInLineId() {
        return this.storeOrderInLineId;
    }

    public void setStoreOrderInLineId(long storeOrderInLineId) {
        this.storeOrderInLineId = storeOrderInLineId;
    }

    public String getLotNo() {
        return this.lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
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

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getStoreOrderInId() {
        return this.storeOrderInId;
    }

    public void setStoreOrderInId(long storeOrderInId) {
        this.storeOrderInId = storeOrderInId;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
