package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.AppDiscover;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * User:wangshihao
 * Date:2017-12-18
 * Time:10:43
 */
public interface AppDiscoverDao {
    public List<AppDiscover> queryAppDiscover();

    Map appDiscoverShare(@Param("appDiscoverId") long appDiscoverId);
}
