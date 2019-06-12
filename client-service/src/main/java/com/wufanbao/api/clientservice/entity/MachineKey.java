package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineKey,机器key管理
public class MachineKey {
    //MachineId,
    private long machineId;
    //OldKey,
    private String oldKey;
    //CurrentKey,
    private String currentKey;
    //ModifyTime,
    private Date modifyTime;
    //EffectTime,
    private Date effectTime;
    //InvalidTime,
    private Date invalidTime;

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public String getOldKey() {
        return this.oldKey;
    }

    public void setOldKey(String oldKey) {
        this.oldKey = oldKey;
    }

    public String getCurrentKey() {
        return this.currentKey;
    }

    public void setCurrentKey(String currentKey) {
        this.currentKey = currentKey;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getEffectTime() {
        return this.effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Date getInvalidTime() {
        return this.invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

}
