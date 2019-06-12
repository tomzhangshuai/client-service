package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserBinding,第三方用户绑定表
public class UserBinding {
    //UserId,
    private long userId;
    //BindingId,
    private String bindingId;
    //openId,
    private String opneId;
    //BindingType,
    private int bindingType;
    //BindingTime,
    private Date bindingTime;

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBindingId() {
        return this.bindingId;
    }

    public void setBindingId(String bindingId) {
        this.bindingId = bindingId;
    }

    public String getOpneId() {
        return opneId;
    }

    public void setOpneId(String opneId) {
        this.opneId = opneId;
    }

    public int getBindingType() {
        return this.bindingType;
    }

    public void setBindingType(int bindingType) {
        this.bindingType = bindingType;
    }

    public Date getBindingTime() {
        return this.bindingTime;
    }

    public void setBindingTime(Date bindingTime) {
        this.bindingTime = bindingTime;
    }

    @Override
    public String toString() {
        return "UserBinding{" +
                "userId=" + userId +
                '}';
    }
}
