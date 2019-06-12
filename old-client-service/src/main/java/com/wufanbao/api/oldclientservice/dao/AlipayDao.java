package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.Alipay;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-14
 * Time:10:09
 */
public interface AlipayDao {

    public List<Alipay> queryAlipay(long userOrderId);
}
