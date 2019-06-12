package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.UserInvoiceOrder;

public interface UserInvoiceOrderService {

    //将用户报销的订单存入订单发票关系表
    int insertUserInvoiceOrder(UserInvoiceOrder vo);
}
