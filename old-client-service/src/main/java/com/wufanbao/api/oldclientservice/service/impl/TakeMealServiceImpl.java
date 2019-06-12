package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.TakeMealDao;
import com.wufanbao.api.oldclientservice.entity.ProductDetails;
import com.wufanbao.api.oldclientservice.entity.ProductOff;
import com.wufanbao.api.oldclientservice.entity.ProductTakeMeal;
import com.wufanbao.api.oldclientservice.entity.UserOrderProductLine;
import com.wufanbao.api.oldclientservice.service.TakeMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:wangshihao
 * Date:2017-12-06
 * Time:15:34
 */
@Service
@Transactional
public class TakeMealServiceImpl implements TakeMealService {
    @Autowired
    private TakeMealDao takeMealDao;

    @Override
    public ProductDetails productName(long productGlobalId) {
        return takeMealDao.productName(productGlobalId);
    }

    @Override
    public List<ProductOff> productStatus(long sourceId) {
        return takeMealDao.productStatus(sourceId);
    }


    @Override
    public List<ProductTakeMeal> productLists(long userOrderId) {
        return takeMealDao.productLists(userOrderId);
    }

    @Override
    public int addUserOrderProductLine(UserOrderProductLine userOrderProductLine) {
        return takeMealDao.addUserOrderProductLine(userOrderProductLine);
    }

    @Override
    public List<UserOrderProductLine> queryUserOrderProductLine(long userOrderId) {
        return takeMealDao.queryUserOrderProductLine(userOrderId);
    }
}
