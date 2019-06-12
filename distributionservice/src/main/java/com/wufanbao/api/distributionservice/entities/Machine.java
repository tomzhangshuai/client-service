package com.wufanbao.api.distributionservice.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Machine {
    //MachineId,
    private long machineId;
    //设备编号,
    private String machineNo;
    //设备型号,
    private String model;
    //设备名称,
    private String machineName;
    //生产日期,
    private Date productionDate;
    //关联城市运营商,
    private long cityCompanyId;
    //关联物流商,
    private long logisticCompanyId;
    //所属加盟商,
    private long joinCompanyId;
    //发货仓库,
    private long storeId;
    //记录添加时间,
    private Date createTime;
    //设备负责人,
    private long employeeId;
    //状态,
    private int status;
    //是否启用,
    private boolean isActive;
    //容量,
    private int capacity;
    //库存,
    private int quantity;
    //锁定库存,
    private int lockQuantity;
    //可用库存,
    private int useableQuantity;
    //机器投放Id,
    private long machinePutId;
    //投放名称,
    private String putMachineName;
    //投放区域,
    private int putAreaId;
    private String AreaName;
    //当前设备坐标x,
    private BigDecimal x;
    //当前设备坐标y,
    private BigDecimal y;
    //当前设备投放地址,
    private String address;
    //距离附近
    private double distance;
    //软件版本,
    private String softwareVersion;
    //机器激活码,
    private String activationCode;
    //寻找机器路径图片照片(json),
    private String seekPhotos;
    //是否维护中,
    private boolean inMaintenance;
    //是否可全额退款,
    private boolean fullRefund;

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public String getMachineNo() {
        return this.machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMachineName() {
        return this.machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Date getProductionDate() {
        return this.productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public long getCityCompanyId() {
        return this.cityCompanyId;
    }

    public void setCityCompanyId(long cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }

    public long getLogisticCompanyId() {
        return this.logisticCompanyId;
    }

    public void setLogisticCompanyId(long logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public long getJoinCompanyId() {
        return this.joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLockQuantity() {
        return this.lockQuantity;
    }

    public void setLockQuantity(int lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public int getUseableQuantity() {
        return this.useableQuantity;
    }

    public void setUseableQuantity(int useableQuantity) {
        this.useableQuantity = useableQuantity;
    }

    public long getMachinePutId() {
        return this.machinePutId;
    }

    public void setMachinePutId(long machinePutId) {
        this.machinePutId = machinePutId;
    }

    public String getPutMachineName() {
        return this.putMachineName;
    }

    public void setPutMachineName(String putMachineName) {
        this.putMachineName = putMachineName;
    }

    public int getPutAreaId() {
        return this.putAreaId;
    }

    public void setPutAreaId(int putAreaId) {
        this.putAreaId = putAreaId;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getActivationCode() {
        return this.activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getSeekPhotos() {
        return this.seekPhotos;
    }

    public void setSeekPhotos(String seekPhotos) {
        this.seekPhotos = seekPhotos;
    }

    public boolean getInMaintenance() {
        return this.inMaintenance;
    }

    public void setInMaintenance(boolean inMaintenance) {
        this.inMaintenance = inMaintenance;
    }

    public boolean getFullRefund() {
        return this.fullRefund;
    }

    public void setFullRefund(boolean fullRefund) {
        this.fullRefund = fullRefund;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }
}
