package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// AppVersion,app版本
public class AppVersion {
    //VersionId,
    private long versionId;
    //当前版本号,
    private String version;
    //版本说明,
    private String description;
    //前一版本号,
    private String formerVersion;
    //版本生效时间,
    private Date effectTime;
    //发布时间,
    private Date issueTime;

    public long getVersionId() {
        return this.versionId;
    }

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

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

    public String getFormerVersion() {
        return this.formerVersion;
    }

    public void setFormerVersion(String formerVersion) {
        this.formerVersion = formerVersion;
    }

    public Date getEffectTime() {
        return this.effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Date getIssueTime() {
        return this.issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

}
