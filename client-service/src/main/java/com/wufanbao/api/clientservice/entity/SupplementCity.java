package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// SupplementCity,城市补货详情
public class SupplementCity {
    //SupplementCitytId,
    private long supplementCitytId;
    //补货日期,
    private Date supplementday;
    //城市运营商,
    private long cityCompanyId;
    //商品定义,
    private long productGlobalId;
    //发货仓库,
    private long storeId;
    //补货总数,
    private int supplementQuantity;
    //库存数,
    private int storeQuantity;
    //库存更新时间,
    private Date storeUpdateTime;
    //预计可消耗天数,
    private int predictDay;
    //采购周期,
    private int purchaseFrequency;
    //调整比例,
    private int adjustRate;
    //是否已提交,
    private boolean isSubmit;
    //提交人,
    private long submitEmployeeId;
    //提交时间,
    private Date submitTime;

    public long getSupplementCitytId() {
        return this.supplementCitytId;
    }

    public void setSupplementCitytId(long supplementCitytId) {
        this.supplementCitytId = supplementCitytId;
    }

    public Date getSupplementday() {
        return this.supplementday;
    }

    public void setSupplementday(Date supplementday) {
        this.supplementday = supplementday;
    }

    public long getCityCompanyId() {
        return this.cityCompanyId;
    }

    public void setCityCompanyId(long cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
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

    public int getSupplementQuantity() {
        return this.supplementQuantity;
    }

    public void setSupplementQuantity(int supplementQuantity) {
        this.supplementQuantity = supplementQuantity;
    }

    public int getStoreQuantity() {
        return this.storeQuantity;
    }

    public void setStoreQuantity(int storeQuantity) {
        this.storeQuantity = storeQuantity;
    }

    public Date getStoreUpdateTime() {
        return this.storeUpdateTime;
    }

    public void setStoreUpdateTime(Date storeUpdateTime) {
        this.storeUpdateTime = storeUpdateTime;
    }

    public int getPredictDay() {
        return this.predictDay;
    }

    public void setPredictDay(int predictDay) {
        this.predictDay = predictDay;
    }

    public int getPurchaseFrequency() {
        return this.purchaseFrequency;
    }

    public void setPurchaseFrequency(int purchaseFrequency) {
        this.purchaseFrequency = purchaseFrequency;
    }

    public int getAdjustRate() {
        return this.adjustRate;
    }

    public void setAdjustRate(int adjustRate) {
        this.adjustRate = adjustRate;
    }

    public boolean getIsSubmit() {
        return this.isSubmit;
    }

    public void setIsSubmit(boolean isSubmit) {
        this.isSubmit = isSubmit;
    }

    public long getSubmitEmployeeId() {
        return this.submitEmployeeId;
    }

    public void setSubmitEmployeeId(long submitEmployeeId) {
        this.submitEmployeeId = submitEmployeeId;
    }

    public Date getSubmitTime() {
        return this.submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

}
