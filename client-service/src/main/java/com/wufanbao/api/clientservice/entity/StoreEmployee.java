package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// 仓库员工,
public class StoreEmployee {
    //仓库,
    private long storeId;
    //员工,
    private long employeeId;

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

}
