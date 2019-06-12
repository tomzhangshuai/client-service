package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.UserCapitalLog;
import com.wufanbao.api.oldclientservice.entity.UserCapitalLogParameter;
import org.apache.ibatis.annotations.Param;


import java.math.BigDecimal;
import java.util.List;

public interface UserCapitalLogService {
    //根据用户id,年份，月份，去查询
    List<UserCapitalLog> getMonthInfo(UserCapitalLogParameter userCapitalLogParameter);

    //根据用户ID查询所有的年放到list
    List<UserCapitalLogParameter> getYear(UserCapitalLogParameter userCapitalLogParameter);

    //根据用户id去查所有的月
    List<UserCapitalLogParameter> getMonth(UserCapitalLogParameter userCapitalLogParameter);

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
    int addUserCapital(long userCapitalLogId, long userId, double amount, String sourceType, long sourceId, String description, double balance);
}
