package com.wufanbao.api.distributionservice.transfer;

/**
 * 扫码信息
 */
public class SupplementOpenInfo {

    /**
     * 用户
     */
    private long employeeId;

    /**
     * 机器ID
     */
    private long machineId;

    /**
     * 补货单ID
     */
    private long supplementOrderId;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getSupplementOrderId() {
        return supplementOrderId;
    }

    public void setSupplementOrderId(long supplementOrderId) {
        this.supplementOrderId = supplementOrderId;
    }
}
