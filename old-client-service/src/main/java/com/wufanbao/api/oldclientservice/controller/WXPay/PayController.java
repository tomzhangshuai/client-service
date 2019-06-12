package com.wufanbao.api.oldclientservice.controller.WXPay;

import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.controller.PayTools;
import com.wufanbao.api.oldclientservice.dao.UserOrderDao;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.*;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "wxPay", method = RequestMethod.POST)
public class PayController {
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private UserCapitalLogService userCapitalLogService;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private UserRechargeService userRechargeService;
    @Autowired
    private UserRegisteredService userRegisteredService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private TakeMealService takeMealService;
    @Autowired
    private ClientSetting clientSetting;

    /**
     * 微信统一下单接口
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "wxPrePay", method = RequestMethod.POST)
    public Map<String, Object> wxPrePay(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //价格
        String price = request.getParameter("price");
        //把价格变成分
        int price100 = new BigDecimal(price).multiply(new BigDecimal(100)).intValue();
        if (price100 <= 0) {
            resultMap.put("msg", "付款金额错误");
            resultMap.put("code", "500");
            return resultMap;
        }
        //设置回调地址-获取当前的地址拼接回调地址
        String url = request.getRequestURL().toString();
        String domain = url.substring(0, url.length() - 13);
        String body = request.getParameter("body");
        //生产环境
        // String notify_url= domain;
        //测试环境

        String notify_url = clientSetting.getAlipaycallback() + "/wxPay/getNotify";
        String out_trade_no = String.valueOf(request.getParameter("userOrderId"));
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", ConfigUtil.APPID);//appid
        parameters.put("mch_id", ConfigUtil.MCH_ID);//商户号
        parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());//随机字符串
        parameters.put("body", body);//商品描述
        parameters.put("out_trade_no", out_trade_no); //订单id
        parameters.put("fee_type", "CNY");//货币类型人民币
        parameters.put("total_fee", String.valueOf(price100));//总金额
        parameters.put("spbill_create_ip", CommonUtil.toIpAddr(request));//终端ip
        //通知地址
        parameters.put("notify_url", notify_url);
        parameters.put("trade_type", "APP");
        //设置签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        //签名
        parameters.put("sign", sign);
        System.out.println(sign + "asdfaf");
        //封装请求参数结束
        String requestXML = PayCommonUtil.getRequestXml(parameters);
        System.out.println(requestXML);
        //调用统一下单接口
        String result = PayCommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
        System.out.println("\n" + result);
        try {
            /**统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，将数据传输给APP。参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay**/
            Map<String, String> map = XMLUtil.doXMLParse(result);
            System.out.println(map.size() + "map size");
            SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();
            parameterMap2.put("appid", ConfigUtil.APPID);
            parameterMap2.put("partnerid", ConfigUtil.MCH_ID);
            parameterMap2.put("prepayid", map.get("prepay_id"));
            parameterMap2.put("package", "Sign=WXPay");
            parameterMap2.put("noncestr", PayCommonUtil.CreateNoncestr());
            //本来生成的时间戳是13位，但是ios必须是10位，所以截取了一下
            parameterMap2.put("timestamp", Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10)));
            String sign2 = PayCommonUtil.createSign("UTF-8", parameterMap2);
            parameterMap2.put("sign", sign2);
            System.out.println(sign2);
            resultMap.put("code", "200");
            resultMap.put("msg", parameterMap2);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 微信充值接口
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "wxRechargePay", method = RequestMethod.POST)
    public Map<String, Object> wxRechargePay(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //价格
        String price = request.getParameter("price");
        //用户得到的便当币
        String amount = request.getParameter("amount");
        //用户id
        long userId = Long.parseLong(request.getParameter("userId"));
        //充值表id
        long rechargeId = IDGenerator.generateById("rechargeId", userId);
        redisUtils.set(String.valueOf(rechargeId), amount);
        System.out.println(rechargeId + "asdfasfasfasafsafafsaf");
        //把价格变成分
        int price100 = new BigDecimal(price).multiply(new BigDecimal(100)).intValue();
        if (price100 <= 0) {
            resultMap.put("msg", "付款金额错误");
            resultMap.put("code", "500");
            return resultMap;
        }
        //设置回调地址-获取当前的地址拼接回调地址
        String url = request.getRequestURL().toString();
        String domain = url.substring(0, url.length() - 13);
        String body = request.getParameter("body");
        //生产环境
        // String notify_url= domain;
        //测试环境
        String notify_url = clientSetting.getAlipaycallback() + "/wxPay/wxRechargePayNotify";
        String out_trade_no = String.valueOf(rechargeId);//充值记录商户订单编号
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", ConfigUtil.APPID);//appid
        parameters.put("mch_id", ConfigUtil.MCH_ID);//商户号
        parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());//随机字符串
        parameters.put("body", body);//商品描述
        parameters.put("out_trade_no", out_trade_no); //订单id
        parameters.put("fee_type", "CNY");//货币类型人民币
        parameters.put("total_fee", String.valueOf(price100));//总金额
        parameters.put("spbill_create_ip", CommonUtil.toIpAddr(request));//终端ip
        parameters.put("notify_url", notify_url);//通知地址
        parameters.put("trade_type", "APP");//交易类型 支付类型
        //设置签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);//签名
        System.out.println(sign + "asdfaf");
        //封装请求参数结束
        String requestXML = PayCommonUtil.getRequestXml(parameters);
        System.out.println(requestXML);
        //调用统一下单接口
        String result = PayCommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
        System.out.println("\n" + result);
        insertRecharge(userId, Double.parseDouble(price), rechargeId);//向用户充值表里添加数据
        System.out.println(price);
        try {
            /**统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，将数据传输给APP。参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay**/
            Map<String, String> map = XMLUtil.doXMLParse(result);
            System.out.println(map.size() + "map size");
            SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();
            parameterMap2.put("appid", ConfigUtil.APPID);
            parameterMap2.put("partnerid", ConfigUtil.MCH_ID);
            parameterMap2.put("prepayid", map.get("prepay_id"));
            parameterMap2.put("package", "Sign=WXPay");
            parameterMap2.put("noncestr", PayCommonUtil.CreateNoncestr());
            //本来生成的时间戳是13位，但是ios必须是10位，所以截取了一下
            parameterMap2.put("timestamp", Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10)));
            String sign2 = PayCommonUtil.createSign("UTF-8", parameterMap2);
            parameterMap2.put("sign", sign2);
            System.out.println(sign2);
            resultMap.put("code", "200");
            resultMap.put("msg", parameterMap2);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 用户订单支付回到
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "getNotify", method = RequestMethod.POST)
    public void getNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
        Map<Object, Object> map = XMLUtil.doXMLParse(result);
        if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
            //验证签名,在网上看到很多人都不去验证签名，这种做法，一般情况下不会有问题，但是。。。。。，多的我就不说了
            if (verifyWeixinNotify(map)) {
                //修改订单状态付款金额
                long userOrderId = Long.parseLong(map.get("out_trade_no").toString());//用户订单id
                double actualAmount = Double.parseDouble(map.get("cash_fee").toString());//用户订单金额
                long userId = getuserIdByOrderId(userOrderId);//根据用户订单编号获取用户id
                double balance = getBalance(userId);//用户余额
                OrderInfo OrderInfo = userOrderDao.getPayTime(userOrderId);
                System.out.println(OrderInfo.getPayTime() + "用户微信");
                if (OrderInfo.getPayTime() == null) {
                    PayTools payTools = new PayTools();
                    int payType = 2;
                    String SourceType = "用户订单";
                    UserCapitalLog userCapitalLog = payTools.userMoney(userId, -actualAmount / 100, balance, SourceType, userOrderId);
                    userCapitalLogService.addUserCapital(userCapitalLog.getUserCapitalLogId(), userCapitalLog.getUserId(), userCapitalLog.getAmount(), userCapitalLog.getSourceType(), userCapitalLog.getSourceId(), userCapitalLog.getDescription(), userCapitalLog.getBalance());
                    splitUserOrder(userOrderId, userId);
                    userOrderDao.updatePay(payTools.updateUserOrder(userOrderId, actualAmount / 100, payType));
                    //订单处理
                    response.getWriter().write(PayCommonUtil.setXML("SUCCESS", "OK")); // 告诉微信服务器，我收到信息了，不要在调用回调action了
                    System.out.println("付款成功回打微信成功+。。。。。。。。。。");
                } else {
                    //订单处理
                    response.getWriter().write(PayCommonUtil.setXML("SUCCESS", "OK")); // 告诉微信服务器，我收到信息了，不要在调用回调action了
                    System.out.println("付款成功回打微信成功+。。。。。。。。。。");
                }
            }

        }
    }

    /**
     * 拆分订单
     *
     * @param userOrderId
     * @param userId
     */
    public void splitUserOrder(long userOrderId, long userId) {
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

    /**
     * 充值回调
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "wxRechargePayNotify", method = RequestMethod.POST)
    public void getRechargePayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        System.out.println("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
        Map<Object, Object> map = XMLUtil.doXMLParse(result);
        if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
            //验证签名,在网上看到很多人都不去验证签名，这种做法，一般情况下不会有问题，但是。。。。。，多的我就不说了
            if (verifyWeixinNotify(map)) {
                long rechargeId = Long.parseLong(map.get("out_trade_no").toString());//用户订单id
                String tradeNo = map.get("transaction_id").toString();
                UserRecharge userRecharge = userRechargeService.queryUserRecharge(rechargeId);
                try {
                    if (userRecharge.getReceiveTime() == null) {//判断业务是否已经执行过哇
                        System.out.println(rechargeId + "insertCapitalLog问题");
                        long userId = userRecharge.getUserId();
                        //修改订单状态付款金额
                        double actualAmount = Double.parseDouble(map.get("cash_fee").toString());//用户订单金额
                        System.out.println(actualAmount + "订单金额");
                        //updateRecharge(userId,actualAmount/100,tradeNo,rechargeId);//跟新用户充值表状态
                        double Balance = getBalance(userId);//用户余额
                        double newBalance = Balance + actualAmount / 100;//用户当前余额
                        System.out.println(newBalance + "用户当期那");
                        PayTools payTools = new PayTools();
                        double kkk = Double.valueOf(redisUtils.get(String.valueOf(rechargeId)));
                        double give = kkk - actualAmount / 100;
                        String SourceType = "微信充值";
                        String SourceType1 = "赠送";
                        UserCapitalLog userCapitalLog = payTools.userMoney(userId, actualAmount / 100, newBalance, SourceType, userId);
                        userCapitalLogService.addUserCapital(userCapitalLog.getUserCapitalLogId(), userCapitalLog.getUserId(), userCapitalLog.getAmount(), userCapitalLog.getSourceType(), userCapitalLog.getSourceId(), userCapitalLog.getDescription(), userCapitalLog.getBalance());
                        UserCapitalLog userCapitalLog1 = payTools.userMoney(userId, give, newBalance + give, SourceType1, userId);
                        userCapitalLogService.addUserCapital(userCapitalLog1.getUserCapitalLogId(), userCapitalLog1.getUserId(), userCapitalLog1.getAmount(), userCapitalLog1.getSourceType(), userCapitalLog1.getSourceId(), userCapitalLog1.getDescription(), userCapitalLog1.getBalance());
                        UserRegistered userRegistered = payTools.updateBalance(userId, newBalance + give);
                        userRegisteredService.updateBalance(userRegistered.getUserId(), userRegistered.getBalance(), userRegistered.getUsableBalance());
                        userRechargeService.afterUserRecharge(payTools.userPay(tradeNo, actualAmount / 100, rechargeId, userId));
                        response.getWriter().write(PayCommonUtil.setXML("SUCCESS", "OK")); // 告诉微信服务器，我收到信息了，不要在调用回调action了
                        System.out.println("付款成功回打微信成功+。。。。。。。。。。");
                    } else {
                        //订单处理
                        response.getWriter().write(PayCommonUtil.setXML("SUCCESS", "OK")); // 告诉微信服务器，我收到信息了，不要在调用回调action了
                        System.out.println("付款成功回打微信成功+。。。。。。。。。。");
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
    }

    /**
     * 验证签名
     *
     * @param map
     * @return
     */
    public boolean verifyWeixinNotify(Map<Object, Object> map) {
        SortedMap<Object, Object> parameterMap = new TreeMap<Object, Object>();
        String sign = (String) map.get("sign");
        for (Object keyValue : map.keySet()) {
            if (!keyValue.toString().equals("sign")) {
                parameterMap.put(keyValue.toString(), map.get(keyValue));
            }

        }
        String createSign = PayCommonUtil.createSign("UTF-8", parameterMap);
        if (createSign.equals(sign)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 修改订单状态
     *
     * @param userOrderId
     * @param actualAmount
     */
    public void updateOrderType(long userOrderId, double actualAmount) {
        UserOrder userOrder = new UserOrder();
        userOrder.setUserOrderId(userOrderId);
        userOrder.setActualAmount(actualAmount);
        userOrder.setReceiveAmount(actualAmount);
        userOrder.setPayType(2);
        userOrder.setStatus(3);
        Timestamp invalidTime = new Timestamp(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(invalidTime);
        calendar.add(Calendar.HOUR_OF_DAY, 36);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ts = simpleDateFormat.format(calendar.getTime());
        invalidTime = Timestamp.valueOf(ts);
        System.out.println(">>>>>失效时间" + invalidTime);
        userOrder.setInvalidTime(invalidTime);
        userOrderDao.updatePay(userOrder);
    }

    /**
     * 像资金流水表中添加数据
     *
     * @param userId
     * @param amount
     * @param userOrderId
     */
    public void insertCapitalLog(long userId, double amount, long userOrderId, double newBalance, String SourceType) {
        UserCapitalLog userCapitalLog = new UserCapitalLog();
        long userCapitalLogId = IDGenerator.generateById("userCapitalLogId", userId);
        userCapitalLog.setUserCapitalLogId(userCapitalLogId);
        userCapitalLog.setUserId(userId);
        System.out.println(amount + "有问题的钱");
        userCapitalLog.setAmount(amount);
        userCapitalLog.setSourceType(SourceType);
        userCapitalLog.setSourceId(userOrderId);
        userCapitalLog.setDescription(" ");
        userCapitalLog.setBalance(newBalance);
        userCapitalLogService.addUserCapital(userCapitalLogId, userId, amount, SourceType, userOrderId, " ", newBalance);
    }

    /**
     * 根据用户订单id获取用户id
     *
     * @param userOrderId
     * @return
     */
    public long getuserIdByOrderId(long userOrderId) {
        List<Alipay> alipayList = alipayService.queryAlipay(userOrderId);
        long userId = 0;
        for (int i = 0; i < alipayList.size(); i++) {
            Alipay alipay = alipayList.get(i);
            userId = alipay.getUserId();
        }
        return userId;
    }

    /**
     * 插入用户充值表
     *
     * @param userId
     * @param amount
     */
    public void insertRecharge(long userId, double amount, long rechargeId) {
        UserRecharge userRecharge = new UserRecharge();
        userRecharge.setRechargeId(rechargeId);
        userRecharge.setUserId(userId);
        userRecharge.setAmount(amount);
        System.out.println(amount + " sadfdsafasdf金额");
        String out_trade_no = String.valueOf(rechargeId);
        userRecharge.setBcTradeNo(out_trade_no);
        userRecharge.setPayType(2);
        userRecharge.setPayStatus(1);
        userRecharge.setTradeNo("");
        userRecharge.setActualAmount(0);
        userRechargeService.beforeUserRecharge(userRecharge);
    }

    /**
     * 更新用户充值记录
     *
     * @param userId
     * @param price
     */
    public void updateRecharge(long userId, double price, String TradeNo, long rechargeId) {
        UserRecharge userRecharge1 = new UserRecharge();
        userRecharge1.setTradeNo(TradeNo);
        userRecharge1.setPayStatus(2);
        userRecharge1.setActualAmount(price);
        userRecharge1.setRechargeId(rechargeId);
        userRecharge1.setUserId(userId);
        userRechargeService.afterUserRecharge(userRecharge1);
    }

    /**
     * 更新用户余额
     *
     * @param userId
     * @param newBalance
     */
    public void updateBalance(long userId, double newBalance) {
        UserRegistered userRegistered1 = new UserRegistered();
        userRegistered1.setUserId(userId);
        userRegistered1.setBalance(newBalance);
        userRegistered1.setUsableBalance(newBalance);
        userRegisteredService.updateBalance(userId, newBalance, newBalance);
    }

    /**
     * 获取用户余额
     */
    public double getBalance(long userId) {
        UserRegistered userRegistered = userRegisteredService.querybalance(userId);
        double usableBalance = userRegistered.getUsableBalance();
        return usableBalance;
    }

    @ResponseBody
    @RequestMapping(value = "wxOpenPay", method = RequestMethod.POST)
    public Map<String, Object> wxOpenPay(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //价格
        String price = request.getParameter("price");
        //把价格变成分
        int price100 = new BigDecimal(price).multiply(new BigDecimal(100)).intValue();
        if (price100 <= 0) {
            resultMap.put("msg", "付款金额错误");
            resultMap.put("code", "500");
            return resultMap;
        }
        String openid = request.getParameter("openId");
        String tradeNo = String.valueOf(request.getParameter("userOrderId"));
        String body = request.getParameter("body");
        String notifyUrl = clientSetting.getAlipaycallback() + "/wxPay/getNotify";


        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        //appid
        parameters.put("appid", "wxeb95941a5c320350");
        //商户号
        parameters.put("mch_id", "1490321412");
        //随机字符串
        parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
        //商品描述
        parameters.put("body", body);
        //订单id
        parameters.put("out_trade_no", tradeNo);
        //货币类型人民币
        parameters.put("fee_type", "CNY");
        //总金额
        parameters.put("total_fee", String.valueOf(price100));
        //终端ip
        parameters.put("spbill_create_ip", CommonUtil.toIpAddr(request));
        //openId
        parameters.put("openid", openid);
        //通知地址
        parameters.put("notify_url", notifyUrl);
        parameters.put("trade_type", "JSAPI");
        //设置签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        //签名
        parameters.put("sign", sign);
        System.out.println(sign + "asdfaf");
        //封装请求参数结束
        String requestXML = PayCommonUtil.getRequestXml(parameters);
        System.out.println(requestXML);
        //调用统一下单接口
        String result = PayCommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
        System.out.println("\n" + result);
        try {
            //统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，将数据传输给APP。
            //参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay
            Map<String, String> map = XMLUtil.doXMLParse(result);
            System.out.println(map.size() + "map size");
            SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();
            parameterMap2.put("appId", "wxeb95941a5c320350");
            //本来生成的时间戳是13位，但是ios必须是10位，所以截取了一下
            parameterMap2.put("timeStamp", String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap2.put("nonceStr", PayCommonUtil.CreateNoncestr());
            parameterMap2.put("package", "prepay_id=" + map.get("prepay_id"));
            parameterMap2.put("signType", "MD5");
            String sign2 = PayCommonUtil.createSign("UTF-8", parameterMap2);
            parameterMap2.put("sign", sign2);
            System.out.println(sign2 + "sing2");
            resultMap.put("code", "200");
            resultMap.put("msg", parameterMap2);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
        long currentTime = System.currentTimeMillis();
        currentTime +=3*60*1000;
        Date date=new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            RequestData wxJsrequestData = WXApi.jspay("","", tradeNo, Double.parseDouble(price), 0, notifyUrl, "", "", "", body, dateFormat.format(date), "","");
            WxJSPayHandle handle = new WxJSPayHandle();
            Helper.request(wxJsrequestData, handle);
            ResWxJSPay.Data wxJsData = handle.getData();
            ResWxJSPay.PayInfo payInfo = wxJsData.getPayInfo();

            SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();
            parameterMap2.put("appId", payInfo.getAppId());
            //本来生成的时间戳是13位，但是ios必须是10位，所以截取了一下
            parameterMap2.put("timeStamp", payInfo.getTimeStamp());
            parameterMap2.put("nonceStr", payInfo.getNonceStr());
            parameterMap2.put("package", payInfo.getPackageX());
            parameterMap2.put("signType", payInfo.getSignType());
            parameterMap2.put("sign", wxJsData.getResponseBody().getSign());
            resultMap.put("code", "200");
            resultMap.put("msg", parameterMap2);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return resultMap;
    }
}
