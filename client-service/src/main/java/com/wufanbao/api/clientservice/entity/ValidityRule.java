package com.wufanbao.api.clientservice.entity;

import java.util.Date;

public class ValidityRule {
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private int activeTime;//天数

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(int activeTime) {
        this.activeTime = activeTime;
    }
}
