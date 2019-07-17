//package com.wufanbao.api.clientservice.service;
//
//import com.google.gson.reflect.TypeToken;
//import com.wufanbao.api.clientservice.Sign;
//import com.wufanbao.api.clientservice.common.DateUtils;
//import com.wufanbao.api.clientservice.common.JsonUtils;
//import com.wufanbao.api.clientservice.common.RequestData;
//import com.wufanbao.api.clientservice.common.rabbitMQ.RabbitMQSender;
//import com.wufanbao.api.clientservice.entity.User;
//import org.json.JSONObject;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.text.ParseException;
//import java.util.Date;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserServiceTest {
//    @Autowired
//    private UserService userService;
//
//
////    @Test
////    public void insertUserTest(){
//////        try {
//////            User user=new User();
//////            user.setLoginNo("1104000009");//登录号
//////            user.setPassword("123456");
//////            userService.insertUser(user);
//////        } catch (ApiServiceException e) {
//////            e.printStackTrace();
//////        }
////    }
////
////    @Test
////    public void TestSign(){
////        RequestData requestData=new RequestData();
////        requestData.setBody("");
////        requestData.setTimestamp("1537943831079");
////        requestData.setVersion("2");
////        requestData.setSignType("md5");
////        String jsonData= JsonUtils.GsonString(requestData);
////        String sign= Sign.getSign(jsonData,requestData.getTimestamp());
////        String sign1=Sign.getSign(requestData);
////        String s="6188db6f5d1c83feeef1109b81d60a9d";
////
////    }
////
////    @Test
////    public void jsonTest() {
////        String str = "\"{\\\"nonce_str\\\":\\\"WnrZ6TD9c8gM82hV\\\",\\\"appid\\\":\\\"wx72b9fa2f86bba0cf\\\",\\\"sign\\\":\\\"6B2F51D349955B60C348E43383DC9413\\\",\\\"trade_type\\\":\\\"APP\\\",\\\"return_msg\\\":\\\"OK\\\",\\\"result_code\\\":\\\"SUCCESS\\\",\\\"mch_id\\\":\\\"1490321412\\\",\\\"return_code\\\":\\\"SUCCESS\\\",\\\"prepay_id\\\":\\\"wx16192232115184aff2e0273b3657327382\\\"}\"";
////        Map<String, String> map = JsonUtils.GsonToBean(str.substring(1,str.length()-1), new TypeToken<Map<String, String>>() {
////        }.getType());
////        String a=map.get("nonce_str");
////    }
//    @Autowired
//    private RabbitMQSender rabbitMQSender;
//    @Test
//    public void TestDateUtils(){
//        String userOrderId="26028476760067";
//        String machineId="2079466356737";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("userOrderId", userOrderId);
//        jsonObject.put("machineId",machineId);
//        rabbitMQSender.sendRefundOrder(jsonObject);
//
//    }
//}
