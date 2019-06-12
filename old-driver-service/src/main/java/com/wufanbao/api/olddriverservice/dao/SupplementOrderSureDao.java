package com.wufanbao.api.olddriverservice.dao;

import com.wufanbao.api.olddriverservice.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-09-11
 * Time:15:49
 */
public interface SupplementOrderSureDao {
    /**
     * 填前仓确认详情
     *
     * @param supplementOrderId
     * @return
     */
    public List<SupplementOrderSure> querySupplementOrderSure(long supplementOrderId);

    /**
     * 填后仓详情
     *
     * @param supplementOrderId
     * @return
     */
    public List<SupplementOrderSure> querysupplementOrderhou(long supplementOrderId);

    /**
     * 填前仓确定
     */
    public Integer updateSupplementOrderLine(SupplementOrderLine supplementOrderLint);

    public List<DistributionProductLine> queryDistributionproductline(@Param("distributionOrderId") long distributionOrderId, @Param("productGlobalId") long productGlobalId);

    public Integer updateDistributionproductline(DistributionProductLine distributionproductline);

    /**
     * 修改补货单数量
     *
     * @param distributionOrderId 配送单ID
     * @param productGlobalId     商品ID
     * @param exceptionQuantity   异常数量
     * @param extraQuantity       可用额外数量
     * @param actualQuantity      实际上架数量
     * @param surplusQuantity     剩余数量
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/3/16
     */
    int updateDistributionProductLine(@Param("distributionOrderId") long distributionOrderId,
                                      @Param("productGlobalId") long productGlobalId,
                                      @Param("exceptionQuantity") Integer exceptionQuantity,
                                      @Param("extraQuantity") Integer extraQuantity,
                                      @Param("actualQuantity") Integer actualQuantity,
                                      @Param("surplusQuantity") Integer surplusQuantity);

    /**
     * 根据补货单ID查询计划补货数
     *
     * @param supplementOrderId 补货单ID
     * @param productGlobalId   商品ID
     * @return java.lang.Long
     * @author Wang Zhiyuan
     * @date 2018/3/16
     */
    Integer getQuantityBySupplementOrderId(@Param("supplementOrderId") long supplementOrderId, @Param("productGlobalId") long productGlobalId);


    /**
     * 填后仓确认 exceptionQuantity  extraQuantity  actualQuantity  surplusQuantity  distributionOrderId  productGlobalId
     */
    public Integer updateAfterSupplementOrderAfter(SupplementOrderLine supplementOrderLint);

    /**
     * 添加退还商品
     */
    public void addSupplementOrderLine(SupplementOrderLine supplementOrderLint);

    /**
     * 获取商品id
     */
    public List<ProductGlobal> queryProductGuobal(String productName);

    public List<SupplementOrderLine> querySMT(@Param("supplementOrderId") long supplementOrderId, @Param("productGlobalId") long productGlobalId);

    /**
     * 查询商品id，名字，图片
     */
    public List<ProductGlobal> productGlobalAll();

    /**
     * 异常状态回滚
     *
     * @param supplementOrderId
     * @return
     */
    int rollbackStatus(@Param("supplementOrderId") long supplementOrderId);

    /**
     * 查询头资料
     *
     * @param supplementOrderId
     * @return
     */
    public HeadTool queryHeadTool(long supplementOrderId);

    /**
     * 根据补货单查询信息
     *
     * @param supplementOrderId 补货单Id
     * @return java.util.Map
     * @date 2018/7/4
     */
    Map supplementOrderInfo(long supplementOrderId);

    /**
     * 查询有没有下货操作的补货单
     *
     * @param machineId 机器ID
     * @return java.util.Map
     * @date 2018/7/4
     */
    Map getSupplementOrderByMachineId(@Param("machineId") long machineId, @Param("supplementOrderId") long supplementOrderId);

    /**
     * 设置补货单状态
     *
     * @param supplementOrderId 补货单ID
     * @return
     */
    int setSupplementStatus(@Param("supplementOrderId") long supplementOrderId, @Param("status") int status);

    /**
     * 获取补货单明细
     *
     * @param supplementOrderId
     * @return
     */
    public List<SupplementOrderLine> getSupplementOrderLines(@Param("supplementOrderId") long supplementOrderId);

    /**
     * 终止配送单时更新补货单明细
     *
     * @param supplementOrderLine
     * @return
     */
    public int updateOrderLineForTermination(SupplementOrderLine supplementOrderLine);

}



