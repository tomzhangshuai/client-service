package com.wufanbao.api.olddriverservice.service;

import java.util.List;
import java.util.Map;

/**
 * @program: AlphaDriver
 * @description: 补货准备工作
 * @author: Wang Zhiyuan
 * @create: 2018-03-24 16:27
 **/
public interface ReplenishmentPrepareService {
    /**
     * 展示机器信息
     *
     * @param machineId 机器ID
     * @return java.util.List<java.util.Map>
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    List<Map> showMachineInfo(long machineId);

    Map preparatoryWork(String machineId, String product, String mb);
}
