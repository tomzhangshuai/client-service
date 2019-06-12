package com.wufanbao.api.oldclientservice.entity;

/**
 * User:Wangshihao
 * Date:2017-08-10
 * Time:15:27
 */
public class ProductGlobal {
    private long productGlobalId;
    private String productName;
    private String image60;

    public String getImage60() {
        return image60;
    }

    public void setImage60(String image60) {
        this.image60 = image60;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }
}
