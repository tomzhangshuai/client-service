package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// ProductQR,商品二维码
public class ProductQR {
    //QRCode,
    private String qRCode;
    //ProductGlobalId,
    private long productGlobalId;
    //LotNo,
    private String lotNo;

    public String getQRCode() {
        return this.qRCode;
    }

    public void setQRCode(String qRCode) {
        this.qRCode = qRCode;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public String getLotNo() {
        return this.lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

}
