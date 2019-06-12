package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.Area;
import com.wufanbao.api.oldclientservice.entity.MachineDistance;
import com.wufanbao.api.oldclientservice.entity.ProductInfo;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * User:WangZhiyuan
 */
public interface HomePageService {
    /**
     * 根据定位地址提供最近的机器
     *
     * @param name 城市名称
     * @param x    经线
     * @param y    纬线
     * @return
     */
    MachineDistance.MachineDistanceInfo getNearMachine(String name, BigDecimal x, BigDecimal y);

    /**
     * 根据机器id查询机器上的商品
     *
     * @param machineId 机器id
     * @return
     */
    List<ProductInfo> getProductInfo(long machineId);

    /**
     * 查询离我最近的五台机器
     *
     * @param areaName 城市名称
     * @param x        经线
     * @param y        纬线
     * @return
     */
    MachineDistance getNearMachine5(String areaName, BigDecimal x, BigDecimal y);

    /**
     * 获取附近五台机器的信息还有机器上的商品
     *
     * @param areaName 城市名称
     * @param x        经线
     * @param y        纬线
     * @return
     */
    MachineDistance getNearMachine5ProductInfo(String areaName, BigDecimal x, BigDecimal y);

    /**
     * 根据机器Id展示附近五台机器
     *
     * @param machineId
     * @return com.wufanbao.api.oldclientservice.entity.MachineDistance
     * @author Wang Zhiyuan
     * @date 2018/3/13
     */
    MachineDistance getProductInfoByMachineId(long machineId);

    /**
     * 获取首页广告
     *
     * @return
     */
    Map getAppAd();

    /**
     * 根据已开通城市名称查询某台机器上商品信息
     *
     * @param areaName 城市名称
     * @return
     */
    MachineDistance getNearMachineByAddsProductInfo(String areaName);

    /**
     * 获取已经开放的城市
     *
     * @return
     */
    Map getOpenCity();

    /**
     * 查询机器是否为空
     *
     * @param machineId
     * @return
     */
    boolean machineIfNull(long machineId);

    /**
     * 版本控制
     *
     * @return
     */
    ResponseInfo versionCode();

    /**
     * 根据机器投放区域查询投放地址
     *
     * @param areaId 区域Id
     * @param x      经线
     * @param y      纬线
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/4/16
     */
    ResponseInfo getMachineByAreaId(int areaId, BigDecimal x, BigDecimal y);
}
