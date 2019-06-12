package com.wufanbao.api.oldclientservice.entity;

import java.sql.Date;

/**
 * User:Wangshihao
 * Date:2017-08-04
 * Time:17:28
 */
public class UserAuth {
    private long userId;//userid
    private int cardType;//1,ID,身份证2,DriverId,驾驶证3,Passport,护照
    private String cardNo;//证件号
    private String cardImages;//证件图片
    private Date CreateTime;//创建时间
    private Date updateTime;//修改时间

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardImages() {
        return cardImages;
    }

    public void setCardImages(String cardImages) {
        this.cardImages = cardImages;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
