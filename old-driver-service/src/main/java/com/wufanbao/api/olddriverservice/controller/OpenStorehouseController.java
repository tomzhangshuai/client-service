package com.wufanbao.api.olddriverservice.controller;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.olddriverservice.DriverSetting;
import org.apache.commons.codec.binary.Base64;
import com.wufanbao.api.olddriverservice.Tool.AES_256;
import com.wufanbao.api.olddriverservice.Tool.RandomString;
import com.wufanbao.api.olddriverservice.Tool.ReturnParameter;
import com.wufanbao.api.olddriverservice.dao.GoodsUnderDao;
import com.wufanbao.api.olddriverservice.dao.OpenStorehouseDao;
import com.wufanbao.api.olddriverservice.entity.*;
import com.wufanbao.api.olddriverservice.service.OpenStorehouseService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User:Wangshihao
 * Date:2017-09-13
 * Time:15:27
 */
@Controller
@RequestMapping("openStorehouse")
public class OpenStorehouseController {

    @Autowired
    private OpenStorehouseService openStorehouseService;
    @Autowired
    private OpenStorehouseDao openStorehouseDao;
    @Autowired
    private GoodsUnderDao goodsUnderDao;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private DriverSetting driverSetting;
    @Autowired
    private ReturnParameter returnParameter;

