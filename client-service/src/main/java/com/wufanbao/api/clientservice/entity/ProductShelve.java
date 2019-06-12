package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// ProductShelve,商品上架
public class ProductShelve {
    //ShelveId,
    private long shelveId;
    //ProductGlobalId,
    private long productGlobalId;
    //MachineId,
    private long machineId;
    //批次,
    private String lotNo;
    //条形码/二维码,
    private String barcode;
    //数量(默认1),
    private int quantity;
    //源类型,
    private String sourceType;
    //源id,
    private long sourceId;
    //上架时间,
    private Date shelveTime;
    //XY,
    private int xY;

    public long getShelveId() {
        return this.shelveId;
    }

    public void setShelveId(long shelveId) {
        this.shelveId = shelveId;
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

    public Date getShelveTime() {
        return this.shelveTime;
    }

    public void setShelveTime(Date shelveTime) {
        this.shelveTime = shelveTime;
    }

    public int getXY() {
        return this.xY;
    }

    public void setXY(int xY) {
        this.xY = xY;
    }

}
