package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.BaseController;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.controller.RabbitMQ.RabbitMQSender;
import com.wufanbao.api.oldclientservice.dao.*;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.*;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeoutException;

/**
 * Date:2017-08-09
 * Time:14:37
 */
@Controller
@RequestMapping("userorder")
public class UserOrderController extends BaseController {
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserCapitalLogService userCapitalLogService;
    @Autowired
    private UserRegisteredService userRegisteredService;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private ProductSrevice productSrevice;
    @Autowired
    private UserOrderLineDao userOrderLineDao;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private TakeMealService takeMealService;
    @Autowired
    private SignInDao signInDao;
    @Autowired
    private UpdateUserInfoDao updateUserInfoDao;
    @Autowired
    private FamilyPayService familyPayService;
    @Resource
    private PlatformTransactionManager transactionManager;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private ClientSetting clientSetting;

    private final static String SERVER = "http://121.196.201.74:81/LianLian/WxRefund";
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserOrderController.class);


    /**
     * 用户浏览发现奖励积分
     *
     * @param userId
     * @param appDiscoverId
     * @return
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping("getUserFindAward")
    public Object getUserFindAward(long userId, long appDiscoverId) {
        ResponseInfo responseInfo = userOrderService.getUserFindAward(userId, appDiscoverId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 确认订单
     *
     * @return
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "insUserOrder", method = RequestMethod.POST)
    public Object insUserOrder(UserOrder userOrder, String productGlobalList, long couponDefinitionId, String appointTime) throws Exception {
        return userOrderService.insertUserOrder(userOrder, productGlobalList, couponDefinitionId, appointTime);
    }

    /**
     * 取消订单
     *
     * @edito zhaojing
     */
    @LoginRequired
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object cancelOrder(long userOrderId, long userId, long couponId) throws IOException, TimeoutException {
        return userOrderService.cancelOrder(userOrderId, userId, couponId);
    }

    /**
     * 加料和商品详情接口
     *
     * @param request
     * @return
     * @eidtor zhaojing 暂时没使用
     */
    @LoginRequired
    @PostMapping("queryProduct")
    @ResponseBody
    public Object queryProduct(HttpServletRequest request) {
        long machineId = Long.valueOf(request.getParameter("machineId"));
        long productGlobalId = Long.valueOf(request.getParameter("productGlobalId"));
        Map map = new HashMap();
        if (productGlobalId == 0) {
            List<ProductDetails> productDetailsList = userOrderService.queryProduct(machineId);
            for (int i = 0; i < productDetailsList.size(); i++) {
                ProductDetails productDetails = productDetailsList.get(i);
                if (productDetailsList.size() != 0) {
                    map.put("data", productDetailsList);
                    map.put("status", 1);
                } else {
                    map.put("data", productDetails);
                    map.put("status", 0);
                }
            }
        }
        if (productGlobalId != 0) {
            List<ProductDetails> productDetailsList = userOrderService.queryDetails(machineId, productGlobalId);
            for (int i = 0; i < productDetailsList.size(); i++) {
                productDetailsList.get(i).setAttachImageUrls(attachImageUrls(productDetailsList.get(i).getAttachImageUrls()));
            }
            if (productDetailsList.size() != 0) {
                map.put("data", productDetailsList);
                map.put("status", 1);
            } else {
                map.put("data", productDetailsList);
                map.put("status", 0);
            }
        }
        return map;
    }

    /**
     * 支付成功
     *
     * @param request
     * @return
     * @editro zhaojing
     */
    @LoginRequired
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    @ResponseBody
    public Object pay(HttpServletRequest request) {
        long userOrderId = Long.parseLong(request.getParameter("userOrderId"));//订单编号
        int payType = Integer.parseInt(request.getParameter("payType"));//2，微信。3支付宝。4，便当币。5，银行卡
        int status = Integer.parseInt(request.getParameter("status"));//给我3是已支付
        long userId = Long.parseLong(request.getParameter("userId"));//userId
        double amount = Double.valueOf(request.getParameter("amount"));//金额
        String paypassword = request.getParameter("paypassword");//支付密码
        return userOrderService.orderPay(userOrderId, payType, userId, amount, paypassword);
    }

    /**
     * 支付
     *
     * @editor zhaojing 获取下单时间
     */
    @LoginRequired
    @RequestMapping(value = "lockingFood", method = RequestMethod.POST)
    @ResponseBody
    public Object LockingFood(HttpServletRequest request) {
        Map map = new HashMap();
        map.put("return", new Date());
        return map;
    }

    /**
     * 取餐
     *
     * @param userId
     * @param userOrderId
     * @return
     * @throws Exception
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping("takeFood")
    public Object takeFood(long userId, long userOrderId) throws Exception {
        ResponseInfo responseInfo = userOrderService.takeFood(userId, userOrderId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 退款
     *
     * @param userId
     * @param userOrderId
     * @return
     * @throws Exception
     * @edtor zhaojing
     */
    @ResponseBody
    @RequestMapping("refundUser")
    public Map refundUser(long userId, long userOrderId) throws Exception {
        return userOrderService.refundUser(userId, userOrderId);
    }

    /**
     * 亲密付历史账单
     *
     * @param userId
     * @param sonUserId
     * @return
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping("familyPayOrderByUserId")
    public Object familyPayOrderByUserId(long userId, long sonUserId) {
        ResponseInfo responseInfo = userOrderService.familyPayOrderByUserId(userId, sonUserId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 用户评价
     *
     * @param userOrder
     * @param userOrderLineList
     * @return
     * @editor zhaojing
     */
