package com.wufanbao.api.oldclientservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.Tool.Privilege;
import com.wufanbao.api.oldclientservice.Tool.RandomNum;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.controller.SMSInterface;
import com.wufanbao.api.oldclientservice.controller.WXPay.MD5Util;
import com.wufanbao.api.oldclientservice.dao.*;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.IOException;
import java.util.*;


/**
 * 用户登录
 *
 * @author Wang Zhiyuan
 * @date 2018/4/9
 */
@Transactional
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginDao userLoginDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private InsertInvitationCodeDao insertInvitationCodeDao;
    @Autowired
    private GetUserInfoDao getUserInfoDao;
    @Autowired
    private UserRegisteredDao registeredDao;
    @Autowired
    private UserGradeDao userGradeDao;
    @Autowired
    private SignInDao signInDao;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ClientSetting clientSetting;

    /**
     * 根据手机号密码登录
     *
     * @param loginNo        手机号码
     * @param password       密码
     * @param registrationId 推送ID
     * @return java.util.Map
     * @author Wang Zhiyuan
     */
    @Override
    public Map getUserLogin(String loginNo, String password, String registrationId) {
        //用户优惠券数量
        UserRegistered vo1 = new UserRegistered();
        try {
            //根据用户手机号和密码去查询用户信息
            vo1 = userLoginDao.getUserLogin(loginNo, password);
        } catch (Exception e) {
            vo1 = null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        //判断用户账号密码是否正确
        if (vo1 == null) {
            //错误
            map.put("LoginType", 0);
            return map;
        } else {
            if (vo1.getIsActive() == 0) {
                map.put("LoginType", 2);
                return map;
            } else {
                System.out.println(vo1.getIsActive());
                //正确的时候
                List<UserCoupon> userCouponList = userCouponDao.userCouponDaoRow(vo1.getUserId());
                int UserCouponDaoRow = 0;
                if (userCouponList.size() < 0) {
                    UserCouponDaoRow = 0;
                } else {
                    UserCouponDaoRow = userCouponList.size();
                }
                //判读支付密码是否设置
                if (vo1.getPayPassWord() == null || vo1.getPayPassWord().equals("")) {
                    //0没有设置
                    map.put("payPasswordType", 0);
                } else {
                    //1已经设置
                    map.put("payPasswordType", 1);
                }
                List<UserInvitation> userInvitationList = insertInvitationCodeDao.getUserInvitation(vo1.getUserId());
                if (userInvitationList.size() > 0) {
                    //邀请码已经填写
                    map.put("invitationCodeType", 1);
                } else {
                    //邀请码没有填写
                    map.put("invitationCodeType", 0);
                }
                //判断银行卡有没有填写
                List<UserBank> userBankList = getUserInfoDao.getUserBankInfo(vo1.getUserId());
                if (userBankList.size() > 0) {
                    map.put("userBankType", 1);
                } else {
                    map.put("userBankType", 0);
                }
                boolean ifSignIn = signInDao.ifSignIn(vo1.getUserId());
                //个人的验证码
                map.put("ifSignIn", ifSignIn);
                map.put("invitationCode", vo1.getInvitationCode());
                map.put("usableBalance", vo1.getUsableBalance());
                map.put("integral", vo1.getIntegral());
                map.put("mb", vo1.getmB());
                map.put("sex", vo1.getSex());
                map.put("birthday", vo1.getBirthday());
                map.put("breakfast", vo1.getBreakFast());
                map.put("lunch", vo1.getLunch());
                map.put("dinner", vo1.getDinner());
                String userId = String.valueOf(vo1.getUserId());
                String time = String.valueOf(System.currentTimeMillis());
                String encode = userId + clientSetting.getTokenKey() + time;
                String md5 = MD5Util.MD5Encode(encode, "UTF-8");
                redisUtils.set(md5, userId);
                map.put("token", md5);
                map.put("UserCouponDaoRow", UserCouponDaoRow);
                String portrait = "";
                if (vo1.getPortrait() != null && !("").equals(vo1.getPortrait())) {
                    portrait = clientSetting.getResource() + vo1.getPortrait();
                } else {
                    portrait = "";
                }
                //用户头像
                map.put("portrait", portrait);
                map.put("UserName", vo1.getUserName());
                map.put("gradeName", userGradeDao.getUserGradeName(vo1.getGradeValue()));
                redisUtils.set(String.valueOf(vo1.getUserId()) + ",1", registrationId);
                //正确时状态：1
                map.put("LoginType", 1);
                map.put("UserId", vo1.getUserId());
            }
            return map;
        }
    }

    /**
     * 给手机发验证码
     *
     * @param Mb 手机号
     * @return
     */
    @Override
    public Map sendMessage(String Mb) throws IOException {
        String key = Long.toString(Long.parseLong(Mb), 12) + 6;
        long count = redisUtils.incr(key);
        if (count >= 1) {
            redisUtils.expire(key, 300);
        }
        if (count > 3) {
            Map map1 = new HashMap();
            map1.put("succeed", 2);
            return map1;
        }

        RandomNum randomNum = new RandomNum();
        String code = randomNum.random(4);
        SMSInterface smsInterface = new SMSInterface();
        smsInterface.SMS(Mb, code);

        redisUtils.set(Mb, code);
        redisUtils.expire(Mb, 300);

        Map map = new HashMap();
        map.put("succeed", 1);
        return map;
    }
    /*@Override
    public Map checkMb(String Mb)  throws IOException {
        Jedis jedis = jedisPool.getResource();
        UserRegistered uvo=new UserRegistered();
        uvo.setmB(Mb);
        Map map = new HashMap();
        RandomNum randomNum = new RandomNum();
        String code = randomNum.random(4);
        System.out.println("验证码:"+code);
        System.out.println(">>>>>>>>>>>><<<<<<"+Mb);

        String str = Long.toString(Long.parseLong(Mb),12)+6;
        System.out.println(str);
        try {
            String sss = jedis.get(str);
            if (Integer.parseInt(sss) < 3) {
                if (jedis.get(str) != null) {
                    jedis.incr(str);
                    jedis.set(Mb,code);
                    jedis.expire(Mb,300);
                    SMSInterface smsInterface = new SMSInterface();
                    Object status = smsInterface.SMS(Mb,code);
                    System.out.println(">>>>>>>>>>>>>>>>>>状态"+status);
                    map.put("succeed",1);
                    return map;
                }
            } else if (Integer.parseInt(sss) == 3) {
                Map map1 = new HashMap();
                map1.put("succeed",2);
                return map1;
            }
        }catch (Exception e) {
            if (jedis.get(str) == null) {
                jedis.incr(str);
                jedis.expire(str, 300);
                jedis.set(Mb,code);
                jedis.expire(Mb,300);
                SMSInterface smsInterface = new SMSInterface();
                Object status = smsInterface.SMS(Mb,code);
                System.out.println(">>>>>>>>>>>>>>>>>>状态"+status);
                map.put("succeed",1);
                return map;
            }
        }finally {
            if (null!=jedis){
                jedis.close();
            }
        }
        map.put("succeed",1);
        return map;
    }*/

    /**
     * 快速登录
     *
     * @param Mb   手机号
     * @param code 验证码
     * @return
     */
    @Override
    public Map quickLogin(String Mb, String code, String registrationId, String openId) {
        Map map = new HashMap();
        long userId = IDGenerator.generate("User", 0);
        try {
            String i = redisUtils.get(Mb);
            String s = code;
            if (i != null) {
                System.out.println(code + "验证码" + i);
                System.out.println(i == s);
                if (i.equals(s)) {
                    redisUtils.del(Mb);
                    UserRegistered uvo3 = new UserRegistered();
                    try {
                        uvo3 = userLoginDao.checkUserMb(Mb);
                    } catch (Exception e) {
                        uvo3 = null;
                    }
                    if (uvo3 == null) {
                        UserRegistered userRegistered = new UserRegistered();
                        userRegistered.setUserId(userId);
                        userRegistered.setParentUserId(0);
                        userRegistered.setUserName(Mb);
                        userRegistered.setLoginNo(Mb);
                        userRegistered.setPassWord("");
                        userRegistered.setPayPassWord("");
                        userRegistered.setUserType(1);
                        userRegistered.setmB(Mb);
                        userRegistered.setEmail("");
                        userRegistered.setIntegral(0);
                        userRegistered.setBalance(0);
                        userRegistered.setUsableBalance(0);
                        userRegistered.setLockBalance(0);
                        //根据id生成邀请码
                        String invitationCode = Long.toHexString(userId);
                        //邀请码
                        System.out.println(invitationCode + "邀请码生成");
                        userRegistered.setInvitationCode(invitationCode);
                        userRegistered.setIsActive(1);
                        userRegistered.setResetPasswordNeeded(0);
                        userRegistered.setIsAuth(1);
                        userRegistered.setIsCredit(0);
                        userRegistered.setCreditAmount(0);
                        userRegistered.setCreditUasbelAmount(0);
                        userRegistered.setCreditLimit(0);
                        userRegistered.setPortrait("");
                        userRegistered.setRemark("");
                        userRegistered.setCompanyPayment(false);
                        userRegistered.setSex(0);
//                        十块钱的优惠券
                        //int count1=Coupon(233838379010L,userId);
//                        五块钱的优惠券
                        //int count2=Coupon(233838379009L,userId);
                        //int count3=Coupon(233838379009L,userId);
                        //int count4=count1+count2+count3;
//                        if (count1==6){
//                            map.put("成功", "success");
//                        }else{
//                            map.put("失败","error");
//                        }
                        map.put("成功", "success");
                        try {
                            registeredDao.add(userRegistered);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //跟据用户手机号去查询用户信息
                    UserRegistered uvo = userLoginDao.checkUserMb(Mb);
                    try {
                        if (openId != null) {
                            Long bindingUserId = userLoginDao.getUserBinding(openId);
                            if (bindingUserId == null) {
                                int count = userLoginDao.userBinding(uvo.getUserId(), openId, 1);
                            } else if (uvo.getUserId() != bindingUserId) {
                                int count = userLoginDao.updateBinDingUserId(openId, uvo.getUserId());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println(uvo.getIsActive() + "asdfafd");
                    //判断用户是否被禁用
                    if (uvo.getIsActive() == 0) {
                        map.put("LoginType", 2);
                        return map;
                    } else {
                        List<UserCoupon> userCouponList = userCouponDao.userCouponDaoRow(uvo.getUserId());
                        int UserCouponDaoRow = 0;
                        if (userCouponList.size() < 0) {
                            UserCouponDaoRow = 0;
                        } else {
                            UserCouponDaoRow = userCouponList.size();
                        }
                        //判读支付密码是否设置
                        if (uvo.getPayPassWord() == null || uvo.getPayPassWord().equals(" ")) {
                            //0没有设置
                            map.put("payPasswordType", 0);
                        } else {
                            //1已经设置
                            map.put("payPasswordType", 1);
                        }
                        List<UserInvitation> userInvitationList = insertInvitationCodeDao.getUserInvitation(uvo.getUserId());
                        if (userInvitationList.size() > 0) {
                            //邀请码已经填写
                            map.put("invitationCodeType", 1);
                        } else {
                            //邀请码没有填写
                            map.put("invitationCodeType", 0);
                        }
                        //判断银行卡有没有填写
                        List<UserBank> userBankList = getUserInfoDao.getUserBankInfo(uvo.getUserId());
                        if (userBankList.size() > 0) {
                            map.put("userBankType", 1);
                        } else {
                            map.put("userBankType", 0);
                        }
                        //个人的验证码

                        map.put("invitationCode", uvo.getInvitationCode());
                        map.put("usableBalance", uvo.getUsableBalance());
                        map.put("integral", uvo.getIntegral());
                        map.put("mb", uvo.getmB());
                        map.put("sex", uvo.getSex());
                        map.put("birthday", uvo.getBirthday());
                        map.put("breakfast", uvo.getBreakFast());
                        map.put("lunch", uvo.getLunch());
                        map.put("dinner", uvo.getDinner());
                        map.put("UserCouponDaoRow", UserCouponDaoRow);
                        String userId1 = String.valueOf(uvo.getUserId());
                        String time = String.valueOf(System.currentTimeMillis());
                        String encode = userId + clientSetting.getTokenKey() + time;
                        String md5 = MD5Util.MD5Encode(encode, "UTF-8");
                        redisUtils.set(md5, userId1);
                        map.put("token", md5);

                        String portrait = "";
                        if (uvo.getPortrait() != null && !("").equals(uvo.getPortrait())) {
                            portrait = clientSetting.getResource() + uvo.getPortrait();
                        } else {
                            portrait = "";
                        }
                        //用户头像
                        map.put("portrait", portrait);
                        map.put("UserName", uvo.getUserName());
                        map.put("gradeName", userGradeDao.getUserGradeName(uvo.getGradeValue()));
                        redisUtils.set(userId + ",1", registrationId);
                        //正确时状态：1
                        map.put("LoginType", 1);
                        map.put("UserId", uvo.getUserId());
                        return map;
                    }
                } else {
                    map.put("LoginType", 0);
                    return map;
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        map.put("LoginType", 0);
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
        calendar.add(Calendar.DAY_OF_MONTH, +actieTime);//+1今天的时间加一天
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = calendar.getTime();
        return date;
    }


    @Override
    public UserRegistered checkUserMb(String mB) {
        return userLoginDao.checkUserMb(mB);
    }

    @Override
    public ResponseInfo userGradeInfo(long userId) {
        //用户等级信息
        Map map = new HashMap();
        ResponseInfo responseInfo = new ResponseInfo();
        UserGrade userGrade = userGradeDao.getUserGrade(userId);
        if (userGrade != null) {
            //用户高一级信息
            UserGrade userGrade1 = new UserGrade();
            if (userGrade.getGradeNum() < 5) {
                userGrade1 = userGradeDao.getUserGradePlusOne(userGrade.getGradeNum() + 1);
                map.put("gradeNamePlusOne", userGrade1.getGradeName());
                map.put("difference", userGrade1.getStartValue() - userGrade.getGradeValue());
            } else {
                map.put("gradeNamePlusOne", "无");
                map.put("difference", 0);
            }

            //用户排名
            double userRanking = userGradeDao.userRanking(userId);
            //用户的特权
            double ss = userGrade.getGradeValue() - userGrade.getStartValue() - 1;
            double sss = userGrade.getEndValue() - userGrade.getStartValue();
            double ssss = ss / sss;
            map.put("gradeName", userGrade.getGradeName());
            map.put("gradeValue", userGrade.getGradeValue());
            map.put("percent", ssss);
            map.put("userRanking", userRanking);
            map.put("userGradePrivilege", getCodeValue(userGrade.getPrivilege()));
            map.put("regulation", regulation());
            responseInfo.setData(map);
            responseInfo.setCode(1);
            return responseInfo;
        } else {
            //此用户不存在
            responseInfo.setCode(1031);
            return responseInfo;
        }
    }

    /**
     * 通过 bindingId登录
     *
     * @param bindingId 用户绑定ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/4/17
     */
    @Override
    public ResponseInfo loginByBindingId(String bindingId) {
        Long userId = userLoginDao.getUserIdByBindingId(bindingId);
        Map map = new HashMap();
        ResponseInfo responseInfo = new ResponseInfo();
        String time = String.valueOf(System.currentTimeMillis());

        if (userId != null) {
            map.put("userId", userId);
            //生成token
            String encode = userId + clientSetting.getTokenKey() + time;
            String md5 = MD5Util.MD5Encode(encode, "UTF-8");
            map.put("token", md5);
            redisUtils.set(md5, userId.toString());
            responseInfo.setCode(1);
            responseInfo.setData(map);
            return responseInfo;
        }
        long userId2 = IDGenerator.generate("User", 0);
        UserRegistered userRegistered = UserRegistered.registered(userId2, "", "", "", "", 1);
        registeredDao.add(userRegistered);
        UserRegistered user = getUserInfoDao.getUserInfo(userId2);
        int count = userLoginDao.userBinding(user.getUserId(), bindingId, 1);
        userId = user.getUserId();
        map.put("userId", user.getUserId());
        //生成token
        String encode = userId + clientSetting.getTokenKey() + time;
        String md5 = MD5Util.MD5Encode(encode, "UTF-8");
        map.put("token", md5);
        redisUtils.set(md5, userId.toString());
        responseInfo.setData(map);
        responseInfo.setCode(1);
        return responseInfo;
    }

    /**
     * 第三方登录
     *
     * @param bindingId   绑定ID
     * @param bindingType 绑定ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/5/14
     */
    @Override
    public ResponseInfo thirdPartyLogin(String bindingId, String bindingType) {
        Long userId = userLoginDao.getUserIdByBindingId(bindingId);
        Map map = new HashMap();
        ResponseInfo responseInfo = new ResponseInfo();
        String time = String.valueOf(System.currentTimeMillis());
        if (userId != null) {
            User user = userLoginDao.getUserByUserId(userId);
            if (user.getLoginNo().length() <= 0 || user.getLoginNo() == "") {
                map.put("bindingStatus", "0");
                map.put("userId", userId);
                responseInfo.setData(map);
                responseInfo.setCode(1);
                return responseInfo;
            }

            map.put("userId", userId);
            //生成token
            String encode = userId + clientSetting.getTokenKey() + time;
            String md5 = MD5Util.MD5Encode(encode, "UTF-8");
            map.put("token", md5);
            map.put("bindingStatus", "1");
            redisUtils.set(md5, userId.toString());
            responseInfo.setCode(1);
            responseInfo.setData(map);
            return responseInfo;
        }
        map.put("bindingStatus", "0");
        responseInfo.setData(map);
        responseInfo.setCode(1);
        return responseInfo;
    }

    /**
     * 绑定手机号
     *
     * @param bindingId   绑定ID
     * @param bindingType 绑定类型
     * @param mb          手机号
     * @param code        验证码
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/5/14
     */
    @Override
    public ResponseInfo thirdPartyLoginBindingMb(String bindingId, int bindingType, String mb, String code, long userId) {
        ResponseInfo responseInfo = new ResponseInfo();
        String i = redisUtils.get(mb);
        if (!code.equals(i)) {
            responseInfo.setCode(2035);
            return responseInfo;
        }
        redisUtils.del(mb);
        Map map = new HashMap();
        String time = String.valueOf(System.currentTimeMillis());
        Long id = getUserInfoDao.getUserIdByMb(mb);//判断改手机号是否注册过
        if (id == null) {//没有注册过
            if (userId <= 0) {
                //用户ID
                long userId2 = IDGenerator.generate("User", 0);
                UserRegistered userRegistered = UserRegistered.registered(userId2, mb, "", "", "", 1);
                registeredDao.add(userRegistered);
                UserRegistered user = getUserInfoDao.getUserInfo(userId2);
                //用户绑定
                int count = userLoginDao.userBinding(user.getUserId(), bindingId, bindingType);
                if (count < 1) {
                    responseInfo.setCode(2036);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return responseInfo;
                }
                map.put("userId", user.getUserId());
            } else {
                userLoginDao.updateUserByUserId(mb, userId);
            }
        } else {
            //用户绑定
            Map userBindingMap = userLoginDao.getUserBindingInfo(id, bindingType);
            int count = 0;
            if (userBindingMap == null) {
                count = userLoginDao.userBinding(id, bindingId, bindingType);
            } else {
                count = userLoginDao.updateUserBinding(id, bindingType, bindingId);
            }
            if (count < 1) {
                responseInfo.setCode(2036);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return responseInfo;
            } else {
                map.put("userId", id);
            }
        }
        //生成token
        String encode = id + clientSetting.getTokenKey() + time;
        String md5 = MD5Util.MD5Encode(encode, "UTF-8");
        map.put("token", md5);
        redisUtils.set(md5, id.toString());
        responseInfo.setData(map);
        responseInfo.setCode(1);
        return responseInfo;
    }

    /**
     * 绑定用户发送验证码
     *
     * @param
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @date 2018/5/15
     */
    /*@Override
    public Map sendCodeBinding(String mb) throws UnsupportedEncodingException{
        Jedis jedis = jedisPool.getResource();
        UserRegistered uvo=new UserRegistered();
        uvo.setmB(mb);
        Map map = new HashMap();
        RandomNum randomNum = new RandomNum();
        String code = randomNum.random(4);
        String str = Long.toString(Long.parseLong(mb),12)+6;
        try {
            String sss = jedis.get(str);
            if (Integer.parseInt(sss) < 3) {
                if (jedis.get(str) != null) {
                    jedis.incr(str);
                    jedis.set(mb,code);
                    jedis.expire(mb,300);
                    SMSInterface smsInterface = new SMSInterface();
                    Object status = smsInterface.SMS(mb,code);
                    map.put("succeed",1);
                    return map;
                }
            } else if (Integer.parseInt(sss) == 3) {
                Map map1 = new HashMap();
                map1.put("succeed",2);
                return map1;
            }
        }catch (Exception e) {
            if (jedis.get(str) == null) {
                jedis.incr(str);
                jedis.expire(str, 300);
                jedis.set(mb,code);
                jedis.expire(mb,300);
                SMSInterface smsInterface = new SMSInterface();
                Object status = smsInterface.SMS(mb,code);
                map.put("succeed",1);
                return map;
            }
        }finally {
            if (null!=jedis){
                jedis.close();
            }
        }

        return map;
    }*/
    public List getCodeValue(int privilegeValue) {
        Map map = new HashMap();
        for (Privilege privilege : Privilege.values()
                ) {
            map.put(privilege.getPrivilege(), privilege.getPrivilegeList());
        }
        String[] privilegeArray = map.get(privilegeValue).toString().split(",");
        List list = new ArrayList();
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        for (int i = 0; i < privilegeArray.length; i++) {
            int xx = Integer.valueOf(privilegeArray[i]) - 1;
            list.set(xx, 1);
        }
        return list;

    }

    public List regulation() {
        Map map1 = new HashMap();
        map1.put("1", "会员在平台消费时可获得积分，不同等级会员可获得" +
                "不同程度的积分加速。\n");
        map1.put("2", "\n" + "入门吃货：消费1元获得1积分\n" +
                "小露吃性：消费1元获得1.1积分\n" +
                "吃货新秀：消费1元获得1.2积分\n" +
                "吃中王者：消费1元获得1.5积分\n" +
                "饕餮：消费1元获得1.8积分\n");
        Map map2 = new HashMap();
        map2.put("1", "会员在生日当天会获得伍饭宝平台送出的生日\n" +
                "祝福。\n");
        map2.put("2", "\n" + "入门吃货：生日祝福短信\n" +
                "小露吃性：生日祝福短信\n" +
                "吃货新秀：生日祝福+15元生日券\n" +
                "吃中王者：生日祝福+15元生日券\n" +
                "饕餮：生日祝福+15元生日券");
        Map map3 = new HashMap();
        map3.put("1", "等级达到一定级别后，平台将会在每月送给\n" +
                "会员一定面值的优惠券。\n");
        map3.put("2", "\n" + "入门吃货：无优惠券赠送\n" +
                "小露吃性：每月2元优惠券1张\n" +
                "吃货新秀：每月2元优惠券2张\n" +
                "吃中王者：每月5元优惠券1张\n" +
                "饕餮：每月5元优惠券2张\n");
        Map map4 = new HashMap();
        map4.put("1", "达到一定会员等级后，会员在进行客服咨询时\n" +
                "可享受客服优先接入的权益。\n");
        map4.put("2", "\n" + "入门吃货：不享受该权益\n" +
                "小露吃性：不享受该权益\n" +
                "吃货新秀：不享受该权益\n" +
                "吃中王者：享受客服优先接入\n" +
                "饕餮：享受客服优先接入\n" +
                "\n");
        List list = new ArrayList();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        return list;
    }
}
