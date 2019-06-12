package com.wufanbao.api.oldclientservice.service;

import java.util.Map;

/**
 * User:WangZhiyuan
 */
public interface UserMessageService {
    /**
     * 获取用户所有的信息
     *
     * @param userId
     * @return
     */
    Map getAllUserMessage(long userId);

    /**
     * 标记已读
     *
     * @param userId 用户id
     * @return
     */
    Map markRead(long userId);

    /**
     * 标记删除
     *
     * @param userId
     * @return
     */
    Map markDelete(long userId);
}
