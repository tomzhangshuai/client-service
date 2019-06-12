package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserMessageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User：WangZhiyuan
 */
public interface UserMessageDao {
    /**
     * 根据用户的id获取用户的未读信息
     *
     * @param userId
     * @return
     */
    List<UserMessageInfo> getNoReadMessage(@Param("userId") long userId);

    /**
     * 根据用户id获取用户已经读过的信息
     *
     * @param userId
     * @return
     */
    List<UserMessageInfo> getReadMessage(@Param("userId") long userId);

    /**
     * 标记已读
     *
     * @param userId        用户id
     * @param messageInfoId 消息表id
     * @return
     */
    int markRead(@Param("userId") long userId, @Param("messageInfoId") long messageInfoId);

    /**
     * 标记删除
     *
     * @param userId 用户id
     * @return
     */
    int markDelete(@Param("userId") long userId);

}
