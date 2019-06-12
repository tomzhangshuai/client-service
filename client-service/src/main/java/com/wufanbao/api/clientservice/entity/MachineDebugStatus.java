package com.wufanbao.api.clientservice.entity;

//MachineDebugStatus
//机器调试状态
public enum MachineDebugStatus {
    //已创建:1
    Created,
    //已提交:2
    Submited,
    //已确认:3
    Confirmed,
    //调试中:4
    Debugging,
    //已验收:5
    Validated,
    //已完成:6
    Completed,
    //失败:0
    Failed
}
