package com.wufanbao.api.olddriverservice.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User:Wangshihao
 * Date:2017-09-07
 * Time:13:44
 * 设备
 */
public class Machine {
    private long machineId;//MachineId
    private String machineNo;//设备编号
    private String model;//设备型号
    private String machineName;//设备名称
    private Date productionDate;//生产日期
    private long cityCompanyId;//所属城市运营商
    private long logisticCompanyId;//关联物流商
    private long joinCompanyId;//关联加盟商
    private long storeId;//发货仓库
    private Date createTime;//记录添加时间
    private long employeeId;//设备负责人
    private long machinePutId;//机器投放Id
    /**
     * 状态
     * 1,Commissioned,未分配
     * 2,Allotted,已分配
     * 3,PrePut,待审核
     * 4,NotAcitive,未激活
     * 5,Testing,试运行
     * 6,Standby,未运行
     * 7,Running,运行中
     * 8,Retired,已回收
     */
    private Integer status;//状态
    private Integer isActive;//是否启用
    private BigDecimal x;//当前设备坐标x
    private BigDecimal y;//当前设备坐标y
    private String address;//当前设备投放地址
    private Integer capacity;//容量
    private Integer quantity;//库存
    private Integer lockQuantity;//锁定库存
    private Integer useableQuantity;//可用库存
    private Integer putAreaId;//投放区域

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

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(Integer lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public Integer getUseableQuantity() {
        return useableQuantity;
    }

    public void setUseableQuantity(Integer useableQuantity) {
        this.useableQuantity = useableQuantity;
    }

    public Integer getPutAreaId() {
        return putAreaId;
    }

    public void setPutAreaId(Integer putAreaId) {
        this.putAreaId = putAreaId;
    }
}
