package com.wufanbao.api.oldclientservice.controller.RabbitMQ;//package com.wufanbao.api.oldclientservice.controller.RabbitMQ;
//
//import com.wufanbao.api.oldclientservice.controller.Alipay.Alipays;
//import com.wufanbao.api.oldclientservice.controller.JPush.JpushClientUtil;
//import com.wufanbao.api.oldclientservice.entity.UserOrder;
//import com.wufanbao.api.oldclientservice.service.UserOrderService;
//import org.json.JSONObject;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.logging.Logger;
//
///**
// * User:Wangshihao
// * Date:2017-10-10
// * Time:9:41
// *消费接收
// */
//public class MealsService implements MessageListener {
//    @Autowired
//    private JedisPool jedisPool;
//    @Autowired
//    private UserOrderService userOrderService;
//    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MealsService.class);
//    public void onMessage(Message message) {
//        String sss = new String(message.getBody());
//        Jedis jedis = jedisPool.getResource();
//        JSONObject jsonObject = new JSONObject(sss);
//        try {
//            String userId = jsonObject.getString("userId");
//            String orderId = jsonObject.getString("orderId");
//            UserOrder userOrder = userOrderService.queryInvalidTime(Long.valueOf(orderId));
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
//            Date time = userOrder.getInvalidTime();
//            String invalidTime = simpleDateFormat.format(time);
//            String registrationId = jedis.get(userId + ",1");
//            String notification_title = "您有一份餐食将在 " + invalidTime + " 后失效，请及时取餐!";
//            String extrasparam = orderId + ",2";
//            System.out.println(extrasparam);
//            int jj = JpushClientUtil.sendToRegistrationId(registrationId, notification_title, extrasparam);
//            System.out.println(jj + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        }catch (Exception e){
//            logger.info(e.toString());
//            throw new RuntimeException();
//        }finally {
//            if (null!=jedisPool){
//                jedisPool.returnResource(jedis);
//            }
//        }
//    }
//}
