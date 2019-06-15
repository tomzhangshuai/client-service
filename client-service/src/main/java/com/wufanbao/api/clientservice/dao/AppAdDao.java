package com.wufanbao.api.clientservice.dao;

import com.wufanbao.api.clientservice.entity.AppAD;
import com.wufanbao.api.clientservice.entity.AppDiscover;
import com.wufanbao.api.clientservice.entity.VersionControl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface AppAdDao {
    /**
     * 获取广告
     *
     * @return
     */
    List<AppAD> getAppADs();

    /**
     * 获取发现列表
     *
     * @return
     */
    List<AppDiscover> getAppDiscovers();

    /**
     * 获取发现
     *
     * @param appDiscoverId
     * @return
     */
    AppDiscover getAppDiscover(@Param("appDiscoverId") long appDiscoverId);

    /**
     * 获取版本控制信息
     * @return
     */
    List<VersionControl> getVersionControl();
}
