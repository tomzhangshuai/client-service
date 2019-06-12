package com.wufanbao.api.olddriverservice.dao;

import com.wufanbao.api.olddriverservice.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-07
 * Time:14:37
 * 配送
 */
public interface DistributionDao {
    /**
     * 配送
     */
    public List<Distribution> queryDistribution(@Param("distributionOrderId") long distributionOrderId);

    /**
     * 配送合
     */
    public List<DistributionOne> distributionlist(long distributionOrderId);

    /**
     * 总上架数
     */
    DistributionProductLine actualQuantity(@Param("distributionOrderId") long distributionOrderId);

    /**
     * 配送单仓库信息
     *
     * @param distributionOrderId
     * @return
     */
    Store getStoreInfo(@Param("distributionOrderId") long distributionOrderId);


}
