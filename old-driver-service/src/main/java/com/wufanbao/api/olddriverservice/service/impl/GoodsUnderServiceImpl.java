package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.dao.GoodsUnderDao;
import com.wufanbao.api.olddriverservice.entity.*;
import com.wufanbao.api.olddriverservice.service.GoodsUnderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-08
 * Time:14:42
 */
@Service
@Transactional
public class GoodsUnderServiceImpl implements GoodsUnderService {

    @Autowired
    private GoodsUnderDao goodsUnderDao;

    @Override
    public List<GoodsUnder> queryGoodsUnders(long supplementOrderId) {
        return goodsUnderDao.queryGoodsUnders(supplementOrderId);
    }

    @Override
    public SupplementOrder queryGoodsUnder(long supplementOrderId) {
        return goodsUnderDao.queryGoodsUnder(supplementOrderId);
    }

    @Override
    public List<SupplementOrderLine> qureySupp(long supplementOrderId) {
        return goodsUnderDao.qureySupp(supplementOrderId);
    }

    @Override
    public List<DistributionProductLine> qureyDBPL(long distributionOrderId, long productGlobalId) {
        return goodsUnderDao.qureyDBPL(distributionOrderId, productGlobalId);
    }

    @Override
    public int updateSupplementOrderLint(SupplementOrderLine supplementOrderLine) {
        return goodsUnderDao.updateSupplementOrderLine(supplementOrderLine);
    }

    @Override
    public SupplementOrderLine querySMT(long supplementOrderId, long productGlobalId) {
        return goodsUnderDao.querySMT(supplementOrderId, productGlobalId);
    }

    @Override
    public int updateSupplementOrderStatus(SupplementOrder supplementOrder) {
        return goodsUnderDao.updateSupplementOrderStatus(supplementOrder);
    }

    @Override
    public List<Product> ProductInfo(long machineId) {
        return goodsUnderDao.ProductInfo(machineId);
    }

    @Override
    public List<SupplementOrderLine> SupplementOrderInfo(long supplementOrderId) {
        return goodsUnderDao.SupplementOrderInfo(supplementOrderId);
    }
}
