package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.List;

public class MachineLocation {
    private BigDecimal x;//经度
    private BigDecimal y;//纬度
    private String machineNo;//机器编号
    private String seekPhotos;//机器位置图
    private double distance;
    private List<seekPhotosVo> seekPhotosVoList;


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public static class seekPhotosVo {
        private String seekPhotosVo;

        public String getSeekPhotosVo() {
            return seekPhotosVo;
        }

        public void setSeekPhotosVo(String seekPhotosVo) {
            this.seekPhotosVo = seekPhotosVo;
        }
    }


    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public String getSeekPhotos() {
        return seekPhotos;
    }

    public void setSeekPhotos(String seekPhotos) {
        this.seekPhotos = seekPhotos;
    }

    public List<seekPhotosVo> getSeekPhotosVoList() {
        return seekPhotosVoList;
    }

    public void setSeekPhotosVoList(List<seekPhotosVo> seekPhotosVoList) {
        this.seekPhotosVoList = seekPhotosVoList;
    }
}
