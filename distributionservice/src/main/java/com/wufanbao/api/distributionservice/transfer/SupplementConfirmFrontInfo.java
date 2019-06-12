package com.wufanbao.api.distributionservice.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wufanbao.api.distributionservice.entities.SupplementOrderLine;


/**
 * 补货单 前仓补货完成 接收
 */
public class SupplementConfirmFrontInfo {
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


    public SupplementOrderLine[] getItems(){

        GatherInfo[] gatherInfos=JSON.parseObject(this.quantityGather,new TypeReference<GatherInfo[]>(){});

        SupplementOrderLine[] items=new SupplementOrderLine[gatherInfos.length];

        GatherInfo gatherInfo=null;
        for(int i=0;i<items.length;i++){
            items[i]=new SupplementOrderLine();
            gatherInfo=gatherInfos[i];
            String[] array=gatherInfo.getGather().split(",");
            items[i].setProductGlobalId(Long.parseLong(array[0]));
            items[i].setActualQuantity(Integer.parseInt(array[1]));
        }
        return items;
    }
}
