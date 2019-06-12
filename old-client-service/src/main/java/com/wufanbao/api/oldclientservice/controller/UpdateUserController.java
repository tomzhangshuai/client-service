package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.BaseController;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.Tool.RandomNum;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import com.wufanbao.api.oldclientservice.service.UpdateUserInfoService;
import com.wufanbao.api.oldclientservice.service.UserLoginService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户基本信息修改
 *
 * @author Wang Zhiyuan
 */
@Controller
@RequestMapping(value = "UserUpdate", method = RequestMethod.POST)
public class UpdateUserController extends BaseController {
    @Autowired
    private UpdateUserInfoService service;
    @Autowired
    private UserLoginService UserLoginService;
    @Autowired
    private RedisUtils redisUtils;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UpdateUserController.class);


    /**
     * 完善用户信息
     *
     * @param sex       性别
     * @param birthday  生日
     * @param breakFast 早餐
     * @param lunch     午餐
     * @param dinner    晚餐
     * @param userId    用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @eitor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "updateUserSex", method = RequestMethod.POST)
    public Object updateUserSex(@RequestParam(value = "sex", required = false) Integer sex,
                                @RequestParam(value = "birthday", required = false) String birthday,
                                @RequestParam(value = "breakFast", required = false) String breakFast,
                                @RequestParam(value = "lunch", required = false) String lunch,
                                @RequestParam(value = "dinner", required = false) String dinner,
                                long userId) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        if (birthday != null && birthday != "") {
            try {
                date = sdf.parse(birthday);
            } catch (Exception e) {
                date = null;
            }

        } else {
            date = null;
        }
        if (sex == null) {
            sex = 0;
        }
        ResponseInfo responseInfo = service.updateUserSex(sex, date, formatTime(breakFast), formatTime(lunch), formatTime(dinner), userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 更新用户名
     *
     * @param userId   用户ID
     * @param userName 用户名
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "UpdateUserName", method = RequestMethod.POST)
    public Map<String, Object> updateUserName(long userId, String userName) {
        //VOUP
        UserRegistered vo = new UserRegistered();
        vo.setUserId(userId);
        vo.setUserName(userName);
        int count = service.updateUserName(vo);
        Map<String, Object> map = new HashMap<String, Object>();
        if (count > 0) {
            //成功返回状态1
            map.put("UpdateType", 1);
            return map;
        } else {
            //不成功返回状态0
            map.put("UpdateType", 0);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return map;
        }
    }

    /**
     * 验证新手机号是否被绑定，没有的话发送验证码
     *
     * @param newMb 新手机号
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getNewMbCheckCode", method = RequestMethod.POST)
    public Object getNewMbCheckCode(String newMb) throws IOException {
        UserRegistered uvo = new UserRegistered();
        uvo.setmB(newMb);
        UserRegistered uvo1 = new UserRegistered();
        Map map = new HashMap();
        try {
            //取数据库中查询有没有这个手机号
            uvo1 = UserLoginService.checkUserMb(newMb);
        } catch (RuntimeException e) {
            logger.info(e.toString());
            uvo1 = null;
        }
        if (uvo1 == null) {
            // 生成随机6位码
            RandomNum randomNum = new RandomNum();
            String code = randomNum.random(4);
            System.out.println("验证码:" + code);
            System.out.println(">>>>>>>>>>>><<<<<<" + newMb);
            String str = Long.toString(Long.parseLong(newMb), 12) + 3;
            System.out.println(str);
            try {
                String sss = redisUtils.get(str);
                if (Integer.parseInt(sss) < 3) {
                    if (redisUtils.get(str) != null) {
                        redisUtils.incr(str);
                        redisUtils.set(newMb, code);
                        redisUtils.expire(newMb, 300);
                        SMSInterface smsInterface = new SMSInterface();
                        Object status = smsInterface.SMS(newMb, code);
                        System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                        map.put("checkType", 1);
                        return map;
                    }
                } else if (Integer.parseInt(sss) == 3) {
                    Map map1 = new HashMap();
                    map1.put("checkType", 2);
                    return map1;
                }
            } catch (Exception e) {
                if (redisUtils.get(str) == null) {
                    redisUtils.incr(str);
                    redisUtils.expire(str, 300);
                    redisUtils.set(newMb, code);
                    redisUtils.expire(newMb, 300);
                    SMSInterface smsInterface = new SMSInterface();
                    Object status = smsInterface.SMS(newMb, code);
                    System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                    map.put("checkType", 1);
                    return map;
                }
                logger.info(e.toString());
            }
        } else {
            //手机号存在返回状态
            map.put("checkType", 0);
        }
        return map;
    }

    /**
     * 验证验证码是否正确
     *
     * @param mb   手机号
     * @param code 验证码
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @eidtor zhaojing
     */
    //    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "ConfirmModification", method = RequestMethod.POST)
    public Object confirmModification(String mb, String code) {
        String codes = redisUtils.get(mb);
        try {
            if (codes != null) {
                System.out.println(codes.equals(code));
                if (codes.equals(code)) {
                    redisUtils.del(mb);
                    //验证成功
                    Map map = new HashMap();
                    map.put("checkType", 1);
                    return map;
                } else {
                    //验证失败
                    Map map = new HashMap();
                    map.put("checkType", 0);
                    return map;
                }
            }
        } catch (Exception e) {
            logger.info(e.toString());
            throw new RuntimeException();
        }
        Map map = new HashMap();
        map.put("checkType", 0);
        return map;
    }

    /**
     * 更改用户手机号获取原手机号码验证码
     *
     * @param mb 用户id
     * @return 更新结果Map
     * @eidtor zhaojing
     */
