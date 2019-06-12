package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// Category,商品类别-alpha
public class Category {
    //主键,
    private long categoryId;
    //类别名称,
    private String categoryName;
    //上级类别,上级地区,0表示顶级
    private long parentCategoryId;
    //顺序,
    private long turn;
    //是否启用,
    private boolean isActive;
    //级别,
    private long level;
    //创建时间,
    private Date createTime;
    //初次启用时间,
    private Date firstActiveTime;
    //类别归属,
    private CategoryAssign categoryAssign;

    public long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getParentCategoryId() {
        return this.parentCategoryId;
    }

    public void setParentCategoryId(long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public long getTurn() {
        return this.turn;
    }

    public void setTurn(long turn) {
        this.turn = turn;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public long getLevel() {
        return this.level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFirstActiveTime() {
        return this.firstActiveTime;
    }

    public void setFirstActiveTime(Date firstActiveTime) {
        this.firstActiveTime = firstActiveTime;
    }

    public CategoryAssign getCategoryAssign() {
        return this.categoryAssign;
    }

    public void setCategoryAssign(CategoryAssign categoryAssign) {
        this.categoryAssign = categoryAssign;
    }

}
