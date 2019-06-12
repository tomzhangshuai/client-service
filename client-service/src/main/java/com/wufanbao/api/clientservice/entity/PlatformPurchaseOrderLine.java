package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// PlatformPurchaseOrderLine,平台关联采购订单
public class PlatformPurchaseOrderLine {
    //PlatformPurchaseOrderId,
    private long platformPurchaseOrderId;
    //PurchaseOrderId,
    private long purchaseOrderId;

    public long getPlatformPurchaseOrderId() {
        return this.platformPurchaseOrderId;
    }

    public void setPlatformPurchaseOrderId(long platformPurchaseOrderId) {
        this.platformPurchaseOrderId = platformPurchaseOrderId;
    }

    public long getPurchaseOrderId() {
        return this.purchaseOrderId;
    }

    public void setPurchaseOrderId(long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

}
