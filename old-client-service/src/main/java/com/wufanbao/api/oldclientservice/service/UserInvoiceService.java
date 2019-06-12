package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.UserInvoice;
import com.wufanbao.api.oldclientservice.entity.UserOrder;

import java.util.List;

public interface UserInvoiceService {
    //查询历史发票
    List<UserInvoice> getUserInvoiceInfo(UserInvoice vo);

    //发票中订单详情
    List<UserOrder> getOrder(UserInvoice vo);

    //像用户发票中添加数据
    int insertOrder(UserInvoice vo);
}
