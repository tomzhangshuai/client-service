package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// EmployeeOperation,员工操作日志
public class EmployeeOperation {
    //OperationId,
    private long operationId;
    //公司ID,
    private long companyId;
    //操作人,
    private long employeeId;
    //发生时间,
    private Date occurTime;
    //IP,
    private String iP;
    //用户浏览器信息,
    private String userAgent;
    //模块,
    private String module;
    //操作项,
    private String operateItem;
    //描述,
    private String description;
    //详细内容,
    private String content;

    public long getOperationId() {
        return this.operationId;
    }

    public void setOperationId(long operationId) {
        this.operationId = operationId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getOccurTime() {
        return this.occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public String getIP() {
        return this.iP;
    }

    public void setIP(String iP) {
        this.iP = iP;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getModule() {
        return this.module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getOperateItem() {
        return this.operateItem;
    }

    public void setOperateItem(String operateItem) {
        this.operateItem = operateItem;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
