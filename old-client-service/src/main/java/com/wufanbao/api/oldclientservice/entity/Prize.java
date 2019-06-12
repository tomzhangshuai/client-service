package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * alphaWuFan
 * 奖品类
 *
 * @author Wang Zhiyuan
 * @date 2018-04-16 14:10
 **/
public class Prize {
    private long prizeId;
    private String prizeName;
    private String imageUrl;
    private int quantity;
    private int surplusQuantity;
    private int winRate;
    private long couponId;
    private int integral;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(long prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSurplusQuantity() {
        return surplusQuantity;
    }

    public void setSurplusQuantity(int surplusQuantity) {
        this.surplusQuantity = surplusQuantity;
    }

    public int getWinRate() {
        return winRate;
    }

    public void setWinRate(int winRate) {
        this.winRate = winRate;
    }

    public List<Prize> listPrize() {
        Prize prize1 = new Prize();
        prize1.setPrizeId(2018416143501L);
        prize1.setPrizeName("5元优惠券");
        prize1.setImageUrl("www.baidu.com");
        prize1.setQuantity(100);
        prize1.setSurplusQuantity(100);
        prize1.setWinRate(1);
        prize1.setCouponId(233838379011L);
        prize1.setType(1);

        Prize prize2 = new Prize();
        prize2.setPrizeId(2018416143502L);
        prize2.setPrizeName("3元优惠券");
        prize2.setImageUrl("www.baidu.com");
        prize2.setQuantity(200);
        prize2.setSurplusQuantity(200);
        prize2.setWinRate(2);
        prize2.setCouponId(233838379012L);
        prize2.setType(1);

        Prize prize3 = new Prize();
        prize3.setPrizeId(2018416143503L);
        prize3.setPrizeName("2元优惠券");
        prize3.setImageUrl("");
        prize3.setQuantity(2000);
        prize3.setSurplusQuantity(2000);
        prize3.setWinRate(20);
        prize3.setCouponId(233838379013L);
        prize3.setType(1);

        Prize prize4 = new Prize();
        prize4.setPrizeId(2018416143504L);
        prize4.setImageUrl("");
        prize4.setPrizeName("1元优惠券");
        prize4.setQuantity(2000);
        prize4.setSurplusQuantity(2000);
        prize4.setWinRate(20);
        prize4.setCouponId(233838379014L);
        prize4.setType(1);

        Prize prize5 = new Prize();
        prize5.setPrizeId(2018416143505L);
        prize5.setPrizeName("55个积分");
        prize5.setImageUrl("");
        prize5.setQuantity(300);
        prize5.setSurplusQuantity(300);
        prize5.setWinRate(3);
        prize5.setIntegral(55);
        prize5.setType(2);

        Prize prize6 = new Prize();
        prize6.setPrizeId(2018416143506L);
        prize6.setPrizeName("25个积分");
        prize6.setImageUrl("");
        prize6.setQuantity(1400);
        prize6.setSurplusQuantity(1400);
        prize6.setWinRate(14);
        prize6.setIntegral(25);
        prize6.setType(2);

        Prize prize7 = new Prize();
        prize7.setPrizeId(2018416143501L);
        prize7.setPrizeName("5个积分");
        prize7.setImageUrl("");
        prize7.setQuantity(3000);
        prize7.setSurplusQuantity(3000);
        prize7.setWinRate(30);
        prize7.setIntegral(5);
        prize7.setType(2);

        Prize prize8 = new Prize();
        prize8.setPrizeId(2018416143501L);
        prize8.setPrizeName("1个积分");
        prize8.setImageUrl("");
        prize8.setQuantity(1000);
        prize8.setSurplusQuantity(1000);
        prize8.setWinRate(10);
        prize8.setIntegral(1);
        prize8.setType(2);
        List<Prize> list = new ArrayList<>();
        list.add(prize1);
        list.add(prize2);
        list.add(prize3);
        list.add(prize4);
        list.add(prize5);
        list.add(prize6);
        list.add(prize7);
        list.add(prize8);
        return list;
    }

}
