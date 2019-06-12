package com.wufanbao.api.olddriverservice.dao;

import com.wufanbao.api.olddriverservice.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-08
 * Time:14:37
 */
public interface GoodsUnderDao {
    /**
     * 下货详情
     */
    public List<GoodsUnder> queryGoodsUnders(long supplementOrderId);

    public SupplementOrder queryGoodsUnder(long supplementOrderId);

    public List<SupplementOrderLine> qureySupp(long supplementOrderId);

    public List<DistributionProductLine> qureyDBPL(@Param("distributionOrderId") long distributionOrderId, @Param("productGlobalId") long productGlobalId);

    /**
     * 下货数量
     */
    public int updateSupplementOrderLine(SupplementOrderLine supplementOrderLint);

    public SupplementOrderLine querySMT(@Param("supplementOrderId") long supplementOrderId, @Param("productGlobalId") long productGlobalId);

    /**
     * 修改补货单状态
     */
    public int updateSupplementOrderStatus(SupplementOrder supplementOrder);

    /**
     * 根据机器id获取机器内商品的数量
     *
     * @param machineId 机器id
     * @return
     */
    List<Product> ProductInfo(@Param("machineId") long machineId);

    /**
     * 根据补货单id查询补货单详情
     *
     * @param supplementOrderId
     * @return
     */
    List<SupplementOrderLine> SupplementOrderInfo(@Param("supplementOrderId") long supplementOrderId);

    /**
     * @param supplementOrderId
     * @param productGlobalId
     * @return com.wufanbao.api.olddriverservice.entity.SupplementOrderLine
     * @author Wang Zhiyuan
     * @date 2018/3/19
     */
    SupplementOrderLine supplementOrderInfoOne(@Param("supplementOrderId") long supplementOrderId, @Param("productGlobalId") long productGlobalId);
}
