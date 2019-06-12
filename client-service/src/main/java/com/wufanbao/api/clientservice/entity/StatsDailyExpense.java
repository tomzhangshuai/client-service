package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StatsDailyExpense,统计公司每日费用
public class StatsDailyExpense {
    //CompanyId,
    private long companyId;
    //机器id,
    private long machineId;
    //发生/产生日期,
    private Date occurDate;
    //统计时间,
    private Date statisticsTime;
    //结算周期,
    private int settleCycle;
    //计划结算时间,
    private Date planSettleTime;
    //账户名,
    private String accountName;
    //平台开户/个人账户,
    private String account;
    //平台结算日营业额,
    private BigDecimal dailyTurnover;
    //公司类型,
    private CompanyType companyType;
    //毛收入,
    private BigDecimal roughIncome;
    //已审计收入(匹配等同于毛收入),
    private BigDecimal income;
    //收入公式,
    private String formula;
    //状态,
    private CompanySettleStatus status;
    //数据是否匹配,
    private boolean matchable;
    //数据是否经过修正,
    private boolean modify;
    //修正时间,
    private Date modifyTime;
    //修正人,
    private long modifyManagerId;
    //审计人(匹配审计人为0）,
    private long auditManagerId;
    //审核时间(匹配等同统计时间）,
    private Date auditTime;
    //结算发放时间,
    private Date settleTime;

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public Date getOccurDate() {
        return this.occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    public Date getStatisticsTime() {
        return this.statisticsTime;
    }

    public void setStatisticsTime(Date statisticsTime) {
        this.statisticsTime = statisticsTime;
    }

    public int getSettleCycle() {
        return this.settleCycle;
    }

    public void setSettleCycle(int settleCycle) {
        this.settleCycle = settleCycle;
    }

    public Date getPlanSettleTime() {
        return this.planSettleTime;
    }

    public void setPlanSettleTime(Date planSettleTime) {
        this.planSettleTime = planSettleTime;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getDailyTurnover() {
        return this.dailyTurnover;
    }

    public void setDailyTurnover(BigDecimal dailyTurnover) {
        this.dailyTurnover = dailyTurnover;
    }

    public CompanyType getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public BigDecimal getRoughIncome() {
        return this.roughIncome;
    }

    public void setRoughIncome(BigDecimal roughIncome) {
        this.roughIncome = roughIncome;
    }

    public BigDecimal getIncome() {
        return this.income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public CompanySettleStatus getStatus() {
        return this.status;
    }

    public void setStatus(CompanySettleStatus status) {
        this.status = status;
    }

    public boolean getMatchable() {
        return this.matchable;
    }

    public void setMatchable(boolean matchable) {
        this.matchable = matchable;
    }

    public boolean getModify() {
        return this.modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getModifyManagerId() {
        return this.modifyManagerId;
    }

    public void setModifyManagerId(long modifyManagerId) {
        this.modifyManagerId = modifyManagerId;
    }

    public long getAuditManagerId() {
        return this.auditManagerId;
    }

    public void setAuditManagerId(long auditManagerId) {
        this.auditManagerId = auditManagerId;
    }

    public Date getAuditTime() {
        return this.auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getSettleTime() {
        return this.settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

}