//    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "userEvaluate", method = RequestMethod.POST)
    public Map userEvaluate(UserOrder userOrder, String userOrderLineList) {
        return userOrderService.userEvaluate(userOrder, userOrderLineList);
    }

    /**
     * 根据机器编号找机位图
     *
     * @param machineId
     * @return
     * @editor zhaojing 机位图
     */
    @RequestMapping(value = "getMachineLocation", method = RequestMethod.POST)
    @ResponseBody
    public Object getMachineLocation(long machineId) {
        Map map = userOrderService.getMachineLocation(machineId);
        return map;
    }

    /**
     * 随机展示一张图片
     *
     * @return
     * @editor zhaojing 取餐分享
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "randShowImage", method = RequestMethod.POST)
    public Map randShowImage(long userId, long userOrderId) {
        return userOrderService.randShowImage(userId, userOrderId);
    }

    /**
     * 等待取餐
     *
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @PostMapping("waitTakeMeal")
    public Object waitTakeMeal(HttpServletRequest request) {
        String machineId = request.getParameter("machineId");
        String userOrderId = request.getParameter("userOrderId");
        Map map = new HashMap();
        Map map1 = new HashMap();
        List<ProductTakeMeal> productTakeMeals = takeMealService.productLists(Long.parseLong(userOrderId));
        for (ProductTakeMeal product : productTakeMeals) {
            String image = product.getImageUrl();
            String[] arr = image.split(";");
            product.setImageUrl(clientSetting.getResource() + arr[0]);
        }
        UserOrder userOrder = userOrderService.queryUserOrderStatus(Long.parseLong(userOrderId));
        int machineType = userOrderService.machineType(Long.valueOf(machineId));
        map1.put("machineType", machineType);
        int status = userOrder.getStatus();
        map.put("takeNo", userOrder.getTakeNo());
        map.put("orderStatus", status);
        map.put("userOrderData", productTakeMeals);
        map.put("userOrderId", userOrderId);//订单号
        List<UserOrderProductLine> userOrderProductLineList = new ArrayList();
        BigDecimal countPrice = BigDecimal.valueOf(0);
        if (machineType == 1) {
            userOrderProductLineList = userOrderDao.refundOrderLine(Long.parseLong(userOrderId));
            map1.put("userOrderProductLineList", userOrderProductLineList);
            for (int i = 0; i < userOrderProductLineList.size(); i++) {
                int quantity = userOrderProductLineList.get(i).getQuantity();
                BigDecimal price = userOrderProductLineList.get(i).getPrice();
                countPrice = countPrice.add(price.multiply(BigDecimal.valueOf(quantity)));
            }
            map1.put("countPrice", countPrice);
        } else {
            map1.put("userOrderProductLineList", userOrderProductLineList);
            map1.put("countPrice", countPrice);
        }

        try {
            List<String> data = redisUtils.lrange("OrderRanking_" + machineId, 0, 400);
            for (int j = 0; j < data.size(); j++) {
                String msg = data.get(j).toString();
                String userorderid = msg.substring(0, msg.lastIndexOf(","));
                if (userOrderId.equals(userorderid)) {
                    map.put("userOrderPosition", j);//排队
                }
                String num = msg.substring(msg.indexOf(",") + 1);
                int shijian = (Integer.parseInt(num) - 1) / 3 * 180;
                map.put("Time", shijian / 60);
            }
            List<ProductOff> productOffs = takeMealService.productStatus(Long.parseLong(userOrderId));
            if (productOffs.size() != 0) {
                map.put("userOrderStatus", productOffs);//商品状态
            } else {
                map.put("userOrderStatus", productOffs);
            }
            map1.put("data", map);
            map1.put("status", 1);
            return map1;
        } catch (Exception e) {
            System.out.println(e.toString());
            map1.put("status", 0);
            return map1;
        }
    }

    /**
     * 用户订单评价
     *
     * @param userOrderId 用户订单ID
     * @return java.lang.Object
     * @date 2018/6/22
     * @editor zhaojing 评价详情
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "orderEvaluation", method = RequestMethod.POST)
    public Object orderEvaluation(long userOrderId) {
        ResponseInfo responseInfo = userOrderService.userOrderEvaluation(userOrderId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 退款详情
     *
     * @param userOrderId
     * @return
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping("returnUserOrderLineInfo")
    public Object returnUserOrderLineInfo(long userOrderId) {
        ResponseInfo responseInfo = userOrderService.returnUserOrderLineInfo(userOrderId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 静态页面的ajax 请求
     *
     * @param userOrderId
     * @return
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping("showOrderInfo")
    public Object showOrderInfo(long userOrderId) {
        ResponseInfo responseInfo = userOrderService.showOrderInfo(userOrderId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    private String attachImageUrls(String str) {
        String[] arr = str.split(";");
        String urls = "";
        for (int i = 0; i < arr.length; i++) {
            urls = urls + clientSetting.getResource() + arr[i] + ";";
        }
        return urls;
    }

    /**
     * 用户奖励积分
     *
     * @param userId
     * @param userOrderId
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "userAward", method = RequestMethod.POST)
    public Map userAward(long userId, long userOrderId) {
        return userOrderService.userAward(userId, userOrderId);
    }


    /**
     * 查询订单状态
     */
    @LoginRequired
    @ResponseBody
    @PostMapping("userOrderStatus")
    public Object userOrderStatus(HttpServletRequest request) {
        Map map = new HashMap();
        long userOrderId = Long.parseLong(request.getParameter("userOrderId"));
        UserOrder userOrder = userOrderService.queryUserOrderStatus(userOrderId);
        int status = userOrder.getStatus();
        map.put("msg", status);
        map.put("status", 1);
        return map;
    }


    @ResponseBody
    @RequestMapping("signIn")
    public Object signIn(long userId) {
        return signInDao.selectUserSignIn(userId);
    }

}
