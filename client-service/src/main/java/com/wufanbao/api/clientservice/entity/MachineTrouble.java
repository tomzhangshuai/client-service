package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineTrouble,机器故障表
public class MachineTrouble {
    //TroubleId,
    private long troubleId;
    //机器id,
    private long machineId;
    //故障发生时间,
    private Date troubleTime;
    //故障代码,
    private String troubleCode;
    //从机号,
    private String attachmentId;
    //通道,
    private String enterclose;
    //故障内容描述,
    private String content;
    //设备是否需要下线,
    private boolean isNeedOffline;
    //故障上报人,
    private long reporterEmployeeId;
    //上报时间,
    private Date reportTime;
    //是否自动上报,
    private boolean isAutoReport;
    //系统请求第三方维修时间,
    private Date requestTime;
    //第三方维修响应时间,
    private Date responseTime;
    //故障维修公司,
    private String repairCompany;
    //维修ID,
    private String repairId;
    //故障维修人,
    private String repairer;
    //维修联系电话,
    private String repairerPhone;
    //故障排除时间,
    private Date resolvedTime;
    //是否已解决,
    private boolean isResolved;
    //备注,
    private String remark;

    public long getTroubleId() {
        return this.troubleId;
    }

    public void setTroubleId(long troubleId) {
        this.troubleId = troubleId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public Date getTroubleTime() {
        return this.troubleTime;
    }

    public void setTroubleTime(Date troubleTime) {
        this.troubleTime = troubleTime;
    }

    public String getTroubleCode() {
        return this.troubleCode;
    }

    public void setTroubleCode(String troubleCode) {
        this.troubleCode = troubleCode;
    }

    public String getAttachmentId() {
        return this.attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getEnterclose() {
        return this.enterclose;
    }

    public void setEnterclose(String enterclose) {
        this.enterclose = enterclose;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsNeedOffline() {
        return this.isNeedOffline;
    }

    public void setIsNeedOffline(boolean isNeedOffline) {
        this.isNeedOffline = isNeedOffline;
    }

    public long getReporterEmployeeId() {
        return this.reporterEmployeeId;
    }

    public void setReporterEmployeeId(long reporterEmployeeId) {
        this.reporterEmployeeId = reporterEmployeeId;
    }

    public Date getReportTime() {
        return this.reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public boolean getIsAutoReport() {
        return this.isAutoReport;
    }

    public void setIsAutoReport(boolean isAutoReport) {
        this.isAutoReport = isAutoReport;
    }

    public Date getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public String getRepairCompany() {
        return this.repairCompany;
    }

    public void setRepairCompany(String repairCompany) {
        this.repairCompany = repairCompany;
    }

    public String getRepairId() {
        return this.repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getRepairer() {
        return this.repairer;
    }

    public void setRepairer(String repairer) {
        this.repairer = repairer;
    }

    public String getRepairerPhone() {
        return this.repairerPhone;
    }

    public void setRepairerPhone(String repairerPhone) {
        this.repairerPhone = repairerPhone;
    }

    public Date getResolvedTime() {
        return this.resolvedTime;
    }

    public void setResolvedTime(Date resolvedTime) {
        this.resolvedTime = resolvedTime;
    }

    public boolean getIsResolved() {
        return this.isResolved;
    }

    public void setIsResolved(boolean isResolved) {
        this.isResolved = isResolved;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
