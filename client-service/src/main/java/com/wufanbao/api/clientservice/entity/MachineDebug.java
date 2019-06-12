package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineDebug,机器上线调试
public class MachineDebug {
    //MachineDebugId,
    private long machineDebugId;
    //MachineId,
    private long machineId;
    //城市运营商负责人,
    private long cityEmployeeId;
    //加盟商负责人,
    private long joinEmployeeId;
    //加盟商负责人C端账号,
    private String joinUserLoginNo;
    //计划安装时间,
    private Date planInstallTime;
    //计划上线时间,
    private Date planOnlineTime;
    //计划调试时间,
    private Date planDebugTime;
    //物流商配送负责人,
    private long logisticEmployeeId;
    //商品名称,
    private long productGlobalId;
    //商品数量=4,
    private int quantity;
    //实际商品出库,
    private int outQuantity;
    //状态,
    private MachineDebugStatus status;
    //城市运营商创建时间,
    private Date createTime;
    //城市运营商创建人员,
    private long createEmployeeId;
    //城市运营商提交时间,
    private Date submitTime;
    //城市运营商提交人员,
    private long submitEmployeeId;
    //物流商确认时间,
    private Date confirmTime;
    //物流商确认操作人员,
    private long confirmEmployeeId;
    //物流配送单,
    private long distributionOrderId;
    //物流商取货时间,
    private Date fetchTime;
    //安装完成时间,
    private Date installedTime;
    //商品上架时间,
    private Date shelveTime;
    //验收图片,
    private String validatedImages;
    //加盟商验收时间,
    private Date validateTime;
    //备注,
    private String remark;
    //安装评价,
    private int installEvaluate;
    //调试评价,
    private int debugEvaluate;
    //机位图,
    private String seekPhotos;
    //调试单扫描件,
    private String debugBill;

    public long getMachineDebugId() {
        return this.machineDebugId;
    }

    public void setMachineDebugId(long machineDebugId) {
        this.machineDebugId = machineDebugId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getCityEmployeeId() {
        return this.cityEmployeeId;
    }

    public void setCityEmployeeId(long cityEmployeeId) {
        this.cityEmployeeId = cityEmployeeId;
    }

    public long getJoinEmployeeId() {
        return this.joinEmployeeId;
    }

    public void setJoinEmployeeId(long joinEmployeeId) {
        this.joinEmployeeId = joinEmployeeId;
    }

    public String getJoinUserLoginNo() {
        return this.joinUserLoginNo;
    }

    public void setJoinUserLoginNo(String joinUserLoginNo) {
        this.joinUserLoginNo = joinUserLoginNo;
    }

    public Date getPlanInstallTime() {
        return this.planInstallTime;
    }

    public void setPlanInstallTime(Date planInstallTime) {
        this.planInstallTime = planInstallTime;
    }

    public Date getPlanOnlineTime() {
        return this.planOnlineTime;
    }

    public void setPlanOnlineTime(Date planOnlineTime) {
        this.planOnlineTime = planOnlineTime;
    }

    public Date getPlanDebugTime() {
        return this.planDebugTime;
    }

    public void setPlanDebugTime(Date planDebugTime) {
        this.planDebugTime = planDebugTime;
    }

    public long getLogisticEmployeeId() {
        return this.logisticEmployeeId;
    }

    public void setLogisticEmployeeId(long logisticEmployeeId) {
        this.logisticEmployeeId = logisticEmployeeId;
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

    public int getOutQuantity() {
        return this.outQuantity;
    }

    public void setOutQuantity(int outQuantity) {
        this.outQuantity = outQuantity;
    }

    public MachineDebugStatus getStatus() {
        return this.status;
    }

    public void setStatus(MachineDebugStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getCreateEmployeeId() {
        return this.createEmployeeId;
    }

    public void setCreateEmployeeId(long createEmployeeId) {
        this.createEmployeeId = createEmployeeId;
    }

    public Date getSubmitTime() {
        return this.submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public long getSubmitEmployeeId() {
        return this.submitEmployeeId;
    }

    public void setSubmitEmployeeId(long submitEmployeeId) {
        this.submitEmployeeId = submitEmployeeId;
    }

    public Date getConfirmTime() {
        return this.confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public long getConfirmEmployeeId() {
        return this.confirmEmployeeId;
    }

    public void setConfirmEmployeeId(long confirmEmployeeId) {
        this.confirmEmployeeId = confirmEmployeeId;
    }

    public long getDistributionOrderId() {
        return this.distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public Date getFetchTime() {
        return this.fetchTime;
    }

    public void setFetchTime(Date fetchTime) {
        this.fetchTime = fetchTime;
    }

    public Date getInstalledTime() {
        return this.installedTime;
    }

    public void setInstalledTime(Date installedTime) {
        this.installedTime = installedTime;
    }

    public Date getShelveTime() {
        return this.shelveTime;
    }

    public void setShelveTime(Date shelveTime) {
        this.shelveTime = shelveTime;
    }

    public String getValidatedImages() {
        return this.validatedImages;
    }

    public void setValidatedImages(String validatedImages) {
        this.validatedImages = validatedImages;
    }

    public Date getValidateTime() {
        return this.validateTime;
    }

    public void setValidateTime(Date validateTime) {
        this.validateTime = validateTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getInstallEvaluate() {
        return this.installEvaluate;
    }

    public void setInstallEvaluate(int installEvaluate) {
        this.installEvaluate = installEvaluate;
    }

    public int getDebugEvaluate() {
        return this.debugEvaluate;
    }

    public void setDebugEvaluate(int debugEvaluate) {
        this.debugEvaluate = debugEvaluate;
    }

    public String getSeekPhotos() {
        return this.seekPhotos;
    }

    public void setSeekPhotos(String seekPhotos) {
        this.seekPhotos = seekPhotos;
    }

    public String getDebugBill() {
        return this.debugBill;
    }

    public void setDebugBill(String debugBill) {
        this.debugBill = debugBill;
    }

}
