package com.wufanbao.api.clientservice.service;

import com.wufanbao.api.clientservice.config.ClientSetting;
import com.wufanbao.api.clientservice.dao.AppAdDao;
import com.wufanbao.api.clientservice.entity.AppAD;
import com.wufanbao.api.clientservice.entity.AppDiscover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppADService {
    @Autowired
    private AppAdDao appAdDao;
    @Autowired
    private ClientSetting clientSetting;

    //获取banner 广告
    public List<AppAD> getAppADs() {
        List<AppAD> appADS = appAdDao.getAppADs();
        for (AppAD appAD : appADS) {
            appAD.setADImageUrl(clientSetting.getResource() + appAD.getADImageUrl());
        }
        return appADS;
    }

    //获取发现列表
    public List<AppDiscover> getAppDiscovers() {
        List<AppDiscover> appDiscovers = appAdDao.getAppDiscovers();
        for (AppDiscover appDiscover : appDiscovers) {
            appDiscover.setImageUrl(clientSetting.getResource() + appDiscover.getImageUrl());
        }
        return appDiscovers;
    }

    //获取发现
    public AppDiscover getAppDiscover(long appDiscoverId) {
        AppDiscover appDiscover = appAdDao.getAppDiscover(appDiscoverId);
        appDiscover.setImageUrl(clientSetting.getResource() + appDiscover.getImageUrl());
        return appDiscover;
    }
}
