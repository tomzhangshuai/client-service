package com.wufanbao.api.distributionservice.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wufanbao.api.distributionservice.entities.SupplementOrderLine;

/**
 * 补货单 下货确认数据 接收
 */
public class SupplementDumpInfo {

    private long distributionOrderId=0;

    private long supplementOrderId=0;

    private String quantityGather;


    public long getDistributionOrderId() {
        return distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public String getQuantityGather() {
        return quantityGather;
    }

    public void setQuantityGather(String quantityGather) {
        this.quantityGather = quantityGather;
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
            items[i].setDumpQuantity(Integer.parseInt(array[1]));
        }
        return items;
    }
}
