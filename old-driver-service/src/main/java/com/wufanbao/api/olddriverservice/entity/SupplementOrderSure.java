package com.wufanbao.api.olddriverservice.entity;


/**
 * User:Wangshihao
 * Date:2017-09-11
 * Time:15:48
 */
public class SupplementOrderSure {
    private String machineName;//设备名字
    private String machineNo;//设备编号
    private String companyName;//加盟商
    private long supplementOrderId;//补货单Id
    private String productName;//商品名字
    private int dumpQuantity;//下货数量
    private int isStaple;//类型
    private long productGlobalId;//商品id
    private int quantity;//预计商品配送数
    private int actualQuantity;//实际上架数量

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

    public int getDumpQuantity() {
        return dumpQuantity;
    }

    public void setDumpQuantity(int dumpQuantity) {
        this.dumpQuantity = dumpQuantity;
    }

    public int getIsStaple() {
        return isStaple;
    }

    public void setIsStaple(int isStaple) {
        this.isStaple = isStaple;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }
}
