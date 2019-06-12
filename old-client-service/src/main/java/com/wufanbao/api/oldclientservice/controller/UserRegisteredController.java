package com.wufanbao.api.oldclientservice.controller;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.oldclientservice.Tool.*;
import com.wufanbao.api.oldclientservice.dao.UserCouponDao;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.UserRegisteredService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * User:Wangshihao
 * Date:2017-08-01
 * Time:11:14
 */


@Controller
@RequestMapping("userregistered")

public class UserRegisteredController extends BaseController {
    @Autowired
    private UserRegisteredService userRegisteredService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserCouponDao userCouponDao;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserRegisteredController.class);


    /**
     * 判断是否错误3次
     *
     * @param request
     * @return
     * @editor zhaojing
     */
    @RequestMapping(value = "payPasswordError", method = RequestMethod.POST)
    @ResponseBody
    public Object payPasswordError(HttpServletRequest request) {
        long userId = Long.parseLong(request.getParameter("userId"));
        String str = Long.toString(userId, 16) + 4;
        Map map = new HashMap();
        try {
            if (redisUtils.get(str) == null) {
                map.put("data", 0);
                map.put("status", 0);
                return map;
            } else if (redisUtils.get(str) != null) {
                map.put("data", redisUtils.get(str));
                map.put("status", 0);
                return map;
            }
        } catch (RuntimeException e) {
            logger.info(e.toString());
        }
        map.put("status", 1);
        return map;
    }

    /**
     * 注册验证码
     *
     * @param request
     * @return
     * @throws IOException
     * @editor zhaojing
     */
    @RequestMapping(value = "verification", method = RequestMethod.POST)
    @ResponseBody
    public Object Verification(HttpServletRequest request) throws IOException {
        String mB = request.getParameter("mB");
        UserRegistered userRegistered = userRegisteredService.check(mB);
        if (userRegistered == null) {
            RandomNum randomNum = new RandomNum();
            String code = randomNum.random(4);
            System.out.println("验证码:" + code);
            System.out.println(">>>>>>>>>>>><<<<<<" + mB);
            String str = Long.toString(Long.parseLong(mB), 12) + 1;
            System.out.println(str);
            try {
                String sss = redisUtils.get(str);
                if (Integer.parseInt(sss) < 3) {
                    if (redisUtils.get(str) != null) {
                        redisUtils.incr(str);
                        redisUtils.set(mB, code);
                        redisUtils.expire(mB, 300);
                        SMSInterface smsInterface = new SMSInterface();
                        Object status = smsInterface.SMS(mB, code);
                        System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                        Map map = new HashMap();
                        map.put("returncode", "success");
                        return map;
                    }
                } else if (Integer.parseInt(sss) == 3) {
                    Map map1 = new HashMap();
                    map1.put("returncode", "2");
                    return map1;
                }
            } catch (Exception e) {
                if (redisUtils.get(str) == null) {
                    redisUtils.incr(str);
                    redisUtils.expire(str, 300);
                    redisUtils.set(mB, code);
                    redisUtils.expire(mB, 300);
                    SMSInterface smsInterface = new SMSInterface();
                    Object status = smsInterface.SMS(mB, code);
                    System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                    Map map = new HashMap();
                    map.put("returncode", "success");
                    return map;
                }
                logger.info(e.toString());
            }
        }
        Map map1 = new HashMap();
        map1.put("returncode", "error");
        return map1;
    }

    /**
     * 验证验证吗
     *
     * @param request
     * @return
     * @editor zhaojing
     */
    @RequestMapping(value = "compare", method = RequestMethod.POST)
    @ResponseBody
    public Object compare(HttpServletRequest request) {
        String mB = request.getParameter("mB");
        String code = request.getParameter("code");

        try {
            String s = redisUtils.get(mB);
            if (s != null) {
                System.out.println(s.equals(code));
                if (s.equals(code)) {
                    redisUtils.del(mB);
                    Map map = new HashMap();
                    map.put("su", "trun");
                    return map;
                }
            }
        } catch (RuntimeException e) {
            logger.info(e.toString());
        }
        Map map1 = new HashMap();
        map1.put("error", "error");
        return map1;
    }

    /**
     * 注册
     *
     * @param request
     * @return
     * @editor zhaojing
     */
    @RequestMapping(value = "useradd", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HttpServletRequest request) {
        String mB = request.getParameter("mB");
        String passWord = request.getParameter("passWord");
        long userId = IDGenerator.generate("User", 0);
        Map map = new HashMap();
        UserRegistered userRegistered = new UserRegistered();
        userRegistered.setUserId(userId);
        userRegistered.setParentUserId(0);
        userRegistered.setUserName(mB);
        userRegistered.setLoginNo(mB);
        userRegistered.setPassWord(passWord);
        userRegistered.setPayPassWord("");
        userRegistered.setUserType(1);
        userRegistered.setmB(mB);
        userRegistered.setEmail(" ");
        userRegistered.setIntegral(0);
        userRegistered.setBalance(0);
        userRegistered.setUsableBalance(0);
        userRegistered.setLockBalance(0);
        //根据id生成邀请码
        String invitationCode = Long.toHexString(userId);
        //邀请码
        userRegistered.setInvitationCode(invitationCode);
        userRegistered.setIsActive(1);
        userRegistered.setResetPasswordNeeded(0);
        userRegistered.setIsAuth(1);
        userRegistered.setIsCredit(0);
        userRegistered.setCreditAmount(0);
        userRegistered.setCreditUasbelAmount(0);
        userRegistered.setCreditLimit(0);
        userRegistered.setPortrait(" ");
        userRegistered.setRemark(" ");
        //十块钱的优惠券
        //int count1=Coupon(233838379010L,userId);
        //五块钱的优惠券
        //int count2=Coupon(233838379009L,userId);
        //int count3=Coupon(233838379009L,userId);
        //int count4=count1+count2+count3;
//        if (count4==6){
//            userRegisteredService.add(userRegistered);
//            map.put("成功", "success");
//        }else{
//            map.put("失败","error");
//        }
        userRegisteredService.add(userRegistered);
        map.put("成功", "success");
        map.put("success", "success");
        return map;
    }


    public int Coupon(long couponDefinitionId, long userId) {
        UserCouponInfo userCouponInfo = userCouponDao.selectCouponInfo(couponDefinitionId);
        ValidityRule validityRules = JSON.parseObject(userCouponInfo.getValidityRule(), ValidityRule.class);
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUserId(userId);
        userCoupon.setCouponDefinitionId(couponDefinitionId);
        long couponId = IDGenerator.generateById("couponId", userId);
        userCoupon.setCouponId(couponId);
        userCoupon.setCreateTime(new Date());
        userCoupon.setStartTime(new Date());
        userCoupon.setStatus(1);
        userCoupon.setEndTime(getCouponEndTime(validityRules.getActiveTime()));
        int count = userCouponDao.insertUserCoupon(userCoupon);
        int count1 = userCouponDao.gotCoupon(couponDefinitionId);
        int count2 = count + count1;
        return count2;
    }

    /**
     * 获取优惠券结束时间
     *
     * @param actieTime
     * @return
     */
    public Date getCouponEndTime(int actieTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //+1今天的时间加一天
        calendar.add(Calendar.DAY_OF_MONTH, +actieTime);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 发送验证码
     *
     * @param request
     * @return
     */
    @RequestValidationCode
    @RequestMapping(value = "sweepCode", method = RequestMethod.POST)
    @ResponseBody
    public Object sweepCode(HttpServletRequest request) throws UnsupportedEncodingException {
        String mB = request.getParameter("mB");
        UserRegistered userRegistered = userRegisteredService.check(mB);
        if (userRegistered == null) {
            RandomNum randomNum = new RandomNum();
            String code = randomNum.random(4);
            System.out.println("验证码:" + code);
            System.out.println(">>>>>>>>>>>><<<<<<1234412523" + mB);
            //手机号的十六进制
            String str = Long.toString(Long.parseLong(mB), 12) + 1;
            System.out.println(str);
            try {
                String sss = redisUtils.get(str);
                System.out.println(sss + "fdsafdsafds");
                if (Integer.parseInt(sss) < 3) {
                    if (redisUtils.get(str) != null) {
                        redisUtils.incr(str);
                        redisUtils.set(mB, code);
                        redisUtils.expire(mB, 300);
                        SMSInterface smsInterface = new SMSInterface();
                        Object status = smsInterface.SMS(mB, code);
                        System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                        Map map = new HashMap();
                        map.put("status", 0);
                        return map;
                    }
                } else if (Integer.parseInt(sss) >= 3) {
                    Map map1 = new HashMap();
                    map1.put("status", 2);
                    return map1;
                }
            } catch (Exception e) {
                if (redisUtils.get(str) == null) {
                    redisUtils.incr(str);
                    redisUtils.expire(str, 300);
                    redisUtils.set(mB, code);
                    redisUtils.expire(mB, 300);
                    SMSInterface smsInterface = new SMSInterface();
                    Object status = smsInterface.SMS(mB, code);
                    System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                    Map map = new HashMap();
                    map.put("status", 0);
                    return map;
                }
                logger.info(e.toString());
            }
        }
        Map map1 = new HashMap();
        map1.put("status", 1);
        return map1;
    }

    /**
     * 根据手机号添加用户
     *
     * @param mb 用户
     * @return Object
     */
    @ResponseBody
    @RequestMapping(value = "adduser", method = RequestMethod.POST)
    public Object addUser(String mb, long userId) {
        ResponseInfo responseInfo = userRegisteredService.addUser(mb, "", userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 验证验证码
     */
    @RequestMapping(value = "verifyCode", method = RequestMethod.POST)
    @ResponseBody
    public Object verifyCode(HttpServletRequest request) {
        String mB = request.getParameter("mB");
        String code = request.getParameter("code");
        try {
            String s = redisUtils.get(mB);
            if (s != null) {
                System.out.println(s.equals(code));
                if (s.equals(code)) {
                    Map map = new HashMap();
                    map.put("status", 0);
                    return map;
                }
            }
        } catch (Exception e) {
            logger.info(e.toString());
        }
        Map map1 = new HashMap();
        map1.put("status", 1);
        return map1;
    }

}
