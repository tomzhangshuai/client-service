package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// ProductForecast,机器商品预测
public class ProductForecast {
    //ProductGlobalId,
    private long productGlobalId;
    //MachineId,
    private long machineId;
    //时间段起点,
    private Date pointTime;
    //预测数,
    private BigDecimal forecastNum;
    //实际数,
    private int actualNum;

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public Date getPointTime() {
        return this.pointTime;
    }

    public void setPointTime(Date pointTime) {
        this.pointTime = pointTime;
    }

    public BigDecimal getForecastNum() {
        return this.forecastNum;
    }

    public void setForecastNum(BigDecimal forecastNum) {
        this.forecastNum = forecastNum;
    }

    public int getActualNum() {
        return this.actualNum;
    }

    public void setActualNum(int actualNum) {
        this.actualNum = actualNum;
    }

}
