package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// ProductCity,城市选择商品
public class ProductCity {
    //ProductGlobalId,
    private long productGlobalId;
    //AreaId,
    private int areaId;
    //CompanyId,
    private long companyId;

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public int getAreaId() {
        return this.areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

}
