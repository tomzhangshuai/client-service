package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.entity.UserBank;

import java.util.List;
import java.util.Map;

/**
 * User:WangZhiyuan
 * Data:2017/08/02
 * Time:17:28
 */
public interface GetUserInfoService {
    /**
     * 根据用户UserId获取银行卡是否被绑定
     *
     * @param userId 用户UserId
     * @return
     */
    List<UserBank> getUserBankInfo(long userId);

    /**
     * 根据用户userId 获取用户当前便当币数量
     *
     * @param userId 用户UserId
     * @return
     */
    Map getUserUsableBalance(long userId);

    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return 存放着用户信息的Map
     */
    Map getUserInfoService(Long userId, String bindingId) throws Exception;

    /**
     * 常用取餐时间
     * @param userId
     * @return
     * @throws Exception
     */
    /**
     * @Description: 常用取餐时间
     * @Param: [userId]
     * @return: com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/24
     */
    ResponseInfo oftenTime(long userId) throws Exception;
}
