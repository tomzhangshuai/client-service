package com.wufanbao.api.clientservice.service;

import com.wufanbao.api.clientservice.common.Data;
import com.wufanbao.api.clientservice.common.DateUtils;
import com.wufanbao.api.clientservice.common.StringUtils;
import com.wufanbao.api.clientservice.config.ClientSetting;
import com.wufanbao.api.clientservice.dao.AppAdDao;
import com.wufanbao.api.clientservice.entity.AppAD;
import com.wufanbao.api.clientservice.entity.AppDiscover;
import com.wufanbao.api.clientservice.entity.VersionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Data getVersionControl(String versionCode) throws ApiServiceException {
        List<VersionControl> versionControls=appAdDao.getVersionControl();
        if(versionControls.size()==0){
            throw new ApiServiceException("无法获取最新版本信息");
        }
        Data data=new Data();
        for (VersionControl version:versionControls) {
            String dbVersionCode=version.getVersionCode();
            if(StringUtils.isNullOrEmpty(dbVersionCode)){
                throw new ApiServiceException("无法获取版本号");
            }
            if(versionCode.equals(dbVersionCode)){
                data.put("versionName",version.getVersionName());
                data.put("foreceLevel",String.valueOf(version.getForceLevel()));
                data.put("downloadLink",version.getDownloadLink());
                data.put("content",version.getContent());
                data.put("effectivtime", DateUtils.DateToString(version.getEffectiveTime(),"yyyy-MM-dd HH:mm:ss"));
                break;
            }
        }
        return data;
    }

}
