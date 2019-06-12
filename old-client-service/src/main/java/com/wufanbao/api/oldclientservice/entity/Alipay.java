package com.wufanbao.api.oldclientservice.entity;

/**
 * User:Wangshihao
 * Date:2017-09-14
 * Time:10:08
 */
public class Alipay {
    private long userId;
    private long userOrderId;

    public long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
