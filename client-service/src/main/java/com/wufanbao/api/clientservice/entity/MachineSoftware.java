package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineSoftware,机器软件
public class MachineSoftware {
    //Version,
    private String version;
    //版本说明,
    private String description;
    //发布时间,
    private Date issueTime;
    //适用机器型号,
    private String model;
    //软件下载地址,
    private String packageUrl;
    //升级策略(json),
    private String updatePolicy;

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getIssueTime() {
        return this.issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPackageUrl() {
        return this.packageUrl;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl;
    }

    public String getUpdatePolicy() {
        return this.updatePolicy;
    }

    public void setUpdatePolicy(String updatePolicy) {
        this.updatePolicy = updatePolicy;
    }

}
