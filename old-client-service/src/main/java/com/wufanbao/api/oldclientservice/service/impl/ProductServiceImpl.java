package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.ProductDao;
import com.wufanbao.api.oldclientservice.entity.Machine;
import com.wufanbao.api.oldclientservice.entity.Product;
import com.wufanbao.api.oldclientservice.entity.QueryProduct;
import com.wufanbao.api.oldclientservice.entity.UserOrder;
import com.wufanbao.api.oldclientservice.service.ProductSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-08
 * Time:16:03
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductSrevice {

    @Autowired
    private ProductDao productDao;


    @Override
    public List<QueryProduct> queryProduct(long productGlobalId, long machineId) {
        return productDao.queryProduct(productGlobalId, machineId);
    }

    @Override
    public List<QueryProduct> queryProducts(long userOrderId) {
        return productDao.queryProducts(userOrderId);
    }

    @Override
    public List<Machine> queryMachine(long machineId) {
        return productDao.queryMachine(machineId);
    }

    @Override
    public UserOrder queryOrder(long userOrderId) {
        return productDao.queryOrder(userOrderId);
    }
}