    /**
     * 开仓
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "openStorehouseDoor", method = RequestMethod.POST)
    @ResponseBody
    public Object OpenBeforeStorehouse(String request) throws Exception {
        Map map = new HashMap();
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        requestInfo.getData();//OpenStorehouse
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        OpenStorehouse openStorehouse1 = JSON.parseObject(requestInfo.getData(), OpenStorehouse.class);
//        String machineNo =openStorehouse1.getMachineNo();
//        Long machineId=machineDao.getMachineIdByNo(machineNo);
        long machineId = openStorehouse1.getMachineId();
        long supplementOrderId = openStorehouse1.getSupplementOrderId();
        OpenStatus openStatus = openStorehouseDao.openStatus(supplementOrderId);
        long machineId1 = openStatus.getMachineId();
        List<Integer> productTypesList = openStorehouseDao.productTypes(supplementOrderId);
        int count = productTypesList.size();
        if (count > 0 && machineId1 == machineId) {
            map.put("front", 1);
            if (count < 2) {
                map.put("front", 0);
                int productType = productTypesList.get(0);
                if (productType == 1) {
                    SupplementOrder supplementOrder = new SupplementOrder();
                    supplementOrder.setSupplementOrderId(supplementOrderId);
                    supplementOrder.setSupplementStatus(8);
                    goodsUnderDao.updateSupplementOrderStatus(supplementOrder);
                }
            }
//        List<OpenStorehouse> openStorehouseList = openStorehouseService.openStorehouseList(machineNo,supplementOrderId);
//        if (openStorehouseList.size() !=0) {
//            for (int i = 0; i < openStorehouseList.size(); i++) {

//                OpenStorehouse openStorehouse = openStorehouseList.get(i);
            int supplementStatus = openStatus.getSupplementStatus();
            if (supplementStatus == 6 || supplementStatus == 7 || supplementStatus == 8 || supplementStatus == 9) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String kkk = simpleDateFormat.format(new Date());
                String former = RandomString.getRandomString(6);
                String after = RandomString.getRandomString(6);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("machineId", machineId);
                jsonObject.put("supplementOrderId", supplementOrderId);
                jsonObject.put("former", former);
                jsonObject.put("after", after);
                jsonObject.put("createTime", kkk);
                String key = "OpenMachine_" + String.valueOf(machineId);
                Jedis jedis = jedisPool.getResource();
                jedis.set(key, jsonObject.toString());
                //25分钟之后过期
                jedis.expire(key, 1500);
                jedis.get(key);
                //加密
                String dataFormer = Base64.encodeBase64String(AES_256.Encrypt(driverSetting.getAeskey(), former));
                String dataAfter = Base64.encodeBase64String(AES_256.Encrypt(driverSetting.getAeskey(), after));
                map.put("before", "03," + dataFormer);
                map.put("after", "02," + dataAfter);
                responseInfo.setCode(1);
                responseInfo.setData(map);
                responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
                return responseInfo;
            }
//            }
//        }
        }
        responseInfo.setCode(2281);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    @RequestMapping(value = "openStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object OpenS(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        DistributionOrder distributionOrder = JSON.parseObject(requestInfo.getData(), DistributionOrder.class);
        long supplementOrderId = JSON.parseObject(requestInfo.getData(), SupplementOrder.class).getSupplementOrderId();

        OpenStatus openStatus = openStorehouseService.openStatus(supplementOrderId);
        System.out.println(">>>>>>>>>>>>>>开仓状态" + openStatus + supplementOrderId + request);
        if (openStatus != null) {
            int status = openStatus.getSupplementStatus();
            System.out.println(status);
            Map map1 = new HashMap();
            map1.put("supplementStatus", status);
            responseInfo.setCode(1);
            responseInfo.setData(map1);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;
        }
        responseInfo.setCode(2251);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    /**
     * 补前仓
     *
     * @param request
     * @return java.lang.Object
     * @date 2018/7/3
     */
    @RequestMapping(value = "machineSupplementFront", method = RequestMethod.POST)
    @ResponseBody
    public Object machineSupplementFront(String request) throws Exception {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        Map dataMap = JSON.parseObject(requestInfo.getData(), HashMap.class);
        long supplementOrderId = Long.valueOf(dataMap.get("supplementOrderId").toString());
        long userId = Long.valueOf(dataMap.get("employeeId").toString());
        ResponseInfo responseInfo = openStorehouseService.machineSupplementFront(supplementOrderId, userId);

        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    /**
     * 补后仓
     *
     * @param request
     * @return java.lang.Object
     * @date 2018/7/3
     */
    @RequestMapping(value = "machineSupplementBehind", method = RequestMethod.POST)
    @ResponseBody
    public Object machineSupplementBehind(String request) throws Exception {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        Map dataMap = JSON.parseObject(requestInfo.getData(), HashMap.class);
        long supplementOrderId = Long.valueOf(dataMap.get("supplementOrderId").toString());
        long userId = Long.valueOf(dataMap.get("employeeId").toString());
        ResponseInfo responseInfo = openStorehouseService.machineSupplementBehind(supplementOrderId, userId);

        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    /**
     * 扫描二维码
     *
     * @param request
     * @return com.wufanbao.api.olddriverservice.entity.ResponseInfo
     * @date 2018/7/4
     */
    @ResponseBody
    @RequestMapping(value = "scanCode", method = RequestMethod.POST)
    public Object scanCode(String request) throws Exception {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        Map dataMap = JSON.parseObject(requestInfo.getData(), HashMap.class);
        long machineId = Long.valueOf(dataMap.get("machineId").toString());
        long userId = Long.valueOf(dataMap.get("employeeId").toString());
        long supplementOrderId = Long.valueOf(dataMap.get("supplementOrderId").toString());
        ResponseInfo responseInfo = openStorehouseService.scanCode(machineId, userId, supplementOrderId);
        if (responseInfo.getCode() == 1) {
            //写入redis
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String kkk = simpleDateFormat.format(new Date());
            String former = RandomString.getRandomString(6);
            String after = RandomString.getRandomString(6);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("machineId", machineId);
            jsonObject.put("supplementOrderId", supplementOrderId);
            jsonObject.put("former", former);
            jsonObject.put("after", after);
            jsonObject.put("createTime", kkk);
            String key = "OpenMachine_" + String.valueOf(machineId);
            Jedis jedis = jedisPool.getResource();
            jedis.set(key, jsonObject.toString());
            //60分钟之后过期
            jedis.expire(key, 3600);
        }
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

}
