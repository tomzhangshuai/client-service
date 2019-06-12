package com.wufanbao.api.olddriverservice.entity;

/**
 * User:Wangshihao
 * Date:2017-09-18
 * Time:17:23
 */
public class Quantity {
    private String backQuantity;//退回数
    private String exceptionQuantity;//异常数
    private String actualQuantity;//上架数
    private long productGlobalId;

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public String getBackQuantity() {
        return backQuantity;
    }

    public void setBackQuantity(String backQuantity) {
        this.backQuantity = backQuantity;
    }

    public String getExceptionQuantity() {
        return exceptionQuantity;
    }

    public void setExceptionQuantity(String exceptionQuantity) {
        this.exceptionQuantity = exceptionQuantity;
    }

    public String getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(String actualQuantity) {
        this.actualQuantity = actualQuantity;
    }
}
