package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// ProductOff,商品从机器下架明细
public class ProductOff {
    //ProductOffId,
    private long productOffId;
    //ProductGlobalId,
    private long productGlobalId;
    //MachineId,
    private long machineId;
    //下架时间,
    private Date offTime;
    //开始保温时间,
    private Date beginThermalTime;
    //出餐时间,
    private Date sendTime;
    //拿餐时间,
    private Date takeTime;
    //取餐通道,
    private int outId;
    //取餐位置,
    private int xY;
    //批次,
    private String lotNo;
    //条形码,
    private String barcode;
    //数量(默认是1),
    private int quantity;
    //备注,
    private String remark;
    //实际结算单价(分),
    private int actualPrice;
    //销售单价(分),
    private int price;
    //源类型,
    private String sourceType;
    //源ID/订单ID,
    private long sourceId;
    //源明细ID/订单明细ID,
    private long sourceLineId;
    //状态,
    private int status;

    public long getProductOffId() {
        return this.productOffId;
    }

    public void setProductOffId(long productOffId) {
        this.productOffId = productOffId;
    }

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

    public Date getOffTime() {
        return this.offTime;
    }

    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    public Date getBeginThermalTime() {
        return this.beginThermalTime;
    }

    public void setBeginThermalTime(Date beginThermalTime) {
        this.beginThermalTime = beginThermalTime;
    }

    public Date getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getTakeTime() {
        return this.takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public int getOutId() {
        return this.outId;
    }

    public void setOutId(int outId) {
        this.outId = outId;
    }

    public int getXY() {
        return this.xY;
    }

    public void setXY(int xY) {
        this.xY = xY;
    }

    public String getLotNo() {
        return this.lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getActualPrice() {
        return this.actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public long getSourceLineId() {
        return this.sourceLineId;
    }

    public void setSourceLineId(long sourceLineId) {
        this.sourceLineId = sourceLineId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
