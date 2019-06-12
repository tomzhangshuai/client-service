package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineDebugProduct,机器调试商品
public class MachineDebugProduct {
    //MachineDebugId,
    private long machineDebugId;
    //ProductGlobalId,
    private long productGlobalId;
    //Quantity,
    private int quantity;

    public long getMachineDebugId() {
        return this.machineDebugId;
    }

    public void setMachineDebugId(long machineDebugId) {
        this.machineDebugId = machineDebugId;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
