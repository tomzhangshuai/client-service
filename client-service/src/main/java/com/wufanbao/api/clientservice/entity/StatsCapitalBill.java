package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StatsCapitalBill,公司资金统计/账单
public class StatsCapitalBill {
    //CapitalBillId,
    private long capitalBillId;
    //CompanyId,
    private long companyId;
    //账单日(日期/月份),
    private Date billDate;
    //收入,
    private BigDecimal income;
    //支出,
    private BigDecimal expense;
    //期初余额,
    private BigDecimal beginBalance;
    //期末余额,
    private BigDecimal enBalance;
    //统计时间,
    private Date statisticalTime;
    //账单类型,
    private StatsBillType billType;

    public long getCapitalBillId() {
        return this.capitalBillId;
    }

    public void setCapitalBillId(long capitalBillId) {
        this.capitalBillId = capitalBillId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Date getBillDate() {
        return this.billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public BigDecimal getIncome() {
        return this.income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getExpense() {
        return this.expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getBeginBalance() {
        return this.beginBalance;
    }

    public void setBeginBalance(BigDecimal beginBalance) {
        this.beginBalance = beginBalance;
    }

    public BigDecimal getEnBalance() {
        return this.enBalance;
    }

    public void setEnBalance(BigDecimal enBalance) {
        this.enBalance = enBalance;
    }

    public Date getStatisticalTime() {
        return this.statisticalTime;
    }

    public void setStatisticalTime(Date statisticalTime) {
        this.statisticalTime = statisticalTime;
    }

    public StatsBillType getBillType() {
        return this.billType;
    }

    public void setBillType(StatsBillType billType) {
        this.billType = billType;
    }

}
