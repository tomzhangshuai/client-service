package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserInvoiceOrder;

/**
 * User:WangZhiyuan
 */
public interface UserInvoiceOrderDao {
    //将用户报销的订单存入订单发票关系表
    int insertUserInvoiceOrder(UserInvoiceOrder vo);
}
