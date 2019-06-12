package com.wufanbao.api.oldclientservice.controller.RabbitMQ;
//
//import com.alibaba.fastjson.JSON;
//import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
//import com.wufanbao.api.oldclientservice.controller.JPush.JpushClientUtil;
//import com.wufanbao.api.oldclientservice.dao.UserCouponDao;
//import com.wufanbao.api.oldclientservice.entity.*;
//import com.wufanbao.api.oldclientservice.service.UserOrderService;
//import com.rabbitmq.client.*;
//import org.json.JSONObject;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.TimeoutException;
//
///**
// * User:wangshihao
// * Date:2017-11-09
// * Time:17:57
// */
//@Configuration
//public class ReceiveRabbitMQAll implements ServletContextListener{
//
//    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReceiveRabbitMQAll.class);
//
//    @Value("${spring.rabbitmq.host}")
//    private static String host;
//
//    @Value("${spring.rabbitmq.port}")
//    private static String port;
//
//    @Value("${spring.rabbitmq.username}")
//    private static String username;
//
//    @Value("${spring.rabbitmq.password}")
//    private static String password;
//
//    @Value("${com.wufanbao.coupons}")
//    private static String coupons;
//
//    @Value("${com.wufanbao.meals}")
//    private static String meals;
//
//    @Value("${com.wufanbao.inviteRewards}")
//    private static String inviteRewards;
//
//    @Value("${com.wufanbao.productOut}")
//    private static String productOut;
//
//    @Value("${spring.redis.host}")
//    private static String getHost;
//
//    @Value("${spring.redis.password}")
//    private static String getpassword;
//
//
//    @Autowired
//    private UserOrderService userOrderService;
//
//    @Autowired
//    private UserCouponDao userCouponDao;
//    //优惠券
//    public void Coupons() throws IOException, TimeoutException {
//        /*ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.exchangeDeclare(coupons, "fanout");
//        //产生一个随机的队列名称
//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, coupons, "");//对队列进行绑定
//        System.out.println("优惠券过期");
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String message = new String(body, "UTF-8");
//                Jedis jedis = new Jedis(getHost,6379);
//                jedis.auth(getpassword);
//                JSONObject jsonObject = new JSONObject(message);
//                String userId = jsonObject.getString("userId");
//                String nums = jsonObject.getString("nums");
//                String registrationId = jedis.get(userId + ",1");
//                String notification_title = "难过，您有" + nums + "张优惠卷还有6个小时就过期了，请及时使用";
//                String extrasparam = "1";
//                int jj = JpushClientUtil.sendToRegistrationId(registrationId, notification_title, extrasparam);
//                System.out.println(jj + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                jedis.close();
//            }
//        };
//        channel.basicConsume(queueName, true, consumer);//队列会自动删除*/
//    }
//    //餐食过期
//    public void Meals() throws IOException, TimeoutException {
//        /*ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.exchangeDeclare(meals, "fanout");
//        //产生一个随机的队列名称
//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, meals, "");//对队列进行绑定
//        System.out.println("您有一份餐食即将过期");
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String message = new String(body, "UTF-8");
//                Jedis jedis = new Jedis(getHost,6379);
//                jedis.auth(getpassword);
//                JSONObject jsonObject = new JSONObject(message);
//                String userId = jsonObject.getString("userId");
//                String orderId = jsonObject.getString("orderId");
//                UserOrder userOrder = userOrderService.queryInvalidTime(Long.valueOf(orderId));
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
//                Date time = userOrder.getInvalidTime();
//                String invalidTime = simpleDateFormat.format(time);
//                String registrationId = jedis.get(userId + ",1");
//                String notification_title = "您有一份餐食将在 " + invalidTime + " 后失效，请及时取餐!";
//                String extrasparam = orderId + ",2";
//                System.out.println(extrasparam);
//                int jj = JpushClientUtil.sendToRegistrationId(registrationId, notification_title, extrasparam);
//                System.out.println(jj + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                jedis.close();
//            }
//        };
//        channel.basicConsume(queueName, true, consumer);//队列会自动删除*/
//    }
//    //邀请用户奖励
//    public void InviteRewards() throws IOException, TimeoutException {
//        /*ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.exchangeDeclare(inviteRewards, "fanout");
//        //产生一个随机的队列名称
//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, inviteRewards, "");//对队列进行绑定
//        System.out.println("邀请用户奖励");
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String message = new String(body, "UTF-8");
//                JSONObject jsonObject = new JSONObject(message);
//                String orderId = jsonObject.getString("orderId");
//                Map map=new HashMap();
//                UserInvitation userInvitation=userCouponDao.getUserInvitation(Long.valueOf(orderId));
//                long rewardId= IDGenerator.generateById("rewardId",userInvitation.getUserId());
//                long invitationId=userCouponDao.getInvitationId(userInvitation.getInvitedUserId(),userInvitation.getUserId());
//                BigDecimal value= new BigDecimal("20.000000");
//                UserReward userReward=new UserReward();
//                userReward.setRewardId(rewardId);
//                userReward.setUserId(userInvitation.getUserId());
//                userReward.setSourceType("userInvitation");
//                userReward.setSourceId(invitationId);
//                userReward.setCreateTime(new Date());
//                userReward.setRewardType(2);
//                userReward.setRewardValue(value);
//                userReward.setReward("20元");
//                if (userInvitation==null){
//                    map.put("returnState",0);
//                }else{
//                    int x=0;
//                    for (int i = 0; i <4 ; i++) {
//                        int count=getUserCoupon(233838379008l,userInvitation.getUserId());
//                        x=x+count;
//                    }
//                    System.out.println(x);
//                    if (x==8){
//                        userCouponDao.insertUserReward(userReward);
//                        map.put("returnState",1);
//                    }else{
//                        map.put("returnState",2);
//                    }
//                }
//            }
//        };
//        channel.basicConsume(queueName, true, consumer);//队列会自动删除*/
//    }
//    //用户扫码开仓，商品出仓
//    public void ProductOut() throws IOException, TimeoutException {
//        /*ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.exchangeDeclare(productOut, "fanout");
//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, productOut, "");
//        System.out.println("用户扫码了");
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope,
//                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String message = new String(body, "UTF-8");
//                JSONObject jsonObject = new JSONObject(message);
//                System.out.println(message.toString()+"MMMMMMMMMMMMM");
//                String machineId = jsonObject.getString("MachineId");//机器id
//                String userOrderId = jsonObject.getString("orderId");//订单id
//                Jedis jedis = new Jedis(getHost,6379);
//                jedis.auth(getpassword);
//                jedis.lpush(machineId + userOrderId, jsonObject.toString());
//                jedis.close();
//            }
//        };
//        channel.basicConsume(queueName, true, consumer);//队列会自动删除*/
//    }
//    //启动tomcat开启监听
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        /*try {
//            ReceiveRabbitMQAll receiveRabbitMQAll = new ReceiveRabbitMQAll();
//            receiveRabbitMQAll.Coupons();
//            receiveRabbitMQAll.Meals();
//            receiveRabbitMQAll.InviteRewards();
//            receiveRabbitMQAll.ProductOut();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }*/
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//    }
//
//
//    public int getUserCoupon(long couponDefinitionId,long userId){
//        UserCouponInfo userCouponInfo=userCouponDao.selectCouponInfo(couponDefinitionId);
//        ValidityRule validityRules= JSON.parseObject(userCouponInfo.getValidityRule(), ValidityRule.class);
//        UserCoupon userCoupon=new UserCoupon();
//        userCoupon.setUserId(userId);
//        userCoupon.setCouponDefinitionId(couponDefinitionId);
//        long couponId= IDGenerator.generateById("couponId",userId);
//        userCoupon.setCouponId(couponId);
//        userCoupon.setCreateTime(new Date());
//        userCoupon.setStartTime(new Date());
//        userCoupon.setStatus(1);
//        userCoupon.setEndTime(getCouponEndTime(validityRules.getActiveTime()));
//        int count=userCouponDao.insertUserCoupon(userCoupon);
//        int count1=userCouponDao.gotCoupon(couponDefinitionId);
//        int count2=count+count1;
//        return count2;
//    }
//    /**
//     * 获取优惠券结束时间
//     * @param actieTime
//     * @return
//     */
//    public Date getCouponEndTime(int actieTime){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.DAY_OF_MONTH, + actieTime);//+1今天的时间加一天
//        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = calendar.getTime();
//        return date;
//    }
//
//    public static void main(String[] args) throws IOException, TimeoutException {
//        /*ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.exchangeDeclare("ProductOut", "fanout");
//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, "ProductOut", "");
//        System.out.println("用户扫码了");
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope,
//                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String message = new String(body, "UTF-8");
//                System.out.println(message);
//                JSONObject jsonObject = new JSONObject(message);
//                String machineId = jsonObject.getString("MachineId");//机器id
//                String userOrderId = jsonObject.getString("orderId");//订单id
//                String productGlobalId = jsonObject.getString("ProductGlobalId");//商品id
//                String entercloseId = jsonObject.getString("EntercloseId");//出餐口
//                String timespan = jsonObject.getString("Timespan");//预计加热时间
//                System.out.println(machineId+userOrderId+productGlobalId+entercloseId+timespan);
//            }
//        };
//        channel.basicConsume(queueName, true, consumer);//队列会自动删除*/
//    }
//
//}
