package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.Area;
import com.wufanbao.api.oldclientservice.entity.Machine;
import com.wufanbao.api.oldclientservice.entity.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface HomePageDao {
    /**
     * 根据定位地址提供最近的机器
     *
     * @param areaName 城市名称
     * @param x        经线
     * @param y        纬线
     * @return
     */
    Machine getNearMachine(@Param("areaName") String areaName, @Param("x") BigDecimal x, @Param("y") BigDecimal y);

    /**
     * 根据机器id查询机器上的商品
     *
     * @param machineId 机器id
     * @return
     */
    List<ProductInfo> getProductInfo(@Param("machineId") long machineId);

    /**
     * 查询离我最近的五台机器
     *
     * @param areaName 城市名称
     * @param x        经线
     * @param y        纬线
     * @return
     */
    List<Machine> getNearMachine5(@Param("areaName") String areaName, @Param("x") BigDecimal x, @Param("y") BigDecimal y);

    /**
     * 根据已开通城市名称查询某台机器
     *
     * @param areaName 城市名称
     * @return
     */
    Machine getNearMachineByAdds(@Param("areaName") String areaName);

    /**
     * 查询已经开放的
     *
     * @return
     */
    List<Area> getOpenCity();

    /**
     * 查询城市是否开放
     *
     * @param areaName
     * @return
     */
    Area whetherOpen(@Param("areaName") String areaName);

    /**
     * 根据机器ID查询机器位置
     *
     * @param machineId
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @date 2018/3/13
     */
    Machine machineArea(@Param("machineId") long machineId);

    /**
     * 查询机器是否为空
     *
     * @param machineId
     * @return
     */
    int machineIfNull(@Param("machineId") long machineId);

    /**
     * 根据机器投放区域查询投放地址
     *
     * @param putAreaId 投放区域ID
     * @return java.util.List<java.util.Map>
     * @author Wang Zhiyuan
     * @date 2018/4/16
     */
    List<Map> getMachineByAreaId(@Param("putAreaId") int putAreaId);

    /**
     * 根据机器ID查询机器信息
     *
     * @param machineId 机器ID
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @date 2018/6/19
     */
    Machine getMachineInfoByMachineId(@Param("machineId") Long machineId);

}
