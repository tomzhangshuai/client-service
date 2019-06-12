
package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:13:45
 */
public class UserOrder {
    private long userOrderId;//UserOrderId
    private long joinCompanyId;//加盟商
    private long machineId;//机器
    private long userId;//用户
    private String description;//订单说明
    private double amount;//订单金额
    private double actualAmount;//实付金额
    private double receiveAmount;//实际到账金额
    private int takeNo;

    public int getTakeNo() {
        return takeNo;
    }

    public void setTakeNo(int takeNo) {
        this.takeNo = takeNo;
    }

    /**
     * 订单状态
     * -2,Invalid,已失效
     * -1,Refunded,已退款
     * 0,Canceled,已取消
     * 1,Created,已创建
     * 2,StayPay,待支付
     * 3,Paid,已支付
     * 4,Making,制作中
     * 5,Took,已领取
     * 6,StayAssess,待评价
     * 7,Completed,已完成
     */
    private int status;//订单状态
    /**
     * 1,Offline,线下支付
     * 2,Weixin,微信
     * 3,Alipay,支付宝
     * 4,Account,余额支付
     * 5,银行卡
     */
    private int payType;//支付方式
    private Timestamp createTime;//下单时间
    private Timestamp completedTime;//完结时间
    private Timestamp payTime;//支付时间
    private Timestamp takeTime;//取单时间
    private Timestamp assessTime;//评价时间
    private Timestamp cancelTime;//取消时间
    private Timestamp refundTime;//退款时间
    private Timestamp InvalidTime;//失效时间
    private Integer discountType;//使用优惠类型。。。1,优惠券
    private long discountId;//优惠Id
    private boolean isMadeInvoice;//是否已开发票
    private String evaluation;//用户评价
    private int easyFind;//机器是否好找
    private BigDecimal discountAmount;//优惠券金额
    private BigDecimal companyPayAmount;//企业付金额
    private BigDecimal familyPayAmount;//家庭付额度

    public BigDecimal getFamilyPayAmount() {
        return familyPayAmount;
    }

    public void setFamilyPayAmount(BigDecimal familyPayAmount) {
        this.familyPayAmount = familyPayAmount;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getCompanyPayAmount() {
        return companyPayAmount;
    }

    public void setCompanyPayAmount(BigDecimal companyPayAmount) {
        this.companyPayAmount = companyPayAmount;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public int getEasyFind() {
        return easyFind;
    }

    public void setEasyFind(int easyFind) {
        this.easyFind = easyFind;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public boolean isMadeInvoice() {
        return isMadeInvoice;
    }

    public void setMadeInvoice(boolean madeInvoice) {
        isMadeInvoice = madeInvoice;
    }

    private UserOrderLine userOrderLine;
    private ProductGlobal productGlobal;

    public long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public long getJoinCompanyId() {
        return joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public double getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(double receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Timestamp completedTime) {
        this.completedTime = completedTime;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Timestamp getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Timestamp takeTime) {
        this.takeTime = takeTime;
    }

    public Timestamp getAssessTime() {
        return assessTime;
    }

    public void setAssessTime(Timestamp assessTime) {
        this.assessTime = assessTime;
    }

    public Timestamp getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Timestamp cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Timestamp getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Timestamp refundTime) {
        this.refundTime = refundTime;
    }

    public Timestamp getInvalidTime() {
        return InvalidTime;
    }

    public void setInvalidTime(Timestamp invalidTime) {
        InvalidTime = invalidTime;
    }

    public UserOrderLine getUserOrderLine() {
        return userOrderLine;
    }

    public void setUserOrderLine(UserOrderLine userOrderLine) {
        this.userOrderLine = userOrderLine;
    }

    public ProductGlobal getProductGlobal() {
        return productGlobal;
    }

    public void setProductGlobal(ProductGlobal productGlobal) {
        this.productGlobal = productGlobal;
    }
}