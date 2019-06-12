package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StoreProductInOut,仓库商品出入库明细
public class StoreProductInOut {
    //ProductInOutId,
    private long productInOutId;
    //商品ID,
    private long productGlobalId;
    //仓库id,
    private long storeId;
    //公司ID,
    private long companyId;
    //数量,
    private int quantity;
    //批号,
    private String lotNo;
    //入出库类型,
    private StoreInOutType inOutType;
    //源ID,
    private long sourceId;
    //源类型,
    private String sourceType;
    //备注,
    private String remark;
    //生成时间,
    private Date createTime;

    public long getProductInOutId() {
        return this.productInOutId;
    }

    public void setProductInOutId(long productInOutId) {
        this.productInOutId = productInOutId;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
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

    public String getLotNo() {
        return this.lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public StoreInOutType getInOutType() {
        return this.inOutType;
    }

    public void setInOutType(StoreInOutType inOutType) {
        this.inOutType = inOutType;
    }

    public long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
