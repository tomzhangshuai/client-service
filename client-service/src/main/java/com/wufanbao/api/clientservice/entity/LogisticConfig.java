package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// LogisticConfig,物流商配置
public class LogisticConfig {
    //LogisticCompanyId,
    private long logisticCompanyId;
    //配送最低起送数,
    private int startUpNum;
    //配送频次（天）,
    private int autoDays;
    //配送最低库存,
    private int autoLimit;
    //自动生成补货单时间,
    private Date autoCreateTime;
    //最后确认补货单时间,
    private Date lastConfirmTime;
    //支线物流开始配送时间,
    private Date distributionTime;
    //支线物流结束配送时间,
    private Date distirbutedTime;
    //采购周期(天),
    private int purchasingCycle;
    //干线物流工厂生产天数,
    private int produceDays;
    //干线物流运输天数,
    private int transportDays;
    //可能异常情况附加天数,
    private int additionalDays;
    //销量波动百分比,
    private int wavePercent;
    //商品过期前预警天数,
    private int warnDays;
    //日均消耗计算周期,
    private int computeConsumeCycle;
    //配送部门,
    private long distributionDepartmentId;
    //驾驶部门,
    private long driveDepartmentId;
    //采购部门,
    private long purchaseDepartmentId;
    //仓储部门,
    private long storageDepartmentId;

    public long getLogisticCompanyId() {
        return this.logisticCompanyId;
    }

    public void setLogisticCompanyId(long logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public int getStartUpNum() {
        return this.startUpNum;
    }

    public void setStartUpNum(int startUpNum) {
        this.startUpNum = startUpNum;
    }

    public int getAutoDays() {
        return this.autoDays;
    }

    public void setAutoDays(int autoDays) {
        this.autoDays = autoDays;
    }

    public int getAutoLimit() {
        return this.autoLimit;
    }

    public void setAutoLimit(int autoLimit) {
        this.autoLimit = autoLimit;
    }

    public Date getAutoCreateTime() {
        return this.autoCreateTime;
    }

    public void setAutoCreateTime(Date autoCreateTime) {
        this.autoCreateTime = autoCreateTime;
    }

    public Date getLastConfirmTime() {
        return this.lastConfirmTime;
    }

    public void setLastConfirmTime(Date lastConfirmTime) {
        this.lastConfirmTime = lastConfirmTime;
    }

    public Date getDistributionTime() {
        return this.distributionTime;
    }

    public void setDistributionTime(Date distributionTime) {
        this.distributionTime = distributionTime;
    }

    public Date getDistirbutedTime() {
        return this.distirbutedTime;
    }

    public void setDistirbutedTime(Date distirbutedTime) {
        this.distirbutedTime = distirbutedTime;
    }

    public int getPurchasingCycle() {
        return this.purchasingCycle;
    }

    public void setPurchasingCycle(int purchasingCycle) {
        this.purchasingCycle = purchasingCycle;
    }

    public int getProduceDays() {
        return this.produceDays;
    }

    public void setProduceDays(int produceDays) {
        this.produceDays = produceDays;
    }

    public int getTransportDays() {
        return this.transportDays;
    }

    public void setTransportDays(int transportDays) {
        this.transportDays = transportDays;
    }

    public int getAdditionalDays() {
        return this.additionalDays;
    }

    public void setAdditionalDays(int additionalDays) {
        this.additionalDays = additionalDays;
    }

    public int getWavePercent() {
        return this.wavePercent;
    }

    public void setWavePercent(int wavePercent) {
        this.wavePercent = wavePercent;
    }

    public int getWarnDays() {
        return this.warnDays;
    }

    public void setWarnDays(int warnDays) {
        this.warnDays = warnDays;
    }

    public int getComputeConsumeCycle() {
        return this.computeConsumeCycle;
    }

    public void setComputeConsumeCycle(int computeConsumeCycle) {
        this.computeConsumeCycle = computeConsumeCycle;
    }

    public long getDistributionDepartmentId() {
        return this.distributionDepartmentId;
    }

    public void setDistributionDepartmentId(long distributionDepartmentId) {
        this.distributionDepartmentId = distributionDepartmentId;
    }

    public long getDriveDepartmentId() {
        return this.driveDepartmentId;
    }

    public void setDriveDepartmentId(long driveDepartmentId) {
        this.driveDepartmentId = driveDepartmentId;
    }

    public long getPurchaseDepartmentId() {
        return this.purchaseDepartmentId;
    }

    public void setPurchaseDepartmentId(long purchaseDepartmentId) {
        this.purchaseDepartmentId = purchaseDepartmentId;
    }

    public long getStorageDepartmentId() {
        return this.storageDepartmentId;
    }

    public void setStorageDepartmentId(long storageDepartmentId) {
        this.storageDepartmentId = storageDepartmentId;
    }

}
