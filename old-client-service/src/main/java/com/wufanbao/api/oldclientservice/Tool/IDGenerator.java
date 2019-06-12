package com.wufanbao.api.oldclientservice.Tool;

import java.util.*;
import java.lang.*;
import java.text.*;

public class IDGenerator {


    /**
     * 生成注册器
     */
    static Map<String, IDGenerator> generatorMap = null;


    /**
     * 时间起始点
     */
    private static Date beginTime;


    static {

        // DateFormat  dateFormat=DateFormat.getDateInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // beginTime = dateFormat.parse("2017-6-1");
            beginTime = sdf.parse("1994-4-4");
        } catch (Exception ex) {

        }

        generatorMap = new HashMap<String, IDGenerator>();
    }


    /**
     * 服务ID
     */
    static int ServerId = 0;


    /**
     * 后缀
     */
    private int suffixId = 0;


    /**
     * 前缀时间
     */
    private long preTime = 0;


    /**
     * 后缀最大值
     */
    private static final int suffixTotal = 32768;


    /**
     * 后缀长度
     */
    private static final int SuffixLen = 15;


    /**
     * 数据库定义长度
     */
    private static final int DbLen = 8;


    /**
     * 服务定义长度
     */
    private static final int ServerLen = 8;


    /**
     * 8位数据
     */
    private static final int Bit8Mod = 255;


    /**
     * 后缀mod
     */
    private static final int SuffixMod = 32767;


    /**
     * 时间30年，30位存储
     */
    private static final int TimeYearLen = 30;


    /**
     * 根据类型生成ID,数据库默认为0
     *
     * @param type 类型，ID在某一类型保证唯一
     * @param dbId 数据库默认为0
     * @return 生成的key
     */
    public static long generate(String type, int dbId)

    {
        IDGenerator kg = generatorMap.get(type);
        if (kg == null) {
            synchronized (IDGenerator.generatorMap) {
                kg = generatorMap.get(type);
                if (kg == null) {
                    kg = new IDGenerator();
                    generatorMap.put(type, kg);
                }
            }
        }

        long key = kg.generate();
        key |= ((long) dbId) << (ServerLen + TimeYearLen + SuffixLen);
        return key;

    }

    public static long generate(String type) {
        return generate(type, 0);
    }


    /**
     * 根据类型和源ID生成新的ID，新的ID生成后与源ID相同的数据库编码
     *
     * @param type       类型，ID在某一类型保证唯一
     * @param inheriteId 继承的父ID
     * @return 生成的key
     */
    public static long generateById(String type, long inheriteId)

    {
        IDGenerator kg = generatorMap.get(type);
        if (kg == null) {
            synchronized (IDGenerator.generatorMap) {
                kg = generatorMap.get(type);
                if (kg == null) {
                    kg = new IDGenerator();
                    generatorMap.put(type, kg);
                }
            }
        }
        long key = kg.generate();
        long dbId = (inheriteId >> (ServerLen + TimeYearLen + SuffixLen)) % Bit8Mod;
        key |= dbId << (ServerLen + TimeYearLen + SuffixLen);
        return key;

    }


    /**
     * 获取id生成时间
     *
     * @param id
     * @return id生成时间
     */
    public static Date GetTime(long id) {

        long timeSec = (id >> SuffixLen) % ((long) Math.pow(2, TimeYearLen) - 1);
        Date date = new Date();
        date.setTime(beginTime.getTime() + (timeSec * 1000));
        return date;
    }

    /**
     * 生成一个id
     *
     * @return 生成的id
     */
    private long generate() {
        long value = 0;

        synchronized (this) {

            Date date = new Date();
            //long times = date.getTime();

            long time = (long) ((date.getTime() - beginTime.getTime()) / 1000);

            if (time != preTime) {
                preTime = time;
                suffixId = 1;
            }

            value |= time << SuffixLen;

            value |= (long) ((suffixId++) & SuffixMod);

        }
        return value;
    }


    public static void main(String[] args) {

        long id = IDGenerator.generate("User", 0);
        System.out.println("User id1 is: " + id);

        id = IDGenerator.generate("User", 0);
        System.out.println("User id2 is: " + id);

        id = IDGenerator.generate("User", 0);
        System.out.println("User id3 is: " + id);

        id = IDGenerator.generate("User", 0);
        System.out.println("User id4 is: " + id);

        id = IDGenerator.generateById("UserCoupon", id);
        System.out.println("UserCoupon id1 is: " + id);


        id = IDGenerator.generate("User", 0);
        System.out.println("User id5 is: " + id);

        id = IDGenerator.generateById("UserCoupon", id);
        System.out.println("UserCoupon id2 is: " + id);

        id = IDGenerator.generate("User", 0);
        System.out.println("User id6 is: " + id);

        id = IDGenerator.generateById("UserCoupon", id);
        System.out.println("UserCoupon id3 is: " + id);

    }
}
