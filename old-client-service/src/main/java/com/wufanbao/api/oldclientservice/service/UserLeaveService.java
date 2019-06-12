package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.Userleavemessage;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:15:43
 */
public interface UserLeaveService {
    /**
     * 留言
     */
    public Integer addleave(Userleavemessage userleavemessage);
}
