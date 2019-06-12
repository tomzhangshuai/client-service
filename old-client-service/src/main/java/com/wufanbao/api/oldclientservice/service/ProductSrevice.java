package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.Machine;
import com.wufanbao.api.oldclientservice.entity.Product;
import com.wufanbao.api.oldclientservice.entity.QueryProduct;
import com.wufanbao.api.oldclientservice.entity.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-08
 * Time:16:02
 */
public interface ProductSrevice {

    /**
     * 根据餐食id获取餐食数量
     *
     * @param productGlobalId
     * @param machineId
     * @return
     */
    List<QueryProduct> queryProduct(@Param("productGlobalId") long productGlobalId, @Param("machineId") long machineId);

    /**
     * 根据餐食id获取餐食数量
     *
     * @param userOrderId
     * @return
     */
    List<QueryProduct> queryProducts(long userOrderId);

    List<Machine> queryMachine(long machineId);

    /**
     * 查询用户id和优惠券id
     */
    UserOrder queryOrder(long userOrderId);
}
