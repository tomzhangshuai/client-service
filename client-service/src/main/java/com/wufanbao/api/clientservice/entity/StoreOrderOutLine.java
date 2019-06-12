package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StoreOrderOutLine,仓库出库明细
public class StoreOrderOutLine {
    //StoreOrderOutLineId,
    private long storeOrderOutLineId;
    //StoreOrderOurtId,
    private long storeOrderOutId;
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
    //备注,
    private String remark;

    public long getStoreOrderOutLineId() {
        return this.storeOrderOutLineId;
    }

    public void setStoreOrderOutLineId(long storeOrderOutLineId) {
        this.storeOrderOutLineId = storeOrderOutLineId;
    }

    public long getStoreOrderOutId() {
        return this.storeOrderOutId;
    }

    public void setStoreOrderOutId(long storeOrderOutId) {
        this.storeOrderOutId = storeOrderOutId;
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

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
