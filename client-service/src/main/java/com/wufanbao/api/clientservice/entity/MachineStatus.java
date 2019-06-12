package com.wufanbao.api.clientservice.entity;

//MachineStatus
//机器状态
public enum MachineStatus {
    //未分配:1
    Commissioned,
    //已分配:2
    Allotted,
    //待审核:3
    PrePut,
    //未激活:4
    NotAcitive,
    //试运行:5
    Testing,
    //未运行:6
    Standby,
    //运行中:7
    Running,
    //已回收:8
    Retired
}
