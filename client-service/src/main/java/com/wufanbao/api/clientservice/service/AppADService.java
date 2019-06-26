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

import java.sql.SQLOutput;
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
    public Data getVersionControl(String versionCode,int versionType) throws ApiServiceException {
        VersionControl versionControl=appAdDao.getVersionControl(versionType);
        String versionLevel=appAdDao.getVersionLevel(versionCode,versionType);
        if(versionControl==null||StringUtils.isNullOrEmpty(versionLevel)){
            throw new ApiServiceException("无法获取版本信息");
        }
        if(versionControl.getVersionCode()!=versionCode){
            Data data=new Data();
            data.put("versionName",versionControl.getVersionName());
            data.put("foreceLevel",versionLevel);
            data.put("downloadLink",versionControl.getDownloadLink());
            data.put("content",versionControl.getContent());
            data.put("effectivtime", DateUtils.DateToString(versionControl.getEffectiveTime(),"yyyy-MM-dd HH:mm:ss"));
            return data;
        }
        return null;
    }
}
