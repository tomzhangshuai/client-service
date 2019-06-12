package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// AbnormalOrderLine,异常单明细
public class AbnormalOrderLine {
    //AbnormalOrderLineId,
    private long abnormalOrderLineId;
    //AbnormalOrderId,
    private long abnormalOrderId;
    //描述,
    private String description;
    //意见,
    private String opinion;

    public long getAbnormalOrderLineId() {
        return this.abnormalOrderLineId;
    }

    public void setAbnormalOrderLineId(long abnormalOrderLineId) {
        this.abnormalOrderLineId = abnormalOrderLineId;
    }

    public long getAbnormalOrderId() {
        return this.abnormalOrderId;
    }

    public void setAbnormalOrderId(long abnormalOrderId) {
        this.abnormalOrderId = abnormalOrderId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

}
