package com.wufanbao.api.distributionservice.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wufanbao.api.distributionservice.entities.DistributionProductLine;

/**
 * 配送返仓确认接收
 */
public class DistributionConfirmInfo {

    private long distributionOrderId;

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

    public DistributionProductLine[] getItems() {

        DistributionProductLine[] items = JSON.parseObject(this.quantityGather, new TypeReference<DistributionProductLine[]>() {});
        return items;
    }
}
