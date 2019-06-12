package com.wufanbao.api.oldclientservice.controller.LianLianPay.Pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.BaseController;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Bean.CombinePayBean;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Bean.OrderQuery;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Bean.WeChatAccessToken;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Bean.WeChatPayBean;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Util.BaseBean;
import com.wufanbao.api.oldclientservice.controller.LianLianPay.Util.HttpRequestSimple;
import com.wufanbao.api.oldclientservice.controller.PayTools;
import com.wufanbao.api.oldclientservice.dao.FamilyPayDao;
import com.wufanbao.api.oldclientservice.dao.UserOrderDao;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.wufanbao.api.oldclientservice.Tool.Sign.sign;

/**
 * 余额支付测试
 *
 * @author duzl
 * @date 2017-06-01
 */
@Controller
@RequestMapping("combinePay")
public class CombinePay extends BaseBean {
    private final static String SERVER = "http://121.196.201.74:81/LianLian/";
    private final static String oid_partner = "201711150001146004";
    private final static String wechat_app_id = "wx72b9fa2f86bba0cf";
    //公众号
    private final static String wechat_open_app_id = "wxeb95941a5c320350";
    private final static String wechat_open_app_secret = "52c30a65d2318eed9e3d3e1bce0ed2b4";

    private static final Logger logger = LoggerFactory.getLogger(CombinePay.class);
    private static CombinePay combinePay;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private UserCapitalLogService userCapitalLogService;
    @Autowired
    private UserRegisteredService userRegisteredService;
    @Autowired
    private UserRechargeService userRechargeService;
    @Autowired
    private TakeMealService takeMealService;
    @Autowired
    private FamilyPayDao familyPayDao;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ClientSetting clientSetting;


