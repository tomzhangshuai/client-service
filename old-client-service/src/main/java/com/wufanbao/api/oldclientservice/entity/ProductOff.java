package com.wufanbao.api.oldclientservice.entity;

import java.sql.Date;

/**
 * User:wangshihao
 * Date:2017-12-06
 * Time:15:43
 */
public class ProductOff {
    private long productOffId;//
    private long productGlobalId;//
    private long machineId;//
    private Date offTime;//下架时间
    private Date beginThermalTime;//开始保温时间
    private Date sendTime;//出餐时间
    private Date takeTime;//拿餐时间
    private int outId;//取餐通道
    private int XY;//取餐位置
    private String lotNo;//批次
    private String barcode;//条形码
    private int quantity;//数量(默认是1)
    private String remark;//备注
    private int actualPrice;//实际结算单价(分)
    private int price;//销售单价(分)
    private String sourceType;//源类型
    private long sourceId;//源ID/订单ID
    private long sourceLineId;//源明细ID/订单明细ID
    private int status;//状态

    public long getProductOffId() {
        return productOffId;
    }

    public void setProductOffId(long productOffId) {
        this.productOffId = productOffId;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public Date getOffTime() {
        return offTime;
    }

    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    public Date getBeginThermalTime() {
        return beginThermalTime;
    }

    public void setBeginThermalTime(Date beginThermalTime) {
        this.beginThermalTime = beginThermalTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public int getOutId() {
        return outId;
    }

    public void setOutId(int outId) {
        this.outId = outId;
    }

    public int getXY() {
        return XY;
    }

    public void setXY(int XY) {
        this.XY = XY;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public long getSourceLineId() {
        return sourceLineId;
    }

    public void setSourceLineId(long sourceLineId) {
        this.sourceLineId = sourceLineId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
