package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StatsDailyDistribution,统计每日配送
public class StatsDailyDistribution {
    //机器id,
    private long machineId;
    //发生/产生日期,
    private Date occurDate;
    //配送费用,
    private BigDecimal distributionExpense;
    //主食配送数量,
    private int stapleNum;
    //统计时间,
    private Date statisticsTime;
    //结算周期,
    private int settleCycle;
    //计划结算时间,
    private Date planSettleTime;
    //加盟商,
    private long joinCompanyId;
    //加盟商账户名,
    private String joinAccountName;
    //加盟商账户,
    private String joinAccount;
    //物流商,
    private long logisticCompanyId;
    //物流商账户名,
    private String logisticAccountName;
    //物流商账户,
    private String logisticAccount;
    //状态,
    private CompanySettleStatus status;
    //结算发放时间,
    private Date settleTime;

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

    public BigDecimal getDistributionExpense() {
        return this.distributionExpense;
    }

    public void setDistributionExpense(BigDecimal distributionExpense) {
        this.distributionExpense = distributionExpense;
    }

    public int getStapleNum() {
        return this.stapleNum;
    }

    public void setStapleNum(int stapleNum) {
        this.stapleNum = stapleNum;
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

    public long getJoinCompanyId() {
        return this.joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public String getJoinAccountName() {
        return this.joinAccountName;
    }

    public void setJoinAccountName(String joinAccountName) {
        this.joinAccountName = joinAccountName;
    }

    public String getJoinAccount() {
        return this.joinAccount;
    }

    public void setJoinAccount(String joinAccount) {
        this.joinAccount = joinAccount;
    }

    public long getLogisticCompanyId() {
        return this.logisticCompanyId;
    }

    public void setLogisticCompanyId(long logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public String getLogisticAccountName() {
        return this.logisticAccountName;
    }

    public void setLogisticAccountName(String logisticAccountName) {
        this.logisticAccountName = logisticAccountName;
    }

    public String getLogisticAccount() {
        return this.logisticAccount;
    }

    public void setLogisticAccount(String logisticAccount) {
        this.logisticAccount = logisticAccount;
    }

    public CompanySettleStatus getStatus() {
        return this.status;
    }

    public void setStatus(CompanySettleStatus status) {
        this.status = status;
    }

    public Date getSettleTime() {
        return this.settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

}
