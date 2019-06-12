package com.wufanbao.api.clientservice.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.math.BigDecimal;


// CompanyDriver,驾驶员
public class CompanyDriver {
    //驾驶人,
    private long driverEmployeeId;
    //所属公司,
    private long companyId;
    //驾驶证号,
    private String licenseNumber;
    //档案编号,
    private String fileNo;
    //准驾车型,
    @SerializedName("class")
    private String classes;
    //出生日期,
    private Date birthday;
    //初次领证日期,
    private Date issueDate;
    //姓名,
    private String name;
    //性别,
    private int mF;
    //国籍,
    private String nationality;
    //驾驶证,
    private String drivingLicense;
    //驾驶证副页,
    private String drivingLicenseVice;

    public long getDriverEmployeeId() {
        return this.driverEmployeeId;
    }

    public void setDriverEmployeeId(long driverEmployeeId) {
        this.driverEmployeeId = driverEmployeeId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getFileNo() {
        return this.fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getClasses() {
        return this.classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMF() {
        return this.mF;
    }

    public void setMF(int mF) {
        this.mF = mF;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDrivingLicense() {
        return this.drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getDrivingLicenseVice() {
        return this.drivingLicenseVice;
    }

    public void setDrivingLicenseVice(String drivingLicenseVice) {
        this.drivingLicenseVice = drivingLicenseVice;
    }

}
