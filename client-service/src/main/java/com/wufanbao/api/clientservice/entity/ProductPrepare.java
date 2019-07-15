package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// ProductPrepare,商品保温仓记录表
public class ProductPrepare {
    //PrepareId,
    private long prepareId;
    //冷冻仓出仓ID,
    private long productOffId;
    //商品id,
    private int productGlobalId;
    //冷冻仓出仓,
    private Date freezerOut;
    //过期时间,
    private Date expirationTime;
    //加热完成时间,
    private Date heatedTime;
    //保温仓出仓时间,
    private Date prepareOut;
    //修复状态
    private int repairStatus;
    //冷冻仓位置(仅记录),
    private int freezerOutPotion;
    //保温仓入位置(仅记录),
    private int prepareInPotion;
    //保温仓出位置(仅记录),
    private int prepareOutPosition;

    public int getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(int repairStatus) {
        this.repairStatus = repairStatus;
    }

    public long getPrepareId() {
        return this.prepareId;
    }

    public void setPrepareId(long prepareId) {
        this.prepareId = prepareId;
    }

    public long getProductOffId() {
        return this.productOffId;
    }

    public void setProductOffId(long productOffId) {
        this.productOffId = productOffId;
    }

    public int getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(int productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public Date getFreezerOut() {
        return this.freezerOut;
    }

    public void setFreezerOut(Date freezerOut) {
        this.freezerOut = freezerOut;
    }

    public Date getExpirationTime() {
        return this.expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Date getHeatedTime() {
        return this.heatedTime;
    }

    public void setHeatedTime(Date heatedTime) {
        this.heatedTime = heatedTime;
    }

    public Date getPrepareOut() {
        return this.prepareOut;
    }

    public void setPrepareOut(Date prepareOut) {
        this.prepareOut = prepareOut;
    }

    public int getFreezerOutPotion() {
        return this.freezerOutPotion;
    }

    public void setFreezerOutPotion(int freezerOutPotion) {
        this.freezerOutPotion = freezerOutPotion;
    }

    public int getPrepareInPotion() {
        return this.prepareInPotion;
    }

    public void setPrepareInPotion(int prepareInPotion) {
        this.prepareInPotion = prepareInPotion;
    }

    public int getPrepareOutPosition() {
        return this.prepareOutPosition;
    }

    public void setPrepareOutPosition(int prepareOutPosition) {
        this.prepareOutPosition = prepareOutPosition;
    }

}
