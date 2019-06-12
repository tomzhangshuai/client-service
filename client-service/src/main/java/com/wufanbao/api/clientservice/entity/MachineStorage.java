package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineStorage,机器存储商品，主要针对主商品
public class MachineStorage {
    //MachineId,
    private long machineId;
    //位置xy,
    private int position;
    //存放商品，当纵深数量为0时,忽略ID,
    private long productGlobalId;
    //纵深数量(最多0~3个),
    private int boxNum;
    //序号,
    private int turn;
    //存储仓类型,
    private MachineStorageType storageType;

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public int getBoxNum() {
        return this.boxNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public MachineStorageType getStorageType() {
        return this.storageType;
    }

    public void setStorageType(MachineStorageType storageType) {
        this.storageType = storageType;
    }

}
