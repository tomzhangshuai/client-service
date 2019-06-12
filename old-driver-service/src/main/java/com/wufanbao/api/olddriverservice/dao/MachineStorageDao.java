package com.wufanbao.api.olddriverservice.dao;

import com.wufanbao.api.olddriverservice.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MachineStorageDao {
    /**
     * 根据机器id获取机器内商品情况
     *
     * @param machineId
     * @return
     */
    List<MachineStorage> getMachineStorageInfo(@Param("machineId") long machineId);

    /**
     * 获取下架商品信息
     *
     * @param machineId
     * @return
     */
    List<Product> soldOutInfo(@Param("machineId") long machineId);

    /**
     * 根据机器id获取机器内商品的数量
     *
     * @param machineId 机器id
     * @return
     */
    List<Product> getProductInfo(@Param("machineId") long machineId);

    /**
     * 根据补货单id查询补货单详情
     *
     * @param supplementOrderId
     * @return
     */
    List<SupplementOrderLine> getSupplementOrderInfo(@Param("supplementOrderId") long supplementOrderId);

    /**
     * 更新机器内商品的位置
     *
     * @param machineStorage List<UserOrderLine> productGlobalInfo= JSON.parseArray(productGlobalList, UserOrderLine.class);
     * @return
     */
    int arrangeGoods(MachineStorage machineStorage);

    /**
     * 插入信息
     *
     * @param machineStorage
     * @return
     */
    int insertGoods(MachineStorage machineStorage);

    /**
     * 获取下货数量
     *
     * @param supplementOrderId
     * @return
     */
    List<SupplementOrderLine> dumpQuantity(@Param("supplementOrderId") long supplementOrderId);

    /**
     * 查询机器位置表中商品数量
     *
     * @param machineId
     * @return
     */
    List<Product> getMachineProduct(@Param("machineId") long machineId);

    /**
     * 修改机器内的商品
     */
    int updateProduct(@Param("machineId") long machineId, @Param("productGlobalId") long productGlobalId, @Param("quantity") int quantity, @Param("useableQuantity") int useableQuantity);

    /**
     * 获取机器商品
     */
    Product getProduct(@Param("machineId") long machineId, @Param("productGlobalId") long productGlobalId);

    /**
     * 根据机器id获取机器型号
     *
     * @param machineId
     * @return
     */
    MachineModel getMachineModel(@Param("machineId") long machineId);

    /**
     * 获取最大的坐标值
     *
     * @param machineId
     * @return
     */
    int getBigPosition(@Param("machineId") long machineId);


}
