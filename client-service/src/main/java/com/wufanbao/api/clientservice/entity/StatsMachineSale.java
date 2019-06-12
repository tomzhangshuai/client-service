package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StatsMachineSale,统计机器销售数据
public class StatsMachineSale {
    //发生/产生日期,
    private Date occurDate;
    //MachineId,
    private long machineId;
    //JoinCompanyId,
    private long joinCompanyId;
    //LogisticCompayId,
    private long logisticCompayId;
    //CityCompanyId,
    private long cityCompanyId;
    //实际成交金额,
    private BigDecimal dealAmount;
    //用户实际消费金额,
    private BigDecimal consumeAmount;
    //订单实际优惠总金额,
    private BigDecimal totalDiscount;
    //平台实际优惠金额,
    private BigDecimal platformDiscount;
    //加盟商实际优惠金额,
    private BigDecimal joinDiscount;
    //运营商实际优惠金额,
    private BigDecimal cityDiscount;
    //第三方支付手续费,
    private BigDecimal brokerage;
    //消费主食/便当份数,
    private int stapleNum;
    //消费主食/便当金额,
    private BigDecimal stapleAmount;
    //消费包装袋数,
    private int reticuleNum;
    //消费包装袋金额,
    private BigDecimal reticuleAmount;
    //消费餐具数,
    private int cutleryNum;
    //消费餐具金额,
    private BigDecimal cutleryAmount;
    //消费榨菜包数,
    private int pouchNum;
    //消费榨菜包金额,
    private BigDecimal pouchAmount;
    //消费调味包数,
    private int sauceNum;
    //消费调味包金额,
    private BigDecimal sauceAmount;
    //平台服务费,
    private BigDecimal platformService;
    //物流服务费,
    private BigDecimal logisticService;
    //运营管理费,
    private BigDecimal cityService;
    //成交成本,
    private BigDecimal cost;
    //加盟商收益,
    private BigDecimal joinIncome;
    //统计时间,
    private Date statisticalTime;
    //状态,
    private CompanySettleStatus status;

    public Date getOccurDate() {
        return this.occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getJoinCompanyId() {
        return this.joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public long getLogisticCompayId() {
        return this.logisticCompayId;
    }

    public void setLogisticCompayId(long logisticCompayId) {
        this.logisticCompayId = logisticCompayId;
    }

    public long getCityCompanyId() {
        return this.cityCompanyId;
    }

    public void setCityCompanyId(long cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }

    public BigDecimal getDealAmount() {
        return this.dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }

    public BigDecimal getConsumeAmount() {
        return this.consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public BigDecimal getTotalDiscount() {
        return this.totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getPlatformDiscount() {
        return this.platformDiscount;
    }

    public void setPlatformDiscount(BigDecimal platformDiscount) {
        this.platformDiscount = platformDiscount;
    }

    public BigDecimal getJoinDiscount() {
        return this.joinDiscount;
    }

    public void setJoinDiscount(BigDecimal joinDiscount) {
        this.joinDiscount = joinDiscount;
    }

    public BigDecimal getCityDiscount() {
        return this.cityDiscount;
    }

    public void setCityDiscount(BigDecimal cityDiscount) {
        this.cityDiscount = cityDiscount;
    }

    public BigDecimal getBrokerage() {
        return this.brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

    public int getStapleNum() {
        return this.stapleNum;
    }

    public void setStapleNum(int stapleNum) {
        this.stapleNum = stapleNum;
    }

    public BigDecimal getStapleAmount() {
        return this.stapleAmount;
    }

    public void setStapleAmount(BigDecimal stapleAmount) {
        this.stapleAmount = stapleAmount;
    }

    public int getReticuleNum() {
        return this.reticuleNum;
    }

    public void setReticuleNum(int reticuleNum) {
        this.reticuleNum = reticuleNum;
    }

    public BigDecimal getReticuleAmount() {
        return this.reticuleAmount;
    }

    public void setReticuleAmount(BigDecimal reticuleAmount) {
        this.reticuleAmount = reticuleAmount;
    }

    public int getCutleryNum() {
        return this.cutleryNum;
    }

    public void setCutleryNum(int cutleryNum) {
        this.cutleryNum = cutleryNum;
    }

    public BigDecimal getCutleryAmount() {
        return this.cutleryAmount;
    }

    public void setCutleryAmount(BigDecimal cutleryAmount) {
        this.cutleryAmount = cutleryAmount;
    }

    public int getPouchNum() {
        return this.pouchNum;
    }

    public void setPouchNum(int pouchNum) {
        this.pouchNum = pouchNum;
    }

    public BigDecimal getPouchAmount() {
        return this.pouchAmount;
    }

    public void setPouchAmount(BigDecimal pouchAmount) {
        this.pouchAmount = pouchAmount;
    }

    public int getSauceNum() {
        return this.sauceNum;
    }

    public void setSauceNum(int sauceNum) {
        this.sauceNum = sauceNum;
    }

    public BigDecimal getSauceAmount() {
        return this.sauceAmount;
    }

    public void setSauceAmount(BigDecimal sauceAmount) {
        this.sauceAmount = sauceAmount;
    }

    public BigDecimal getPlatformService() {
        return this.platformService;
    }

    public void setPlatformService(BigDecimal platformService) {
        this.platformService = platformService;
    }

    public BigDecimal getLogisticService() {
        return this.logisticService;
    }

    public void setLogisticService(BigDecimal logisticService) {
        this.logisticService = logisticService;
    }

    public BigDecimal getCityService() {
        return this.cityService;
    }

    public void setCityService(BigDecimal cityService) {
        this.cityService = cityService;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getJoinIncome() {
        return this.joinIncome;
    }

    public void setJoinIncome(BigDecimal joinIncome) {
        this.joinIncome = joinIncome;
    }

    public Date getStatisticalTime() {
        return this.statisticalTime;
    }

    public void setStatisticalTime(Date statisticalTime) {
        this.statisticalTime = statisticalTime;
    }

    public CompanySettleStatus getStatus() {
        return this.status;
    }

    public void setStatus(CompanySettleStatus status) {
        this.status = status;
    }

}
