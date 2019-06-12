package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * User:WangZhiyuan
 * Date:2017-08-5
 */
public interface UserCapitalLogDao {
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
    int addUserCapital(
            @Param("userCapitalLogId") long userCapitalLogId,
            @Param("userId") long userId,
            @Param("amount") double amount,
            @Param("sourceType") String sourceType,
            @Param("sourceId") long sourceId,
            @Param("description") String description,
            @Param("balance") double balance
    );

    /**
     * 添加用户消费记录
     * @param userCapitalLogId
     * @param userId
     * @param amount
     * @param sourceType
     * @param sourceId
     * @param description
     * @return
     */
    /**
     * 插入订单流水
     *
     * @param userCapitalLogId
     * @param userId
     * @param amount
     * @param sourceType
     * @param sourceId
     * @param description
     * @return
     */
    int insertUserCapital(
            @Param("userCapitalLogId") long userCapitalLogId,
            @Param("userId") long userId,
            @Param("amount") BigDecimal amount,
            @Param("sourceType") String sourceType,
            @Param("sourceId") long sourceId,
            @Param("description") String description
    );
}
