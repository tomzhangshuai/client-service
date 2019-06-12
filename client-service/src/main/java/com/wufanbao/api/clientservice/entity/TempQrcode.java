package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// TempQrcode,临时二维码
public class TempQrcode {
    //TempId,
    private long tempId;
    //Phone,
    private String phone;
    //Qrcode,
    private String qrcode;
    //UserId,
    private long userId;
    //Used,
    private boolean used;
    //UseTime,
    private Date useTime;

    public long getTempId() {
        return this.tempId;
    }

    public void setTempId(long tempId) {
        this.tempId = tempId;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQrcode() {
        return this.qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean getUsed() {
        return this.used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getUseTime() {
        return this.useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

}
