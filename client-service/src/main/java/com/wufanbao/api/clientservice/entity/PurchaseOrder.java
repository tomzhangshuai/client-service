package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// PurchaseOrder,采购订单
public class PurchaseOrder {
    //PurchaseOrderId,
    private long purchaseOrderId;
    //物流商,
    private long companyId;
    //收货仓库,
    private long storeId;
    //下单人,接单人，涉及业务员销量统计
    private long orderEmployeeId;
    //下单时间,
    private Date createTime;
    //提交人,
    private long submitorEmployeeId;
    //提交时间,
    private Date submitTime;
    //确认人,
    private long confirmorEmployeeId;
    //确认时间,
    private Date confirmTime;
    //物流商审核人,
    private long auditorEmployeeId;
    //审核时间,
    private Date auditTime;
    //订单状态,
    private PurchaseStatus purchaseStatus;
    //期望交期,
    private Date expectReceive;
    //工厂交期,
    private Date factoryFeedback;
    //采购总额,
    private int purchaseAmount;
    //付款总额,
    private int payAmount;
    //付款时间,
    private Date payTime;
    //付款凭证,
    private String payPhoto;
    //发货单号,
    private String deliveryNo;
    //发货时间,
    private Date deliveryTime;
    //发货人,
    private String deliverer;
    //发货人联系电话,
    private String delivererPhone;
    //取消时间,
    private Date cancelTime;
    //是否买家取消,
    private boolean canceledByByer;
    //终结人,
    private long finalEmployeeId;
    //终结时间,签收时间
    private Date finalTime;
    //备注,
    private String remark;
    //城市运营商,
    private long cityCompanyId;
    //采购合同扫描件,
    private String contractScans;
    //收货人,
    private long receiverEmployeeId;
    //收货人联系电话,
    private String receiverPhone;

    public long getPurchaseOrderId() {
        return this.purchaseOrderId;
    }

    public void setPurchaseOrderId(long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getOrderEmployeeId() {
        return this.orderEmployeeId;
    }

    public void setOrderEmployeeId(long orderEmployeeId) {
        this.orderEmployeeId = orderEmployeeId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getSubmitorEmployeeId() {
        return this.submitorEmployeeId;
    }

    public void setSubmitorEmployeeId(long submitorEmployeeId) {
        this.submitorEmployeeId = submitorEmployeeId;
    }

    public Date getSubmitTime() {
        return this.submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public long getConfirmorEmployeeId() {
        return this.confirmorEmployeeId;
    }

    public void setConfirmorEmployeeId(long confirmorEmployeeId) {
        this.confirmorEmployeeId = confirmorEmployeeId;
    }

    public Date getConfirmTime() {
        return this.confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public long getAuditorEmployeeId() {
        return this.auditorEmployeeId;
    }

    public void setAuditorEmployeeId(long auditorEmployeeId) {
        this.auditorEmployeeId = auditorEmployeeId;
    }

    public Date getAuditTime() {
        return this.auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public PurchaseStatus getPurchaseStatus() {
        return this.purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public Date getExpectReceive() {
        return this.expectReceive;
    }

    public void setExpectReceive(Date expectReceive) {
        this.expectReceive = expectReceive;
    }

    public Date getFactoryFeedback() {
        return this.factoryFeedback;
    }

    public void setFactoryFeedback(Date factoryFeedback) {
        this.factoryFeedback = factoryFeedback;
    }

    public int getPurchaseAmount() {
        return this.purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int getPayAmount() {
        return this.payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayTime() {
        return this.payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayPhoto() {
        return this.payPhoto;
    }

    public void setPayPhoto(String payPhoto) {
        this.payPhoto = payPhoto;
    }

    public String getDeliveryNo() {
        return this.deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public Date getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliverer() {
        return this.deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public String getDelivererPhone() {
        return this.delivererPhone;
    }

    public void setDelivererPhone(String delivererPhone) {
        this.delivererPhone = delivererPhone;
    }

    public Date getCancelTime() {
        return this.cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public boolean getCanceledByByer() {
        return this.canceledByByer;
    }

    public void setCanceledByByer(boolean canceledByByer) {
        this.canceledByByer = canceledByByer;
    }

    public long getFinalEmployeeId() {
        return this.finalEmployeeId;
    }

    public void setFinalEmployeeId(long finalEmployeeId) {
        this.finalEmployeeId = finalEmployeeId;
    }

    public Date getFinalTime() {
        return this.finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCityCompanyId() {
        return this.cityCompanyId;
    }

    public void setCityCompanyId(long cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }

    public String getContractScans() {
        return this.contractScans;
    }

    public void setContractScans(String contractScans) {
        this.contractScans = contractScans;
    }

    public long getReceiverEmployeeId() {
        return this.receiverEmployeeId;
    }

    public void setReceiverEmployeeId(long receiverEmployeeId) {
        this.receiverEmployeeId = receiverEmployeeId;
    }

    public String getReceiverPhone() {
        return this.receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

}
