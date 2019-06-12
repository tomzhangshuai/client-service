package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StatsTurnover,城市运营商-统计营业额
public class StatsTurnover {
    //CompanyId,
    private long companyId;
    //发生/产生日期,
    private Date occurDate;
    //统计时间,
    private Date statisticalTime;
    //销售份数,
    private long saledQuantity;
    //营业额,
    private BigDecimal turnover;

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Date getOccurDate() {
        return this.occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    public Date getStatisticalTime() {
        return this.statisticalTime;
    }

    public void setStatisticalTime(Date statisticalTime) {
        this.statisticalTime = statisticalTime;
    }

    public long getSaledQuantity() {
        return this.saledQuantity;
    }

    public void setSaledQuantity(long saledQuantity) {
        this.saledQuantity = saledQuantity;
    }

    public BigDecimal getTurnover() {
        return this.turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

}
