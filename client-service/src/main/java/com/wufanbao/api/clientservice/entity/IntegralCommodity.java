package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// IntegralCommodity,积分商品表
public class IntegralCommodity {
    //IntegralCommodityId,
    private long integralCommodityId;
    //商品名称,
    private String commodityName;
    //商品类型,
    private int commodityType;
    //价格,
    private BigDecimal price;
    //剩余数量,
    private int quantity;
    //商品描述,
    private String description;
    //是否有效,
    private boolean active;
    //图片地址,
    private String imageUrl;
    //创建时间,
    private Date createTime;
    //兑换内容,
    private String content;

    private long definition;

    //编辑状态,
    private IntegralCommodityStatus status;

    public long getIntegralCommodityId() {
        return this.integralCommodityId;
    }

    public void setIntegralCommodityId(long integralCommodityId) {
        this.integralCommodityId = integralCommodityId;
    }

    public String getCommodityName() {
        return this.commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getCommodityType() {
        return this.commodityType;
    }

    public void setCommodityType(int commodityType) {
        this.commodityType = commodityType;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDefinition() {
        return definition;
    }

    public void setDefinition(long definition) {
        this.definition = definition;
    }

    public IntegralCommodityStatus getStatus() {
        return this.status;
    }

    public void setStatus(IntegralCommodityStatus status) {
        this.status = status;
    }

}
