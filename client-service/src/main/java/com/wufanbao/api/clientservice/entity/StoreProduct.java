package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StoreProduct,商品库存
public class StoreProduct {
    //仓库,
    private long storeId;
    //所属公司,
    private long companyId;
    //商品,
    private long productGlobalId;
    //合格状态,
    private QualityStyle qualityStyleId;
    //批号,
    private String lotNo;
    //总数,
    private int quantity;
    //可用数量,
    private int useableQuantity;
    //锁定数量,
    private int lockQuantity;

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

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public QualityStyle getQualityStyleId() {
        return this.qualityStyleId;
    }

    public void setQualityStyleId(QualityStyle qualityStyleId) {
        this.qualityStyleId = qualityStyleId;
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

    public int getUseableQuantity() {
        return this.useableQuantity;
    }

    public void setUseableQuantity(int useableQuantity) {
        this.useableQuantity = useableQuantity;
    }

    public int getLockQuantity() {
        return this.lockQuantity;
    }

    public void setLockQuantity(int lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

}