    /**
     * @Description: 微信公众号支付
     * @Param: [userId, userOrderId, money, name_goods, info_order, pay_type, openId]
     * @return: java.lang.Object
     * @Author:Wang Zhiyuan
     * @Date:
     */
    @PostMapping("weChatAliOpenPay")
    @ResponseBody
    public Object weChatAliOpenPay(String userId, String userOrderId, String money, String name_goods, String info_order, String pay_type, String openId) {
        WeChatPayBean weChatPayBean = new WeChatPayBean();
        //商家订单号
        weChatPayBean.setNo_order(userOrderId);
        //交易商户号
        weChatPayBean.setOid_partner(oid_partner);
        weChatPayBean.setCol_oidpartner(oid_partner);
        //appId
        weChatPayBean.setAppid(wechat_open_app_id);
        //openId
        weChatPayBean.setOpenid(openId);
        //商户业务类型
        weChatPayBean.setBusi_partner("101001");
        //订单金额
        weChatPayBean.setMoney_order(money);
        //付款方用户
        weChatPayBean.setUser_id(userId);
        //商家订单时间
        weChatPayBean.setDt_order(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        //回调地址
        weChatPayBean.setNotify_url(clientSetting.getAlipaycallback() + "/lianlian/orderPay");
        //微信公众号支付
        weChatPayBean.setPay_type(pay_type);
        //风险控制参数
        weChatPayBean.setRisk_item("{\"frms_client_chnl\":\"10\",\"frms_ip_addr\":\"183.172.12.108\",\"frms_imei\":\"423198429310234\",\"frms_mechine_id\":\"423198429310234\",\"frms_mac_addr\":\"7C:7D:3D:B8:F1:A9\"}");
        //请求签名方式
        weChatPayBean.setSign_type("RSA");
        weChatPayBean.setName_goods(name_goods);
        weChatPayBean.setInfo_order(info_order);
        weChatPayBean.setBuyer_confirm_valid(String.valueOf(3));
        //签名串
        weChatPayBean.setSign(genSign(JSON.parseObject(JSON.toJSONString(weChatPayBean))));
        String reqJson = JSON.toJSONString(weChatPayBean);
        HttpRequestSimple httpclent = new HttpRequestSimple();
        String resJson = httpclent.postSendHttp(SERVER + "combinepay.htm", reqJson);
        System.out.println("请求报文为:" + reqJson);
        System.out.println("结果报文为:" + resJson);
        times(7000, Long.valueOf(userOrderId));
        return resJson;
    }

    public static int orderQuery(long userOrderId) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setOid_partner(oid_partner);
        orderQuery.setSign_type("RSA");
        orderQuery.setNo_order(String.valueOf(userOrderId));
        orderQuery.setType_dc("0");
        orderQuery.setSign(genSign(JSON.parseObject(JSON.toJSONString(orderQuery))));
        String reqJson = JSON.toJSONString(orderQuery);
        HttpRequestSimple httpclent = new HttpRequestSimple();
        String resJson = httpclent.postSendHttp(SERVER + "orderquery.htm", reqJson);
        Map maps = JSON.parseObject(resJson);
        double money_order = Double.valueOf(maps.get("money_order").toString());
        String result_pay = maps.get("result_pay").toString();
        String pay_type = maps.get("pay_type").toString();
        if ("SUCCESS".equals(result_pay)) {
            System.out.println(maps.get("no_order") + "交易成功");
            try {
                combinePay.orderTime(userOrderId, money_order, pay_type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        } else if ("PROCESSING".equals(result_pay) || "WAITING".equals(result_pay)) {
            System.out.println(maps.get("no_order") + "交易进行中");
            return 2;
        } else {
            System.out.println(maps.get("no_order") + "交易失败");
            return 3;
        }
    }


    public void orderTime(long userOrderId, double total_fee, String pay_type) {
        String context = null;
        int payType = 0;
        long userId = 0;
        List<Alipay> alipayList = new ArrayList<>();
        try {
            alipayList = combinePay.alipayService.queryAlipay(userOrderId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < alipayList.size(); i++) {
            Alipay alipay = alipayList.get(i);
            userId = alipay.getUserId();
        }
        UserCapitalLog userCapitalLog = new UserCapitalLog();
        try {
            userCapitalLog = combinePay.familyPayDao.getUserMaster(userId, userOrderId, "userOrder");
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
    }

    public void splitUserOrder(long userOrderId, long userId) {
        List<ProductTakeMeal> productTakeMeals = combinePay.takeMealService.productLists(userOrderId);
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
                    combinePay.takeMealService.addUserOrderProductLine(userOrderProductLine);
                }
            }
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

    public double getBalance(long userId) {
        UserRegistered userRegistered = combinePay.userRegisteredService.querybalance(userId);
        double usableBalance = userRegistered.getUsableBalance();
        return usableBalance;
    }

    public static int recharge(long userOrderId) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setOid_partner(oid_partner);
        orderQuery.setSign_type("RSA");
        orderQuery.setNo_order(String.valueOf(userOrderId));
        orderQuery.setType_dc("0");
        orderQuery.setSign(genSign(JSON.parseObject(JSON.toJSONString(orderQuery))));
        String reqJson = JSON.toJSONString(orderQuery);
        HttpRequestSimple httpclent = new HttpRequestSimple();
        String resJson = httpclent.postSendHttp(SERVER + "orderquery.htm", reqJson);
        Map maps = JSON.parseObject(resJson);
        String money_order = maps.get("money_order").toString();
        String result_pay = maps.get("result_pay").toString();
        String pay_type = maps.get("pay_type").toString();
        String oid_paybill = maps.get("oid_paybill").toString();
        if ("SUCCESS".equals(result_pay)) {
            System.out.println(maps.get("no_order") + "交易成功");
            LianLianPayController lianLianPayController = new LianLianPayController();
            lianLianPayController.rechargeTime(String.valueOf(userOrderId), money_order, oid_paybill, pay_type);
            return 1;
        } else if ("PROCESSING".equals(result_pay) || "WAITING".equals(result_pay)) {
            System.out.println(maps.get("no_order") + "交易进行中");
            return 2;
        } else {
            System.out.println(maps.get("no_order") + "交易失败");
            return 3;
        }
    }

    // 充值
    public static void time(int num, long userId) {
        Timer timer = new Timer();
        System.out.println("查询订单中");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int count = recharge(userId);
                if (count == 3 || count == 1) {
                    timer.cancel();
                    System.out.println("订单结束了");
                    timer.purge();
                    System.out.println("订单结束了");
                    try {
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                }
                //timer.cancel();
                // System.out.println("取消订单中结束了");
                //timer.purge();
                //  System.out.println("取消订单中移除了已取消的任务");
            }
        }, num, 1000);
    }


