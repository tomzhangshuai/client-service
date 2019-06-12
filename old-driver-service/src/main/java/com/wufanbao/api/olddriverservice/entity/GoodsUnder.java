package com.wufanbao.api.olddriverservice.entity;


/**
 * User:Wangshihao
 * Date:2017-09-08
 * Time:14:33
 */
public class GoodsUnder {
    private String machineName;//设备名字
    private String machineNo;//设备编号
    private String companyName;//加盟商
    private long supplementOrderId;//补货单Id
    private String productName;//商品名字
    private long productGlobalId;//商品id
    private Integer isStaple;//类型
    private Integer quantity;//预计商品配送数

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getSupplementOrderId() {
        return supplementOrderId;
    }

    public void setSupplementOrderId(long supplementOrderId) {
        this.supplementOrderId = supplementOrderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public Integer getIsStaple() {
        return isStaple;
    }

    public void setIsStaple(Integer isStaple) {
        this.isStaple = isStaple;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
