package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// VersionControl,APP 版本控制
public class VersionControl {
    //版本控制Id,
    private long versionId;
    //版本号,
    private String versionNumber;
    //安卓版本名字,
    private String versionName;
    //版本描述,
    private String content;
    //下载地址,
    private String downloadLink;
    //创建时间,
    private Date createTime;
    //生效时间,
    private Date effectiveTime;
    //强制升级力度,
    private int forceLevel;

    public long getVersionId() {
        return this.versionId;
    }

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

    public String getVersionNumber() {
        return this.versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDownloadLink() {
        return this.downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEffectiveTime() {
        return this.effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public int getForceLevel() {
        return this.forceLevel;
    }

    public void setForceLevel(int forceLevel) {
        this.forceLevel = forceLevel;
    }

}
