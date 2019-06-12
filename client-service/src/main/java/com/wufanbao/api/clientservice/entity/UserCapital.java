package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserCapital,用户资金池
public class UserCapital {
    //UserCapitalId,
    private long userCapitalId;
    //创建时间,
    private Date createtime;
    //总金额,
    private BigDecimal totalAmount;
    //余额,
    private BigDecimal blance;
    //顺序(同时创建时,以顺序排序),
    private int turn;
    //关联充值记录,
    private long rechargeId;

    public long getUserCapitalId() {
        return this.userCapitalId;
    }

    public void setUserCapitalId(long userCapitalId) {
        this.userCapitalId = userCapitalId;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getBlance() {
        return this.blance;
    }

    public void setBlance(BigDecimal blance) {
        this.blance = blance;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public long getRechargeId() {
        return this.rechargeId;
    }

    public void setRechargeId(long rechargeId) {
        this.rechargeId = rechargeId;
    }

}
