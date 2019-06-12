package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserInvoiceDao;
import com.wufanbao.api.oldclientservice.entity.UserInvoice;
import com.wufanbao.api.oldclientservice.entity.UserInvoiceInfo;
import com.wufanbao.api.oldclientservice.entity.UserOrder;
import com.wufanbao.api.oldclientservice.service.UserInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserInvoiceServiceImpl implements UserInvoiceService {
    @Autowired
    private UserInvoiceDao userInvoiceDao;

    @Override
    public List<UserInvoice> getUserInvoiceInfo(UserInvoice vo) {
        return userInvoiceDao.getUserInvoiceInfo(vo);
    }

    @Override
    public List<UserOrder> getOrder(UserInvoice vo) {
        return userInvoiceDao.getOrder(vo);
    }

    @Override
    public int insertOrder(UserInvoice vo) {
        return userInvoiceDao.insertOrder(vo);
    }
}
