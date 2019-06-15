package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// VersionControl,APP 版本控制
public class VersionControl {
    //版本控制Id,
    private String versionCode;
    //版本号,
    private String versionName;
    /*//安卓版本名字,
    private String versionNumber;*/
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

    public String getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
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