    public static void times(int num, long userId) {
        Timer timer = new Timer();
        System.out.println("查询支付订单中");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int count = 0;
                try {
                    count = orderQuery(userId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (count == 3 || count == 1) {
                    timer.cancel();
                    System.out.println("订单结束了");
                    timer.purge();
                    System.out.println("订单结束了");
                    try {
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                }
                //timer.cancel();
                // System.out.println("取消订单中结束了");
                //timer.purge();
                //  System.out.println("取消订单中移除了已取消的任务");
            }
        }, num, 2000);
    }

    /**
     * @Description: 微信公众号充值
     * @Param: [userId, money, name_goods, info_order, pay_type, amounts, openId]
     * @return: java.lang.Object
     * @Author:Wang Zhiyuan
     * @Date:
     */
    @PostMapping("weChatAliOpenPayCallback")
    @ResponseBody
    public Object weChatAliOpenPayCallback(String userId, String money, String name_goods, String info_order, String pay_type, String amounts, String openId) {
        UserRecharge userRecharge = new UserRecharge();
        long rechargeId = IDGenerator.generateById("rechargeId", Long.parseLong(userId));
        redisUtils.set(String.valueOf(rechargeId), amounts);
        userRecharge.setRechargeId(rechargeId);
        userRecharge.setUserId(Long.parseLong(userId));
        double amount = Double.valueOf(money);
        userRecharge.setAmount(amount);
        String out_trade_no = String.valueOf(rechargeId);
        userRecharge.setBcTradeNo(out_trade_no);
        userRecharge.setPayType(3);
        userRecharge.setPayStatus(1);
        userRecharge.setTradeNo("");
        userRecharge.setActualAmount(0);
        WeChatPayBean weChatPayBean = new WeChatPayBean();
        //商家订单号
        weChatPayBean.setNo_order(String.valueOf(rechargeId));
        //交易商户号
        weChatPayBean.setOid_partner(oid_partner);
        weChatPayBean.setCol_oidpartner(oid_partner);
        weChatPayBean.setAppid(wechat_open_app_id);//appId
        weChatPayBean.setOpenid(openId);//openId
        weChatPayBean.setBusi_partner("101001");//商户业务类型
        weChatPayBean.setMoney_order(money);//订单金额
        weChatPayBean.setUser_id(userId);//付款方用户
        weChatPayBean.setDt_order(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));//商家订单时间
        weChatPayBean.setNotify_url(clientSetting.getAlipaycallback() + "/lianlian/rechargePay");//回调地址
        weChatPayBean.setPay_type(pay_type);//微信公众号支付
        weChatPayBean.setRisk_item("{\"frms_client_chnl\":\"10\",\"frms_ip_addr\":\"183.172.12.108\",\"frms_imei\":\"423198429310234\",\"frms_mechine_id\":\"423198429310234\",\"frms_mac_addr\":\"7C:7D:3D:B8:F1:A9\"}");//风险控制参数
        weChatPayBean.setSign_type("RSA");//请求签名方式
        weChatPayBean.setName_goods(name_goods);
        weChatPayBean.setInfo_order(info_order);
        weChatPayBean.setSign(genSign(JSON.parseObject(JSON.toJSONString(weChatPayBean))));//签名串
        String reqJson = JSON.toJSONString(weChatPayBean);
        HttpRequestSimple httpclent = new HttpRequestSimple();
        String resJson = httpclent.postSendHttp(SERVER + "combinepay.htm", reqJson);
        System.out.println("请求报文为一个人走在路上却还在羡慕别人 :" + reqJson);
        System.out.println("结果报文为:" + resJson);
        try {
            userRechargeService.beforeUserRecharge(userRecharge);
            System.out.println("请求报文为:" + reqJson);
            return resJson;
        } catch (RuntimeException e) {
            logger.info(e.toString());
            return 0;
        }
    }

    /**
     * 微信支付宝支付
     */
    @PostMapping("weChatAliPay")
    @ResponseBody
    public Object WeChatAliPay(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String userOrderId = request.getParameter("userOrderId");
        String money = request.getParameter("money");
        String name_goods = request.getParameter("name_goods");
        String info_order = request.getParameter("info_order");
        String pay_type = request.getParameter("pay_type");
        String amounts = request.getParameter("amounts");
        String openId = request.getParameter("openId");
        Map map = new HashMap();
        if (amounts.equals("")) {
            if (openId != null) {
                map.put("data", weChatAliOpenPay(userId, userOrderId, money, name_goods, info_order, pay_type, openId));
            } else {
                map.put("data", weChatAlipayOrder(userId, userOrderId, money, name_goods, info_order, pay_type));
            }
        }
        if (amounts != "") {
            if (openId != null) {
                map.put("data", weChatAliOpenPayCallback(userId, userOrderId, money, name_goods, info_order, pay_type, openId));
            } else {
                map.put("data", WeChatAliPayCallback(userId, money, name_goods, info_order, pay_type, amounts));
            }
        }
        return map;
    }

    /**
     * 聚合订单支付
     *
     * @param userId
     * @param userOrderId
     * @param money
     * @param name_goods
     * @param info_order
     * @param pay_type
     * @return
     */
    public Object weChatAlipayOrder(String userId, String userOrderId, String money, String name_goods, String info_order, String pay_type) {
        CombinePayBean combinePayBean = new CombinePayBean();
        //接口版本
        combinePayBean.setVer_app("1.0");
        //签名方式
        combinePayBean.setSign_type("RSA");
        combinePayBean.setOid_partner(oid_partner);//用户所属商户号
        combinePayBean.setUser_id(userId);//商户用户唯一编号
        combinePayBean.setCol_oidpartner(oid_partner);//收款方商户号
        combinePayBean.setPay_type(pay_type);//付款方式
        combinePayBean.setBusi_partner("101001");//商户业务类型 业务类型编码 虚拟商品销售：101001 实物商品销售：109001
        combinePayBean.setNo_order(userOrderId);//商户唯一订单号
        combinePayBean.setDt_order(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));//商户订单时间
        combinePayBean.setMoney_order(money);//商户订单金额
        combinePayBean.setName_goods(name_goods);//商品名称
        combinePayBean.setInfo_order(info_order);//订单附加信息
        combinePayBean.setValid_order("1");//订单有效时间
        combinePayBean.setNotify_url(clientSetting.getAlipaycallback() + "/lianlian/orderPay");//异步通知地址
        combinePayBean.setRisk_item("{\"frms_client_chnl\":\"10\",\"frms_ip_addr\":\"183.172.12.108\",\"frms_imei\":\"423198429310234\",\"frms_mechine_id\":\"423198429310234\",\"frms_mac_addr\":\"7C:7D:3D:B8:F1:A9\"}");//风险控制参数
        //combinePayBean.setRisk_item("{\"user_info_dt_register\":\"20131030122130\"}");//风险控制参数
        if (pay_type.equals("Y")) {
            //微信 AppID
            combinePayBean.setWechat_app_id(wechat_app_id);
            //签名
            combinePayBean.setSign(genSign(JSON.parseObject(JSON.toJSONString(combinePayBean))));
            String reqJson = JSON.toJSONString(combinePayBean);
            System.out.println("请求报文为:" + reqJson);
            return reqJson;
        }
        //签名
        combinePayBean.setSign(genSign(JSON.parseObject(JSON.toJSONString(combinePayBean))));
        String reqJson = JSON.toJSONString(combinePayBean);
        System.out.println("请求报文为:" + reqJson);
        times(7000, Long.valueOf(userOrderId));
        return reqJson;
    }

    @ResponseBody
    @RequestMapping(value = "getWXcode")
    public Object getWXcode(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        //WxOpenIdServlet的地址
        String redirect = clientSetting.getWechatCallback() + "/combinePay/getWXOpenId";
        redirect = URLEncoder.encode(redirect, "utf-8");
        String url = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
                .append(wechat_open_app_id).append("&redirect_uri=").append(redirect)
                .append("&response_type=code&scope=snsapi_userinfo").append("#wechat_redirect").toString();
        return url;
    }

    public String sendGetRequest(String getUrl) throws IOException {
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            URL url = new URL(getUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            isr = new InputStreamReader(url.openStream());
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            isr.close();
            br.close();
            //FileOperator.closeResources(isr, br);
        }
        return sb.toString();
    }


    //访问微信获取openid
    //        String appId="EUCP-EMY-SMS1-0POZC";
