package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.dao.DistributionDao;
import com.wufanbao.api.olddriverservice.entity.*;
import com.wufanbao.api.olddriverservice.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-07
 * Time:18:32
 */
@Service
@Transactional
public class DistributionServiceimpl implements DistributionService {

    @Autowired
    private DistributionDao distributionDao;

    @Override
    public List<Distribution> queryDistribution(long distributionOrderId) {
        return distributionDao.queryDistribution(distributionOrderId);
    }

    @Override
    public List<DistributionOne> distributionlist(long distributionOrderId) {
        return distributionDao.distributionlist(distributionOrderId);
    }

    /**
     * 去重
     *
     * @param distributionOrderId
     */
    @Override
    public DistributionProductLine actualQuantity(long distributionOrderId) {
        return distributionDao.actualQuantity(distributionOrderId);
    }

    /**
     * 配送单仓库信息
     *
     * @param distributionOrderId
     * @return
     */
    @Override
    public Store getStoreInfo(long distributionOrderId) {
        return distributionDao.getStoreInfo(distributionOrderId);
    }
}
