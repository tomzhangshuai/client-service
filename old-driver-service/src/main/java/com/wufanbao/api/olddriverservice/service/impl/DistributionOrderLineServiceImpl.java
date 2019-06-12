package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.dao.DistributionOrderLineDao;
import com.wufanbao.api.olddriverservice.entity.DistributionOrderLine;
import com.wufanbao.api.olddriverservice.service.DistributionOrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User:wangshihao
 * Date:2017-10-28
 * Time:2:40
 */
@Service
@Transactional
public class DistributionOrderLineServiceImpl implements DistributionOrderLineService {
    @Autowired
    private DistributionOrderLineDao distributionOrderLineDao;

    @Override
    public int updateArriveTime(DistributionOrderLine distributionOrderLine) {
        return distributionOrderLineDao.updateArriveTime(distributionOrderLine);
    }

    @Override
    public int updateDistributedTime(DistributionOrderLine distributionOrderLine) {
        return distributionOrderLineDao.updateDistributedTime(distributionOrderLine);
    }

    @Override
    public DistributionOrderLine queryId(long supplementOrderId) {
        return distributionOrderLineDao.queryId(supplementOrderId);
    }
}
