package com.wufanbao.api.clientservice.entity;

import com.wufanbao.api.clientservice.common.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//奖品
public class Prize {
    private static List<Prize> prizes;
    private static Prize[] probabilityPrizes;
    private static long MaxPrizeCount = 1000;
    /**
     * 编号
     */
    private int id;
    //奖品名称
    private String name;
    //奖品类型
    private int type;
    //奖品价值
    private String value;
    /**
     * 概率（0.1代表10%，最多3位小数，即千分之一级）
     */
    private float probability;
    /**
     * 数量（该类奖品剩余数量）
     */
    private long count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    static {
        prizes = setPrizes();
        probabilityPrizes = setProbabilityPrizes();
    }

    private static List<Prize> setPrizes() {
        List<Prize> prizes = new ArrayList<>();

        Prize data1 = new Prize();//10%
        data1.setId(1);
        data1.setValue("1");
        data1.setType(1);
        data1.setName("1个积分");
        prizes.add(data1);

        Prize data2 = new Prize();//30%
        data2.setId(2);
        data2.setValue("5");
        data2.setType(1);
        data2.setName("5个积分");
        prizes.add(data2);

        Prize data3 = new Prize();//10%
        data3.setId(3);
        data3.setValue("25");
        data3.setType(1);
        data3.setName("25个积分");
        prizes.add(data3);

        Prize data4 = new Prize();//3%
        data4.setId(4);
        data4.setValue("55");
        data4.setType(1);
        data4.setName("55个积分");
        prizes.add(data4);

        Prize data5 = new Prize();//20%
        data5.setId(5);
        data5.setValue("233838379014");
        data5.setType(2);
        data5.setName("1元优惠券");
        prizes.add(data5);

        Prize data6 = new Prize();//24%
        data6.setId(6);
        data6.setValue("233838379013");
        data6.setType(2);
        data6.setName("2元优惠券");
        prizes.add(data6);

        Prize data7 = new Prize();//2%
        data7.setId(7);
        data7.setValue("233838379012");
        data7.setType(2);
        data7.setName("3元优惠券");
        prizes.add(data7);

        Prize data8 = new Prize();//1%
        data8.setId(8);
        data8.setValue("233838379011");
        data8.setType(2);
        data8.setName("5元优惠券");
        prizes.add(data8);

        Collections.shuffle(prizes);//混乱排序

        return prizes;
    }

    private static Prize[] setProbabilityPrizes() {
        Prize[] prizes = new Prize[8];

        Prize data1 = new Prize();//10%
        data1.setId(1);
        data1.setValue("1");
        data1.setType(1);
        data1.setProbability(0.1f);
        double count1 = MaxPrizeCount * 0.1;
        data1.setCount((long) count1);
        data1.setName("1个积分");
        prizes[0] = data1;

        Prize data2 = new Prize();//30%
        data2.setId(2);
        data2.setValue("5");
        data2.setType(1);
        data2.setProbability(0.3f);
        double count2 = MaxPrizeCount * 0.3;
        data2.setCount((long) count2);
        data2.setName("5个积分");
        prizes[1] = data2;

        Prize data3 = new Prize();//10%
        data3.setId(3);
        data3.setValue("25");
        data3.setType(1);
        data3.setProbability(0.1f);
        double count3 = MaxPrizeCount * 0.1;
        data3.setCount((long) count3);
        data3.setName("25个积分");
        prizes[2] = data3;

        Prize data4 = new Prize();//3%
        data4.setId(4);
        data4.setValue("55");
        data4.setType(1);
        data4.setProbability(0.03f);
        double count4 = MaxPrizeCount * 0.03;
        data4.setCount((long) count4);
        data4.setName("55个积分");
        prizes[3] = data4;

        Prize data5 = new Prize();//20%
        data5.setId(5);
        data5.setValue("233838379014");
        data5.setType(2);
        data5.setProbability(0.2f);
        double count5 = MaxPrizeCount * 0.2;
        data5.setCount((long) count5);
        data5.setName("1元优惠券");
        prizes[4] = data5;

        Prize data6 = new Prize();//24%
        data6.setId(6);
        data6.setValue("233838379013");
        data6.setType(2);
        data6.setProbability(0.24f);
        double count6 = MaxPrizeCount * 0.24;
        data6.setCount((long) count6);
        data6.setName("2元优惠券");
        prizes[5] = data6;

        Prize data7 = new Prize();//2%
        data7.setId(7);
        data7.setValue("233838379012");
        data7.setType(2);
        data7.setProbability(0.02f);
        double count7 = MaxPrizeCount * 0.02;
        data7.setCount((long) count7);
        data7.setName("3元优惠券");
        prizes[6] = data7;

        Prize data8 = new Prize();//1%
        data8.setId(8);
        data8.setValue("233838379011");
        data8.setType(2);
        data8.setProbability(0.01f);
        double count8 = MaxPrizeCount * 0.01;
        data8.setCount((long) count8);
        data8.setName("5元优惠券");
        prizes[7] = data8;

        return prizes;
    }

    public static List<Prize> getPrizes() {
        return prizes;
    }

    public static Prize[] getProbabilityPrizes() {
        return probabilityPrizes;
    }
}
