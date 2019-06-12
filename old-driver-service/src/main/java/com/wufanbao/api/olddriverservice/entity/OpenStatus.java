package com.wufanbao.api.olddriverservice.entity;

/**
 * User:Wangshihao
 * Date:2017-09-25
 * Time:14:29
 */
public class OpenStatus {
    private int supplementStatus;
    private long machineId;

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public int getSupplementStatus() {
        return supplementStatus;
    }

    public void setSupplementStatus(int supplementStatus) {
        this.supplementStatus = supplementStatus;
    }
}
