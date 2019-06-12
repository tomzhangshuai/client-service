package com.wufanbao.api.distributionservice.transfer;


/**
 * 补货单明细
 */
public class SupplementOrderLineInfo {

    private String machineName;//设备名字
    private String machineNo;//设备编号
    private String companyName;//加盟商
    private long supplementOrderId;//补货单Id
    private String productName;//商品名字
    private int isStaple;//类型
    private long productGlobalId;//商品id
    private boolean lockQuantity;//是否锁定补货数量

    private int expectQuantity; //期望库存
    private int quantity;//预计商品配送数
    private int dumpQuantity;//下货数量
    private int actualQuantity;//实际上架数量
    private int backQuantity;//退回数量
    private int exceptionQuantity;//异常数量

    public boolean isLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(boolean lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public int getExpectQuantity() {
        return expectQuantity;
    }

    public void setExpectQuantity(int expectQuantity) {
        this.expectQuantity = expectQuantity;
    }

    public int getBackQuantity() {
        return backQuantity;
    }

    public void setBackQuantity(int backQuantity) {
        this.backQuantity = backQuantity;
    }

    public int getExceptionQuantity() {
        return exceptionQuantity;
    }

    public void setExceptionQuantity(int exceptionQuantity) {
        this.exceptionQuantity = exceptionQuantity;
    }


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
