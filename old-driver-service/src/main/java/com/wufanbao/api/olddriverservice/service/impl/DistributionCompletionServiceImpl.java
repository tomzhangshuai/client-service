package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.dao.DistributionCompletionDao;
import com.wufanbao.api.olddriverservice.entity.DistributionCompletion;
import com.wufanbao.api.olddriverservice.service.DistributionCompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-10-26
 * Time:9:52
 */
@Service
@Transactional()
public class DistributionCompletionServiceImpl implements DistributionCompletionService {
    @Autowired
    private DistributionCompletionDao distributionCompletionDao;

    @Override
    public List<DistributionCompletion> queryDistributionCompletion(long supplementOrderId) {
        return distributionCompletionDao.queryDistributionCompletion(supplementOrderId);
    }
}
