package com.wufanbao.api.clientservice.dao;

import com.wufanbao.api.clientservice.entity.Area;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyDao {
    /**
     * 获取开放城市
     *
     * @return
     */
    List<Area> getCitys();

    /**
     * 根据区域名称获取城市数据
     *
     * @param areaName
     * @return
     */
    Area getCityByAreaName(@Param("areaName") String areaName);

    Area getCityByAreaId(@Param("areaId") int areaId);
}
