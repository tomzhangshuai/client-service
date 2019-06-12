package com.wufanbao.api.distributionservice.entities;

/**
 * User:Wangshihao
 * Date:2017-09-16
 * Time:17:44
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

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
