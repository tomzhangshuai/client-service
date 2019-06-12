package com.wufanbao.api.olddriverservice.entity;

public class MachineModel {
    private long machineId;//机器id
    private int countRow;//总行数
    private int boxNum;//阁子数量
    private int row;
    private int line;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public int getCountRow() {
        return countRow;
    }

    public void setCountRow(int countRow) {
        this.countRow = countRow;
    }

    public int getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
    }
}
