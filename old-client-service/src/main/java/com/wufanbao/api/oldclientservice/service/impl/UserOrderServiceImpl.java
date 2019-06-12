
package com.wufanbao.api.oldclientservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.DateUtil;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Bean.BankPayBean;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Util.Sign;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Util.YTHttpHandler;
import com.wufanbao.api.oldclientservice.controller.RabbitMQ.RabbitMQSender;
import com.wufanbao.api.oldclientservice.controller.UserOrderController;
import com.wufanbao.api.oldclientservice.dao.*;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.*;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeoutException;

/**
 * User:WangZhiyuan
 */
@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    private UserOrderLineDao userOrderLineDao;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private TakeMealService takeMealService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserRegisteredDao userRegisteredDao;
    @Autowired
    private FamilyPayDao familyPayDao;
    @Autowired
    private UserCapitalLogDao userCapitalLogDao;
    @Autowired
    private UpdateUserInfoDao updateUserInfoDao;
    @Autowired
    private UserIntegralDao userIntegralDao;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private ProductSrevice productSrevice;
    @Autowired
    private FamilyPayService familyPayService;
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private UserGradeDao userGradeDao;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private ClientSetting clientSetting;

    private final static String SERVER = "http://121.196.201.74:81/LianLian/WxRefund";
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserOrderController.class);


    /**
     * 添加订单／确认订单
     *
     * @param userOrder          用户订单信息
     * @param products           订单商品集合
     * @param couponDefinitionId 优惠券ID
     * @param appointTime        预约时间
     * @return
     * @throws Exception
     * @editor zhaojing
     */
    @Override
    @Transactional
    public Object insertUserOrder(UserOrder userOrder, String products, long couponDefinitionId, String appointTime) throws Exception {
        Map map = new HashMap();

        long userOrderId = IDGenerator.generateById("userOrderId", userOrder.getUserId());//生成订单Id
        userOrder.setUserOrderId(userOrderId);
        boolean priceCheck = priceCheck(userOrder, products, couponDefinitionId);

        if (!priceCheck) {
            map.put("type", 6);
            map.put("userOrderId", userOrderId);
            map.put("createTime", new Date());
            return map;
        }

        BigDecimal familyAmount = userOrder.getFamilyPayAmount();
        BigDecimal companyPayAmount = userOrder.getCompanyPayAmount();
        if (companyPayAmount.doubleValue() < 0) {
            userOrder.setCompanyPayAmount(BigDecimal.valueOf(0));
        }
        if (familyAmount.doubleValue() < 0) {
            userOrder.setFamilyPayAmount(BigDecimal.valueOf(0));
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        long invalidTime = sdf1.parse(appointTime).getTime();
        //获取当前毫秒数
        long newTime = System.currentTimeMillis();
        long minDate = newTime - 300000; //5 分钟
        long maxDate = newTime + 86400000; //24 小时
        if (invalidTime < minDate && invalidTime > maxDate) {
            map.put("type", 5);
            map.put("userOrderId", userOrderId);
            map.put("createTime", new Date());
            return map;
        }
        //创建的时间
        java.sql.Timestamp time = new java.sql.Timestamp(date.getTime());
        userOrder.setCreateTime(time);
        //失效时间 预约时间的一个半小时
        java.sql.Timestamp time2 = new java.sql.Timestamp(sdf1.parse(appointTime).getTime() + 1000 * 60 * 2);//5400000
        userOrder.setInvalidTime(time2);

        //String str = Long.toString(userOrder.getUserId(), 16) + 4;
        //String userOrderNum = redisUtils.get(String.valueOf(userOrderId) + "num");
        long machineId = userOrder.getMachineId();
        try {
            if (!lockProduct(products, userOrder.getMachineId())) {
                map.put("type", 2);//商品已卖完
                map.put("userOrderId", 0);
                map.put("createTime", time);
                map.put("payment", 3);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return map;
            }

            redisUtils.incr(String.valueOf(userOrderId) + "num");
            //插入订单
            int count = userOrderDao.insertUserOrder(userOrder);
            if (count <= 0) {
                map.put("type", 0);
                map.put("userOrderId", 0);
                map.put("createTime", time);
                map.put("payment", 3);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return map;
            }

            List<UserOrderLine> productGlobalInfo = JSON.parseArray(products, UserOrderLine.class);
            if (userOrder.getDiscountId() != 0) {
                //跟新用户使用的优惠券状态
                userCouponDao.updateUserCouponStatus(userOrder.getDiscountId(), 2);
                //增加该优惠券的使用数量
                userCouponDao.updateCouponDefinitionUsed(couponDefinitionId);
            }
            for (int i = 0; i < productGlobalInfo.size(); i++) {
                UserOrderLine userOrderLine1 = productGlobalInfo.get(i);
                userOrderLine1.setUserOrderId(userOrderId);
                long orderLineId = IDGenerator.generateById("orderLineId", userOrder.getUserId());
                userOrderLine1.setUserOrderLineId(orderLineId);
                userOrderLineDao.insertOrderLine(userOrderLine1);
            }
            if (userOrder.getActualAmount() == 0) {
                //修改订单状态
                Map<String, Integer> quoatMap = couponPayment(userOrder.getUserId(), userOrderId);
                int submit = quoatMap.get("submit");
                int type = quoatMap.get("type");
                Map map1 = results(submit, type, userOrderId);
                int type1 = Integer.parseInt(map1.get("type").toString());
                if (type1 == 4 && type1 == 3) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderId", userOrderId);
                    jsonObject.put("machineId", machineId);
                    rabbitMQSender.lockSend(jsonObject);
                    //三分钟后取消订单
                    Times(180000, userOrderId, userOrder.getUserId(), userOrder.getDiscountId());
                }
                return map1;
            }
            if (userOrder.getFamilyPayAmount() != null && familyAmount.doubleValue() >= 0) {
                Map<String, Integer> quoatMap = familyPayService.familyPay(userOrder.getUserId(), BigDecimal.valueOf(userOrder.getActualAmount()), userOrderId);
                int submit = quoatMap.get("submit");
                int type = quoatMap.get("type");
                Map map1 = results(submit, type, userOrderId);
                int type1 = Integer.parseInt(map1.get("type").toString());
                if (type1 == 4 && type1 == 3) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderId", userOrderId);
                    jsonObject.put("machineId", machineId);
                    rabbitMQSender.lockSend(jsonObject);
                    //三分钟后取消订单
                    Times(180000, userOrderId, userOrder.getUserId(), userOrder.getDiscountId());
                }
                return map1;
            }
            if (companyPayAmount.doubleValue() >= 0 && userOrder.getCompanyPayAmount() != null) {
                Map<String, Integer> quoatMap = userOrderService.getUserQuotaInfo(userOrder.getUserId(), BigDecimal.valueOf(userOrder.getActualAmount()), userOrderId);
                int submit = quoatMap.get("submit");
                int type = quoatMap.get("type");
                Map map1 = results(submit, type, userOrderId);
                int type1 = Integer.parseInt(map1.get("type").toString());
                if (type1 == 4 && type1 == 3) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                } else {

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderId", userOrderId);
                    jsonObject.put("machineId", machineId);
                    rabbitMQSender.lockSend(jsonObject);

                    //三分钟后取消订单
                    Times(180000, userOrderId, userOrder.getUserId(), userOrder.getDiscountId());
                }
                return map1;
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderId", userOrderId);
            jsonObject.put("machineId", machineId);
            rabbitMQSender.lockSend(jsonObject);
            map.put("type", 1);
            map.put("userOrderId", userOrderId);
            map.put("createTime", time);
            map.put("payment", 3);
            //三分钟后取消订单
            Times(180000, userOrderId, userOrder.getUserId(), userOrder.getDiscountId());
        } catch (RuntimeException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.info(e.toString());
        }
        return map;
    }

    //订单支付
    @Override
    @Transactional
    public Map orderPay(long userOrderId, int payType, long userId, double amount, String payPassword) {
        UserRegistered registered = userRegisteredDao.queryPayPwd(userId, payPassword);
        String key = Long.toString(userId, 16) + 4;
        if (registered == null) {
            long count = redisUtils.incr(key);
            if (count >= 1) {
                redisUtils.expire(key, 1000 * 60 * 3);//有效期3分钟
            }
            if (count > 2) {
                Map map1 = new HashMap();
                map1.put("return", 2);
                return map1;
            }
            Map map1 = new HashMap();
            map1.put("return", 1);
            return map1;
        }

        redisUtils.del(key);

        //修改用户余额
        UserRegistered userRegistered = userRegisteredDao.querybalance(userId);//查询可用余额
        double usableBalance = userRegistered.getUsableBalance();
        double newBalance = usableBalance - amount;

        int count = updateUserInfoDao.updateMasterBalance(userId, BigDecimal.valueOf(-amount));
        if (count != 1) {
            //支付失败
            Map map = new HashMap();
            map.put("return", 1);
            return map;
        }

        long userCapitalLogId = IDGenerator.generateById("userCapitalLogId", userId);
        userCapitalLogDao.addUserCapital(userCapitalLogId, userId, -amount, "订单付款", userOrderId, "订单付款", newBalance);
        //修改订单状态付款金额
        UserOrder userOrder = new UserOrder();
        userOrder.setUserOrderId(userOrderId);
        userOrder.setActualAmount(amount);
        userOrder.setReceiveAmount(amount);
        userOrder.setPayType(payType);
        userOrder.setStatus(3);

        userOrderDao.updatePay(userOrder);

        splitUserOrder(userOrderId, userId);

        redisUtils.set(String.valueOf(userId) + "paytype", String.valueOf(payType));

        Map map = new HashMap();
        map.put("return", 0);
        return map;
    }


    @Override
    public List<ProductOnline> getProductOnline(long orderId) {
        return userOrderDao.getProductOnline(orderId);
    }

    @Override
    public List<OrderInfo> getOrderInfo(long userId) {
        return userOrderDao.getOrderInfo(userId);
    }

    @Override
    public UserOrder queryInvalidTime(long userOrderId) {
        return userOrderDao.queryInvalidTime(userOrderId);
    }

    @Override
    public UserOrder queryUserOrderStatus(long userOrderId) {
        return userOrderDao.queryUserOrderStatus(userOrderId);
    }

    @Override
    public List<ProductDetails> queryProduct(long machineId) {
        return userOrderDao.queryProduct(machineId);
    }

    @Override
    public List<ProductDetails> queryDetails(long machineId, long productGlobalId) {
        return userOrderDao.queryDetails(machineId, productGlobalId);
    }

    @Override
    public List<OrderInfo> getOrderInfoByOrderId(long orderId) {
        return userOrderDao.getOrderInfoByOrderId(orderId);
    }

    public String attachImageUrls(String str) {
        String[] arr = str.split(";");
        String urls = "";
        for (int i = 0; i < arr.length; i++) {
            urls = urls + clientSetting.getResource() + arr[i] + ";";
        }
        return urls;
    }

    /**
     * 根据机器号获取机位图
     *
     * @param machineId
     * @return
     */
    @Override
    public Map getMachineLocation(long machineId) {
        MachineLocation machineLocation = userOrderDao.getMachineLocation(machineId);
        Map map = new HashMap();
        if (machineLocation != null) {
            String contractScans = machineLocation.getSeekPhotos();
            machineLocation.setSeekPhotos(attachImageUrls(machineLocation.getSeekPhotos()));
            if (contractScans == null) {
                map.put("returnState", 0);
                map.put("machineLocation", machineLocation);
                return map;
            }
            String[] arr = contractScans.split(";");
            List<MachineLocation.seekPhotosVo> contractScansVoList = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                MachineLocation.seekPhotosVo seekPhotosVo = new MachineLocation.seekPhotosVo();
                seekPhotosVo.setSeekPhotosVo(clientSetting.getResource() + arr[i]);
                contractScansVoList.add(seekPhotosVo);
            }
            machineLocation.setSeekPhotosVoList(contractScansVoList);
            map.put("returnState", 1);
            map.put("machineLocation", machineLocation);
        } else {
            map.put("returnState", 0);
            map.put("machineLocation", machineLocation);
        }

        return map;
    }

    /**
     * 作废掉过期未取餐的订单
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancellationOrder() throws Exception {
        UserOrder userOrder = userOrderDao.getExpireUserOrder();
        if (userOrder == null) {
            return;
        }
        long userOrderId = userOrder.getUserOrderId();
        long userId = userOrder.getUserId();
        BigDecimal companyPayAmount = userOrder.getCompanyPayAmount();//企业付金额
        BigDecimal familyPayAmount = userOrder.getFamilyPayAmount();//家庭付金额
        BigDecimal receiveAmount = BigDecimal.valueOf(userOrder.getReceiveAmount());//实收金额
        BigDecimal actualAmount = BigDecimal.valueOf(userOrder.getActualAmount());//实付金额
        boolean machineType = userOrder.isMadeInvoice();//机器是否可以退款
        int payType = userOrder.getPayType();
        boolean refundCompanyType;
        boolean refundFamilyPay;

        if (userOrderDao.invalidUserOrder(userOrder.getUserOrderId()) <= 0) {
            throw new Exception("失效订单失败");
        }

        if (!unLockProduct(userOrder.getUserOrderId())) {
            throw new Exception("锁定商品失败");
        }


        if (companyPayAmount.doubleValue() > 0) {
            refundCompanyType = portionRefundEnterprise(userId, userOrderId, companyPayAmount);
        } else {
            refundCompanyType = true;
        }
        if (familyPayAmount.doubleValue() > 0) {
            refundFamilyPay = portionRefundFamily(userId, userOrderId, familyPayAmount);
        } else {
            refundFamilyPay = true;
        }

        if (!refundCompanyType || !refundFamilyPay) {
            throw new Exception("退款失败:家庭付、企业付款失败");
        }

        //生成资金流水表id
        long userCapitalLogId = IDGenerator.generateById("userCapitalLogId", userId);
        //插入资金流水
        if (userCapitalLogDao.insertUserCapital(userCapitalLogId, userId, actualAmount, "userOrder", userOrderId, "退款") <= 0) {
            throw new Exception("退款失败:插入资金流水失败");
        }

        if (payType == 4) {
            //余额付款
            if (userOrderDao.userRefund(userId, actualAmount) <= 0) {
                throw new Exception("退款失败:退还余额失败");
            }
        }

//        if (payType == 2 || payType == 3 || payType == 5) {
//            //支付宝，微信，银行卡付款
//            if (tuikuan(String.valueOf(userId), actualAmount.toString(), String.valueOf(userOrderId)) <= 0) {
//                throw new Exception("退款失败:退还支付宝，微信，银行卡失败");
//            }
//        }

//        try {
//            WechatPay wechatPay = new WechatPay();
//            long capitalLogId = IDGenerator.generateById("capitalLogId", userOrderId);
//            wechatPay.refund(String.valueOf(userOrderId), String.valueOf(capitalLogId), actualAmount, actualAmount, "/webapi/wechat/refundNotify");
//        } catch (Exception e) {
//            throw new Exception("退款失败:退还余额失败");
//        }

        if (userOrder.getDiscountId() > 0) {
            //返还优惠券状态
            if (userCouponDao.updateCoupon(userId, userOrder.getDiscountId(), 1) <= 0) {
                throw new Exception("退款失败:退还优惠券失败");
            }
        }
    }

    /**
     * 根据用户id去查询用户所有的订单
     *
     * @param userId
     * @return
     */
    @Override
    public List<OrderInfo> getAllOrderInfo(long userId) {
        return userOrderDao.getAllOrderInfo(userId);
    }

    /**
     * 判断是否有交易完成的用户订单
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserOrder> judgeOrder(long userId) {
        return userOrderDao.judgeOrder(userId);
    }

    /**
     * 取消订单退钱
     */
    @Override
    public int cancelRefund(long userOrderId, int status) {
        return userOrderDao.cancelRefund(userOrderId, status);
    }

    @Override
    public TimeOut qutrytimeOut(long userId, long userOrderId) {
        return userOrderDao.qutrytimeOut(userId, userOrderId);
    }

    /**
     * 用户评价
     *
     * @param userOrder
     * @param userOrderLine
     * @return java.util.Map
     */
    @Override
    @Transactional
    public Map userEvaluate(UserOrder userOrder, String userOrderLine) {
        Map map = new HashMap();
        //接受用户的订单的商品
        System.out.println(userOrderLine);
        long userOrderId = userOrder.getUserOrderId();
        UserOrder userOrder1 = productDao.queryOrder(userOrderId);
        long userId = userOrder1.getUserId();
        UserGrade userGrade = userGradeDao.getUserGrade(userId);
        double multiple = Double.valueOf(userGradeDao.getUserGradePrivilege(userGrade.getUserGradeId(), 1).getContent());
        int integral = (int) Math.ceil(userOrder1.getActualAmount() * multiple);
        int count = userOrderDao.userEvaluate(userOrder);
        List<UserOrderLine> userOrderLineList = JSON.parseArray(userOrderLine, UserOrderLine.class);
        int x = 0;
        for (UserOrderLine userOrderLineInfo : userOrderLineList) {
            int count1 = userOrderDao.userEvaluateLine(userOrderLineInfo);
            if (count1 > 0) {
                x++;
            }
        }
        //用户积分日志
        int count1 = 0;
        try {
            long integralLogId = IDGenerator.generateById("integralLogId", userId);
            count1 += userIntegralDao.integralGrowUp(userId, 2);
            count1 += userIntegralDao.insertIntegralLog(integralLogId, userId, 2, "用户评价", userOrderId, "用户评价获取积分");
            //用户成长日志
            long userGradeLogId = IDGenerator.generateById("userGradeLogId", userId);
            count1 += userGradeDao.insertGradeLog(userGradeLogId, userId, 2, "userOrder", userOrderId, "用户评价");
            //用户成长值增长
            count1 += userGradeDao.gradeGrowUp(userId, 2);


            //用户购买商品积分
            long integralLogId1 = IDGenerator.generateById("integralLogId", userId);
            count1 += userIntegralDao.integralGrowUp(userId, integral);
            count1 += userIntegralDao.insertIntegralLog(integralLogId1, userId, integral, "订单消费", userOrderId, "订单消费获取积分");
            //用户购买商品成长日志
            long userGradeLogId1 = IDGenerator.generateById("userGradeLogId", userId);
            count1 += userGradeDao.insertGradeLog(userGradeLogId1, userId, integral, "userOrder", userOrderId, "订单消费");
            //用户成长值增长
            count1 += userGradeDao.gradeGrowUp(userId, integral);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 1 && x == userOrderLineList.size() && count1 == 8) {
            map.put("returnState", 1);
        } else {
            map.put("returnState", 0);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    /**
     * 用户奖励
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public Map userAward(long userId, long userOrderId) {
        Map map = new HashMap();
        UserReward userReward = userOrderDao.getUserIdByOrderId(userOrderId);//查询是否已经发过积分
        if (userReward == null) {
            long rewardId = IDGenerator.generateById("rewardId", userId);//用户及奖励表id
            long integralLogId = IDGenerator.generateById("integralLogId", userId);
            BigDecimal integral = BigDecimal.valueOf(10.0);//10积分
            int count = userOrderDao.awardLog(rewardId, userId, "分享", userOrderId, new Date(), integral, 4, "美食分享");//用户奖励表
            int count2 = userIntegralDao.insertIntegralLog(integralLogId, userId, 2, "用户奖励表", userOrderId, "用户分享奖励");//用户积分日志
            int count3 = userOrderDao.updateUserIntegral(userId, integral);//用户积分
            int count4 = count + count2 + count3;
            if (count4 == 3) {
                map.put("returnState", 1);//成功
            } else {
                map.put("returnState", 0);//失败回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } else {
            map.put("returnState", 2);//已经领过积分
        }
        return map;
    }

    @Override
    @Transactional
    public ResponseInfo getUserFindAward(long userId, long sourceTypeId) {
        ResponseInfo responseInfo = new ResponseInfo();
        UserCapitalLog userCapitalLog = userOrderDao.getUserFindAward(userId, "appDiscover", sourceTypeId);//查询是否已经发过积分
        if (userCapitalLog == null) {
            long rewardId = IDGenerator.generateById("rewardId", userId);//用户及奖励表id
            long integralLogId = IDGenerator.generateById("integralLogId", userId);
            BigDecimal integral = BigDecimal.valueOf(2.0);//10积分
            int count = userOrderDao.awardLog(rewardId, userId, "appDiscover", sourceTypeId, new Date(), integral, 4, "浏览发现");//用户奖励表
            int count2 = userIntegralDao.insertIntegralLog(integralLogId, userId, 2, "appDiscover", sourceTypeId, "浏览发现");//用户积分日志
            int count3 = userOrderDao.updateUserIntegral(userId, integral);//用户积分
            int count4 = count + count2 + count3;
            if (count4 == 3) {
                //成功
                responseInfo.setCode(1);
            } else {
                responseInfo.setCode(1100);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } else {
            responseInfo.setCode(1101);
        }
        return responseInfo;
    }

    /**
     * 随机展示一个图片
     *
     * @return
     */
    @Override
    public Map randShowImage(long userId, long userOrderId) {
        Map map = new HashMap();
        ImagesShare imagesShare = userOrderDao.randShowImage();
        imagesShare.setShareImage(clientSetting.getResource() + imagesShare.getShareImage());
        imagesShare.setShowImage(clientSetting.getResource() + imagesShare.getShowImage());
        if (imagesShare != null) {
            map.put("returnState", 1);
            map.put("imagesShare", imagesShare);
            map.put("description", imagesShare.getDescription());
            map.put("shareUrl", imagesShare.getUrl() + "?userOrderId=" + userOrderId + "&userId=" + userId);
        } else {
            map.put("returnState", 0);
            map.put("imagesShare", imagesShare);
            map.put("description", imagesShare.getDescription());
            map.put("shareUrl", imagesShare.getUrl());
        }
        return map;
    }

    /**
     * 企业付ss
     *
     * @return
     */
    @Override
    @Transactional
    public Map<String, Integer> getUserQuotaInfo(long userId, BigDecimal amount, long userOrderId) {
        //获取用户企业和企业额度信息
        Map<String, Integer> map = new HashMap();
        UserQuota userQuota = userOrderDao.getUserQuotaInfo(userId);
        BigDecimal userBalance1 = userQuota.getTotalQuota().subtract(userQuota.getConsumeQuota());
        double userBalance = userBalance1.doubleValue();
        double balance = userQuota.getBalance().doubleValue();
        double db = 0;
        BigDecimal dd = BigDecimal.valueOf(db);
        //付款金额小于公司账户余额
        if (userBalance >= amount.doubleValue() || userQuota.getQuotaType() == 0) {
            //付款金额小于账户限额 （全额）
            if (amount.doubleValue() <= balance || userQuota.getQuotaType() == 0) {
                //修改用户余额
                int count1 = 0;
                if (userQuota.getQuotaType() != 0) {
                    count1 = userOrderDao.updateUserQuota(userId, amount);
                } else {
                    count1 = 1;
                }
                //修改公司余额
                int count2 = userOrderDao.updateCompanyBalance(userQuota.getCompanyId(), amount);
                // 插入企业付记录
                long capitalLogId = IDGenerator.generateById("capitalLogId", userId);
                int count3 = userOrderDao.insertCompanyCapitalLog(capitalLogId, userQuota.getCompanyId(), 0, amount, 0, "userOrder", String.valueOf(userOrderId), 1, "企业付");

                long newTime = System.currentTimeMillis();
                long time = newTime + 129600000;
                Date invalidTime = new Date(time);
                int count5 = userOrderDao.updateUserOrderType(userOrderId, 3, dd, amount, new Date(), 4);
                splitUserOrder(userOrderId, userId);
                int count4 = count1 + count2 + count3 + count5;
                if (count4 == 4) {
                    map.put("submit", 1);
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    map.put("submit", 0);
                }
            } else {
                map.put("submit", 2);
            }
            //全款
            map.put("type", 1);
            return map;
        }
        if (amount.doubleValue() > userBalance && userQuota.getQuotaType() != 0) {
            if (userBalance <= balance) {
                int count1 = 0;
                //修改用户余额
                try {
                    count1 = userOrderDao.updateUserQuota(userId, BigDecimal.valueOf(userBalance));
                } catch (Exception e) {
                    e.printStackTrace();
                    count1 = 0;
                }
                //修改公司余额
                int count2 = userOrderDao.updateCompanyBalance(userQuota.getCompanyId(), BigDecimal.valueOf(userBalance));
                // 插入企业付记录
                long capitalLogId = IDGenerator.generateById("capitalLogId", userId);
                int count3 = userOrderDao.insertCompanyCapitalLog(capitalLogId, userQuota.getCompanyId(), 0, BigDecimal.valueOf(userBalance), 0, "userOrder", String.valueOf(userOrderId), 1, "企业赴");
                int count5 = userOrderDao.updateUserOrderType(userOrderId, 2, amount.subtract(userBalance1), amount, null, 0);
                int count4 = count1 + count3 + count2 + count5;
                if (count4 == 4) {
                    map.put("submit", 1);
                } else {
                    map.put("submit", 0);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            } else {
                map.put("submit", 2);
            }
            map.put("type", 2);//部分款

        }
        return map;
    }

    // public Object userFamilyPay


    /**
     * 取消订单
     *
     * @param userId
     * @param userOrderId
     * @return
     */
    @Override
    @Transactional
    public boolean refundEnterprise(long userId, long userOrderId) {
        CompanyCapitalLog companyCapitalLog = userOrderDao.getCompanyPayAmount(userOrderId);
        UserQuota userQuota = userOrderDao.getUserQuotaInfo(userId);
        int amount = userQuota.getConsumeQuota().intValue();
        BigDecimal refundAmount = companyCapitalLog.getAmount();
        int quotaType = companyCapitalLog.getQuotaType();
        long companyId = companyCapitalLog.getCompanyId();
        long insertTime = companyCapitalLog.getInsertTime().getTime();
        return refundEnterpriseAmount(quotaType, amount, userId, companyId, refundAmount, insertTime);
    }

    //家庭付取消
    @Override
    @Transactional
    public boolean refundFamily(long userId, long userOrderId) {
        UserCapitalLog userCapitalLog = familyPayDao.getUserMaster(userId, userOrderId, "userOrder");
        if (BigDecimal.valueOf(userCapitalLog.getAmount()) == null) {
            return true;
        }
        BigDecimal familyAmount = BigDecimal.valueOf(userCapitalLog.getAmount());
        return portionRefundFamily(userId, userOrderId, familyAmount);
    }

    /**
     * 用户订单评价详情
     *
     * @param userOrderId 订单ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/6/22
     */
    @Override
    public ResponseInfo userOrderEvaluation(long userOrderId) {
        Map userOrderInfo = userOrderDao.getUserOrderInfoByOrderId(userOrderId);
        String assessTime = userOrderInfo.get("assessTime").toString();
        assessTime = assessTime.substring(0, 10);
        List<Map> orderEvaluationList = userOrderDao.getOrderEvaluationByUserOrderId(userOrderId);
        for (Map orderEvaluationMap : orderEvaluationList) {
            //图片地址
            String imageUrl = orderEvaluationMap.get("imageUrl").toString();
            String[] arr = imageUrl.split(";");
            orderEvaluationMap.put("imageUrl", clientSetting.getResource() + arr[0]);

        }
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(1);
        Map map = new HashMap();
        map.put("assessTime", assessTime);
        map.put("orderEvaluationList", orderEvaluationList);
        responseInfo.setData(map);
        return responseInfo;
    }

    /**
     * 根据用户ID查询用户家庭付订单
     *
     * @param userId    主用户ID
     * @param sonUserId 子账ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/7/2
     */
    @Override
    public ResponseInfo familyPayOrderByUserId(long userId, long sonUserId) {
        ResponseInfo responseInfo = new ResponseInfo();
        List<Map> familyPayOrderList = userOrderDao.familyPayOrderByUserId(userId, sonUserId);
        Map map = new HashMap();
        map.put("familyPayOrderList", familyPayOrderList);
        responseInfo.setCode(1);
        responseInfo.setData(map);
        return responseInfo;
    }

    @Override
    public ResponseInfo takeFood(long userId, long userOrderId) throws Exception {
        Map map = userOrderDao.takeFoodUserOrderInfo(userId, userOrderId);
        ResponseInfo responseInfo = new ResponseInfo();
        if (map == null) {
            responseInfo.setCode(2060);
            return responseInfo;
        }
        String machineId = map.get("machineId").toString();
        String key = machineId + "scanCode" + userId;
        String machineId2 = redisUtils.get("AlphaWuFan:ScanCode:" + key);
        if (machineId2 == null) {
            responseInfo.setCode(2061);
            return responseInfo;
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("MachineId", machineId);
            jsonObject.put("OrderId", userOrderId);
            rabbitMQSender.takeFood(jsonObject);
        }
        responseInfo.setCode(1);
        return responseInfo;
    }

    /**
     * 退款详情
     *
     * @param userOrderId
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/7/2
     */
    @Override
    public ResponseInfo returnUserOrderLineInfo(long userOrderId) {
        List<Map> userOrderInfo = userOrderDao.returnUserOrderLineInfo(userOrderId);
        BigDecimal receiveAmount = userOrderDao.receiveAmount(userOrderId);
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(1);
        Map map = new HashMap();
        map.put("userOrderInfo", userOrderInfo);
        map.put("receiveAmount", receiveAmount);
        responseInfo.setData(map);
        return responseInfo;
    }

    /**
     * 展示的订单信息
     *
     * @param userOrderId 用户订单ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/7/4
     */
    @Override
    public ResponseInfo showOrderInfo(long userOrderId) {
        Map map = userOrderDao.showOrderInfo(userOrderId);
        String[] arr = map.get("seekPhotos").toString().split(";");
        map.put("seekPhotos", clientSetting.getResource() + arr[0].toString());
        String[] arr1 = map.get("imageUrl").toString().split(";");
        map.put("imageUrl", clientSetting.getResource() + arr1[0].toString());
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(1);
        responseInfo.setData(map);
        return responseInfo;
    }


    /**
     * 企业退款
     *
     * @param userId
     * @param userOrderId
     * @param refundAmount
     * @return
     */
    private boolean portionRefundEnterprise(long userId, long userOrderId, BigDecimal refundAmount) {

        CompanyCapitalLog companyCapitalLog = userOrderDao.getCompanyPayAmount(userOrderId);
        UserQuota userQuota = userOrderDao.getUserQuotaInfo(userId);
        int amount = userQuota.getConsumeQuota().intValue();
        System.out.println(userQuota.getConsumeQuota().intValue() + " " + companyCapitalLog.getQuotaType() + " " + companyCapitalLog.getCompanyId());
        int quotaType = companyCapitalLog.getQuotaType();
        long companyId = companyCapitalLog.getCompanyId();
        long insertTime = companyCapitalLog.getInsertTime().getTime();
        return refundEnterpriseAmount(quotaType, amount, userId, companyId, refundAmount, insertTime);

    }

    /**
     * 家庭付退款
     *
     * @param userId
     * @param userOrderId
     * @param refundAmount
     * @return
     */
    private boolean portionRefundFamily(long userId, long userOrderId, BigDecimal refundAmount) {
        UserCapitalLog userCapitalLog = familyPayDao.getUserMaster(userId, userOrderId, "userOrder");
        long masterUserId = userCapitalLog.getUserId();
        //生成资金流水表id
        long userCapitalLogId = IDGenerator.generateById("userCapitalLogId", userCapitalLog.getUserId());
        //退还用户额度
        int count2 = familyPayDao.updateUserFamilyPay(userId, refundAmount.negate());
        //退款给主账户
        int count3 = updateUserInfoDao.updateMasterBalance(masterUserId, refundAmount);
        //插入资金流水
        int count1 = userCapitalLogDao.insertUserCapital(userCapitalLogId, masterUserId, refundAmount, "退款", userCapitalLog.getUserId(), "亲密付退款");
        count1 = count2 + count1 + count3;
        if (count1 == 3) {
            return true;
        }
        return false;
    }

    /**
     * 企业付订单退款
     *
     * @param quotaType
     * @param amount
     * @param userId
     * @param companyId
     * @param refundAmount
     * @param insertTime
     * @return
     */
    private boolean refundEnterpriseAmount(int quotaType, int amount, long userId, long companyId, BigDecimal refundAmount, long insertTime) {
        int count = 0;
        int count1 = 0;
        //日
        if (quotaType == 1) {
            //在当天的话把额度退还给用户
            if (DateUtil.isToday(insertTime)) {
                if (amount == 0) {
                    count1 = 1;
                } else {
                    count1 = userOrderDao.refundUser(userId, refundAmount);
                }
            }
            count1 = 1;
            count = userOrderDao.refundEnterprise(companyId, refundAmount);
        }
        //月
        if (quotaType == 2) {
            //在当月的话把额度退给用户
            if (DateUtil.isThisMonth(insertTime)) {
                if (amount == 0) {
                    count1 = 1;
                } else {
                    count1 = userOrderDao.refundUser(userId, refundAmount);
                }
            }
            count1 = 1;
            count = userOrderDao.refundEnterprise(companyId, refundAmount);
        }
        //无限
        if (quotaType == 0) {
            count1 = 1;
            count = userOrderDao.refundEnterprise(companyId, refundAmount);
        }
        count = count + count1;
        if (count == 2) {
            return true;
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }


    /**
     * 退款
     *
     * @param userId
     * @param userOrderId
     * @return
     */
    @Override
    @Transactional
    public Map refundUser(long userId, long userOrderId) throws Exception {
        Map map = new HashMap();
        UserOrder userOrder = userOrderDao.orderType(userOrderId);
        int userOrderStatus = userOrder.getStatus();
        BigDecimal companyPayAmount = userOrder.getCompanyPayAmount();//企业付金额
        BigDecimal familyPayAmount = userOrder.getFamilyPayAmount();//家庭付金额
        BigDecimal receiveAmount = BigDecimal.valueOf(userOrder.getReceiveAmount());//实收金额
        BigDecimal actualAmount = BigDecimal.valueOf(userOrder.getActualAmount());//实付金额
        boolean machineType = userOrder.isMadeInvoice();//机器是否可以退款
        int payType = userOrder.getPayType();
        boolean refundCompanyType;
        boolean refundFamilyPay;

        try {

            if (userOrderStatus == 3) {
                //  if (limitingCondition==1){
                if (companyPayAmount.doubleValue() > 0) {
                    refundCompanyType = portionRefundEnterprise(userId, userOrderId, companyPayAmount);
                } else {
                    refundCompanyType = true;
                }
                if (familyPayAmount.doubleValue() > 0) {
                    refundFamilyPay = portionRefundFamily(userId, userOrderId, familyPayAmount);
                } else {
                    refundFamilyPay = true;
                }
                if (refund(payType, userId, actualAmount, userOrderId, userOrder.getDiscountId()) && refundCompanyType && refundFamilyPay) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderId", userOrderId);
                    jsonObject.put("machineId", userOrder.getMachineId());
                    //RabbitMQAll.unLockSend(jsonObject);
                    rabbitMQSender.unLockSend(jsonObject);
                    map.put("returnState", 1);//退款成功
                    return map;
                }
                //退全部
            }
            if (userOrderStatus == 4) {
                //机器是否可以退款
                if (machineType) {
                    BigDecimal useAmount = useAmount(userOrderId);//消费金额
                    //是否使用企业付
                    if (companyPayAmount.doubleValue() > 0) {
                        if (companyPayAmount.subtract(useAmount).doubleValue() > 0) {
                            companyPayAmount = companyPayAmount.subtract(useAmount); //企业付金额大于消费金额
                            refundCompanyType = portionRefundEnterprise(userId, userOrderId, companyPayAmount);
                        } else {
                            //企业付不需要退款
                            companyPayAmount = BigDecimal.valueOf(0);//企业付金额小于消费金额
                            refundCompanyType = true;
                        }
                    } else {
                        //没有使用企业付
                        refundCompanyType = true;
                    }
                    //应退给用户的金额
                    BigDecimal refundAmount = receiveAmount.subtract(useAmount).subtract(companyPayAmount);
                    if (refund(payType, userId, refundAmount, userOrderId, userOrder.getDiscountId()) && refundCompanyType) {

                        //退款成功
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("orderId", userOrderId);
                        jsonObject.put("machineId", userOrder.getMachineId());
                        //RabbitMQAll.unLockSend(jsonObject);
                        rabbitMQSender.unLockSend(jsonObject);
                        map.put("returnState", 1);
                    }

                } else {
                    //机器无法退款请告知客服处理
                    map.put("returnState", 4);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return map;
                }
            }

        } catch (Exception e) {
            logger.info(e.toString());
            e.printStackTrace();
        }
        System.out.println(map);
        return map;
    }


    /**
     * 数据库时间
     *
     * @return
     */
    @Override
    public Date selectTime() {
        return userOrderDao.selectTime();
    }

    /**
     * 查看机器状态
     *
     * @param machineId
     * @return
     */
    @Override
    public int machineType(long machineId) {
        return userOrderDao.machineType(machineId);
    }

    /**
     * 支付宝，微信，银行卡退款
     *
     * @param userId
     * @param money
     * @param userOrderId
     * @return
     */
    private int tuikuan(String userId, String money, String userOrderId) {
        long tuikuanId = IDGenerator.generateById("tuikuanId", Long.parseLong(userId));
        BankPayBean bankPayBean = new BankPayBean();
        bankPayBean.setOid_partner("201711150001146004");
        bankPayBean.setSign_type("RSA");
        bankPayBean.setNo_refund(String.valueOf(tuikuanId));
        bankPayBean.setDt_refund(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        bankPayBean.setMoney_refund(money);
        bankPayBean.setNo_order(userOrderId);
        bankPayBean.setNotify_url(clientSetting.getAlipaycallback() + "/lianlian/lianlianPay");
        Sign sign = new Sign();
        System.out.println(sign.genSign(JSON.parseObject(JSON
                .toJSONString(bankPayBean))).toString());
        //签名
        bankPayBean.setSign(sign.genSign(JSON.parseObject(JSON.toJSONString(bankPayBean))));
        String reqJson = JSON.toJSONString(bankPayBean);
        System.out.println("退款请求报文为:" + reqJson);
        String resJson = YTHttpHandler.getInstance().doRequestPostString(reqJson, SERVER);
        System.out.println(reqJson + "   " + "  sadfasfasfa");
        //String resJson = HttpSender.send(reqJson);
        //BankCardPayBean res = JSON.parseObject(resJson, BankPayBean.class);
        System.out.println("退款结果报文为:" + resJson);
        return 1;
    }


    //取消订单ss
    @Transactional
    @Override
    public Map cancelOrder(long userOrderId, long userId, long couponId) throws IOException, TimeoutException {

        Map map = new HashMap();
        UserOrder userOrder = userOrderService.queryUserOrderStatus(userOrderId);
        int status = userOrder.getStatus();
        if (status == 2) {
            boolean ll = unLockProduct(userOrderId);
            if (ll) {
                System.out.println("取消订单");
                UserOrder userOrder1 = new UserOrder();
                userOrder1.setUserOrderId(userOrderId);
                userOrder1.setStatus(0);
                userOrderDao.cancelOrder(userOrder1);
                //退还优惠券
                userCouponService.updateCoupon(userId, couponId, 1);

                //退回企业付
                if (userOrder.getCompanyPayAmount().doubleValue() > 0) {
                    if (userOrderService.refundEnterprise(userId, userOrderId)) {
                        map.put("return", 0);
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("orderId", userOrderId);
                        jsonObject.put("machineId", userOrder.getMachineId());
                        //RabbitMQAll.unLockSend(jsonObject);
                        rabbitMQSender.unLockSend(jsonObject);
                    } else {
                        map.put("return", 1);
                    }
                }
                //退回家庭付
                if (userOrder.getFamilyPayAmount().doubleValue() > 0) {
                    if (userOrderService.refundFamily(userId, userOrderId)) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("orderId", userOrderId);
                        jsonObject.put("machineId", userOrder.getMachineId());
                        //RabbitMQAll.unLockSend(jsonObject);
                        rabbitMQSender.unLockSend(jsonObject);
                        map.put("return", 0);
                    } else {
                        map.put("return", 1);
                    }
                }
            } else {
                map.put("return", 1);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", userOrderId);
        jsonObject.put("machineId", userOrder.getMachineId());
        //RabbitMQAll.unLockSend(jsonObject);
        rabbitMQSender.unLockSend(jsonObject);
        return map;
    }


    /**
     * 订单价格检查
     *
     * @param userOrder          用户订单信息
     * @param productGlobalList  用户商品集合
     * @param couponDefinitionId 优惠券ID
     * @return boolean
     * @author Wang Zhiyuan
     */
    private boolean priceCheck(UserOrder userOrder, String productGlobalList, long couponDefinitionId) {
        //优惠券金额
        BigDecimal couponAmount = BigDecimal.valueOf(0);
        try {
            //根据优惠券ID查询优惠券金额
            couponAmount = userCouponDao.getCouponAmount(couponDefinitionId);
        } catch (Exception e) {
            //出错的话优惠券金额为0
            couponAmount = BigDecimal.valueOf(0);
        }
        //查不到的话优惠券金额为0
        if (couponAmount == null) {
            couponAmount = BigDecimal.valueOf(0);
        }
        //用户实付金额 ：订单金额-优惠券金额
        BigDecimal actualAmount = BigDecimal.valueOf(userOrder.getActualAmount());
        //机器id
        long machineId = userOrder.getMachineId();
        // 机器内商品价格
        List<Map> products = userOrderDao.getProduct(machineId);
        //用户购买的商品
        List<UserOrderLine> userOrderLines = JSON.parseArray(productGlobalList, UserOrderLine.class);
        // 总价
        BigDecimal totalPrice = BigDecimal.valueOf(0);

        HashMap<Long, BigDecimal> productPrices = new HashMap<>();
        for (Map map : products) {
            long productGlobalId = Long.parseLong(map.get("productGlobalId").toString());
            BigDecimal onlinePrice = BigDecimal.valueOf(Double.parseDouble(map.get("onlinePrice").toString()) / 100);
            productPrices.put(productGlobalId, onlinePrice);
        }
        for (UserOrderLine userOrderLine : userOrderLines) {
            long productGlobalId = userOrderLine.getProductGlobalId();
            BigDecimal quantity = BigDecimal.valueOf(userOrderLine.getQuantity());
            totalPrice = totalPrice.add(productPrices.get(productGlobalId).multiply(quantity));
        }

        //如果商品的价格小于优惠券的价格,那么商品实际付款价格为0
        double price = totalPrice.subtract(couponAmount).doubleValue() < 0 ? 0 : totalPrice.subtract(couponAmount).doubleValue();
        //价格验证成功返回 true 否则 false
        return actualAmount.doubleValue() == price;
    }


    private boolean lockProduct(String productGlobalList, long machineId) throws IOException, TimeoutException {
        int countBuyQuantity = 0;
        int success = 0;
        //锁定json错误
        List<UserOrderLine> productGlobalInfo = JSON.parseArray(productGlobalList, UserOrderLine.class);
        Collections.sort(productGlobalInfo, Comparator.comparing(UserOrderLine::getProductGlobalId));

        for (int t = 0; t < productGlobalInfo.size(); t++) {
            UserOrderLine userOrderLine = productGlobalInfo.get(t);
            int buyQuantity = userOrderLine.getQuantity();
            long productGlobalId = userOrderLine.getProductGlobalId();
            boolean ss = productGlobalId >= 28 && productGlobalId < 32;
            if (!ss) {
                countBuyQuantity = countBuyQuantity + buyQuantity;//4.25修改
            }
            int count = 0;
            try {
                count = productDao.lockProduct(productGlobalId, machineId, buyQuantity);
            } catch (Exception e) {
                count = 0;
            }
            if (count < 1) {
                break;
            }
            success = count + success;
        }
        int count = productDao.lockMachineProduct(machineId, countBuyQuantity);
        if (success == productGlobalInfo.size() && count > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 解锁订单
     *
     * @param userOrderId
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    private boolean unLockProduct(long userOrderId) throws IOException, TimeoutException {
        List<QueryProduct> queryProductList = productDao.queryProducts(userOrderId);
        int success = 0;
        int countBuyQuantity = 0;
        long machineId = 0;
        Collections.sort(queryProductList, Comparator.comparing(QueryProduct::getProductGlobalId));
        for (int i = 0; i < queryProductList.size(); i++) {
            QueryProduct queryProduct = queryProductList.get(i);
            int buyQuantity = queryProduct.getBuyQuantity();
            long productGlobalId = queryProduct.getProductGlobalId();
            machineId = queryProduct.getMachineId();
            int count = productDao.unLockProduct(machineId, productGlobalId, buyQuantity);
            if (count <= 0) {
                break;
            }
            boolean ss = productGlobalId >= 28 && productGlobalId < 32;
            if (!ss) {
                countBuyQuantity = countBuyQuantity + buyQuantity;
            }
            success += count;
        }
        int count = productDao.unLockMachineProduct(machineId, countBuyQuantity);
        if (success == queryProductList.size() && count > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void Times(int num, long userOrderId, long userId, long couponId) {
        Timer timer = new Timer();
        System.out.println("取消订单中");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                UserOrder userOrder = userOrderDao.queryUserOrderStatus(userOrderId);
                int status = userOrder.getStatus();
                if (status == 2) {
                    try {
                        cancelOrder(userOrderId, userId, couponId);
                    } catch (Exception e) {
                        logger.info(e.toString());
                        throw new RuntimeException();
                    }
                }
                timer.cancel();
                System.out.println("取消订单中结束了");
                timer.purge();
                System.out.println("取消订单中移除了已取消的任务");
            }
        }, num, 1000);
    }

    private void splitUserOrder(long userOrderId, long userId) {
        List<ProductTakeMeal> productTakeMeals = takeMealService.productLists(userOrderId);
        UserOrderProductLine userOrderProductLine = null;
        for (int i = 0; i < productTakeMeals.size(); i++) {
            if (productTakeMeals.get(i).getIsStaple() == 1) {
                for (int j = 0; j < productTakeMeals.get(i).getQuantity(); j++) {
                    userOrderProductLine = new UserOrderProductLine();
                    userOrderProductLine.setProductGlobalId(productTakeMeals.get(i).getProductGlobalId());
                    userOrderProductLine.setUserOrderId(userOrderId);
                    userOrderProductLine.setProductOffId(0);
                    long userOrderProductLineId = IDGenerator.generateById("userOrderProductLineId", userId);
                    userOrderProductLine.setUserOrderProductLineId(userOrderProductLineId);
                    takeMealService.addUserOrderProductLine(userOrderProductLine);
                }
            }
        }
    }

    private Map<String, Integer> couponPayment(long userId, long userOrderId) {
        Map<String, Integer> map = new HashMap();
        BigDecimal amount = BigDecimal.valueOf(0);
        long capitalLogId = IDGenerator.generateById("capitalLogId", userId);
        //插入主账户消费
        int count3 = userCapitalLogDao.insertUserCapital(capitalLogId, userId, amount, "userOrder", userOrderId, "优惠券付款");
        int count5 = userOrderDao.updateUserOrderType(userOrderId, 3, amount, amount, new Date(), 4);
        //拆分订单
        splitUserOrder(userOrderId, userId);
        if (count3 + count5 == 2) {
            map.put("submit", 1);
            //全款
            map.put("type", 1);
        }
        return map;
    }

    private Map results(int submit, int type, long userOrderId) {
        Map map = new HashMap();
        Date time = new Date();
        if (submit == 1) {
            if (type == 1) {
                map.put("type", 1);
                map.put("userOrderId", userOrderId);
                map.put("createTime", time);
                map.put("payment", 1);
                return map;
            }
            if (type == 2) {
                map.put("type", 1);
                map.put("userOrderId", userOrderId);
                map.put("createTime", time);
                map.put("payment", 2);
                return map;
            }
            //企业付余额不足
        }
        if (submit == 2) {
            map.put("type", 3);
            map.put("userOrderId", userOrderId);
            map.put("createTime", time);
            return map;
        } else {
            map.put("type", 4);
            map.put("userOrderId", userOrderId);
            map.put("createTime", time);
            map.put("payment", 0);
            return map;
        }
    }


    /**
     * 退款组装
     *
     * @param payType
     * @param userId
     * @param amount
     * @param userOrderId
     * @param discountId
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    private boolean refund(int payType, long userId, BigDecimal amount, long userOrderId, long discountId) throws IOException, TimeoutException {
        if (refundUserBalance(userId, amount, userOrderId, payType) && refundUserCoupon(discountId, userId) && unLockProduct(userOrderId)) {
            return true;
        }
        return false;
    }

    /**
     * 退还用户余额,插入资金流水，修改订单状态
     *
     * @param userId
     * @param amount
     * @return 1, Offline, 线下支付 2,Weixin,微信 3,Alipay,支付宝 4,Account,余额支付 5,银行卡
     */
    private boolean refundUserBalance(long userId, BigDecimal amount, long userOrderId, int payType) {
        int count = 0;
        //生成资金流水表id
        long userCapitalLogId = IDGenerator.generateById("userCapitalLogId", userId);

        if (payType == 4) {
            //余额付款
            count = userOrderDao.userRefund(userId, amount);
        }
        //插入资金流水
        int count1 = userCapitalLogDao.insertUserCapital(userCapitalLogId, userId, amount, "userOrder", userOrderId, "退款");
        if (payType == 2 || payType == 3 || payType == 5) {
            //支付宝，微信，银行卡付款
            count = tuikuan(String.valueOf(userId), amount.toString(), String.valueOf(userOrderId));
        }
        //System.out.println(userOrderDao.ssssss(userId));
        //修改订单状态
        int count2 = userOrderDao.cancelRefund(userOrderId, -1);
        if (count + count2 + count1 == 3) {
            return true;
        }
        return false;
    }

    /**
     * 返还优惠券
     *
     * @param discountId
     * @param userId
     * @return
     */
    private boolean refundUserCoupon(long discountId, long userId) {
        if (discountId == 0) {
            return true;
        }
        //返还优惠券状态
        int count = userCouponDao.updateCoupon(userId, discountId, 1);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 花了多少钱
     *
     * @param userOrderId
     * @return
     */
    private BigDecimal useAmount(long userOrderId) {
        BigDecimal useAmount = BigDecimal.valueOf(0);
        List<UserOrderProductLine> userOrderProductLine = userOrderDao.orderLineType(userOrderId);
        for (int i = 0; i < userOrderProductLine.size(); i++) {
            int orderLineStatus = userOrderProductLine.get(i).getStatus();
            if (orderLineStatus == 3 || orderLineStatus == 4) {
                useAmount = useAmount.add(userOrderProductLine.get(i).getPrice());
            }
        }
        return useAmount;
    }

    /**
     * 返回可以退款的集合
     *
     * @param userOrderId
     * @return
     */
    private List<UserOrderProductLine> refundOrderLine(long userOrderId) {
        List<UserOrderProductLine> userOrderProductLine = userOrderDao.refundOrderLine(userOrderId);
        //List<UserOrderProductLine> userOrderProductLine1=new ArrayList();
        for (int i = 0; i < userOrderProductLine.size(); i++) {
            int orderLineStatus = userOrderProductLine.get(i).getStatus();
            if (orderLineStatus == 3 && orderLineStatus == 4) {
                userOrderProductLine.remove(i);
            }
        }
        return userOrderProductLine;
    }
}