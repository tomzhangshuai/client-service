package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// Company,公司表:包括地推、加盟商、物流商，生成商,支付公司-alpha
public class Company {
    //CompanyId,
    private long companyId;
    //ParentCompanyId,
    private long parentCompanyId;
    //公司全称,
    private String companyFullName;
    //公司名称/个人名称,
    private String companyName;
    //公司Logo图片URL,
    private String logoImage;
    //注册时间/成立时间,
    private Date registerTime;
    //公司所属区域,
    private int locationAreaId;
    //详细地址,
    private String address;
    //X,
    private BigDecimal x;
    //Y,
    private BigDecimal y;
    //公司联系电话,
    private String phone;
    //公司简介,
    private String companyDescription;
    //公司法人/个人名称,
    private String legalPersonName;
    //法人类型,
    private LegalPersonType legalPersonType;
    //法人证件类型/个人证件,
    private CardType legalPersonCardType;
    //法人证件号/个人证件号,
    private String legalPersonNo;
    //法人联系方式/个人联系方式,
    private String legalPersonPhone;
    //备用联系人,
    private String contactName;
    //备用联系人联系方式,
    private String contactPhone;
    //开户行,
    private String accountBank;
    //开户行户名,
    private String accountName;
    //开户行账号,
    private String accountNo;
    //营业执照URL,
    private String certificateImage;
    //法人图片URL,
    private String legalPersonImage;
    //注册资金(元),
    private BigDecimal registeredCapital;
    //仓储面积(平方),
    private int storageArea;
    //物流配送范围,
    private String distributionScope;
    //运营许可证URL,
    private String operationPermit;
    //食品流通许可证URL,
    private String foodCirculationLicense;
    //物流企业认证等级,
    private String certificationLevel;
    //负责区域,
    private long areaId;
    //平台管理者,
    private long managerId;
    //审核状态,
    private AuditState auditState;
    //审核人,
    private long auditManagerId;
    //审核时间,
    private Date auditTime;
    //地图位置是否标记,
    private boolean isMapped;
    //类别,
    private CompanyType companyType;
    //物流商干线物流方式,
    private LogisticType logisticType;
    //是否启用,
    private boolean isActive;
    //备注,
    private String remark;
    //编辑状态,
    private CompanyEditStatus editStatus;
    //支付密码,
    private String payPassword;
    //余额,
    private BigDecimal balance;
    //管理员,
    private long administratorId;
    //组织类型,
    private OrganizationType organizationType;

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getParentCompanyId() {
        return this.parentCompanyId;
    }

    public void setParentCompanyId(long parentCompanyId) {
        this.parentCompanyId = parentCompanyId;
    }

    public String getCompanyFullName() {
        return this.companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogoImage() {
        return this.logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public Date getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public int getLocationAreaId() {
        return this.locationAreaId;
    }

    public void setLocationAreaId(int locationAreaId) {
        this.locationAreaId = locationAreaId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getX() {
        return this.x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return this.y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyDescription() {
        return this.companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getLegalPersonName() {
        return this.legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public LegalPersonType getLegalPersonType() {
        return this.legalPersonType;
    }

    public void setLegalPersonType(LegalPersonType legalPersonType) {
        this.legalPersonType = legalPersonType;
    }

    public CardType getLegalPersonCardType() {
        return this.legalPersonCardType;
    }

    public void setLegalPersonCardType(CardType legalPersonCardType) {
        this.legalPersonCardType = legalPersonCardType;
    }

    public String getLegalPersonNo() {
        return this.legalPersonNo;
    }

    public void setLegalPersonNo(String legalPersonNo) {
        this.legalPersonNo = legalPersonNo;
    }

    public String getLegalPersonPhone() {
        return this.legalPersonPhone;
    }

    public void setLegalPersonPhone(String legalPersonPhone) {
        this.legalPersonPhone = legalPersonPhone;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAccountBank() {
        return this.accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCertificateImage() {
        return this.certificateImage;
    }

    public void setCertificateImage(String certificateImage) {
        this.certificateImage = certificateImage;
    }

    public String getLegalPersonImage() {
        return this.legalPersonImage;
    }

    public void setLegalPersonImage(String legalPersonImage) {
        this.legalPersonImage = legalPersonImage;
    }

    public BigDecimal getRegisteredCapital() {
        return this.registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public int getStorageArea() {
        return this.storageArea;
    }

    public void setStorageArea(int storageArea) {
        this.storageArea = storageArea;
    }

    public String getDistributionScope() {
        return this.distributionScope;
    }

    public void setDistributionScope(String distributionScope) {
        this.distributionScope = distributionScope;
    }

    public String getOperationPermit() {
        return this.operationPermit;
    }

    public void setOperationPermit(String operationPermit) {
        this.operationPermit = operationPermit;
    }

    public String getFoodCirculationLicense() {
        return this.foodCirculationLicense;
    }

    public void setFoodCirculationLicense(String foodCirculationLicense) {
        this.foodCirculationLicense = foodCirculationLicense;
    }

    public String getCertificationLevel() {
        return this.certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    public long getAreaId() {
        return this.areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public long getManagerId() {
        return this.managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public AuditState getAuditState() {
        return this.auditState;
    }

    public void setAuditState(AuditState auditState) {
        this.auditState = auditState;
    }

    public long getAuditManagerId() {
        return this.auditManagerId;
    }

    public void setAuditManagerId(long auditManagerId) {
        this.auditManagerId = auditManagerId;
    }

    public Date getAuditTime() {
        return this.auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public boolean getIsMapped() {
        return this.isMapped;
    }

    public void setIsMapped(boolean isMapped) {
        this.isMapped = isMapped;
    }

    public CompanyType getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public LogisticType getLogisticType() {
        return this.logisticType;
    }

    public void setLogisticType(LogisticType logisticType) {
        this.logisticType = logisticType;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public CompanyEditStatus getEditStatus() {
        return this.editStatus;
    }

    public void setEditStatus(CompanyEditStatus editStatus) {
        this.editStatus = editStatus;
    }

    public String getPayPassword() {
        return this.payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public long getAdministratorId() {
        return this.administratorId;
    }

    public void setAdministratorId(long administratorId) {
        this.administratorId = administratorId;
    }

    public OrganizationType getOrganizationType() {
        return this.organizationType;
    }

    public void setOrganizationType(OrganizationType organizationType) {
        this.organizationType = organizationType;
    }

}
