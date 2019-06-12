package com.wufanbao.api.olddriverservice.dao;

import com.wufanbao.api.olddriverservice.entity.OpenStatus;
import com.wufanbao.api.olddriverservice.entity.OpenStorehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-13
 * Time:15:14
 */
public interface OpenStorehouseDao {
    /**
     * 开仓操作
     */
    public List<OpenStorehouse> openStorehouseList(@Param("machineNo") String machineNo, @Param("supplementOrderId") long supplementOrderId);

    /**
     * 开仓返回状态
     */
    public OpenStatus openStatus(long supplementOrderId);

    /**
     * 商品类型集合
     *
     * @param supplementOrderId
     * @return java.util.List<java.lang.Integer>
     * @author Wang Zhiyuan
     * @date 2018/3/22
     */
    List<Integer> productTypes(@Param("supplementOrderId") long supplementOrderId);
}
