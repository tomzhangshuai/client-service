package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachinePut,机器投放记录
public class MachinePut {
    //MachinePutId,
    private long machinePutId;
    //MachineId,
    private long machineId;
    //投放人,
    private long putEmployeeId;
    //投放时间,
    private Date putTime;
    //审核人,
    private long auditManagerId;
    //审核时间,
    private Date auditTime;
    //加盟商,
    private long joinComanyId;
    //物流商,
    private long logisticCompanyId;
    //运营商,
    private long cityCompanyId;
    //发货仓库,
    private long storeId;
    //物业公司名称,
    private String propertiesCompanyName;
    //物业联系电话,
    private String propertiesCompanyPhone;
    //物业协议PDFUrl,
    private String propertiesAgreement;
    //投放区域,
    private int putAreaId;
    //投放地址,
    private String address;
    //坐标X,
    private BigDecimal x;
    //坐标Y,
    private BigDecimal y;
    //投放区域照片(json),
    private String areaPhotos;
    //寻找机器路径图片照片(json),
    private String seekPhotos;
    //状态,
    private AuditState auditStatus;
    //备注,
    private String remark;
    //是否配送限制,
    private boolean isLimited;
    //允许开始配送时间,
    private Date allowBeginTime;
    //允许结束配送时间,
    private Date allowEndTime;
    //车辆限高(cm),
    private int truckLimitHeight;
    //进出路口,
    private String inOutDetail;
    //投放机器名称,
    private String putMachineName;
    //机器负责人,
    private long employeeId;
    //加盟商投放合同,
    private long contractId;

    public long getMachinePutId() {
        return this.machinePutId;
    }

    public void setMachinePutId(long machinePutId) {
        this.machinePutId = machinePutId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getPutEmployeeId() {
        return this.putEmployeeId;
    }

    public void setPutEmployeeId(long putEmployeeId) {
        this.putEmployeeId = putEmployeeId;
    }

    public Date getPutTime() {
        return this.putTime;
    }

    public void setPutTime(Date putTime) {
        this.putTime = putTime;
    }

    public long getAuditManagerId() {
        return this.auditManagerId;
    }

    public void setAuditManagerId(long auditManagerId) {
        this.auditManagerId = auditManagerId;
    }

    public Date getAuditTime() {
        return this.auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public long getJoinComanyId() {
        return this.joinComanyId;
    }

    public void setJoinComanyId(long joinComanyId) {
        this.joinComanyId = joinComanyId;
    }

    public long getLogisticCompanyId() {
        return this.logisticCompanyId;
    }

    public void setLogisticCompanyId(long logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public long getCityCompanyId() {
        return this.cityCompanyId;
    }

    public void setCityCompanyId(long cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getPropertiesCompanyName() {
        return this.propertiesCompanyName;
    }

    public void setPropertiesCompanyName(String propertiesCompanyName) {
        this.propertiesCompanyName = propertiesCompanyName;
    }

    public String getPropertiesCompanyPhone() {
        return this.propertiesCompanyPhone;
    }

    public void setPropertiesCompanyPhone(String propertiesCompanyPhone) {
        this.propertiesCompanyPhone = propertiesCompanyPhone;
    }

    public String getPropertiesAgreement() {
        return this.propertiesAgreement;
    }

    public void setPropertiesAgreement(String propertiesAgreement) {
        this.propertiesAgreement = propertiesAgreement;
    }

    public int getPutAreaId() {
        return this.putAreaId;
    }

    public void setPutAreaId(int putAreaId) {
        this.putAreaId = putAreaId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getX() {
        return this.x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return this.y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public String getAreaPhotos() {
        return this.areaPhotos;
    }

    public void setAreaPhotos(String areaPhotos) {
        this.areaPhotos = areaPhotos;
    }

    public String getSeekPhotos() {
        return this.seekPhotos;
    }

    public void setSeekPhotos(String seekPhotos) {
        this.seekPhotos = seekPhotos;
    }

    public AuditState getAuditStatus() {
        return this.auditStatus;
    }

    public void setAuditStatus(AuditState auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean getIsLimited() {
        return this.isLimited;
    }

    public void setIsLimited(boolean isLimited) {
        this.isLimited = isLimited;
    }

    public Date getAllowBeginTime() {
        return this.allowBeginTime;
    }

    public void setAllowBeginTime(Date allowBeginTime) {
        this.allowBeginTime = allowBeginTime;
    }

    public Date getAllowEndTime() {
        return this.allowEndTime;
    }

    public void setAllowEndTime(Date allowEndTime) {
        this.allowEndTime = allowEndTime;
    }

    public int getTruckLimitHeight() {
        return this.truckLimitHeight;
    }

    public void setTruckLimitHeight(int truckLimitHeight) {
        this.truckLimitHeight = truckLimitHeight;
    }

    public String getInOutDetail() {
        return this.inOutDetail;
    }

    public void setInOutDetail(String inOutDetail) {
        this.inOutDetail = inOutDetail;
    }

    public String getPutMachineName() {
        return this.putMachineName;
    }

    public void setPutMachineName(String putMachineName) {
        this.putMachineName = putMachineName;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getContractId() {
        return this.contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

}
