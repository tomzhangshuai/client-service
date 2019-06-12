package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.dao.SupplementOrderSureDao;
import com.wufanbao.api.olddriverservice.entity.*;
import com.wufanbao.api.olddriverservice.service.SupplementOrderSureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-09-11
 * Time:16:00
 */
@Service
@Transactional
public class SupplementOrderSureServiceImpl implements SupplementOrderSureService {
    @Autowired
    private SupplementOrderSureDao supplementOrderSureDao;

    @Override
    public List<SupplementOrderSure> querySupplementOrderSure(long supplementOrderId) {
        return supplementOrderSureDao.querySupplementOrderSure(supplementOrderId);
    }

    @Override
    public List<SupplementOrderSure> querysupplementOrderhou(long supplementOrderId) {
        return supplementOrderSureDao.querysupplementOrderhou(supplementOrderId);
    }

    @Override
    public Integer updateSupplementOrderLine(SupplementOrderLine supplementOrderLint) {
        return supplementOrderSureDao.updateSupplementOrderLine(supplementOrderLint);
    }

    @Override
    public List<DistributionProductLine> queryDistributionproductline(long distributionOrderId, long productGlobalId) {
        return supplementOrderSureDao.queryDistributionproductline(distributionOrderId, productGlobalId);
    }

    @Override
    public Integer updateDistributionproductline(DistributionProductLine distributionproductline) {
        return supplementOrderSureDao.updateDistributionproductline(distributionproductline);
    }

    @Override
    public Integer updateAfterSupplementOrderAfter(SupplementOrderLine supplementOrderLint) {
        return supplementOrderSureDao.updateAfterSupplementOrderAfter(supplementOrderLint);
    }

    @Override
    public void addSupplementOrderLine(SupplementOrderLine supplementOrderLine) {
        supplementOrderSureDao.addSupplementOrderLine(supplementOrderLine);
    }

    @Override
    public List<ProductGlobal> queryProductGuobal(String productName) {
        return supplementOrderSureDao.queryProductGuobal(productName);
    }

    @Override
    public List<SupplementOrderLine> querySMT(long supplementOrderId, long productGlobalId) {
        return supplementOrderSureDao.querySMT(supplementOrderId, productGlobalId);
    }

    @Override
    public List<ProductGlobal> productGlobalAll() {
        return supplementOrderSureDao.productGlobalAll();
    }

    /**
     * 异常状态回滚
     *
     * @param supplementOrderId
     * @return
     */
    @Override
    public ResponseInfo rollbackStatus(long supplementOrderId) {
        Map map = new HashMap();
        ResponseInfo responseInfo = new ResponseInfo();
        //回滚补货单状态
        int count = supplementOrderSureDao.rollbackStatus(supplementOrderId);
        if (count > 0) {
            responseInfo.setCode(1);
        } else {
            responseInfo.setCode(2331);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return responseInfo;
    }

    @Override
    public HeadTool queryHeadTool(long supplementOrderId) {
        return supplementOrderSureDao.queryHeadTool(supplementOrderId);
    }

}
