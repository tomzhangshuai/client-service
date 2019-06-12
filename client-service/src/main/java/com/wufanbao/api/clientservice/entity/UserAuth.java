package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserAuth,实名认证信息
public class UserAuth {
    //UserId,
    private long userId;
    //证件类型,
    private CardType cardType;
    //证件号,
    private String cardNo;
    //证件图片,
    private String cardImages;
    //创建时间,
    private Date createTime;
    //修改时间,
    private Date updateTime;

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public CardType getCardType() {
        return this.cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardImages() {
        return this.cardImages;
    }

    public void setCardImages(String cardImages) {
        this.cardImages = cardImages;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
