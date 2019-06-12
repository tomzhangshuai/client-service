package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// AppDevice,APP设备
public class AppDevice {
    //DeviceId,
    private long deviceId;
    //设备类型,
    private int deviceType;
    //唯一ID,
    private String uniqueId;
    //设备主型号,
    private String mode;
    //创建时间,
    private Date createTime;

    public long getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
