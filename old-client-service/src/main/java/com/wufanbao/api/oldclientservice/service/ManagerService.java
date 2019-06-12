package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.Manager;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-07-31
 * Time:10:04
 */
public interface ManagerService {
    //查询管理员表
    public List<Manager> getSelect();
}
