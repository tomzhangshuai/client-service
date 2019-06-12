package com.wufanbao.api.distributionservice.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wufanbao.api.distributionservice.entities.SupplementOrderLine;

/**
 * 补货单 后仓补货完成 接收
 */
public class SupplementConfirmBehindInfo {
    private long distributionOrderId;
    private long supplementOrderId;

    private String quantityGather;

    public String getQuantityGather() {
        return quantityGather;
    }

    public void setQuantityGather(String quantityGather) {
        this.quantityGather = quantityGather;
    }
    public long getDistributionOrderId() {
        return distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public long getSupplementOrderId() {
        return supplementOrderId;
    }

    public void setSupplementOrderId(long supplementOrderId) {
        this.supplementOrderId = supplementOrderId;
    }


    public SupplementOrderLine[] getItems() {

        SupplementOrderLine[] items = JSON.parseObject(this.quantityGather, new TypeReference<SupplementOrderLine[]>() {
        });
        return items;
    }
}
