package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// 全局产品,alpha 商品定义
public class ProductGlobal {
    //ProductGlobalId,
    private long productGlobalId;
    //商品编号,
    private String productNo;
    //类别,
    private long categoryId;
    //商品名称,
    private String productName;
    //描述,
    private String description;
    //条形码,
    private String barcode;
    //是否需要加热,
    private boolean isHeating;
    //加热时间(单位秒),
    private int heatingSecs;
    //搜索缓存,一般是拼音首字母
    private String searchKey;
    //主图地址,
    private String imageUrl;
    //小图地址,
    private String image60;
    //显示单位,
    private String displayUnit;
    //是否启用,
    private boolean isActive;
    //创建时间,
    private Date createTime;
    //修改时间,
    private Date updateTime;
    //添加人,
    private long managerId;
    //重量(g),
    private int weight;
    //出厂价(分),
    private int factorPrice;
    //建议零售价(分),
    private int retailPrice;
    //保质期(天),
    private int shelfLife;
    //尺寸-长(cm),
    private int l;
    //尺寸-宽(cm),
    private int w;
    //尺寸-高(cm),
    private int h;
    //箱内包装数,
    private int boxNum;
    //箱长(cm),
    private int boxL;
    //箱宽(cm),
    private int boxW;
    //箱高cm),
    private int boxH;
    //幅图(多张图片),
    private String attachImageUrls;
    //商品参数(图片),
    private String detailUrl;
    //限售区域,
    private int limitAreaId;
    //口味,
    private TasteType tasteType;
    //是否主食,
    private boolean isStaple;

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public String getProductNo() {
        return this.productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean getIsHeating() {
        return this.isHeating;
    }

    public void setIsHeating(boolean isHeating) {
        this.isHeating = isHeating;
    }

    public int getHeatingSecs() {
        return this.heatingSecs;
    }

    public void setHeatingSecs(int heatingSecs) {
        this.heatingSecs = heatingSecs;
    }

    public String getSearchKey() {
        return this.searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage60() {
        return this.image60;
    }

    public void setImage60(String image60) {
        this.image60 = image60;
    }

    public String getDisplayUnit() {
        return this.displayUnit;
    }

    public void setDisplayUnit(String displayUnit) {
        this.displayUnit = displayUnit;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public long getManagerId() {
        return this.managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFactorPrice() {
        return this.factorPrice;
    }

    public void setFactorPrice(int factorPrice) {
        this.factorPrice = factorPrice;
    }

    public int getRetailPrice() {
        return this.retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getShelfLife() {
        return this.shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public int getL() {
        return this.l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getBoxNum() {
        return this.boxNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
    }

    public int getBoxL() {
        return this.boxL;
    }

    public void setBoxL(int boxL) {
        this.boxL = boxL;
    }

    public int getBoxW() {
        return this.boxW;
    }

    public void setBoxW(int boxW) {
        this.boxW = boxW;
    }

    public int getBoxH() {
        return this.boxH;
    }

    public void setBoxH(int boxH) {
        this.boxH = boxH;
    }

    public String getAttachImageUrls() {
        return this.attachImageUrls;
    }

    public void setAttachImageUrls(String attachImageUrls) {
        this.attachImageUrls = attachImageUrls;
    }

    public String getDetailUrl() {
        return this.detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public int getLimitAreaId() {
        return this.limitAreaId;
    }

    public void setLimitAreaId(int limitAreaId) {
        this.limitAreaId = limitAreaId;
    }

    public TasteType getTasteType() {
        return this.tasteType;
    }

    public void setTasteType(TasteType tasteType) {
        this.tasteType = tasteType;
    }

    public boolean getIsStaple() {
        return this.isStaple;
    }

    public void setIsStaple(boolean isStaple) {
        this.isStaple = isStaple;
    }

}
