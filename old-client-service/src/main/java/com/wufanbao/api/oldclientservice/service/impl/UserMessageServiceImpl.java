package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserMessageDao;
import com.wufanbao.api.oldclientservice.entity.MessageInfo;
import com.wufanbao.api.oldclientservice.entity.UserMessageInfo;
import com.wufanbao.api.oldclientservice.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserMessageServiceImpl implements UserMessageService {
    @Autowired
    private UserMessageDao userMessageDao;

    /**
     * 获取用户所有的信息
     *
     * @param userId
     * @return
     */
    @Override
    public Map getAllUserMessage(long userId) {
        Map map = new HashMap();
        //根据userId读出用户已读读去读取的信息
        List<UserMessageInfo> messageInfoList = userMessageDao.getReadMessage(userId);
        int noRead = userMessageDao.getNoReadMessage(userId).size();
        for (int i = 0; i < noRead; i++) {
            messageInfoList.add(userMessageDao.getNoReadMessage(userId).get(i));
        }
        if (messageInfoList.size() > 0) {
            map.put("type", 1);
            map.put("noRead", noRead);
            map.put("messageInfoList", messageInfoList);
        } else {
            map.put("type", 1);
            map.put("noRead", noRead);
            map.put("messageInfoList", messageInfoList);
        }
        return map;
    }

    /**
     * 标记已读
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public Map markRead(long userId) {
        Map map = new HashMap();
        List<UserMessageInfo> userMessageInfoList = userMessageDao.getNoReadMessage(userId);
        if (userMessageInfoList.size() > 0) {
            for (int i = 0; i < userMessageInfoList.size(); i++) {
                int count = userMessageDao.markRead(userId, userMessageInfoList.get(i).getMessageInfoId());
                System.out.println(count + "adsfasdfasdfas");
            }
        }
        map.put("type", 1);
        return map;
    }

    /**
     * 标记删除
     *
     * @param userId
     * @return
     */
    @Override
    public Map markDelete(long userId) {
        Map map = new HashMap();
        //标记删除
        int count = userMessageDao.markDelete(userId);
        if (count > 0) {
            map.put("type", 1);
        } else {
            map.put("type", 0);
        }
        return map;
    }
}
