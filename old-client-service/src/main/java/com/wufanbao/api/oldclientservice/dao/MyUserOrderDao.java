package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserOrder;

public interface MyUserOrderDao {
    /**
     * 添加订单
     *
     * @param userOrder
     * @return
     */
    int insertUserOrder(UserOrder userOrder);


}
