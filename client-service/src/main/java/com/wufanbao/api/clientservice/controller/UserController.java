package com.wufanbao.api.clientservice.controller;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.clientservice.common.*;
import com.wufanbao.api.clientservice.common.alipay.AliPay;
import com.wufanbao.api.clientservice.common.wechat.AccessToken;
import com.wufanbao.api.clientservice.common.wechat.UserInfo;
import com.wufanbao.api.clientservice.common.wechat.WechatAuth;
import com.wufanbao.api.clientservice.common.wechat.WechatPay;
import com.wufanbao.api.clientservice.entity.User;
import com.wufanbao.api.clientservice.service.ApiServiceException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

/**
 * 用户相关
 */
@RestController
public class UserController extends BaseController {
    @Autowired
    private WechatAuth wechatAuth;
    @Autowired
    private AliPay aliPay;

    /**
     * 用户登录
     * 登录方式有三种：
     * 1、用户名密码登录:password
     * 2、手机验证码登录:message
     * 3、第三方账号登录:binding
     *
     * @param data:{loginType:"password","loginNo":"15968844656","password":"25f9e794323b453885f5181f1b624d0b"} {loginType:"message","loginNo":"15968844656","code":"5179"}
     *                                                                                                          {loginType:"binding","bindingId":"332258fd12"}
     * @return
     */
    @PostMapping("login")
    public ResponseData login(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String loginType = map.get("loginType");
        String loginNo = map.get("loginNo");
        try {
            long userId = 0;
            switch (loginType) {
                case "password"://密码登录
                    String password = map.get("password").toString();
                    userId = userService.passwordLogin(loginNo, password);
                    break;
                case "message"://短信登录
                    String messageCode = map.get("code").toString();
                    userId = userService.messageLogin(loginNo, messageCode);
                    break;
                case "binding"://绑定第三方账号
                    String ushareStr = map.get("ushare").toString();
                    if (StringUtils.isNullOrEmpty(ushareStr)) {
                        throw new ApiServiceException("登录失败，用户信息错误");
                    }
                    UShare ushare = JsonUtils.GsonToBean(ushareStr, UShare.class);
                    User user = userService.bindingLogin(ushare);
                    if (user == null||user.getMb()=="") {
                        return responseData.success(1008).sign(ushare.getUid());
                    }
                    userId = user.getUserId();
                    loginNo = user.getLoginNo();
                    if(StringUtils.isNullOrEmpty(loginNo)) {
                        return responseData.error("登陆号为空").sign(null);
                    }
                    if(!(userId>0))
                        return responseData.error("用户不存在").sign(null);
                    System.out.println(user.toString());
                    break;
                default:
                    return responseData.error("没有该登录方式").sign(null);
            }
            String jwtToken = JwtHelper.createJWT(userId,
                    loginNo,
                    audience.getClientId(),
                    audience.getName(),
                    audience.getExpiresSecond() * 1000,
                    audience.getBase64Secret());
            String result = "bearer;" + jwtToken;
            redisUtils.set("JwtToken_" + userId, result);
            redisUtils.expire("JwtToken_" + userId, audience.getExpiresSecond() * 1000);
            return responseData.success().sign(result);
        } catch (ApiServiceException ex) {
            return responseData.error(ex).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //第三方登录绑定手机号
    @PostMapping("bingLoginNo")
    public ResponseData bingLoginNo(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String uid = map.get("uid");
        String loginNo = map.get("phone");
        String typeStr = map.get("type");
        if (StringUtils.isNullOrEmpty(uid) || StringUtils.isNullOrEmpty(loginNo) || StringUtils.isNullOrEmpty(typeStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            int type = Integer.parseInt(typeStr);
            long userId = userService.bindLoginNo(loginNo, uid, type);
            String jwtToken = JwtHelper.createJWT(userId,
                    loginNo,
                    audience.getClientId(),
                    audience.getName(),
                    audience.getExpiresSecond() * 1000,
                    audience.getBase64Secret());
            String result = "bearer;" + jwtToken;
            redisUtils.set("JwtToken_" + userId, result);
            redisUtils.expire("JwtToken_" + userId, audience.getExpiresSecond() * 1000);
            return responseData.success().sign(result);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    /**
     * 发送短信验证码
     *
     * @param data:{"phone":"15968844656"}
     * @return
     */
    @PostMapping("sendMessage")
    public ResponseData sendMessage(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String phone = map.get("phone");
        try {
            userService.sendMessage(phone);
            return responseData.success().sign(null);
        } catch (ApiServiceException ex) {
            return responseData.error(ex).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    /**
     * 判断该手机号码是否注册过用户
     *
     * @param data
     * @return
     */
    @PostMapping("existUser")
    public ResponseData exsitUser(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String phone = map.get("phone");
        try {
            Data datas = new Data();
            if (userService.userExist(phone)) {
                datas.put("isExist", 1);
            } else {
                datas.put("isExist", 0);
            }
            return responseData.success().sign(datas);
        } catch (ApiServiceException ex) {
            return responseData.error(ex).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    /**
     * 验证手机短信验证吗
     *
     * @param data
     * @return
     */
    @PostMapping("checkMessage")
    public ResponseData checkMessage(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String phone = map.get("phone");
        String code = map.get("code");
        try {
            userService.checkMessage(phone, code);
            return responseData.success().sign(null);
        } catch (ApiServiceException ex) {
            return responseData.error(ex).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    /**
     * 注册
     *
     * @return
     */
    @PostMapping("regist")
    public ResponseData regist(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String phone = map.get("phone");
        String code = map.get("code");
        String password = map.get("password");
        User user = new User();
        user.setLoginNo(phone);
        user.setUserName(phone);
        user.setPassword(password);
        try {
            User user1 = userService.insertUser(user);
            if (user1 == null) {
                return responseData.error("注册用户失败，请检查注册信息或联系客服").sign(map);
            }
            long userId = user1.getUserId();
            String jwtToken = JwtHelper.createJWT(userId,
                    user1.getLoginNo(),
                    audience.getClientId(),
                    audience.getName(),
                    audience.getExpiresSecond() * 1000,
                    audience.getBase64Secret());
            String result = "bearer;" + jwtToken;
            redisUtils.set("JwtToken_" + userId, result);
            redisUtils.expire("JwtToken_" + userId, audience.getExpiresSecond() * 1000);
            return responseData.success().sign(result);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(map);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //更换手机号
    @PostMapping("/auth/setLoginNo")
    public ResponseData setLoginNo(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String loginNo = map.get("loginNo");
        try {
            userService.setLoginNo(userId, loginNo);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //设置／修改支付密码
    @PostMapping("/auth/setPayPassword")
    public ResponseData setPayPassword(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String password = map.get("payPassword");
        try {
            userService.setPayPassword(userId, password);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //设置登录密码
    @PostMapping("/auth/setLoginPassword")
    public ResponseData setLoginPassword(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String password = map.get("password");
        try {
            userService.setLoginPassword(userId, password);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //设置登录密码
    @PostMapping("setLoginPassword")
    public ResponseData setLoginPasswordByPhone(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String loginNo = map.get("loginNo");
        String password = map.get("password");
        try {
            userService.setLoginPasswordByPhone(loginNo, password);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //获取用户相关数据
    @PostMapping("/auth/getUser")
    public ResponseData getUser(long userId) {
        try {
            return responseData.success().sign(userService.getUserDetail(userId));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //完善用户信息
    @PostMapping("/auth/perfectUser")
    public ResponseData perfectUser(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userName = map.get("userName");
        String sexStr = map.get("sex");
        String birthdayStr = map.get("birthday");
        String breakFastStr = map.get("breakFast");
        String lunchStr = map.get("lunch");
        String dinnerStr = map.get("dinner");
        try {
            int sex = 0;
            Date birthday = null;
            Date breakFast = null;
            Date lunch = null;
            Date dinner = null;
            if (!StringUtils.isNullOrEmpty(sexStr)) {
                sex = Integer.parseInt(sexStr);
            }
            if (!StringUtils.isNullOrEmpty(birthdayStr)) {
                birthday = DateUtils.StringToDate(birthdayStr, "yyyy-MM-dd");
            }
            if (!StringUtils.isNullOrEmpty(breakFastStr)) {
                breakFast = DateUtils.StringToDate(breakFastStr, "HH:mm");
            }
            if (!StringUtils.isNullOrEmpty(lunchStr)) {
                lunch = DateUtils.StringToDate(lunchStr, "HH:mm");
            }
            if (!StringUtils.isNullOrEmpty(dinnerStr)) {
                dinner = DateUtils.StringToDate(dinnerStr, "HH:mm");
            }
            if(!StringUtils.isNullOrEmpty(userName)) {
                userName=userName.replaceAll("[^a-zA-Z0-9\u4e00-\u9fa5,.;*@!()+_，。！？；：/（）#&]","");
            }
            if (!StringUtils.isNullOrEmpty(userName) || sex > 0 || birthday != null || breakFast != null || lunch != null || dinner != null) {
                userService.perfectUser(userId, userName, sex, birthday, breakFast, lunch, dinner);
            }
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }

    }

    //更新用户头像
    @PostMapping("/auth/updateUserPhoto")
    public Object updateUserPortrait(String data, long userId) throws IOException {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String photo = map.get("photo");
        if (StringUtils.isNullOrEmpty(photo)) {
            return responseData.error("请选择要上传的头像").sign(null);
        }
        try {
            userService.updatePortrait(userId, photo);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception e) {
            return responseData.error(e).sign(null);
        }
    }

    //获取用户的资金交易记录
    @PostMapping("/auth/getUsercapitallogs")
    public ResponseData getUsercapitallogs(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String pageIndexStr = map.get("pageIndex");//第几页
        if (StringUtils.isNullOrEmpty(pageIndexStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            int p = Integer.parseInt(pageIndexStr);
            return responseData.success().sign(userService.getUsercapitallogs(userId, DateUtils.getBeforeMonth(p - 1)));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //获取用户子帐户的资金交易记录
    @PostMapping("/auth/getSonUsercapitalLogs")
    public ResponseData getSonUserCapitalLogs(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userIdStr = map.get("userId");//第几页
        String pageIndexStr = map.get("pageIndex");//第几页
        if (StringUtils.isNullOrEmpty(pageIndexStr) || StringUtils.isNullOrEmpty(userIdStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            long userId = Long.parseLong(userIdStr);
            int p = Integer.parseInt(pageIndexStr);
            return responseData.success().sign(userService.getSonUserCapitalLogs(userId, DateUtils.getBeforeMonth(p - 1)));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //获取用户积分记录
    @PostMapping("/auth/getUserIntegralLogs")
    public ResponseData getUserIntegralLogs(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String pageIndexStr = map.get("pageIndex");//第几页
        if (StringUtils.isNullOrEmpty(pageIndexStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            int pageIndex = Integer.parseInt(pageIndexStr);
            int pageSize = 20;
            int pageStart = (pageIndex - 1) * pageSize;
            if (pageStart < 0) {
                pageStart = 0;
            }
            return responseData.success().sign(userService.getUserIntegralLogs(userId, pageStart, pageSize));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }
    @PostMapping("/auth/getHistoryUserCoupons")
    public ResponseData getHistoryUserCoupons(String data,long userId){
        Map<String,String> map= JsonUtils.GsonToMaps(data);
        String pageIndexStr=map.get("pageIndex");
        int pageIndex=Integer.valueOf(pageIndexStr);
        int pageSize=10;
        int  pageStart=(pageIndex-1)*pageSize;
        if(pageStart<0){
            pageStart=0;
        }
        try {
            return responseData.success().sign(userService.getHistoryUserCoupons(userId,pageStart,pageSize));
        } catch (Exception ex) {
            return  responseData.error(ex).sign(null);
        }
    }
    //获取用户优惠券
    @PostMapping("/auth/getUseableUserCoupons")
    public ResponseData getUseableUserCoupons(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String areaName = null;//所在城市
        String productIdStr = null;//商品ids
        String amountStr = null;//订单金额
        if (map != null) {
            areaName = map.get("areaName");//所在城市
            productIdStr = map.get("productIds");//商品ids
            amountStr = map.get("amount");//订单金额
        }
        try {
            List<Data> result = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(areaName) && !StringUtils.isNullOrEmpty(productIdStr) && !StringUtils.isNullOrEmpty(amountStr)) {
                double amount = Double.valueOf(amountStr);
                List<Long> productIds = JsonUtils.GsonToBean(productIdStr, new TypeToken<List<Long>>() {
                }.getType());
                result = userService.getUseableUserCoupons(userId, productIds, BigDecimal.valueOf(amount / 100), areaName);
            } else {
                result = userService.getUseableUserCoupons(userId);
            }
            return responseData.success().sign(result);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    @PostMapping("/auth/getCouponDetail")
    public ResponseData getCouponDetail(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String areaName = map.get("areaName");//所在城市
        String xStr = map.get("x");
        String yStr = map.get("y");
        String couponIdStr = map.get("couponId");
        if (StringUtils.isNullOrEmpty(areaName) || StringUtils.isNullOrEmpty(xStr) || StringUtils.isNullOrEmpty(yStr) || StringUtils.isNullOrEmpty(couponIdStr)) {
            responseData.error("参数数据错误").sign(null);
        }
        try {
            BigDecimal x = new BigDecimal(xStr);
            BigDecimal y = new BigDecimal(yStr);
            long couponId = Long.parseLong(couponIdStr);
            return responseData.success().sign(userService.getCouponDetail(areaName, x, y, couponId));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //获取用户消息列表
    @PostMapping("/auth/getMessages")
    public ResponseData getMessage(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String pageIndexStr = map.get("pageIndex");//第几页
        String isReadStr = map.get("isRead");
        if (StringUtils.isNullOrEmpty(pageIndexStr) || StringUtils.isNullOrEmpty(isReadStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            int pageIndex = Integer.parseInt(pageIndexStr);
            int pageSize = 10;
            int pageStart = (pageIndex - 1) * pageSize;
            if (pageStart < 0) {
                pageStart = 0;
            }
            int isRead = Integer.parseInt(isReadStr);
            return responseData.success().sign(userService.getUserMessages(userId, isRead, pageStart, pageSize));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //设置消息已读
    @PostMapping("/auth/setMessgeRead")
    public ResponseData setMessgeRead(long userId) {
        try {
            userService.setUserMessageRead(userId);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //设置消息已删除
    @PostMapping("/auth/setMessgeDelete")
    public ResponseData setMessgeDelete(long userId) {
        try {
            userService.setUserMessageDelete(userId);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //获取用户签到数据
    @PostMapping("/auth/getUserSignIn")
    public ResponseData getUserSignIn(long userId) {
        try {
            return responseData.success().sign(userService.getUserSignIn(userId));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //用户签到
    @PostMapping("/auth/userSignIn")
    public ResponseData userSignIn(long userId) {
        try {
            userService.userSignIn(userId);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //充值
    @PostMapping("/auth/recharge")
    public ResponseData recharge(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String typeStr = map.get("type");//充值方式
        String ip = map.get("ip");//ip
        String amountStr = map.get("amount");//充值金额（分）
        if (StringUtils.isNullOrEmpty(typeStr) || StringUtils.isNullOrEmpty(amountStr) || StringUtils.isNullOrEmpty(ip)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            int type = Integer.parseInt(typeStr);
            double amount = Double.valueOf(amountStr);
            Object object = userService.recharge(userId, ip, type, BigDecimal.valueOf(amount / 100));
            return responseData.success().sign(object);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //微信充值成功通知
    @PostMapping("/webapi/wechat/rechargeNotify")
    public void wxPayNotify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        try {
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
            map = XMLUtil.doXMLParse(result);
        } catch (Exception ex) {
            logger.error(commonFun.getStackTraceInfo(ex));
        }

        if (map.containsKey("return_code") && map.get("return_code").toString().equalsIgnoreCase("SUCCESS")) {
            try {
                WechatPay wechatPay = new WechatPay();
                if (wechatPay.checkSign(map)) {
                    long rechargeId = Long.parseLong(map.get("out_trade_no"));
                    long totalFee = Long.parseLong(map.get("total_fee"));
                    String transaction_id = map.get("transaction_id");//微信支付订单号
                    BigDecimal amount = BigDecimal.valueOf(totalFee).divide(BigDecimal.valueOf(100));
                    userService.rechargeNotify(rechargeId, transaction_id, amount);
                    PrintWriter writer = null;
                    try {
                        writer = response.getWriter();
                        writer.write(wechatPay.notifySuccess("SUCCESS", "OK")); //告诉微信服务器，我收到信息了，不要在调用回调action了
                        writer.flush();
                    } finally {
                        if (writer != null) {
                            writer.close();
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error(commonFun.getStackTraceInfo(ex));
            }
        }
    }

    //微信公众号授权登录
    @GetMapping("/webapi/wechat/requestCodeCallback")
    public void wechatRequestCodeCallback(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        if (StringUtils.isNullOrEmpty(code)) {
            commonFun.writer(response, responseData.error("code 不能为空").sign(null));
        }
        try {
            AccessToken accessToken = wechatAuth.getAccessTokenByCode(code);
            UserInfo userInfo = wechatAuth.getUserInfo(accessToken.getOpenid());
            User user = new User();
            String nickName=userInfo.getNickname().replaceAll("[^a-zA-Z0-9\u4e00-\u9fa5_-]", "");
            if(StringUtils.isNullOrEmpty(nickName)){
                nickName=""+System.currentTimeMillis();
            }
            user.setUserName(nickName);
            user.setSex(userInfo.getSex());
            user.setPortrait(userInfo.getHeadimgurl());
            user.setLoginNo("");
            user.setPassword("");
            long userId = userService.insertUserBinding(user, userInfo.getUnionid(), userInfo.getOpenid(),1);//微信
            String jwtToken = JwtHelper.createJWT(userId,
                    userInfo.getUnionid(),
                    audience.getClientId(),
                    audience.getName(),
                    audience.getExpiresSecond() * 1000,
                    audience.getBase64Secret());
            String result = "bearer;" + jwtToken;
            redisUtils.set("JwtToken_" + userId, result);
            redisUtils.expire("JwtToken_" + userId, audience.getExpiresSecond() * 1000);
            response.sendRedirect(clientSetting.getWechat() + "?openId=" + accessToken.getOpenid() + "&token=" + result);
        } catch (ApiServiceException e) {
            commonFun.writer(response, responseData.error(e).sign(null));
        } catch (IOException e) {
            commonFun.writer(response, responseData.error(e.getMessage()).sign(null));
        }
    }

    //支付宝H5授权登录
    @GetMapping("/webapi/alipay/requestCodeCallback")
    public void alipayRequestCodeCallback(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("auth_code");
        if (StringUtils.isNullOrEmpty(code)) {
            commonFun.writer(response, responseData.error("code 不能为空").sign(null));
        }
        try {
            AlipaySystemOauthTokenResponse accessToken = aliPay.getAccessTokenByCode(code);
            AlipayUserInfoShareResponse userInfo = aliPay.getUserInfo(accessToken.getAccessToken());
            User user = new User();
            user.setUserName(userInfo.getNickName().replaceAll("[\ue000-\uefff]", ""));
            user.setSex(0);
            user.setPortrait(userInfo.getAvatar());
            user.setLoginNo(userInfo.getUserId());
            user.setPassword("123456");
            long userId = userService.insertUserBinding(user, userInfo.getUserId(),userInfo.getUserId(),2);//支付宝
            String jwtToken = JwtHelper.createJWT(userId,
                    userInfo.getUserId(),
                    audience.getClientId(),
                    audience.getName(),
                    audience.getExpiresSecond() * 1000,
                    audience.getBase64Secret());
            String result = "bearer;" + jwtToken;
            redisUtils.set("JwtToken_" + userId, result);
            redisUtils.expire("JwtToken_" + userId, audience.getExpiresSecond() * 1000);
            response.sendRedirect(clientSetting.getAlipay() + "?alipayId=" + accessToken.getUserId() + "&token=" + result);
        } catch (ApiServiceException e) {
            commonFun.writer(response, responseData.error(e).sign(null));
        } catch (IOException e) {
            commonFun.writer(response, responseData.error(e.getMessage()).sign(null));
        }
    }

    //支付宝充值成功通知
    @PostMapping("/webapi/alipay/rechargeNotify")
    public void alipayPayNotify(HttpServletRequest request, HttpServletResponse response) {
        String tradeStatus = request.getParameter("trade_status");
        if ("TRADE_SUCCESS".equals(tradeStatus)) {
            Enumeration<?> pNames = request.getParameterNames();
            Map<String, String> param = new HashMap<String, String>();
            try {
                while (pNames.hasMoreElements()) {
                    String pName = (String) pNames.nextElement();
                    param.put(pName, request.getParameter(pName));
                }
                AliPay aliPay = new AliPay();
                if (aliPay.checkSign(param)) {
                    try {
                        long rechargeId = Long.parseLong(param.get("out_trade_no"));
                        Double totalFee = Double.parseDouble(param.get("total_amount"));
                        String trade_no = param.get("trade_no");//支付宝订单号
                        userService.rechargeNotify(rechargeId, trade_no, BigDecimal.valueOf(totalFee));
                        PrintWriter writer = null;
                        try {
                            writer = response.getWriter();
                            writer.write("success"); //一定要打印success
                            writer.flush();
                        } finally {
                            if (writer != null) {
                                writer.close();
                            }
                        }
                    } catch (Exception ex) {
                        logger.error(commonFun.getStackTraceInfo(ex));
                    }
                }
            } catch (Exception e) {
                logger.error(commonFun.getStackTraceInfo(e));
            }
        }
    }


    //获取绑定的子账号数据
    @PostMapping("/auth/getFamilyUser")
    public ResponseData getFamilyUser(long userId) {
        try {
            return responseData.success().sign(userService.getFamilyUser(userId));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //判断是否可以绑定用户
    @PostMapping("/auth/checkBindUser")
    public ResponseData checkBindUser(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String phone = map.get("phone");
        if (StringUtils.isNullOrEmpty(phone)) {
            return responseData.error("手机号不能为空").sign(null);
        }
        try {
            userService.checkBindUser(userId, phone);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //绑定用户关系
    @PostMapping("/auth/bindUser")
    public ResponseData bindUser(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String phone = map.get("phone");
        String password = map.get("password");
        if (StringUtils.isNullOrEmpty(phone) || StringUtils.isNullOrEmpty(password)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            userService.bindUser(userId, phone, password);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //解除绑定用户关系
    @PostMapping("/auth/unBindUser")
    public ResponseData unBindUser(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userIdStr = map.get("userId");
        String typeStr = map.get("type");
        if (StringUtils.isNullOrEmpty(userIdStr) || StringUtils.isNullOrEmpty(typeStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            int type = Integer.parseInt(typeStr);
            long userId1 = Long.parseLong(userIdStr);
            if (type == 1) {
                userService.unBindUser(userId1, userId);
            } else {
                userService.unBindUser(userId, userId1);
            }
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

   //修改限额，限额类型，0=不限额，1=每日额度，2=每月额度
    @PostMapping("/auth/setFamilyLimit")
    public ResponseData setFamilyLimit(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userIdStr = map.get("userId");
        String typeStr = map.get("type");
        String amountStr = map.get("amount");
        if (StringUtils.isNullOrEmpty(userIdStr) ||
                StringUtils.isNullOrEmpty(typeStr) ||
                StringUtils.isNullOrEmpty(amountStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            int type = Integer.parseInt(typeStr);
            long userId1 = Long.parseLong(userIdStr);
            int amount = Integer.parseInt(amountStr);
            boolean isLimit = type == 0 ? true : false;
            //亲密付无限制默认10万额度
            if(isLimit==true){
                amount=100000;
            }
            userService.setFamilyLimit(userId, userId1, type, amount, isLimit);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //获取积分商场商品
    @PostMapping("/auth/getIntegralCommoditys")
    public ResponseData getIntegralCommoditys(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String pageIndexStr = map.get("pageIndex");//第几页
        if (StringUtils.isNullOrEmpty(pageIndexStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            int pageIndex = Integer.parseInt(pageIndexStr);
            int pageSize = 20;
            int pageStart = (pageIndex - 1) * pageSize;
            if (pageStart < 0) {
                pageStart = 0;
            }
            return responseData.success().sign(userService.getIntegralCommoditys(pageStart, pageSize));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //获取用户积分兑换记录
    @PostMapping("/auth/getIntegralexchanges")
    public ResponseData getIntegralexchanges(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String pageIndexStr = map.get("pageIndex");//第几页
        if (StringUtils.isNullOrEmpty(pageIndexStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            int pageIndex = Integer.parseInt(pageIndexStr);
            int pageSize = 20;
            int pageStart = (pageIndex - 1) * pageSize;
            if (pageStart < 0) {
                pageStart = 0;
            }
            return responseData.success().sign(userService.getUserIntegralexchanges(userId, pageStart, pageSize));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //获取用户最新的积分兑换记录
    @PostMapping("/auth/getNewIntegralexchanges")
    public ResponseData getIntegralexchanges(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String pageIndexStr = map.get("pageIndex");//第几页
        if (StringUtils.isNullOrEmpty(pageIndexStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            return responseData.success().sign(userService.getIntegralexchanges());
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //兑换积分
    @PostMapping("/auth/exchangeIntegral")
    public ResponseData exchangeIntegral(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String integralCommodityIdStr = map.get("integralCommodityId");
        if (StringUtils.isNullOrEmpty(integralCommodityIdStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            long integralCommodityId = Long.parseLong(integralCommodityIdStr);
            userService.exchangeIntegral(integralCommodityId, userId);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //获取抽奖数据
    @PostMapping("/auth/getPrizes")
    public ResponseData getPrizes(long userId) {
        try {
            return responseData.success().sign(userService.getPrizes(userId));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //用户抽奖
    @PostMapping("/auth/extractPrize")
    public ResponseData extractPrize(long userId) {
        try {
            return responseData.success().sign(userService.extractPrize(userId));
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //用户留言
    @PostMapping("/auth/leveMessage")
    public ResponseData leveMessage(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String messageStr = map.get("message").replaceAll("[^a-zA-Z0-9\u4e00-\u9fa5,.;*@!()+_，。！？；：/（）#&]","");//留言内容
        String message=messageStr.replaceAll("[\ue000-\uefff]","");
        if (StringUtils.isNullOrEmpty(message)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            userService.leaveMessage(userId, message);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

}
