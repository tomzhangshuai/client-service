package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineAlarm,机器报警记录
public class MachineAlarm {
    //MachineAlarmId,
    private long machineAlarmId;
    //机器,
    private long machineId;
    //机器报警码,
    private String machineAlarmCode;
    //X,
    private BigDecimal x;
    //Y,
    private BigDecimal y;
    //描述,
    private String description;
    //报警时间,
    private Date alarmTime;

    public long getMachineAlarmId() {
        return this.machineAlarmId;
    }

    public void setMachineAlarmId(long machineAlarmId) {
        this.machineAlarmId = machineAlarmId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public String getMachineAlarmCode() {
        return this.machineAlarmCode;
    }

    public void setMachineAlarmCode(String machineAlarmCode) {
        this.machineAlarmCode = machineAlarmCode;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAlarmTime() {
        return this.alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

}
