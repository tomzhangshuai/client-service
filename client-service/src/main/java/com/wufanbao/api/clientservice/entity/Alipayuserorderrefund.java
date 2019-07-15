package com.wufanbao.api.clientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Alipayuserorderrefund {

  private long reqId;
  //网关返回码
  private String code;
  //网关返回码描述
  private String msg;
  //业务返回码
  private String subCode;
  //业务返回码描述
  private String subMsg;
  //签名
  private String sign;
  //支付宝交易号
  private String tradeNo;
  //商户订单号
  private String outTradeNo;
  //用户的登录id
  private String buyerLogonId;
  //本次退款是否发生了资金变化
  private String fundChange;
  //退款总金额
  private String refundFee;
  //退款币种信息
  private String refundCurrency;
  //退款支付时间
  private Date gmtRefundPay;
  //退款使用的资金渠道
  private String refundDetailItemList;
  //交易在支付时候的门店名称
  private String storeName;
  //买家在支付宝的用户id
  private String buyerUserId;
  //退回的前置资产列表
  private String refundPresetPaytoolList;
/*  //本次退款针对收款方的退收费金额
  private String refundChargeAmount;*/
  //退款清算编号，用于清算对账使用
  private String refundSettlementId;
  //本次退款金额中买家退款金额
  private String presentRefundBuyerAmount;
  //本次退款金额中平台优惠退款金额
  private String presentRefundDiscountAmount;
  //本次退款金额中商家优惠退款金额
  private String presentRefundMdiscountAmount;

  /*
   `ReqId` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `Code` VARCHAR(64) NOT NULL COMMENT '',
  `Msg` VARCHAR(128) NOT NULL COMMENT '',
  `SubCode` VARCHAR(128) DEFAULT NULL COMMENT '',
  `SubMsg` VARCHAR(64) DEFAULT NULL COMMENT '',
  `Sign` VARCHAR(256) NOT NULL COMMENT '',
  `TradeNo` VARCHAR(64) NOT NULL COMMENT '',
  `OutTradeNo` VARCHAR(64) NOT NULL COMMENT '',
  `BuyerLogonId` VARCHAR(100) NOT NULL COMMENT '',
  `FundChange` VARCHAR(1) NOT NULL COMMENT '',
  `RefundFee` DECIMAL(11,0) NOT NULL,
  `RefundCurrency` VARCHAR(8) DEFAULT NULL COMMENT '',
  `GmtRefundPay` VARCHAR(32) NOT NULL COMMENT '',
  `RefundDetailItemList` VARCHAR(1024) DEFAULT NULL COMMENT '',
  `StoreName` VARCHAR(512) DEFAULT NULL COMMENT '',
  `BuyerUserId` VARCHAR(28) DEFAULT NULL COMMENT '',
  `RefundPresetPaytoolList` VARCHAR(1024) DEFAULT NULL COMMENT '',
  `RefundChargeAmount` VARCHAR(11) DEFAULT NULL COMMENT '； ',
  `RefundSettlementId` VARCHAR(64) DEFAULT NULL COMMENT '； ',
  `PresentRefundBuyerAmount` VARCHAR(11) DEFAULT NULL COMMENT '',
  `PresentRefundDiscountAmount` VARCHAR(11) DEFAULT NULL COMMENT '',
  `PresentRefundMdiscountAmount` VARCHAR(11) DEFAULT NULL COMMENT '',
  PRIMARY KEY (`reqId`
   */

  public long getReqId() {
    return reqId;
  }

  public void setReqId(long reqId) {
    this.reqId = reqId;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }


  public String getSubCode() {
    return subCode;
  }

  public void setSubCode(String subCode) {
    this.subCode = subCode;
  }


  public String getSubMsg() {
    return subMsg;
  }

  public void setSubMsg(String subMsg) {
    this.subMsg = subMsg;
  }


  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }


  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }


  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }


  public String getBuyerLogonId() {
    return buyerLogonId;
  }

  public void setBuyerLogonId(String buyerLogonId) {
    this.buyerLogonId = buyerLogonId;
  }


  public String getFundChange() {
    return fundChange;
  }

  public void setFundChange(String fundChange) {
    this.fundChange = fundChange;
  }


  public String getRefundFee() {
    return refundFee;
  }

  public void setRefundFee(String refundFee) {
    this.refundFee = refundFee;
  }


  public String getRefundCurrency() {
    return refundCurrency;
  }

  public void setRefundCurrency(String refundCurrency) {
    this.refundCurrency = refundCurrency;
  }


  public Date getGmtRefundPay() {
    return gmtRefundPay;
  }

  public void setGmtRefundPay(Date gmtRefundPay) {
    this.gmtRefundPay = gmtRefundPay;
  }


  public String getRefundDetailItemList() {
    return refundDetailItemList;
  }

  public void setRefundDetailItemList(String refundDetailItemList) {
    this.refundDetailItemList = refundDetailItemList;
  }


  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }


  public String getBuyerUserId() {
    return buyerUserId;
  }

  public void setBuyerUserId(String buyerUserId) {
    this.buyerUserId = buyerUserId;
  }


  public String getRefundPresetPaytoolList() {
    return refundPresetPaytoolList;
  }

  public void setRefundPresetPaytoolList(String refundPresetPaytoolList) {
    this.refundPresetPaytoolList = refundPresetPaytoolList;
  }


 /* public String getRefundChargeAmount() {
    return refundChargeAmount;
  }

  public void setRefundChargeAmount(String refundChargeAmount) {
    this.refundChargeAmount = refundChargeAmount;
  }
*/

  public String getRefundSettlementId() {
    return refundSettlementId;
  }

  public void setRefundSettlementId(String refundSettlementId) {
    this.refundSettlementId = refundSettlementId;
  }


  public String getPresentRefundBuyerAmount() {
    return presentRefundBuyerAmount;
  }

  public void setPresentRefundBuyerAmount(String presentRefundBuyerAmount) {
    this.presentRefundBuyerAmount = presentRefundBuyerAmount;
  }


  public String getPresentRefundDiscountAmount() {
    return presentRefundDiscountAmount;
  }

  public void setPresentRefundDiscountAmount(String presentRefundDiscountAmount) {
    this.presentRefundDiscountAmount = presentRefundDiscountAmount;
  }


  public String getPresentRefundMdiscountAmount() {
    return presentRefundMdiscountAmount;
  }

  public void setPresentRefundMdiscountAmount(String presentRefundMdiscountAmount) {
    this.presentRefundMdiscountAmount = presentRefundMdiscountAmount;
  }

}
