package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.Machine;
import com.wufanbao.api.oldclientservice.entity.Product;
import com.wufanbao.api.oldclientservice.entity.QueryProduct;
import com.wufanbao.api.oldclientservice.entity.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-04
 * Time:14:26
 */
public interface ProductDao {
    /**
     * 获取餐食可用数量
     *
     * @param productGlobalId
     * @param machineId
     * @return
     */
    public List<QueryProduct> queryProduct(@Param("productGlobalId") long productGlobalId, @Param("machineId") long machineId);

    /**
     * 根据餐食id获取餐食数量
     *
     * @param userOrderId
     * @return
     */
    public List<QueryProduct> queryProducts(long userOrderId);

    public List<Machine> queryMachine(long machineId);

    /**
     * 查询用户id和优惠券id
     */
    public UserOrder queryOrder(long userOrderId);

    /**
     * 锁定商品库存
     *
     * @param machineId       机器Id
     * @param productGlobalId 商品ID
     * @param quantity        商品数量
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/4/25
     */
    int unLockProduct(@Param("machineId") long machineId, @Param("productGlobalId") long productGlobalId, @Param("quantity") int quantity);

    /**
     * 解锁商品库存
     *
     * @param productGlobalId
     * @param machineId
     * @param quantity
     * @return
     */
    int lockProduct(@Param("productGlobalId") long productGlobalId, @Param("machineId") long machineId, @Param("quantity") int quantity);

    /**
     * 锁定机器商品库存
     *
     * @param machineId
     * @param quantity
     * @return
     */
    int lockMachineProduct(@Param("machineId") long machineId, @Param("quantity") int quantity);

    int unLockMachineProduct(@Param("machineId") long machineId, @Param("quantity") int quantity);
}
