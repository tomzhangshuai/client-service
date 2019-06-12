package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.Manager;

import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-07-31
 * Time:9:45
 */

public interface ManagerDao {
    //查询管理员表
    public List<Manager> managerList();
}
