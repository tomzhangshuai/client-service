package com.wufanbao.api.oldclientservice.entity;

/**
 * User:wangshihao
 * Date:2017-12-08
 * Time:11:55
 */
public class WaitTakeMeal {
    private String MachineId;
    private String Timespan;
    private String EntercloseId;
    private String ProductGlobalId;
    private String orderId;

    public String getMachineId() {
        return MachineId;
    }

    public void setMachineId(String MachineId) {
        this.MachineId = MachineId;
    }

    public String getTimespan() {
        return Timespan;
    }

    public void setTimespan(String Timespan) {
        this.Timespan = Timespan;
    }

    public String getEntercloseId() {
        return EntercloseId;
    }

    public void setEntercloseId(String EntercloseId) {
        this.EntercloseId = EntercloseId;
    }

    public String getProductGlobalId() {
        return ProductGlobalId;
    }

    public void setProductGlobalId(String ProductGlobalId) {
        this.ProductGlobalId = ProductGlobalId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
