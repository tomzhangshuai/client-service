package com.wufanbao.api.oldclientservice.entity;

import java.util.Date;

public class Category {
    private long categoryId;//商品类别id
    private String categoryName;//类别名称
    private long parentCategoryId;//上级类别
    private long turn;//顺序
    private Boolean isActive;//是否启用
    private long level;//级别
    private Date createTime;//创建时间
    private Date firstActiveTime;//初次启用时间
    private int categoryAssign;//类别归属

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public long getTurn() {
        return turn;
    }

    public void setTurn(long turn) {
        this.turn = turn;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFirstActiveTime() {
        return firstActiveTime;
    }

    public void setFirstActiveTime(Date firstActiveTime) {
        this.firstActiveTime = firstActiveTime;
    }

    public int getCategoryAssign() {
        return categoryAssign;
    }

    public void setCategoryAssign(int categoryAssign) {
        this.categoryAssign = categoryAssign;
    }

}
