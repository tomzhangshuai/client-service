package com.wufanbao.api.olddriverservice.entity;

/**
 * User:Wangshihao
 * Date:2017-09-13
 * Time:15:18
 */
public class OpenStorehouse {
    private String machineNo;

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    private long machineId;
    private long supplementOrderId;
    private int supplementStatus;

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

    public int getSupplementStatus() {
        return supplementStatus;
    }

    public void setSupplementStatus(int supplementStatus) {
        this.supplementStatus = supplementStatus;
    }
}
