package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserInvoiceOrderDao;
import com.wufanbao.api.oldclientservice.entity.UserInvoiceOrder;
import com.wufanbao.api.oldclientservice.service.UserInvoiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInvoiceOrderServiceImpl implements UserInvoiceOrderService {
    @Autowired
    private UserInvoiceOrderDao userInvoiceOrderDao;

    @Override
    public int insertUserInvoiceOrder(UserInvoiceOrder vo) {
        return userInvoiceOrderDao.insertUserInvoiceOrder(vo);
    }
}
