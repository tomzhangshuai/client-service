/*
package com.wufanbao.api.clientservice.api;

import com.google.gson.Gson;
import com.wufanbao.api.clientservice.Sign;
import com.wufanbao.api.clientservice.common.Data;
import com.wufanbao.api.clientservice.common.JsonUtils;
import com.wufanbao.api.clientservice.common.RequestData;
import com.wufanbao.api.clientservice.common.StringUtils;
import com.wufanbao.api.clientservice.common.rabbitMQ.RabbitMQSender;
import com.wufanbao.api.clientservice.entity.Machine;
import com.wufanbao.api.clientservice.entity.UserOrderLine;
import com.wufanbao.api.clientservice.service.MachineService;
import com.wufanbao.api.clientservice.service.UserOrderService;
import com.wufanbao.api.clientservice.service.UserService;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.net.ssl.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexControllerTest {

        public String request(String url,RequestData data, String sign) {
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    }).build();
            FormBody.Builder builder = new FormBody.Builder();
            String dataJson = new Gson().toJson(data);
            //System.out.println("data: " + dataJson);
            //System.out.println("sign: " + sign);
            builder.add("data", dataJson);
            builder.add("sign", sign);
            RequestBody body = builder.build();
            Request request = new Request.Builder()
                    .url("http://192.168.2.121:8080/"+url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    @Autowired
    RabbitMQSender rabbitMQSender;

*/
/*    @Test
    public void rabbitMQ1() {
        String userOrderIdStr = "25892719558657";
        try {
            rabbitMQSender.sendTakeFood(userOrderIdStr);
            System.out.println("已发送日志完成。。。。。。。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*//*




    @Test
    public void getVersion(){
         RequestData requestData=new RequestData();
         requestData.setVersion("2");
         requestData.setSignType("md5");
         requestData.setTimestamp("201801052145");

         request("/version",requestData, Sign.getSign(requestData));
     }

     @Test
     public void getCitys(){
         RequestData requestData=new RequestData();
         requestData.setVersion("2");
         requestData.setSignType("md5");
         requestData.setBody("");
         requestData.setTimestamp(System.currentTimeMillis()+"");
         request("/getCitys",requestData, Sign.getSign(requestData));
     }

    @Test
    public void getAppADS(){
        RequestData requestData=new RequestData();
        requestData.setVersion("2");
        requestData.setSignType("md5");
        requestData.setTimestamp(System.currentTimeMillis()+"");
        request("/getAppADS",requestData, Sign.getSign(requestData));
    }

    @Test
    public void getMachineProducts(){
        RequestData requestData=new RequestData();
        requestData.setVersion("2");
        requestData.setSignType("md5");
        requestData.setTimestamp("201801052145");

        Data data=new Data();
        data.put("areaName","杭州市");
        data.put("X","120.186278");
        data.put("Y","30.325401");
        data.put("machineId","194800386049");
        requestData.setBody(JsonUtils.GsonString(data));

        request("/getMachineProducts",requestData, Sign.getSign(requestData));
    }
    @Test
    public void  getHistoryUserCoupons(){
        RequestData requestData=new RequestData();
        requestData.setVersion("2");
        requestData.setSignType("md5");
        requestData.setTimestamp("20190222110503");

        Data data=new Data();
        data.put("pageIndex","1");
        requestData.setBody(JsonUtils.GsonString(data));

        request("/auth/getHistoryUserCoupons",requestData, Sign.getSign(requestData));

    }



    @Test
    public void sendMessage(){
        RequestData requestData=new RequestData();
        requestData.setVersion("2");
        requestData.setSignType("md5");
        requestData.setTimestamp("201801052145");

        Data data =new Data();
        data.put("phone","15968844656");
        requestData.setBody(JsonUtils.GsonString(data));

        request("/sendMessage",requestData, Sign.getSign(requestData));
    }

    */
/*@Autowired
    private UserOrderService userOrderService;
    @Autowired
    private MachineService machineService;
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String templateIdOverDue = "kPBCWfVHpGxPdfIzhCLaNWFbmm-gL4FqWn9h_o0GiuY";
    private final String templateIdInvode = "ovmRlu308MwvOyrfJQz9bKHTC9uuAQCiWXswZHNZBOk";

    private final String overDueMessage = "客官，小伍正在为您准备餐食，请及时取餐。";
    private final String invodeMessage = "客官，餐食已在取餐口，请及时取餐。";

    @Test
    public void send() throws Exception {

        List<Data> userOrders = userOrderService.getOverdueUserOrder();
        if (userOrders.size() == 0) {
            return;
        }
        for (Data userOrder : userOrders) {
            int originateid = Integer.parseInt(userOrder.get("originateId").toString());
            if (originateid == 0 || originateid == 5) {
                return;
            }
            //微信扫一扫支付
            if (originateid == 4) {
                long userId = Long.parseLong(userOrder.get("userId").toString());
                long machineId = Long.parseLong(userOrder.get("machineId").toString());
                Machine machine = machineService.getMachine(machineId);
                //订单号码
                long userOrderId = Long.parseLong(String.valueOf(userOrder.get("userOrderId")));
                //机器名称
                String machineName = machine.getMachineName();
                List<UserOrderLine> userOrderLines = userOrderService.getUserOrderLine(userOrderId);
                if (userOrderLines.size() == 0) {
                    return;
                }
                //出餐数量
                int quantityint = 0;
                for (UserOrderLine userOrderLine : userOrderLines) {
                    quantityint += userOrderLine.getQuantity();
                }
                String quantity = String.valueOf(quantityint);
                //取餐号码
                String takeNo = String.valueOf(userOrder.get("takeNo"));
                //获取openId
                String openId = userService.getOpenId(userId);
                if (StringUtils.isNullOrEmpty(openId)) {
                    return;
                }
                userOrderService.sendTemplateClaim(userOrderId, openId, quantity, machineName, takeNo, templateIdOverDue, overDueMessage);

            }
        }
    }*//*

}

*/
