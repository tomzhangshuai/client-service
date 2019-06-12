package com.wufanbao.api.oldclientservice.entity;

import java.util.List;

/**
 * user:WangZhiyuan
 */
public class MachineDistance {
    private int returnState;
    private List<MachineDistanceInfo> machineDistanceInfos;//机器的集合

    public int getReturnState() {
        return returnState;
    }

    public void setReturnState(int returnState) {
        this.returnState = returnState;
    }

    public List<MachineDistanceInfo> getMachineDistanceInfos() {
        return machineDistanceInfos;
    }

    public void setMachineDistanceInfos(List<MachineDistanceInfo> machineDistanceInfos) {
        this.machineDistanceInfos = machineDistanceInfos;
    }

    public static class MachineDistanceInfo {
        private double distance;//用户和机器的距离
        private Machine machine;//机器的信息
        private List<ProductInfo> productInfoList;//机器上商品的集合

        public List<ProductInfo> getProductInfoList() {
            return productInfoList;
        }

        public void setProductInfoList(List<ProductInfo> productInfoList) {
            this.productInfoList = productInfoList;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public Machine getMachine() {
            return machine;
        }

        public void setMachine(Machine machine) {
            this.machine = machine;
        }

    }

}
