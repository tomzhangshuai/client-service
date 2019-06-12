package com.wufanbao.api.clientservice.dao;

import com.wufanbao.api.clientservice.entity.MessageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageDao {
    /**
     * 获取用户数据
     *
     * @param userId   用户id
     * @param isRead   是否已读
     * @param isDelete 是否已删除
     * @return
     */
    List<MessageInfo> getMessages(@Param("userId") long userId, @Param("isRead") boolean isRead, @Param("isDelete") boolean isDelete);


}
