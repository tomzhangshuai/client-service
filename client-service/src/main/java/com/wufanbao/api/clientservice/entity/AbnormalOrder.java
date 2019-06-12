package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// AbnormalOrder,异常单
public class AbnormalOrder {
    //AbnormalOrderId,
    private long abnormalOrderId;
    //发生时间,
    private Date occurTime;
    //来源,
    private String sourceType;
    //来源订单,
    private long sourceId;
    //异常记录数,
    private int abnomalNum;
    //状态,
    private AbnormalOrderStatus status;
    //处理方式/意见,
    private String opinion;

    public long getAbnormalOrderId() {
        return this.abnormalOrderId;
    }

    public void setAbnormalOrderId(long abnormalOrderId) {
        this.abnormalOrderId = abnormalOrderId;
    }

    public Date getOccurTime() {
        return this.occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public int getAbnomalNum() {
        return this.abnomalNum;
    }

    public void setAbnomalNum(int abnomalNum) {
        this.abnomalNum = abnomalNum;
    }

    public AbnormalOrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(AbnormalOrderStatus status) {
        this.status = status;
    }

    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

}
