package com.wufanbao.api.clientservice.dao;

import com.wufanbao.api.clientservice.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDao {
    /**
     * 根据机器id 获取机器内的商品数据
     *
     * @param machineId
     * @return
     */
    List<Product> getProductsByMachineId(@Param("machineId") long machineId);

    /**
     * 获取机器商品数据
     *
     * @param machineId
     * @param productGlobalId
     * @return
     */
    Product getMachineProductByProductId(@Param("machineId") long machineId, @Param("productGlobalId") long productGlobalId);

    /**
     * 锁定机器商品库存
     *
     * @param productGlobalId
     * @param machineId
     * @param quantity
     * @return
     */
    int lockProduct(@Param("productGlobalId") long productGlobalId, @Param("machineId") long machineId, @Param("quantity") int quantity);


    Product getProduct(@Param("productGlobalId") long productGlobalId, @Param("machineId") long machineId);

    /**
     * 解锁机器商品库存
     *
     * @param machineId
     * @param productGlobalId
     * @param quantity
     * @return
     */
    int unLockProduct(@Param("machineId") long machineId, @Param("productGlobalId") long productGlobalId, @Param("quantity") int quantity);

    /**
     * 锁定机器库存
     *
     * @param machineId
     * @param quantity
     * @return
     */
    int lockMachineProduct(@Param("machineId") long machineId, @Param("quantity") int quantity);

    /**
     * 解锁机器库存
     *
     * @param machineId
     * @param quantity
     * @return
     */
    int unLockMachineProduct(@Param("machineId") long machineId, @Param("quantity") int quantity);

    /**
     * 解锁商品库存
     * @param machineId
     * @param productGlobalId
     * @return
     */
    int unLockProductPrepare(@Param("machineId") long machineId,@Param("productGlobalId") int productGlobalId);

    /**
     * 解锁机器库存
     * @param machineId
     * @return
     */
    int unLockMachineProductPrepare(long machineId);

    /**
     * 更新状态
     * @param productOffId
     * @return
     */
    int updateRepairStatus(long productOffId);

    /**
     * 根据productGlobalId获取商品名字
     * @param productGlobalId
     */
    String getProductGlobalName(long productGlobalId);
}
