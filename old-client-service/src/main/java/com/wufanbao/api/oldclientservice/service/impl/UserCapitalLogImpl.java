package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserCapitalLogDao;
import com.wufanbao.api.oldclientservice.entity.UserCapitalLog;
import com.wufanbao.api.oldclientservice.entity.UserCapitalLogParameter;
import com.wufanbao.api.oldclientservice.service.UserCapitalLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class UserCapitalLogImpl implements UserCapitalLogService {
    @Autowired
    private UserCapitalLogDao userCapitalLogDao;

    @Override
    public List<UserCapitalLog> getMonthInfo(UserCapitalLogParameter userCapitalLogParameter) {
        return userCapitalLogDao.getMonthInfo(userCapitalLogParameter);
    }

    @Override
    public List<UserCapitalLogParameter> getYear(UserCapitalLogParameter userCapitalLogParameter) {
        return userCapitalLogDao.getYear(userCapitalLogParameter);
    }

    @Override
    public List<UserCapitalLogParameter> getMonth(UserCapitalLogParameter userCapitalLogParameter) {
        return userCapitalLogDao.getMonth(userCapitalLogParameter);
    }

    /**
     * 添加进用户资金流水
     *
     * @param userCapitalLogId
     * @param userId
     * @param amount
     * @param sourceType
     * @param sourceId
     * @param description
     * @param balance
     * @return
     */
    @Override
    public int addUserCapital(long userCapitalLogId, long userId, double amount, String sourceType, long sourceId, String description, double balance) {
        return userCapitalLogDao.addUserCapital(userCapitalLogId, userId, amount, sourceType, sourceId, description, balance);
    }

}
