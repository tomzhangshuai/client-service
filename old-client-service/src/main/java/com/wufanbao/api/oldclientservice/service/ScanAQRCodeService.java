package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.ResponseInfo;

/**
 * alphaWuFan
 * 扫描二维码
 *
 * @author Wang Zhiyuan
 * @date 2018-06-19 15:30
 **/
public interface ScanAQRCodeService {
    /**
     * 扫描二维码
     *
     * @param machineId 机器ID
     * @param userId    用户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/6/19
     */
    ResponseInfo scanCode(Long machineId, Long userId) throws Exception;

    ResponseInfo orderScanCode(Long machineId, Long userOrderId) throws Exception;
}