//  @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getMbCheckCode", method = RequestMethod.POST)
    public Object getMbCheckCode(String mb) throws IOException {
        UserRegistered userRegistered = new UserRegistered();
        try {
            userRegistered = UserLoginService.checkUserMb(mb);
            System.out.println(userRegistered.getmB());
        } catch (RuntimeException e) {
            userRegistered = null;
        }
        if (userRegistered == null) {
            Map map = new HashMap();
            map.put("succeed", 0);
            return map;
        } else {
            RandomNum randomNum = new RandomNum();
            String code = randomNum.random(4);
            System.out.println("验证码:" + code);
            System.out.println(">>>>>>>>>>>><<<<<<" + mb);
            String str = Long.toString(Long.parseLong(mb), 12) + 2;
            System.out.println(str);
            try {
                String sss = redisUtils.get(str);
                if (Integer.parseInt(sss) < 3) {
                    if (redisUtils.get(str) != null) {
                        redisUtils.incr(str);
                        redisUtils.set(mb, code);
                        redisUtils.expire(mb, 300);
                        SMSInterface smsInterface = new SMSInterface();
                        Object status = smsInterface.SMS(mb, code);
                        System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                        Map map = new HashMap();
                        map.put("succeed", 1);
                        return map;
                    }
                } else if (Integer.parseInt(sss) == 3) {
                    Map map1 = new HashMap();
                    map1.put("succeed", 2);
                    return map1;
                }
            } catch (Exception e) {
                if (redisUtils.get(str) == null) {
                    redisUtils.incr(str);
                    redisUtils.expire(str, 300);
                    redisUtils.set(mb, code);
                    redisUtils.expire(mb, 300);
                    SMSInterface smsInterface = new SMSInterface();
                    Object status = smsInterface.SMS(mb, code);
                    System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                    Map map = new HashMap();
                    map.put("succeed", 1);
                    return map;
                }
                logger.info(e.toString());
            }
        }
        Map map = new HashMap();
        map.put("succeed", 1);
        return map;
    }

    /**
     * 验证新手机号验证码,验证码是否正确
     *
     * @param newMb  新手机号
     * @param code   验证码
     * @param userId 用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "checkNewMbcode", method = RequestMethod.POST)
    public Object checkNewMbcode(String newMb, String code, long userId) {
        try {
            String codes = redisUtils.get(newMb);
            if (code != null) {
                System.out.println(codes.equals(code));
                if (codes.equals(code)) {
                    redisUtils.del(newMb);
                    //验证成功
                    UserRegistered userVo = new UserRegistered();
                    userVo.setmB(newMb);
                    userVo.setUserId(userId);
                    userVo.setLoginNo(newMb);
                    int count = service.updateUserMb(userVo);
                    if (count > 0) {
                        Map map = new HashMap();
                        map.put("checkType", 1);
                        return map;
                    } else {
                        Map map = new HashMap();
                        map.put("checkType", 2);
                        return map;
                    }
                } else {
                    //验证失败
                    Map map = new HashMap();
                    map.put("checkType", 0);
                    return map;
                }
            }
        } catch (Exception e) {
            logger.info(e.toString());
        }
        Map map = new HashMap();
        map.put("checkType", 0);
        return map;
    }

    /**
     * 修改支付密码
     *
     * @param userId      用户id
     * @param payPassword 支付密码
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "updatePayPassword", method = RequestMethod.POST)
    public Object updatePayPassword(long userId, String payPassword) {
        //VOUP
        UserRegistered vo = new UserRegistered();
        vo.setUserId(userId);
        vo.setPayPassWord(payPassword);
        int count = service.updateUserPayPassword(vo);
        String str = Long.toString(userId, 16) + 4;
        try {
            if (count > 0) {
                redisUtils.del(str);
                Map map = new HashMap();
                map.put("updateType", 1);
                return map;
            } else {
                Map map = new HashMap();
                map.put("updateType", 0);
                return map;
            }
        } catch (Exception e) {
        }
        Map map = new HashMap();
        map.put("updateType", 0);
        return map;
    }

    /**
     * 修改登录密码
     *
     * @param userId   用户id
     * @param password 登录密码
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public Object updatePassword(long userId, String password) {
        //VOUP
        UserRegistered vo = new UserRegistered();
        vo.setUserId(userId);
        vo.setPassWord(password);
        int count = service.updateUserPassword(vo);
        System.out.println(count);
        if (count > 0) {
            Map map = new HashMap();
            map.put("updateType", 1);
            return map;
        } else {
            Map map = new HashMap();
            map.put("updateType", 0);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return map;
        }
    }

    /**
     * 用户积分记录
     *
     * @param userId 用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getUserIntegralLog", method = RequestMethod.POST)
    public Object getUserIntegralLog(long userId) {
        ResponseInfo responseInfo = service.getUserIntegralLog(userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 根据手机号修改密码
     *
     * @param mB       手机号
     * @param passWord 密码
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
//    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "updateUserPasswordByMb", method = RequestMethod.POST)
    public Map updateUserPasswordByMb(String mB, String passWord) {
        Map map = new HashMap();
        int count = service.updateUserPasswordByMb(mB, passWord);
        if (count > 0) {
            map.put("updateType", 1);
        } else {
            map.put("updateType", 0);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    /**
     * 查看用户信息是否完善
     *
     * @param userId 用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "perfectDegree", method = RequestMethod.POST)
    public Object perfectDegree(long userId) {
        ResponseInfo responseInfo = service.perfectDegree(userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }


    /**
     * 格式化时间
     *
     * @param date 时间
     * @return java.util.Date
     * @author Wang Zhiyuan
     */
    public Date formatTime(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("2018-01-01 ");
        sb.append(date);
        Date date1;
        try {
            date1 = sdf.parse(sb.toString());
        } catch (Exception e) {
            date1 = null;
        }
        return date1;
    }


}

