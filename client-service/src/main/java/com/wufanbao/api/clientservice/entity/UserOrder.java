package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;

// UserOrder,用户销售订单
public class UserOrder {
    //UserOrderId,
    private long userOrderId;
    //加盟商,
    private long joinCompanyId;
    //机器,
    private long machineId;
    //取餐地址
    private String address;
    //用户,
    private long userId;
    //订单相关的商品图片
    private String imageUrl;
    //订单说明,
    private String description;
    //订单金额,
    private BigDecimal amount;
    //实付金额,
    private BigDecimal actualAmount;
    //实际到账金额,实收金额
    private BigDecimal receiveAmount;
    //订单状态,
    private int status;
    //支付方式,
    private int payType;
    //状态值
    //0：未推送；
    //1：30分钟后未取餐失效
    private int MessageStatus;

    //下单时间,
    private Date createTime;
    //支付时间,
    private Date payTime;
    //取餐扫码时间,
    private Date takeTime;
    //取餐完成时间,
    private Date tookTime;
    //评价时间,
    private Date assessTime;
    //完成时间,
    private Date completedTime;
    //取消时间,
    private Date cancelTime;
    //退款时间,
    private Date refundTime;
    //失效时间,
    private Date invalidTime;
    //使用优惠类型,
    private int discountType;
    //优惠Id,
    private long discountId;
    //是否已开发票,
    private boolean isMadeInvoice;
    //评价,
    private String evaluation;
    //机器易寻找,
    private int easyFind;
    //优惠金额,
    private BigDecimal discountAmount;
    //企业付金额,
    private BigDecimal companyPayAmount;
    //家庭付款
    private BigDecimal familyPayAmount;
    //取餐编号,
    private String takeNo;
    //订单来源
    private int originateId;

    public int getMessageStatus() {
        return MessageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        MessageStatus = messageStatus;
    }

    public int getOriginateId() {
        return originateId;
    }

    public void setOriginateId(int originateId) {
        this.originateId = originateId;
    }

    public long getUserOrderId() {
        return this.userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public long getJoinCompanyId() {
        return this.joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getActualAmount() {
        return this.actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getReceiveAmount() {
        return this.receiveAmount;
    }

    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPayType() {
        return this.payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return this.payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getTakeTime() {
        return this.takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public Date getTookTime() {
        return this.tookTime;
    }

    public void setTookTime(Date tookTime) {
        this.tookTime = tookTime;
    }

    public Date getAssessTime() {
        return this.assessTime;
    }

    public void setAssessTime(Date assessTime) {
        this.assessTime = assessTime;
    }

    public Date getCompletedTime() {
        return this.completedTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
    }

    public Date getCancelTime() {
        return this.cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getRefundTime() {
        return this.refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getInvalidTime() {
        return this.invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public int getDiscountType() {
        return this.discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    public long getDiscountId() {
        return this.discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public boolean getIsMadeInvoice() {
        return this.isMadeInvoice;
    }

    public void setIsMadeInvoice(boolean isMadeInvoice) {
        this.isMadeInvoice = isMadeInvoice;
    }

    public String getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public int getEasyFind() {
        return this.easyFind;
    }

    public void setEasyFind(int easyFind) {
        this.easyFind = easyFind;
    }

    public BigDecimal getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getCompanyPayAmount() {
        return this.companyPayAmount;
    }

    public void setCompanyPayAmount(BigDecimal companyPayAmount) {
        this.companyPayAmount = companyPayAmount;
    }

    public BigDecimal getFamilyPayAmount() {
        return familyPayAmount;
    }

    public void setFamilyPayAmount(BigDecimal familyPayAmount) {
        this.familyPayAmount = familyPayAmount;
    }

    public String getTakeNo() {
        return this.takeNo;
    }

    public void setTakeNo(String takeNo) {
        this.takeNo = takeNo;
    }

}
