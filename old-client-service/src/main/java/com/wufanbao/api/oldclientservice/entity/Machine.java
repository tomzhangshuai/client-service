package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Machine {
    private long machineId;//机器表id
    private String machineNo;//设备编号
    private String model;//设备型号
    private String machineName;//设备名称
    private Date productionDate;//生产日期
    private long cityCompanyId;//所属运营商
    private long logisticCompanyId;//关联物流商
    private long joinCompanyId;//关联加盟商
    private Date createTime;//记录添加时间
    private long employeeId;//设备负责人
    private long machinePutId;//机器投放id
    private int status;//状态
    private Boolean isActive;//是否启用;
    private BigDecimal x;//经线
    private BigDecimal y;//纬线
    private String address;//当前设备投放地址
    private int capacity;//容量
    private int quantity;//库存
    private int lockQuantity;//锁定库存
    private int useableQuantity;//可用库存
    private long storeId;//商店id
    private double distance;//用户和机器的距离
    private boolean inMaintenance;//是否维护中
    private String areaName;
    private int fullRefund;

    public int getFullRefund() {
        return fullRefund;
    }

    public void setFullRefund(int fullRefund) {
        this.fullRefund = fullRefund;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    private int machineState;
    private boolean machineIsNull;

    public boolean isMachineIsNull() {
        return machineIsNull;
    }

    public void setMachineIsNull(boolean machineIsNull) {
        this.machineIsNull = machineIsNull;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public long getCityCompanyId() {
        return cityCompanyId;
    }

    public void setCityCompanyId(long cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }

    public long getLogisticCompanyId() {
        return logisticCompanyId;
    }

    public void setLogisticCompanyId(long logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public long getJoinCompanyId() {
        return joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getMachinePutId() {
        return machinePutId;
    }

    public void setMachinePutId(long machinePutId) {
        this.machinePutId = machinePutId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(int lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public int getUseableQuantity() {
        return useableQuantity;
    }

    public void setUseableQuantity(int useableQuantity) {
        this.useableQuantity = useableQuantity;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isInMaintenance() {
        return inMaintenance;
    }

    public void setInMaintenance(boolean inMaintenance) {
        this.inMaintenance = inMaintenance;
    }

    public int getMachineState() {
        return machineState;
    }

    public void setMachineState(int machineState) {
        this.machineState = machineState;
    }
}
