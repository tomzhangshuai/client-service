package com.wufanbao.api.oldclientservice.controller.LianLianPay.Pay;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Bean.PayDataBean;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Bean.RetBean;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Util.PartnerConfig;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Util.YinTongUtil;
import com.wufanbao.api.oldclientservice.controller.PayTools;
import com.wufanbao.api.oldclientservice.controller.UserOrderController;
import com.wufanbao.api.oldclientservice.dao.FamilyPayDao;
import com.wufanbao.api.oldclientservice.dao.UserOrderDao;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("lianlian")
public class LianLianPayController {
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private UserCapitalLogService userCapitalLogService;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private UserRegisteredService userRegisteredService;
    @Autowired
    private UserRechargeService userRechargeService;
    @Autowired
    private TakeMealService takeMealService;
    @Autowired
    private FamilyPayDao familyPayDao;
    @Autowired
    private RedisUtils redisUtils;

    private static final Logger logger = LoggerFactory.getLogger(LianLianPayController.class);


    /**
     * 聚合商品交易回调
     *
     * @param req
     * @throws IOException
     */
    @RequestMapping(value = "orderPay", method = RequestMethod.POST)
    @ResponseBody
    public Object orderPay(HttpServletRequest req) throws IOException {
        System.out.println("进入支付异步通知数据接收处理");
        Map map = new HashMap();
        String reqStr = YinTongUtil.readReqStr(req);
        if (YinTongUtil.isnull(reqStr)) {
            map.put("ret_code", "9999");
            map.put("ret_msg", "交易失败");
            return map;
        }
        System.out.println("接收支付异步通知数据：【" + reqStr + "】");
        try {
            //签名验证
            if (!YinTongUtil.checkSign(reqStr, PartnerConfig.YT_PUB_KEY,
                    PartnerConfig.MD5_KEY)) {
                map.put("ret_code", "9999");
                map.put("ret_msg", "交易失败");
                System.out.println("支付异步通知验签失败");
                return map;

            }
        } catch (Exception e) {
            System.out.println("异步通知报文解析异常：" + e);
            map.put("ret_code", "9999");
            map.put("ret_msg", "交易失败");
            return map;
        }
        //解析异步通知对象
        PayDataBean payDataBean = JSON.parseObject(reqStr, PayDataBean.class);
        long userOrderId = Long.parseLong(payDataBean.getNo_order());
        double total_fee = Double.parseDouble(payDataBean.getMoney_order());
        String context = null;
        int payType = 0;
        long userId = 0;
        List<Alipay> alipayList = alipayService.queryAlipay(userOrderId);
        for (int i = 0; i < alipayList.size(); i++) {
            Alipay alipay = alipayList.get(i);
            userId = alipay.getUserId();
        }
        UserCapitalLog userCapitalLog = new UserCapitalLog();
        try {
            userCapitalLog = familyPayDao.getUserMaster(userId, userOrderId, "userOrder");
        } catch (Exception e) {
            userCapitalLog = null;
        }
        if (userCapitalLog != null) {
            System.out.println("业务已经处理过");
            map.put("ret_code", "0000");
            map.put("ret_msg", "交易成功");
            return map;
        }
        /**
         * 1,Offline,线下支付
         * 2,Weixin,微信
         * 3,Alipay,支付宝
         * 4,Account,余额支付
         * 5,Bank,银行卡支付
         */
        if (payDataBean.getPay_type().equals("2")) {
            context = "快捷支付";
            payType = 5;
            String acct_name = payDataBean.getAcct_name();
            String card_no = payDataBean.getCard_no();
            String id_no = payDataBean.getId_no();
            addBank(userId, acct_name, card_no, id_no);
        }
        if (payDataBean.getPay_type().equals("13")) {
            context = "支付宝支付";
            payType = 3;
        }
        if (payDataBean.getPay_type().equals("Y") || payDataBean.getPay_type().equals("W")) {
            context = "微信支付";
            payType = 2;
        }
        int iii = paymentCallback(userId, userOrderId, total_fee, context, payType);
        // UserOrderController userOrderController = new UserOrderController();
        try {
            splitUserOrder(userOrderId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("fdasfdasfjdskaf" + iii);
        if (iii == 1) {
            map.put("ret_code", "0000");
            map.put("ret_msg", "交易成功");
            System.out.println("支付异步通知数据接收处理成功");
            return map;
        }
        map.put("ret_code", "9999");
        map.put("ret_msg", "交易失败");
        return map;
    }

    public void orderTime(long userOrderId, double total_fee, String pay_type) {
        String context = null;
        int payType = 0;
        long userId = 0;
        List<Alipay> alipayList = new ArrayList<>();
        try {
            alipayList = alipayService.queryAlipay(userOrderId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < alipayList.size(); i++) {
            Alipay alipay = alipayList.get(i);
            userId = alipay.getUserId();
        }
        UserCapitalLog userCapitalLog = new UserCapitalLog();
        try {
            userCapitalLog = familyPayDao.getUserMaster(userId, userOrderId, "userOrder");
        } catch (Exception e) {
            userCapitalLog = null;
        }
        if (userCapitalLog != null) {
            System.out.println("业务已经处理过");
        }
        /**
         * 1,Offline,线下支付
         * 2,Weixin,微信
         * 3,Alipay,支付宝
         * 4,Account,余额支付
         * 5,Bank,银行卡支付
         */
        if (pay_type.equals("2")) {
            context = "快捷支付";
            payType = 5;
        }
        if (pay_type.equals("13")) {
            context = "支付宝支付";
            payType = 3;
        }
        if (pay_type.equals("Y") || pay_type.equals("W")) {
            context = "微信支付";
            payType = 2;
        }
        int iii = paymentCallback(userId, userOrderId, total_fee, context, payType);
        try {
            splitUserOrder(userOrderId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (iii == 1) {
            System.out.println("支付异步通知数据接收处理成功");
        }
    }


    /**
     * 聚合支付订单交易
     *
     * @param userOrderId
     * @param total_fee
     * @param context
     * @param payType
     * @return
     */
    public int paymentCallback(long userId, long userOrderId, double total_fee, String context, int payType) {
        try {
            redisUtils.set(String.valueOf(userId) + "paytype", String.valueOf(payType));
            System.out.println("用户Id" + userId);
            PayTools payTools = new PayTools();
            //用户余额
            double balance = getBalance(userId);
            UserCapitalLog userCapitalLog = payTools.userMoney(userId, -total_fee, balance, context, userOrderId);
            userCapitalLogService.addUserCapital(userCapitalLog.getUserCapitalLogId(), userCapitalLog.getUserId(), userCapitalLog.getAmount(), userCapitalLog.getSourceType(), userCapitalLog.getSourceId(), userCapitalLog.getDescription(), userCapitalLog.getBalance());
            userOrderDao.updatePay(payTools.updateUserOrder(userOrderId, total_fee, payType));
            return 1;
        } catch (RuntimeException e) {
            logger.info(e.toString());
            return 0;
        }
    }

    /**
     * 获取用户余额
     */
    public double getBalance(long userId) {
        UserRegistered userRegistered = userRegisteredService.querybalance(userId);
        double usableBalance = userRegistered.getUsableBalance();
        return usableBalance;
    }

    /**
     * 聚合支付充值回调
     *
     * @param req
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "rechargePay", method = RequestMethod.POST)
    @ResponseBody
    public Object rechargePay(HttpServletRequest req) throws IOException {
        System.out.println("进入支付异步通知数据接收处理");
        Map map = new HashMap();
        String reqStr = YinTongUtil.readReqStr(req);
        if (YinTongUtil.isnull(reqStr)) {
            map.put("ret_code", "9999");
            map.put("ret_msg", "交易失败");
            return map;
        }
        System.out.println("接收支付异步通知数据：【" + reqStr + "】");
        try {
            if (!YinTongUtil.checkSign(reqStr, PartnerConfig.YT_PUB_KEY,
                    PartnerConfig.MD5_KEY)) {
                map.put("ret_code", "9999");
                map.put("ret_msg", "交易失败");
                System.out.println("支付异步通知验签失败");
                return map;
            }
        } catch (Exception e) {
            System.out.println("异步通知报文解析异常：" + e);
            map.put("ret_code", "9999");
            map.put("ret_msg", "交易失败");
            return map;
        }

        //解析异步通知对象
        PayDataBean payDataBean = JSON.parseObject(reqStr, PayDataBean.class);
        String rechargeId = payDataBean.getNo_order();
        String total_fee = payDataBean.getMoney_order();
        String trade_no = payDataBean.getOid_paybill();
        String context = "";
        String context1 = "";
        UserRecharge userRecharge = userRechargeService.queryUserRecharge(Long.parseLong(rechargeId));
        long userId = userRecharge.getUserId();
        if (payDataBean.getPay_type().equals("2")) {
            context = "快捷充值";
            context1 = "银行卡充值赠送";
            String acct_name = payDataBean.getAcct_name();
            String card_no = payDataBean.getCard_no();
            String id_no = payDataBean.getId_no();
            addBank(userId, acct_name, card_no, id_no);
        }
        if (payDataBean.getPay_type().equals("13")) {
            context = "支付宝充值";
            context1 = "支付宝充值赠送";
        }
        if (payDataBean.getPay_type().equals("Y") || payDataBean.getPay_type().equals("W")) {
            context = "微信充值";
            context1 = "微信充值赠送";
        }
        UserCapitalLog userCapitalLog = new UserCapitalLog();
        try {
            userCapitalLog = familyPayDao.getUserMaster(userId, Long.parseLong(rechargeId), "userOrder");
        } catch (Exception e) {
            userCapitalLog = null;
        }
        if (userCapitalLog != null) {
            System.out.println("业务已经处理过");
            map.put("ret_code", "0000");
            map.put("ret_msg", "交易成功");
            return map;
        }
        int iii = rechargeCallback(userId, rechargeId, total_fee, trade_no, context, context1);
        if (iii == 1) {
            map.put("ret_code", "0000");
            map.put("ret_msg", "交易成功");
            System.out.println("支付异步通知数据接收处理成功");
            return map;
        }
        map.put("ret_code", "9999");
        map.put("ret_msg", "交易失败");
        return map;
    }

    public void rechargeTime(String rechargeId, String total_fee, String trade_no, String pay_type) {
        //解析异步通知对象
//        PayDataBean payDataBean = JSON.parseObject(reqStr, PayDataBean.class);
//        String rechargeId = payDataBean.getNo_order(); //商户订单号
//        String total_fee = payDataBean.getMoney_order();//钱
//        String trade_no = payDataBean.getOid_paybill();//连连订单号
        String context = "";
        String context1 = "";
        UserRecharge userRecharge = userRechargeService.queryUserRecharge(Long.parseLong(rechargeId));
        long userId = userRecharge.getUserId();
        if (pay_type.equals("2")) {
            context = "快捷充值";
            context1 = "银行卡充值赠送";
        }
        if (pay_type.equals("13")) {
            context = "支付宝充值";
            context1 = "支付宝充值赠送";
        }
        if (pay_type.equals("Y") || pay_type.equals("W")) {
            context = "微信充值";
            context1 = "微信充值赠送";
        }
        UserCapitalLog userCapitalLog = new UserCapitalLog();
        try {
            userCapitalLog = familyPayDao.getUserMaster(userId, Long.parseLong(rechargeId), "userOrder");
        } catch (Exception e) {
            userCapitalLog = null;
        }
        if (userCapitalLog != null) {
            System.out.println("业务已经处理过");
        }
        int iii = rechargeCallback(userId, rechargeId, total_fee, trade_no, context, context1);
        if (iii == 1) {
            System.out.println("支付异步通知数据接收处理成功");
        }
    }


    public int rechargeCallback(long userId, String rechargeId, String total_fee, String trade_no, String context, String context1) {
        try {
            //修改用户余额
            UserRegistered userRegistered = userRegisteredService.querybalance(userId);
            double usableBalance = userRegistered.getUsableBalance();
            double newBalance = usableBalance + Double.parseDouble(total_fee);
            double kkk = Double.valueOf(redisUtils.get(String.valueOf(rechargeId)));
            double give = kkk - Double.parseDouble(total_fee);
            PayTools payTools = new PayTools();
            UserCapitalLog userCapitalLog = payTools.userMoney(userId, Double.parseDouble(total_fee), newBalance, context, Long.parseLong(rechargeId));
            userCapitalLogService.addUserCapital(userCapitalLog.getUserCapitalLogId(), userCapitalLog.getUserId(), userCapitalLog.getAmount(), userCapitalLog.getSourceType(), userCapitalLog.getSourceId(), userCapitalLog.getDescription(), userCapitalLog.getBalance());
            UserRegistered userRegistered1 = payTools.updateBalance(userId, newBalance + give);
            userRegisteredService.updateBalance(userRegistered1.getUserId(), userRegistered1.getBalance(), userRegistered1.getUsableBalance());
            return 1;
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @Autowired
    private UserBankService userBankService;
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserPortraitService userPortraitService;

    public void addBank(long userId, String accName/*开户姓名*/, String account/*银行卡*/, String certificateNo/*身份证号*/) {
        UserBank userBank1 = userBankService.checkAccount(account);
        if (userBank1 == null) {
            long userBankId = IDGenerator.generateById("UserBankId", userId);
            UserBank userBank = new UserBank();
            userBank.setUserBankId(userBankId);
            userBank.setUserId(userId);
            userBank.setBankName("");
            userBank.setFullName(accName);
            System.out.println(userBank.getBankName());
            userBank.setAccount(account);
            userBankService.insertUserBank(userBank);
            UserAuth userAuth = userAuthService.check(certificateNo);
            if (userAuth == null) {
                UserAuth userAuth1 = new UserAuth();
                userAuth1.setUserId(userId);
                userAuth1.setCardType(1);
                userAuth1.setCardNo(certificateNo);
                userAuth1.setCardImages("");
                System.out.println(userAuth1.getUserId());
                userAuthService.insertUserAuth(userAuth1);
                System.out.println(userId);
                UserRegistered userRegistered = new UserRegistered();
                userRegistered.setIsAuth(0);
                userRegistered.setUserId(userId);
                userPortraitService.updateisAuth(userRegistered);
            }
        }
    }

    @RequestMapping(value = "requestTest")
    protected void dopost() throws ServletException, IOException {
        System.out.println("连连退款回调");
        return;
    }


    @RequestMapping(value = "lianlianPay", method = RequestMethod.POST)
    @ResponseBody
    public void sss(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        System.out.println("进入支付异步通知数据接收处理");
        RetBean retBean = new RetBean();
        String reqStr = YinTongUtil.readReqStr(req);
        if (YinTongUtil.isnull(reqStr)) {
            retBean.setRet_code("9999");
            retBean.setRet_msg("交易失败");
            resp.getWriter().write(JSON.toJSONString(retBean));
            resp.getWriter().flush();
            return;
        }
        System.out.println("接收支付异步通知数据：【" + reqStr + "】");
        try {
            //签名验证
            if (!YinTongUtil.checkSign(reqStr, PartnerConfig.YT_PUB_KEY,
                    PartnerConfig.MD5_KEY)) {
                retBean.setRet_code("9999");
                retBean.setRet_msg("交易失败");
                resp.getWriter().write(JSON.toJSONString(retBean));
                resp.getWriter().flush();
                System.out.println("支付异步通知验签失败");
                return;
            }
        } catch (Exception e) {
            System.out.println("异步通知报文解析异常：" + e);
            retBean.setRet_code("9999");
            retBean.setRet_msg("交易失败");
            resp.getWriter().write(JSON.toJSONString(retBean));
            resp.getWriter().flush();
            return;
        }
        retBean.setRet_code("0000");
        retBean.setRet_msg("交易成功");

        resp.getWriter().flush();
        System.out.println("支付异步通知数据接收处理成功" + new Date());
    }


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


}
