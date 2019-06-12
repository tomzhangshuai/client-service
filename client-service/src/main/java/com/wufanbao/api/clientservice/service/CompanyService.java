package com.wufanbao.api.clientservice.service;

import com.wufanbao.api.clientservice.dao.CompanyDao;
import com.wufanbao.api.clientservice.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao;

    //获取开发城市
    public List<Area> getCitys() {
        return companyDao.getCitys();
    }

    //根据区域名称获取开发城市
    public Area getCityByAreaName(String areaName) {
        return companyDao.getCityByAreaName(areaName);
    }

}
