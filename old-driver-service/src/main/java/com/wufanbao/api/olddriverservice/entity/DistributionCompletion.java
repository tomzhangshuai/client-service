package com.wufanbao.api.olddriverservice.entity;

/**
 * User:Wangshihao
 * Date:2017-10-26
 * Time:9:41
 */
public class DistributionCompletion {
    private String machineName;
    private String machineNo;
    private String companyName;
    private long supplementOrderId;
    private String productName;
    private int dumpQuantity;
    private int actualQuantity;
    private int exceptionQuantity;
    private int backQuantity;
    private int isStaple;

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

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public int getExceptionQuantity() {
        return exceptionQuantity;
    }

    public void setExceptionQuantity(int exceptionQuantity) {
        this.exceptionQuantity = exceptionQuantity;
    }

    public int getBackQuantity() {
        return backQuantity;
    }

    public void setBackQuantity(int backQuantity) {
        this.backQuantity = backQuantity;
    }

    public int getIsStaple() {
        return isStaple;
    }

    public void setIsStaple(int isStaple) {
        this.isStaple = isStaple;
    }
}
