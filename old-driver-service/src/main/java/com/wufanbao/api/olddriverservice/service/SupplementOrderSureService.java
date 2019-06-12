package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-11
 * Time:15:59
 */
public interface SupplementOrderSureService {
    /**
     * 补货单（填前仓）确认详情
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

    public List<DistributionProductLine> queryDistributionproductline(long distributionOrderId, long productGlobalId);

    public Integer updateDistributionproductline(DistributionProductLine distributionproductline);

    /**
     * 填后仓确认
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

    public List<SupplementOrderLine> querySMT(long supplementOrderId, long productGlobalId);

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
    ResponseInfo rollbackStatus(@Param("supplementOrderId") long supplementOrderId);

    /**
     * 查询头资料
     *
     * @param supplementOrderId
     * @return
     */
    public HeadTool queryHeadTool(long supplementOrderId);
}
