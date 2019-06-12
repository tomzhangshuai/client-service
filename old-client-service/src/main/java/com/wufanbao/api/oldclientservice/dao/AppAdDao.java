package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.AppAd;

import java.util.List;
import java.util.Map;

/**
 * @program: alphaWuFan
 * @description: app首页广告
 * @author: Wang Zhiyuan
 **/
public interface AppAdDao {
    /**
     * @Description: 获取首页广告
     * @Param: []
     * @return: java.util.List<com.wufanbao.api.oldclientservice.entity.AppAd>
     * @Author:Wang Zhiyuan
     */
    List<AppAd> getAppAd();

    /**
     * @Description: 版本控制
     * @Param: []
     * @return: java.util.Map
     * @Author:Wang Zhiyuan
     */
    Map versionCode();
}
