package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// CompanyVehicle,车辆管理
public class CompanyVehicle {
    //VehicleId,
    private long vehicleId;
    //所属公司,
    private long companyId;
    //牌照,
    private String plateNo;
    //车辆类型,
    private String vehicleType;
    //所有人,
    private String owner;
    //住址,
    private String address;
    //使用性质,
    private String useCharacter;
    //品牌型号,
    private String model;
    //车辆识别代号,
    private String vIN;
    //发动机号,
    private String engineNo;
    //注册日期,
    private Date registerDate;
    //核定载人数,
    private int passengers;
    //整备质量(千克),
    private int unladenMass;
    //总质量(千克),
    private int totalMass;
    //核定载重量(千克),
    private int carryingCapacity;
    //商品容量(份),
    private int productCapacity;
    //车长(mm),
    private int length;
    //车宽(mm),
    private int wide;
    //车高(mm),
    private int hight;
    //车箱长(mm),
    private int boxLength;
    //车箱宽(mm),
    private int boxWide;
    //车箱高(mm),
    private int boxHight;
    //行驶证(照片),
    private String vehicleLicense;
    //行驶证副页(照片),
    private String vehicleLicenseVice;

    public long getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getPlateNo() {
        return this.plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUseCharacter() {
        return this.useCharacter;
    }

    public void setUseCharacter(String useCharacter) {
        this.useCharacter = useCharacter;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVIN() {
        return this.vIN;
    }

    public void setVIN(String vIN) {
        this.vIN = vIN;
    }

    public String getEngineNo() {
        return this.engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Date getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public int getPassengers() {
        return this.passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getUnladenMass() {
        return this.unladenMass;
    }

    public void setUnladenMass(int unladenMass) {
        this.unladenMass = unladenMass;
    }

    public int getTotalMass() {
        return this.totalMass;
    }

    public void setTotalMass(int totalMass) {
        this.totalMass = totalMass;
    }

    public int getCarryingCapacity() {
        return this.carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public int getProductCapacity() {
        return this.productCapacity;
    }

    public void setProductCapacity(int productCapacity) {
        this.productCapacity = productCapacity;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWide() {
        return this.wide;
    }

    public void setWide(int wide) {
        this.wide = wide;
    }

    public int getHight() {
        return this.hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getBoxLength() {
        return this.boxLength;
    }

    public void setBoxLength(int boxLength) {
        this.boxLength = boxLength;
    }

    public int getBoxWide() {
        return this.boxWide;
    }

    public void setBoxWide(int boxWide) {
        this.boxWide = boxWide;
    }

    public int getBoxHight() {
        return this.boxHight;
    }

    public void setBoxHight(int boxHight) {
        this.boxHight = boxHight;
    }

    public String getVehicleLicense() {
        return this.vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public String getVehicleLicenseVice() {
        return this.vehicleLicenseVice;
    }

    public void setVehicleLicenseVice(String vehicleLicenseVice) {
        this.vehicleLicenseVice = vehicleLicenseVice;
    }

}
