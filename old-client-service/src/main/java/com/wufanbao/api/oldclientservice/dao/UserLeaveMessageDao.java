package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.Userleavemessage;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:15:38
 */
public interface UserLeaveMessageDao {
    /**
     * 留言
     */
    public int addleave(Userleavemessage userleavemessage);
}
