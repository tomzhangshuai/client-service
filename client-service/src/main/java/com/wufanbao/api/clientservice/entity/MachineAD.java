package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineAD,机器广告
public class MachineAD {
    //MachineADId,
    private long machineADId;
    //广告名称,
    private String aDName;
    //广告源,
    private String aDSource;
    //广告资源地址,
    private String aDUrl;
    //广告格式(图片、GIF、视频）,
    private MachineADType aDType;
    //广告时长(秒),
    private int duration;
    //间隔停留时间(秒),
    private int stay;
    //开始播放日期,
    private Date beginPlayTime;
    //结束播放日期,
    private Date endPlayTime;
    //单循环播放次数,
    private int times;
    //投放区域,
    private int putAreaId;
    //投放机器,
    private long machineId;
    //是否有效,
    private boolean isActive;

    public long getMachineADId() {
        return this.machineADId;
    }

    public void setMachineADId(long machineADId) {
        this.machineADId = machineADId;
    }

    public String getADName() {
        return this.aDName;
    }

    public void setADName(String aDName) {
        this.aDName = aDName;
    }

    public String getADSource() {
        return this.aDSource;
    }

    public void setADSource(String aDSource) {
        this.aDSource = aDSource;
    }

    public String getADUrl() {
        return this.aDUrl;
    }

    public void setADUrl(String aDUrl) {
        this.aDUrl = aDUrl;
    }

    public MachineADType getADType() {
        return this.aDType;
    }

    public void setADType(MachineADType aDType) {
        this.aDType = aDType;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStay() {
        return this.stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public Date getBeginPlayTime() {
        return this.beginPlayTime;
    }

    public void setBeginPlayTime(Date beginPlayTime) {
        this.beginPlayTime = beginPlayTime;
    }

    public Date getEndPlayTime() {
        return this.endPlayTime;
    }

    public void setEndPlayTime(Date endPlayTime) {
        this.endPlayTime = endPlayTime;
    }

    public int getTimes() {
        return this.times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getPutAreaId() {
        return this.putAreaId;
    }

    public void setPutAreaId(int putAreaId) {
        this.putAreaId = putAreaId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
