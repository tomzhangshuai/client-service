package com.wufanbao.api.oldclientservice.Tool;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 根据经纬度去计算距离
 */
public class Distance {
    /**
     * /赤道半径(单位m)
     */
    private static final double EARTH_RADIUS = 6378137;

    /**
     * 转化为弧度(rad)
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
     *
     * @param lon1 第一点的精度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的精度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位km
     */
    public static double GetDistance(BigDecimal lon1, BigDecimal lat1, BigDecimal lon2, BigDecimal lat2) {
        double radLat1 = rad(Double.parseDouble(lat1.toString()));
        double radLat2 = rad(Double.parseDouble(lat2.toString()));
        double a = radLat1 - radLat2;
        double b = rad(Double.parseDouble(lon1.toString())) - rad(Double.parseDouble(lon2.toString()));
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        //s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static String getDistanceKm(BigDecimal lon1, BigDecimal lat1, BigDecimal lon2, BigDecimal lat2) {
        double radLat1 = rad(Double.parseDouble(lat1.toString()));
        double radLat2 = rad(Double.parseDouble(lat2.toString()));
        double a = radLat1 - radLat2;
        double b = rad(Double.parseDouble(lon1.toString())) - rad(Double.parseDouble(lon2.toString()));
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        //s = Math.round(s * 10000) / 10000;
        DecimalFormat decimalFormat = new DecimalFormat("######0.00");
        String ss = decimalFormat.format(s / 1000) + "km";
        return ss;
    }

}
