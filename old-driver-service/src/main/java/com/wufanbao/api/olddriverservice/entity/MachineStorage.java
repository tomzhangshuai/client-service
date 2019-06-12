package com.wufanbao.api.olddriverservice.entity;

public class MachineStorage {
    private long machineId;//机器id
    private int position;//xy
    private long productGlobalId;//商品id
    private int boxNum;//纵深商品
    private int turn;//位置序号

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public int getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
