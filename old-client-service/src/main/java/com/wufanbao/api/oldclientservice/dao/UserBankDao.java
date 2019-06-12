package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserBank;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-08-04
 * Time:13:50
 */
public interface UserBankDao {

    /**
     * 添加银行信息
     *
     * @param userBank
     * @return
     */
    public int insertUserBank(UserBank userBank);

    /**
     * 获取用户银行卡信息
     */
    public List<UserBank> selectUserBank(long userId);

    /**
     * 删除用户银行卡信息
     */
    public void deleteUserBank(String account);

    /**
     * 判断银行卡是否已经添加
     */
    public UserBank checkAccount(String account);
}
