package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// Activity,活动表
public class Activity {
    //ActivityId,
    private long activityId;
    //活动名称,
    private String activityName;
    //活动规则,
    private String activityRule;
    //活动开始时间,
    private Date activityBegin;
    //活动结束时间,
    private Date activityEnd;
    //创建时间,
    private Date createTime;
    //限制参与量,
    private int participate;
    //是否有效,
    private boolean isActive;
    //状态,
    private ActivityStatus status;

    public long getActivityId() {
        return this.activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityRule() {
        return this.activityRule;
    }

    public void setActivityRule(String activityRule) {
        this.activityRule = activityRule;
    }

    public Date getActivityBegin() {
        return this.activityBegin;
    }

    public void setActivityBegin(Date activityBegin) {
        this.activityBegin = activityBegin;
    }

    public Date getActivityEnd() {
        return this.activityEnd;
    }

    public void setActivityEnd(Date activityEnd) {
        this.activityEnd = activityEnd;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getParticipate() {
        return this.participate;
    }

    public void setParticipate(int participate) {
        this.participate = participate;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public ActivityStatus getStatus() {
        return this.status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

}
