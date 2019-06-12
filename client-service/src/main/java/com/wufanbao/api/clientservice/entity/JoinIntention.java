package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// JoinIntention,加盟意向
public class JoinIntention {
    //JoinIntentionId,
    private long joinIntentionId;
    //所在城市(居住城市),
    private int liveAreaId;
    //申请加盟城市,
    private int applyAreaId;
    //姓名,
    private String fullName;
    //性别,
    private SexType sex;
    //联系电话,
    private String contactPhone;
    //Email,
    private String email;
    //主要信息来源,
    private InfoSourceType infoSource;
    //申请角色,
    private CompanyType companyType;
    //备注,
    private String remark;
    //创建时间,
    private Date createTime;

    public long getJoinIntentionId() {
        return this.joinIntentionId;
    }

    public void setJoinIntentionId(long joinIntentionId) {
        this.joinIntentionId = joinIntentionId;
    }

    public int getLiveAreaId() {
        return this.liveAreaId;
    }

    public void setLiveAreaId(int liveAreaId) {
        this.liveAreaId = liveAreaId;
    }

    public int getApplyAreaId() {
        return this.applyAreaId;
    }

    public void setApplyAreaId(int applyAreaId) {
        this.applyAreaId = applyAreaId;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public SexType getSex() {
        return this.sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InfoSourceType getInfoSource() {
        return this.infoSource;
    }

    public void setInfoSource(InfoSourceType infoSource) {
        this.infoSource = infoSource;
    }

    public CompanyType getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
