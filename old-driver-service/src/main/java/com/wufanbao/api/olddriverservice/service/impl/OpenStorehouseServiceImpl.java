package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.controller.RabbitMQ.QueueSend;
import com.wufanbao.api.olddriverservice.dao.OpenStorehouseDao;
import com.wufanbao.api.olddriverservice.dao.SupplementOrderSureDao;
import com.wufanbao.api.olddriverservice.entity.OpenStatus;
import com.wufanbao.api.olddriverservice.entity.OpenStorehouse;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;
import com.wufanbao.api.olddriverservice.service.OpenStorehouseService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-09-13
 * Time:15:26
 */
@Service
@Transactional
public class OpenStorehouseServiceImpl implements OpenStorehouseService {

    @Autowired
    private OpenStorehouseDao openStorehouseDao;
    @Autowired
    private SupplementOrderSureDao supplementOrderSureDao;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private QueueSend queueSend;

    @Override
    public List<OpenStorehouse> openStorehouseList(String machineNo, long supplementOrderId) {
        return openStorehouseDao.openStorehouseList(machineNo, supplementOrderId);
    }

    @Override
    public OpenStatus openStatus(long supplementOrderId) {
        return openStorehouseDao.openStatus(supplementOrderId);
    }

    @Override
    public ResponseInfo machineSupplementFront(long supplementOrderId, long userId) throws Exception {
        Map supplementMap = supplementOrderSureDao.supplementOrderInfo(supplementOrderId);
        Long machineId = Long.valueOf(supplementMap.get("machineId").toString());
        String key = machineId + "scanCode" + userId;
        Jedis jedis = jedisPool.getResource();
        String machineId2 = jedis.get("AlphaDriver:ScanCode:" + key);
        ResponseInfo responseInfo = new ResponseInfo();
        if (!machineId2.equals(machineId.toString())) {
            responseInfo.setCode(2352);
            jedis.close();
            return responseInfo;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MachineId", machineId);
        jsonObject.put("SupplementOrderId", supplementOrderId);
        queueSend.machineSupplementFront(jsonObject);
        responseInfo.setCode(1);
        jedis.close();
        return responseInfo;
    }

    @Override
    public ResponseInfo machineSupplementBehind(long supplementOrderId, long userId) throws Exception {
        Map supplementMap = supplementOrderSureDao.supplementOrderInfo(supplementOrderId);
        Long machineId = Long.valueOf(supplementMap.get("machineId").toString());
        String key = machineId + "scanCode" + userId;
        Jedis jedis = jedisPool.getResource();
        String machineId2 = jedis.get("AlphaDriver:ScanCode:" + key);

        ResponseInfo responseInfo = new ResponseInfo();
        if (!machineId2.equals(machineId.toString())) {
            responseInfo.setCode(2352);
            jedis.close();
            return responseInfo;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MachineId", machineId);
        jsonObject.put("SupplementOrderId", supplementOrderId);
        queueSend.machineSupplementBehind(jsonObject);
        responseInfo.setCode(1);
        jedis.close();
        return responseInfo;
    }

    /**
     * 扫描二维码
     *
     * @param machineId 机器ID
     * @param userId    用户ID
     * @return
     * @throws Exception
     */
    @Override
    public ResponseInfo scanCode(Long machineId, Long userId, long supplementOrderId) throws Exception {
        Map map = supplementOrderSureDao.getSupplementOrderByMachineId(machineId, supplementOrderId);
        ResponseInfo responseInfo = new ResponseInfo();
        if (map != null) {
            Jedis jedis = jedisPool.getResource();
            String key = machineId + "scanCode" + userId;
            jedis.set("AlphaDriver:ScanCode:" + key, machineId.toString());
            //通过扫一扫进入机器记录存储20分钟
            jedis.expire("AlphaDriver:ScanCode:" + key, 1200);
            jedis.close();
        } else {
            responseInfo.setCode(2361);
            return responseInfo;
        }
        responseInfo.setCode(1);
        return responseInfo;
    }
}
