package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineOperation,设备操作记录
public class MachineOperation {
    //OperationId,
    private long operationId;
    //机器ID,
    private long machineId;
    //操作类型,
    private MachineOperateType operateType;
    //操作描述,
    private String description;
    //操作人,
    private long operator;
    //操作人姓名,
    private String operatorName;
    //操作人类型,
    private SystemUserType operatorUserType;
    //操作时间,
    private Date operat;

    public long getOperationId() {
        return this.operationId;
    }

    public void setOperationId(long operationId) {
        this.operationId = operationId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public MachineOperateType getOperateType() {
        return this.operateType;
    }

    public void setOperateType(MachineOperateType operateType) {
        this.operateType = operateType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getOperator() {
        return this.operator;
    }

    public void setOperator(long operator) {
        this.operator = operator;
    }

    public String getOperatorName() {
        return this.operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public SystemUserType getOperatorUserType() {
        return this.operatorUserType;
    }

    public void setOperatorUserType(SystemUserType operatorUserType) {
        this.operatorUserType = operatorUserType;
    }

    public Date getOperat() {
        return this.operat;
    }

    public void setOperat(Date operat) {
        this.operat = operat;
    }

}
