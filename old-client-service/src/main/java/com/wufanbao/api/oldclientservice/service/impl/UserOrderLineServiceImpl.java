package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserOrderLineDao;
import com.wufanbao.api.oldclientservice.entity.PaymentOrder;
import com.wufanbao.api.oldclientservice.entity.UserOrderLine;
import com.wufanbao.api.oldclientservice.entity.UserOrderin;
import com.wufanbao.api.oldclientservice.service.UserOrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-08-10
 * Time:14:45
 */
@Transactional
@Service
public class UserOrderLineServiceImpl implements UserOrderLineService {

    @Autowired
    private UserOrderLineDao userOrderLineDao;

    @Override
    public List<PaymentOrder> queryOrderLine(long userOrderId) {
        return userOrderLineDao.queryOrderLine(userOrderId);
    }

    @Override
    public List<UserOrderin> queryOrder(long userId) {
        return userOrderLineDao.queryOrder(userId);
    }

    /**
     * 将商品加入用户订单明细表
     *
     * @param userOrderLine
     */
    @Override
    public int insertOrderLine(UserOrderLine userOrderLine) {

        return userOrderLineDao.insertOrderLine(userOrderLine);
    }
}
