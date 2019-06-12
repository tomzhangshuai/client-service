package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.OpenStatus;
import com.wufanbao.api.olddriverservice.entity.OpenStorehouse;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-13
 * Time:15:25
 */
public interface OpenStorehouseService {
    /**
     * 开仓操作
     */
    public List<OpenStorehouse> openStorehouseList(@Param("machineNo") String machineNo, @Param("supplementOrderId") long supplementOrderId);

    /**
     * 开仓返回状态
     */
    public OpenStatus openStatus(long supplementOrderId);

    /**
     * 开前仓
     *
     * @param supplementOrderId 补货单ID
     * @return com.wufanbao.api.olddriverservice.entity.ResponseInfo
     * @date 2018/7/4
     */
    ResponseInfo machineSupplementFront(long supplementOrderId, long userId) throws Exception;

    /**
     * 开后仓
     *
     * @param supplementOrderId 补货单ID
     * @return
     * @throws Exception
     */
    ResponseInfo machineSupplementBehind(long supplementOrderId, long userId) throws Exception;

    /**
     * 扫描二维码
     *
     * @param machineId 机器ID
     * @param userId    用户ID
     * @return
     * @throws Exception
     */
    ResponseInfo scanCode(Long machineId, Long userId, long supplementOrderId) throws Exception;

}
