package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// Contract,签约合同
public class Contract {
    //ContractId,
    private long contractId;
    //CompanyId,
    private long companyId;
    //签约日期,
    private Date signDate;
    //生效时间/开始时间,
    private Date effectTime;
    //失效时间/终止时间,
    private Date invalidTime;
    //中止时间,
    private Date suspendTime;
    //运营区域,
    private int areaId;
    //经营范围,
    private String businessScope;
    //结算周期(天),
    private int settlementCycle;
    //结算规则,
    private String settlementRules;
    //平台运营负责人,
    private long operatorManagerId;
    //平台财务负责人,
    private long financeManagerId;
    //平台法务负责人,
    private long justiceManagerId;
    //合同附件,
    private String attachment;
    //Status,
    private ContractStatus status;

    public long getContractId() {
        return this.contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Date getSignDate() {
        return this.signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getEffectTime() {
        return this.effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Date getInvalidTime() {
        return this.invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Date getSuspendTime() {
        return this.suspendTime;
    }

    public void setSuspendTime(Date suspendTime) {
        this.suspendTime = suspendTime;
    }

    public int getAreaId() {
        return this.areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getBusinessScope() {
        return this.businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public int getSettlementCycle() {
        return this.settlementCycle;
    }

    public void setSettlementCycle(int settlementCycle) {
        this.settlementCycle = settlementCycle;
    }

    public String getSettlementRules() {
        return this.settlementRules;
    }

    public void setSettlementRules(String settlementRules) {
        this.settlementRules = settlementRules;
    }

    public long getOperatorManagerId() {
        return this.operatorManagerId;
    }

    public void setOperatorManagerId(long operatorManagerId) {
        this.operatorManagerId = operatorManagerId;
    }

    public long getFinanceManagerId() {
        return this.financeManagerId;
    }

    public void setFinanceManagerId(long financeManagerId) {
        this.financeManagerId = financeManagerId;
    }

    public long getJusticeManagerId() {
        return this.justiceManagerId;
    }

    public void setJusticeManagerId(long justiceManagerId) {
        this.justiceManagerId = justiceManagerId;
    }

    public String getAttachment() {
        return this.attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public ContractStatus getStatus() {
        return this.status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

}
