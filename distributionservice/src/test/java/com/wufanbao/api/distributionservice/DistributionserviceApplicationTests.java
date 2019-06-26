//package com.wufanbao.api.distributionservice;
//
//import com.alibaba.fastjson.JSONObject;
//import com.wufanbao.api.distributionservice.config.Code;
//import com.wufanbao.api.distributionservice.config.CodeException;
//import com.wufanbao.api.distributionservice.dao.MachineDao;
//import com.wufanbao.api.distributionservice.rabbitMQ.Sender;
//import com.wufanbao.api.distributionservice.tools.RedisUtil;
//import com.wufanbao.api.distributionservice.tools.RedisUtils;
//import org.assertj.core.data.MapEntry;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.security.KeyStore;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeoutException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DistributionserviceApplicationTests {
//    @Autowired
//    private RedisUtil redisUtil;
//    @Autowired
//    private  RedisUtils redisUtils;
//    @Autowired
//    private MachineDao machineDao;
//    @Autowired
//    private Sender sender;
//    @Test
//    public void contextLoads() throws IOException, TimeoutException {
//        String supplementOrderId="2139272118278";
//        String s="2102700408833";
//        long s1=Long.parseLong(s);
//        long l=Long.parseLong(supplementOrderId);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("MachineId",s);
//        jsonObject.put("SupplementOrderId", l);
//        sender.supplementCompleted(jsonObject.toJSONString());
//
////String machineIds="2005569798145";
////        long machineId = Long.parseLong(machineIds);
////        boolean inmaintenance=machineDao.getMachineInmaintenance(machineId);
////        if(!inmaintenance) {
//// String machineNo = machineDao.getMachineNo(machineId).toLowerCase();
//////            String key = "SupplementProductInfo_" + machineNo + "_" + 2222222;
////            String key = "MachineEvent_ac07e12f7909343b2";
////            Map<Object, Object> map = redisUtil.hmget(key);
////            for (Map.Entry<Object, Object> entry : map.entrySet()) {
////                if (entry.getValue().toString().equals("0")) {
////                    System.out.println(entry.getValue());
////                    System.out.println(entry.getValue().toString());
////                    break;
////                }
////            }
////
////            String key = "SupplementProductInfo_Z159_2009064210443";
////            Map<String,String> map=redisUtils.getAll(key);
////            for (Map.Entry<String,String> entry : map.entrySet()) {
////                if (entry.getValue().equals("0")){
////                    break;
////                }
////            }
////        }
//
//
//    }
//
//}
//
