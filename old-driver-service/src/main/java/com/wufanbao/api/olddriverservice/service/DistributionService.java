package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.*;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-07
 * Time:18:32
 */
public interface DistributionService {
    /**
     * 配送
     */
    public List<Distribution> queryDistribution(long distributionOrderId);

    /**
     * 配送合
     */
    public List<DistributionOne> distributionlist(long distributionOrderId);

    /**
     * 去重
     */
    DistributionProductLine actualQuantity(long distributionOrderId);

    /**
     * 配送单仓库信息
     *
     * @param distributionOrderId
     * @return
     */
    Store getStoreInfo(long distributionOrderId);
}