//        String url="http://shmtn.b2m.cn/simpleinter/sendSMS";
//        String content = "【伍饭宝】您好，您的验证码是："+code+"，5分钟内有效";
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
//        String timestamp=sdf.format(new Date());
//        String secretKey ="36BC4BA09AA628D7";
//        String sign=DigestUtils.md5Hex(appId+secretKey+timestamp);
//        String sr=sendPost(url, "appId="+appId+"&timestamp="+timestamp+"&sign="+sign+"&mobiles="+mB+"&content="+content);
//        System.out.println(sr);
//    /**
//     * 向指定 URL 发送POST方法的请求
//     *
//     * @param url
//     *            发送请求的 URL
//     * @param param
//     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return 所代表远程资源的响应结果
//     */

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("contentType", "UTF-8");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "getWxUserInfo")
    public Object getWxUserInfo(String openId) throws Exception {
        ResponseInfo<Map<String, String>> responseInfo = getAccessTokenByOpenId(openId);
        ResponseInfo<Map> responseInfo1 = new ResponseInfo<>();
        if (responseInfo.getCode() == 1) {
            Map<String, String> map = responseInfo.getData();
            String accessToken = getWXAccessToken();
            String url = "https://api.weixin.qq.com/cgi-bin/user/info";
            String ss = "access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
            String resJson = sendGet(url, ss);
            Map map1 = JSON.parseObject(resJson);
            responseInfo1.setCode(1);
            responseInfo1.setData(map1);
            responseInfo1.setMessage("请求成功");
            return responseInfo1;
        }
        responseInfo1.setCode(2032);
        responseInfo1.setMessage("请重新获取授权");
        return responseInfo1;
    }

    /**
     * 获取openId
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "getWXOpenId")
    protected void getWXOpenId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=AppId&secret=AppSecret&code=CODE&grant_type=authorization_code";
        url = url.replace("AppId", wechat_open_app_id).replace("AppSecret", wechat_open_app_secret).replace("CODE", code);
        HttpRequestSimple httpclent = new HttpRequestSimple();
        String resJson = httpclent.getSendHttp(url);
        JSONObject jsonTexts = (JSONObject) JSON.parse(resJson);
        WeChatAccessToken weChatAccessToken = JSON.parseObject(jsonTexts.toString(), WeChatAccessToken.class);
        redisUtils.set(weChatAccessToken.getOpenid(), weChatAccessToken.getAccess_token());
        String ref = weChatAccessToken.getOpenid() + "Refresh_token";
        redisUtils.set(ref, weChatAccessToken.getRefresh_token());
        //有效期30天
        redisUtils.expire(ref, 2592000);
        redisUtils.expire(weChatAccessToken.getOpenid(), 7200);
        resp.sendRedirect(clientSetting.getmUrl() + "?openId=" + weChatAccessToken.getOpenid() + "&accessToken=" + weChatAccessToken.getAccess_token());
    }

    @ResponseBody
    @RequestMapping(value = "getAccessTokenByOpenId")
    protected ResponseInfo<Map<String, String>> getAccessTokenByOpenId(String openId) {
        ResponseInfo<Map<String, String>> responseInfo = new ResponseInfo<>();
        String accessToken = redisUtils.get(openId);
        String ref = openId + "Refresh_token";
        String refresh_token = redisUtils.get(ref);
        BaseController baseController = new BaseController();
        if (accessToken != null) {
            Map<String, String> map = new HashMap<>();
            map.put("accessToken", accessToken);
            responseInfo.setCode(1);
            responseInfo.setData(map);
            responseInfo.setMessage("请求成功");
            return responseInfo;
        } else if (refresh_token != null) {
            String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=AppId&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
            url = url.replace("AppId", wechat_open_app_id).replace("REFRESH_TOKEN", refresh_token);
            HttpRequestSimple httpclent = new HttpRequestSimple();
            String resJson = httpclent.getSendHttp(url);
            Map map1 = JSON.parseObject(resJson);
            boolean errCode = map1.get("errCode") == null ? true : false;
            if (errCode) {
                redisUtils.del(ref);
                responseInfo.setCode(2032);
                responseInfo.setMessage("请重新获取授权");
                return responseInfo;
            }
            String accessToken1 = map1.get("access_token").toString();
            redisUtils.set(openId, accessToken1);
            Map<String, String> map = new HashMap<>();
            map.put("accessToken", accessToken1);
            responseInfo.setMessage("请求成功");
            responseInfo.setData(map);
            responseInfo.setCode(1);
            return responseInfo;
        }
        responseInfo.setCode(2032);
        responseInfo.setMessage("请重新获取授权");
        return responseInfo;
    }

    //获取全局accessToken
    @ResponseBody
    @RequestMapping(value = "getWXAccessToken")
    protected String getWXAccessToken() throws ServletException, IOException {
        String accessToken = "";
        try {
            if (redisUtils.get("access_token") != null) {
                accessToken = redisUtils.get("access_token");
            } else {
                String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=AppId&secret=AppSecret";
                url = url.replace("AppId", wechat_open_app_id).replace("AppSecret", wechat_open_app_secret);
                HttpRequestSimple httpclent = new HttpRequestSimple();
                String resJson = httpclent.getSendHttp(url);
                Map map1 = JSON.parseObject(resJson);
                String access_token = map1.get("access_token").toString();
                redisUtils.set("access_token", access_token);
                redisUtils.expire("access_token", 7200);
                System.out.println(access_token + "access_token");
                return access_token;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(accessToken + "accessToken");
        return accessToken;
    }

    //获取getJsApiTicket
    @ResponseBody
    @RequestMapping(value = "getJsApiTicket")
    protected String getJsApiTicket() throws ServletException, IOException {
        String ACCESS_TOKEN = getWXAccessToken();
        String jsApiTickets = "";
        try {
            if (redisUtils.get("ticket") != null) {
                jsApiTickets = redisUtils.get("ticket");
            } else {
                String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
                url = url.replace("ACCESS_TOKEN", ACCESS_TOKEN);
                HttpRequestSimple httpclent = new HttpRequestSimple();
                String resJson = httpclent.getSendHttp(url);
                Map map = JSON.parseObject(resJson);
                String ticket = map.get("ticket").toString();
                redisUtils.set("ticket", ticket);
                redisUtils.expire("ticket", 7200);
                System.out.println(ticket + "ticket");
                return ticket;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsApiTickets + "jsApiTickets");
        return jsApiTickets;
    }

    @ResponseBody
    @RequestMapping(value = "getWxSign")
    public Object WxSign(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsapi_ticket = getJsApiTicket();
        // 注意 URL 一定要动态获取，不能 hardcode
        String url = req.getParameter("url");
        Map<String, String> ret = sign(jsapi_ticket, url);
        System.out.println(ret);
        return ret;
    }


    /**
     * 聚合支付充值
     *
     * @param userId     用户id
     * @param money      交易金额
     * @param name_goods 商品名字
     * @param info_order 商品内容
     * @param pay_type   交易方式
     * @param amounts    赠送金额
     * @return
     */
    public Object WeChatAliPayCallback(String userId, String money, String name_goods, String info_order, String pay_type, String amounts) {
        UserRecharge userRecharge = new UserRecharge();
        long rechargeId = IDGenerator.generateById("rechargeId", Long.parseLong(userId));
        redisUtils.set(String.valueOf(rechargeId), amounts);
        userRecharge.setRechargeId(rechargeId);
        userRecharge.setUserId(Long.parseLong(userId));
        double amount = Double.valueOf(money);
        userRecharge.setAmount(amount);
        String out_trade_no = String.valueOf(rechargeId);
        userRecharge.setBcTradeNo(out_trade_no);
        userRecharge.setPayType(3);
        userRecharge.setPayStatus(1);
        userRecharge.setTradeNo("");
        userRecharge.setActualAmount(0);
        CombinePayBean combinePayBean = new CombinePayBean();
        combinePayBean.setVer_app("1.0");//接口版本
        combinePayBean.setSign_type("RSA");//签名方式
        combinePayBean.setOid_partner(oid_partner);//用户所属商户号
        combinePayBean.setUser_id(userId);//商户用户唯一编号
        combinePayBean.setCol_oidpartner(oid_partner);//收款方商户号
        combinePayBean.setPay_type(pay_type);//付款方式
        combinePayBean.setBusi_partner("101001");//商户业务类型 业务类型编码 虚拟商品销售：101001 实物商品销售：109001
        combinePayBean.setNo_order(String.valueOf(rechargeId));//商户唯一订单号
        combinePayBean.setDt_order(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));//商户订单时间
        combinePayBean.setMoney_order(money);//商户订单金额
        combinePayBean.setName_goods(name_goods);//商品名称
        combinePayBean.setInfo_order(info_order);//订单附加信息
        combinePayBean.setValid_order("3");//订单有效时间
        combinePayBean.setNotify_url(clientSetting.getAlipaycallback() + "/lianlian/rechargePay");//异步通知地址
        combinePayBean.setRisk_item("{\"frms_client_chnl\":\"10\",\"frms_ip_addr\":\"183.172.12.108\",\"frms_imei\":\"423198429310234\",\"frms_mechine_id\":\"423198429310234\",\"frms_mac_addr\":\"7C:7D:3D:B8:F1:A9\"}");//风险控制参数
        //combinePayBean.setRisk_item("{\"user_info_dt_register\":\"20131030122130\"}");//风险控制参数
        if ("Y".equals(pay_type)) {
            combinePayBean.setWechat_app_id(wechat_app_id);//微信 AppID
            combinePayBean.setSign(genSign(JSON.parseObject(JSON.toJSONString(combinePayBean))));//签名
            String reqJson = JSON.toJSONString(combinePayBean);
            userRechargeService.beforeUserRecharge(userRecharge);
            System.out.println("请求报文为:" + reqJson);
            time(7000, rechargeId);
            return reqJson;
        }
        try {
            combinePayBean.setSign(genSign(JSON.parseObject(JSON.toJSONString(combinePayBean))));//签名
            String reqJson = JSON.toJSONString(combinePayBean);
            userRechargeService.beforeUserRecharge(userRecharge);
            System.out.println("请求报文为:" + reqJson);
            time(7000, rechargeId);
            return reqJson;
        } catch (RuntimeException e) {
            logger.info(e.toString());
            return 0;
        }
    }
}
