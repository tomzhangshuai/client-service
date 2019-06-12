package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.DistributionOrderLine;

/**
 * User:wangshihao
 * Date:2017-10-28
 * Time:2:39
 */
public interface DistributionOrderLineService {
    /**
     * 修改配送单明细
     *
     * @param distributionOrderLine
     * @return
     */
    public int updateArriveTime(DistributionOrderLine distributionOrderLine);

    public int updateDistributedTime(DistributionOrderLine distributionOrderLine);

    public DistributionOrderLine queryId(long supplementOrderId);
}
