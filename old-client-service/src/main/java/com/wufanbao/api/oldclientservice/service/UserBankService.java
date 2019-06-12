package com.wufanbao.api.oldclientservice.service;


import com.wufanbao.api.oldclientservice.entity.UserBank;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-08-04
 * Time:14:29
 */
public interface UserBankService {

    /**
     * 添加银行卡信息
     *
     * @param userBank
     * @return
     */
    public Integer insertUserBank(UserBank userBank);

    /**
     * 获取用户银行卡信息
     */
    public List<UserBank> selectUserBank(long userId);

    /**
     * 删除用户银行卡信息
     */
    public void deleteUserBank(String account);

    /**
     * 检查银行卡是否已添加
     */
    public UserBank checkAccount(String account);

}
