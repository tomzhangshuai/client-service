package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// AppInstallLog,app 安装日志
public class AppInstallLog {
    //DeviceId,
    private long deviceId;
    //InstallTime,
    private Date installTime;
    //Version,
    private String version;

    public long getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getInstallTime() {
        return this.installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
