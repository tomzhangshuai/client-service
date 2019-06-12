package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.DistributionCompletion;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-10-26
 * Time:9:51
 */
public interface DistributionCompletionService {
    /**
     * 补货单完成详情
     *
     * @param supplementOrderId
     * @return
     */
    public List<DistributionCompletion> queryDistributionCompletion(long supplementOrderId);
}
