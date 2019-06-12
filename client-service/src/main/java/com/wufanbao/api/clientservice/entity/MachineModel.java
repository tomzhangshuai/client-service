package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineModel,机器型号
public class MachineModel {
    //Model型号,
    private String model;
    //制造商,
    private String manufactor;
    //餐盒容量,
    private int capacity;
    //后冷冻仓栋数,
    private int buildingNums;
    //后冷冻仓每栋层数,
    private int buildingFloors;
    //餐盒容纳深度,
    private int boxNum;
    //榨菜包容量,
    private int pouchCapacity;
    //调味包容量,
    private int sauceCapacity;
    //餐具容量,
    private int cutleryCapacity;
    //钱币容量,
    private int coinCapacity;
    //手提袋容量,
    private int reticuleCapacity;
    //尺寸-L(cm),
    private int l;
    //尺寸-W(cm),
    private int w;
    //尺寸-H(cm),
    private int h;
    //电源电压(V),
    private int supply;
    //功率(W),
    private int power;
    //电流(A),
    private int current;
    //包装尺寸-L(cm),
    private int parkL;
    //包装尺寸-W(cm),
    private int parkW;
    //包装尺寸-H(cm),
    private int parkH;
    //净重(kg）,
    private int suttle;
    //出餐口数,
    private int outNum;
    //理论最大商品数,
    private int productNum;
    //模块组成(json),
    private String modules;
    //备注,
    private String remark;

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufactor() {
        return this.manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBuildingNums() {
        return this.buildingNums;
    }

    public void setBuildingNums(int buildingNums) {
        this.buildingNums = buildingNums;
    }

    public int getBuildingFloors() {
        return this.buildingFloors;
    }

    public void setBuildingFloors(int buildingFloors) {
        this.buildingFloors = buildingFloors;
    }

    public int getBoxNum() {
        return this.boxNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
    }

    public int getPouchCapacity() {
        return this.pouchCapacity;
    }

    public void setPouchCapacity(int pouchCapacity) {
        this.pouchCapacity = pouchCapacity;
    }

    public int getSauceCapacity() {
        return this.sauceCapacity;
    }

    public void setSauceCapacity(int sauceCapacity) {
        this.sauceCapacity = sauceCapacity;
    }

    public int getCutleryCapacity() {
        return this.cutleryCapacity;
    }

    public void setCutleryCapacity(int cutleryCapacity) {
        this.cutleryCapacity = cutleryCapacity;
    }

    public int getCoinCapacity() {
        return this.coinCapacity;
    }

    public void setCoinCapacity(int coinCapacity) {
        this.coinCapacity = coinCapacity;
    }

    public int getReticuleCapacity() {
        return this.reticuleCapacity;
    }

    public void setReticuleCapacity(int reticuleCapacity) {
        this.reticuleCapacity = reticuleCapacity;
    }

    public int getL() {
        return this.l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getSupply() {
        return this.supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getParkL() {
        return this.parkL;
    }

    public void setParkL(int parkL) {
        this.parkL = parkL;
    }

    public int getParkW() {
        return this.parkW;
    }

    public void setParkW(int parkW) {
        this.parkW = parkW;
    }

    public int getParkH() {
        return this.parkH;
    }

    public void setParkH(int parkH) {
        this.parkH = parkH;
    }

    public int getSuttle() {
        return this.suttle;
    }

    public void setSuttle(int suttle) {
        this.suttle = suttle;
    }

    public int getOutNum() {
        return this.outNum;
    }

    public void setOutNum(int outNum) {
        this.outNum = outNum;
    }

    public int getProductNum() {
        return this.productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getModules() {
        return this.modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
