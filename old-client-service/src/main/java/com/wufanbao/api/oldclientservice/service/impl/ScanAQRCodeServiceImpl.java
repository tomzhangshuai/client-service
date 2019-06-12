package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.Tool.DateUtil;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.controller.RabbitMQ.RabbitMQSender;
import com.wufanbao.api.oldclientservice.dao.HomePageDao;
import com.wufanbao.api.oldclientservice.dao.UserOrderDao;
import com.wufanbao.api.oldclientservice.entity.Machine;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.service.ScanAQRCodeService;
import org.apache.ibatis.annotations.Insert;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * alphaWuFan
 * 扫描二维码
 *
 * @author Wang Zhiyuan
 * @date 2018-06-19 15:33
 **/
@Service
public class ScanAQRCodeServiceImpl implements ScanAQRCodeService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private HomePageDao homePageDao;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    /**
     * 扫描二维码
     *
     * @param machineId 机器ID
     * @param userId    用户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/6/19
     */
    @Override
    public ResponseInfo scanCode(Long machineId, Long userId) throws Exception {
        ResponseInfo responseInfo = new ResponseInfo();
        if (machineId == null || userId == null) {
            responseInfo.setCode(2060);
            return responseInfo;
        }
        String key = machineId + "scanCode" + userId;
        redisUtils.set("AlphaWuFan:ScanCode:" + key, machineId.toString());
        //通过扫一扫进入机器记录存储20分钟
        redisUtils.expire("AlphaWuFan:ScanCode:" + key, 180);
        //机器信息
        Machine machine = homePageDao.getMachineInfoByMachineId(machineId);
        Map machineMap = new HashMap();
        machineMap.put("machineId", machineId);
        machineMap.put("address", machine.getAddress());
        machineMap.put("machineName", machine.getMachineName());
        //用户在此台机器上的订单信息
        List<Map> userOrderInfoList = userOrderDao.getUserOrderInfoByMachineIdAndUserId(machineId, userId);
        for (Map userOrderMap : userOrderInfoList) {
            Date payTime = DateUtil.formatDateToDate(userOrderMap.get("payTime").toString(), "yyyy-MM-dd HH:mm:ss");
            String date = DateUtil.format(payTime);
            userOrderMap.put("payTime", date);
            userOrderMap.put("machineType", machine.getFullRefund());
//            int status=Integer.valueOf(userOrderMap.get("status").toString());
            String ss = redisUtils.get("AlphaWuFan:ScanCode:" + key);
            if (null == ss && ("").equals(ss)) {
                userOrderMap.put("buttonName", "扫一扫");
            } else {
                userOrderMap.put("buttonName", "取餐");
            }
        }

        Map map = new HashMap();
        map.put("machineInfo", machineMap);
        map.put("userOrderInfo", userOrderInfoList);
        responseInfo.setData(map);
        responseInfo.setCode(1);
        return responseInfo;
    }

    @Override
    public ResponseInfo orderScanCode(Long machineId, Long userOrderId) throws Exception {
        ResponseInfo responseInfo = new ResponseInfo();
        if (machineId == null || userOrderId == null) {
            responseInfo.setCode(2060);
            return responseInfo;
        }
        Map orderInfo = userOrderDao.getUserOrderInfoByMachineIdAndUserOrderId(machineId, userOrderId);
        if (orderInfo == null) {
            responseInfo.setCode(2060);
            return responseInfo;
        }
        long userId = Long.valueOf(orderInfo.get("userId").toString());
        String key = machineId + "scanCode" + userId;
        redisUtils.set("AlphaWuFan:ScanCode:" + key, machineId.toString());
        //通过扫一扫进入机器记录存储20分钟
        redisUtils.expire("AlphaWuFan:ScanCode:" + key, 180);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MachineId", machineId);
        jsonObject.put("OrderId", userOrderId);
        //RabbitMQAll.takeFood(jsonObject);
        rabbitMQSender.takeFood(jsonObject);
        responseInfo.setCode(1);
        return responseInfo;
    }

}
