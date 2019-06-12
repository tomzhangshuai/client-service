package com.wufanbao.api.clientservice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.wufanbao.api.clientservice.common.*;
import com.wufanbao.api.clientservice.common.alipay.AliPay;
import com.wufanbao.api.clientservice.common.wechat.AccessToken;
import com.wufanbao.api.clientservice.common.wechat.UserInfo;
import com.wufanbao.api.clientservice.common.wechat.WechatPay;
import com.wufanbao.api.clientservice.config.ClientSetting;
import com.wufanbao.api.clientservice.dao.*;
import com.wufanbao.api.clientservice.entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private MachineDao machineDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private CommonFun commonFun;
    @Autowired
    private ClientSetting clientSetting;
    @Autowired
    private UserOrderDao userOrderDao;

    //根据手机号生成存储短信验证吗的Key
    private String getKeyByPhone(String phone) {
        return Long.toString(Long.parseLong(phone), 12) + 6;
    }

    //根据主键获取用户信息
    public Data getUserDetail(long userId) {
        Data data = new Data();
        User user = userDao.getUser(userId);
        data.put("userId", user.getUserId());
        data.put("parentUserId", user.getParentUserId());
        data.put("usableBalance", user.getUsableBalance());
        data.put("userCouponSize", userDao.getUserCouponSize(userId));
        data.put("userMessageSize", userDao.getUserMessgeSize(userId));
        data.put("integral", user.getIntegral());//积分
        data.put("gradeNumber", user.getGradeValue());//会员成长值
        data.put("gradeRatio", "99%");//打败比例
        Data gradeData = commonFun.getGradeValue(user.getGradeValue());
        data.put("gradeValue", gradeData.get("gradeValue"));//会员成长值
        data.put("gradeText", gradeData.get("gradeText"));//会员成长值
        data.put("gradeNext", gradeData.get("gradeNext"));
        data.put("isAuth", user.getIsAuth());
        data.put("isCredit", user.getIsCredit());
        data.put("isSetPayPassword", !StringUtils.isNullOrEmpty(user.getPayPassword()));
        data.put("resetPasswordNeeded", user.getResetPasswordNeeded());
        System.out.println(user.getResetPasswordNeeded());
        data.put("portrait", commonFun.sourceImage(user.getPortrait()));//头像
        data.put("userName", user.getUserName());
        data.put("sex", user.getSex());
        data.put("breakfast", DateUtils.DateToString(user.getBreakfast(), "HH:mm"));
        data.put("birthday", DateUtils.DateToString(user.getBirthday(), "yyyy-MM-dd"));
        data.put("lunch", DateUtils.DateToString(user.getLunch(), "HH:mm"));
        data.put("dinner", DateUtils.DateToString(user.getDinner(), "HH:mm"));
        data.put("loginNo", user.getLoginNo());
        UserSignIn userSignIn = userDao.getUserSignin(userId);
        if (userSignIn == null) {
            data.put("isSignin", false);
        } else {
            data.put("isSignin", DateUtils.isToday(userSignIn.getSignedTime()));
        }

        UserIntegralLog userIntegralLog = userDao.getUserIntegralLogBySourceType(userId, "perfectUser");
        UserGradeLog userGradeLog = userDao.getUserGradelogBySourceType(userId, "perfectUser");
        if (userIntegralLog != null || userGradeLog != null) {//说明已经赠送过
            data.put("isPerfectUser", true);
        } else {
            data.put("isPerfectUser", false);
        }

        return data;
    }

    //完善用户信息
    @Transactional(rollbackFor = ApiServiceException.class)
    public void perfectUser(long userId, String userName, int sex, Date birthday, Date breakFast, Date lunch, Date dinner) throws ApiServiceException {
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new ApiServiceException("用户不存在");
        }
        if (userDao.perfectUser(userId, userName, sex, birthday, breakFast, lunch, dinner) <= 0) {
            throw new ApiServiceException("完善用户信息失败，请重试");
        }
        UserIntegralLog userIntegralLog = userDao.getUserIntegralLogBySourceType(userId, "perfectUser");
        UserGradeLog userGradeLog = userDao.getUserGradelogBySourceType(userId, "perfectUser");
        if (userIntegralLog != null || userGradeLog != null) {//说明已经赠送过
            return;
        }
        //赠送积分
        if (userDao.addIntegral(userId, 10) <= 0) {
            throw new ApiServiceException("完善用户信息，赠送积分失败，请重试");
        }
        //积分日志
        long integralLogId = IDGenerator.generateById("integralLogId", userId);
        if (userDao.insertIntegralLog(integralLogId, userId, 10, "perfectUser", userId, "用户完善信息获取积分") <= 0) {
            throw new ApiServiceException("添加积分日志失败，请重试");
        }
        //提示成长值
        if (userDao.updateGradeGrowUp(userId, 10) <= 0) {
            throw new ApiServiceException("完善用户信息，提示用户成长值失败，请重试");
        }
        long userGradeLogId = IDGenerator.generateById("userGradeLogId", userId);
        if (userDao.insertUserGradeLog(userGradeLogId, userId, 10, "perfectUser", userId, "用户完善信息提升成长值") < 0) {
            throw new ApiServiceException("添加用户成长值记录失败，请重试");
        }
    }

    //更新用户头像
    public void updatePortrait(long userId, String photo) throws UnsupportedEncodingException, ApiServiceException {
        String url = clientSetting.getUploadUrl() + "/Image/UplodaStream";
        String path = "/Upload/AppClintImage";
        String ext = ".png";
        String dst_fname = URLEncoder.encode(photo, "utf-8");
        dst_fname = dst_fname.replaceAll("\\+", "%20");
        String result = commonFun.sendPost(url, "path=" + path + "&ext=" + ext + "&bytes=" + dst_fname);
        Map map1 = JSON.parseObject(result);
        String data = map1.get("data").toString();
        List<Object> list = JSONObject.parseArray(data);
        String imageUrl = list.get(0).toString();
        if (userDao.updatePortrait(userId, imageUrl) <= 0) {
            throw new ApiServiceException("更新用户头像失败，请重试");
        }
    }

    //获取用户消息列表
    public List<Data> getUserMessages(long userId, int isRead, int pageStart, int pageSize) {
        return userDao.getUserMessages(userId, isRead, pageSize, pageStart);
    }

    //设置已读
    public void setUserMessageRead(long userId) throws ApiServiceException {
        userDao.setUserMessgeRead(userId);
    }

    //设置已删除
    public void setUserMessageDelete(long userId) throws ApiServiceException {
        userDao.setUserMessgeDelete(userId);
    }

    //获取用户资金交易记录
    public List<UserCapitalLog> getUsercapitallogs(long userId, Date month) {
        return userDao.getUserCapitallogs(userId, DateUtils.DateToString(month));
    }

    //获取用户资金交易记录
    public List<Data> getSonUserCapitalLogs(long userId, Date month) {
        return userDao.getSonUserCapitalLogs(userId, DateUtils.DateToString(month));
    }

    //获取用户积分日志记录
    public List<UserIntegralLog> getUserIntegralLogs(long userId, int pageStart, int pageSize) {
        return userDao.getUserIntegralLogs(userId, pageStart, pageSize);
    }

    //下单时，获取用户可以优惠券
    public List<Data> getUseableUserCoupons(long userId, List<Long> productIds, BigDecimal amount, String areaName) {
        List<Data> userCoupons = userDao.getUseableUserCoupons(userId);
        long areaId = userDao.getAreaId(areaName);
        List<Data> results = new ArrayList<>();
        for (Data d : userCoupons) {
            //获取使用规则并转化成实体类
            UseRule useRule = JsonUtils.GsonToBean(d.get("useRule").toString(), UseRule.class);
            ValidityRule validityRule = JsonUtils.GsonToBean(d.get("validityRule").toString(), ValidityRule.class);
            BigDecimal qualifiedMoney = useRule.getQualifiedMoney();//满多少进而
            long productGlobalId = useRule.getProductGlobalId();
            //区域限制
            if (useRule.getAreaId() > 0 && areaId != useRule.getAreaId()) {
                continue;
            }

            //订单金额限制
            if (qualifiedMoney.compareTo(BigDecimal.ZERO) == 1 && amount.compareTo(qualifiedMoney) == -1) {
                continue;
            }

            //商品订单里面含不包含必买商品
            if (!productIds.contains(productGlobalId) && productGlobalId > 0) {
                continue;
            }

            //优惠券有效期
            Date startTime = validityRule.getStartTime();
            Date endTime = validityRule.getEndTime();
            if (startTime != null && endTime != null) {
                if (!DateUtils.belongCalendar(new Date(), startTime, endTime)) {
                    continue;
                }
            }
            results.add(d);
        }
        return results;
    }

    //获取用户可以优惠券
    public List<Data> getUseableUserCoupons(long userId) {
        List<Data> userCoupons = userDao.getUseableUserCoupons(userId);
        List<Data> results = new ArrayList<>();
        for (Data d : userCoupons) {
            //获取使用规则并转化成实体类
            UseRule useRule = JsonUtils.GsonToBean(d.get("useRule").toString(), UseRule.class);
            ValidityRule validityRule = JsonUtils.GsonToBean(d.get("validityRule").toString(), ValidityRule.class);
            BigDecimal qualifiedMoney = useRule.getQualifiedMoney();//满多少进而
            long productGlobalId = useRule.getProductGlobalId();

            //优惠券有效期
            Date startTime = validityRule.getStartTime();
            Date endTime = validityRule.getEndTime();
            if (startTime != null && endTime != null) {
                if (!DateUtils.belongCalendar(new Date(), startTime, endTime)) {
                    continue;
                }
            }
            results.add(d);
        }
        return results;
    }

    //提供优惠券详情界面数据
    public Data getCouponDetail(String areaName, BigDecimal x, BigDecimal y, long couponId) {
        Data data = new Data();

        Data userCoupon = userDao.getUserCoupon(couponId);
        Object objNotice = userCoupon.get("notice");//使用须知
        Object objMatter = userCoupon.get("matters");//注意事项
        Object objUseRule = userCoupon.get("useRule");//使用规则
        Object objAmount = userCoupon.get("amount");
        Object objStartTime = userCoupon.get("startTime");
        Object objEndTime = userCoupon.get("endTime");

        String[] notices = null;
        String[] matters = null;
        UseRule useRule = null;
        if (objNotice != null && !StringUtils.isNullOrEmpty(objNotice.toString())) {
            notices = objNotice.toString().split(";");
        }
        if (objMatter != null && !StringUtils.isNullOrEmpty(objMatter.toString())) {
            matters = objMatter.toString().split(";");
        }
        if (objUseRule != null) {
            useRule = JsonUtils.GsonToBean(objUseRule.toString(), UseRule.class);
        }
        data.put("notices", notices);
        data.put("matters", matters);
        data.put("amount", objAmount);
        data.put("startTime", objStartTime);
        data.put("endTime", objEndTime);

        Area area = companyDao.getCityByAreaName(areaName);
        if (area != null) {
            //机器信息
            Machine machine = getNearMachine(area.getAreaId(), x, y);
            data.put("machineId", machine.getMachineId());
            data.put("machineAddress", machine.getAddress());
            double distance = Distance.GetDistance(x, y, machine.getX(), machine.getY());
            data.put("distance", distance);
            data.put("x", machine.getX());
            data.put("y", machine.getY());
            data.put("inMaintenance", machine.getInMaintenance());

            Product product = new Product();
            List<Product> products = productDao.getProductsByMachineId(machine.getMachineId());
            for (Product p : products) {
                BigDecimal price = BigDecimal.valueOf(p.getOnlinePrice() / 100);

                //区域限制
                if (useRule.getAreaId() > 0 && area.getAreaId() != useRule.getAreaId()) {
                    continue;
                }

                //订单金额限制
                if (useRule.getQualifiedMoney().compareTo(BigDecimal.ZERO) == 1 && price.compareTo(useRule.getQualifiedMoney()) == -1) {
                    continue;
                }

                //商品订单里面含不包含必买商品
                if (useRule.getProductGlobalId() > 0 && p.getProductGlobalId() != useRule.getProductGlobalId()) {
                    continue;
                }
                product = p;
                break;
            }
            product.setImageUrl(commonFun.sourceImage(product.getImageUrl()));
            data.put("product", product);
        }
        return data;
    }

    private Machine getNearMachine(int areaId, BigDecimal x, BigDecimal y) {
        //获取机器，启用中的并且运行中
        List<Machine> machines = machineDao.getMachinesByAreaId(areaId);
        Collections.sort(machines, new Comparator<Machine>() {
            /*
             * int compare(Machine o1, Machine o2) 返回一个基本类型的整型，
             * 返回负数表示：p1 小于p2，
             * 返回0 表示：p1和p2相等，
             * 返回正数表示：p1大于p2
             */
            @Override
            public int compare(Machine o1, Machine o2) {
                double distance1 = Distance.GetDistance(x, y, o1.getX(), o1.getY());
                double distance2 = Distance.GetDistance(x, y, o2.getX(), o2.getY());
                if (distance1 > distance2) {
                    return 1;
                } else if (distance1 < distance2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        if (machines.size() <= 0) {
            return null;
        }
        return machines.get(0);
    }


    //获取用户签到数据
    public UserSignIn getUserSignIn(long userId) {
        return userDao.getUserSignin(userId);
    }

    //用户签到
    @Transactional(rollbackFor = ApiServiceException.class)
    public void userSignIn(long userId) throws ApiServiceException {
        UserSignIn userSignIn = userDao.getUserSignin(userId);
        UserSignInLog userSignInLog = userDao.getUserSigninLog(userId);
        if (userSignIn == null) {
            //首次签到
            if (userDao.insertUserSignin(userId) <= 0) {
                throw new ApiServiceException("首次签到失败");
            }
            signInGet(userId, 1);
            return;
        }

        boolean isSignIn = DateUtils.isToday(userSignIn.getSignedTime());
        //当前已经签到，不能重复签到
        if (isSignIn) {
            return;
        }

        //说明不是连续签到
       /* Date date2=userSignInLog.getSignedDay();
        long t1=new Date().getTime();
        long t2=date2.getTime();
        long t3=(t1-t2);
        t3=t3/(1000 * 60 * 60 * 24);*/
        long day = DateUtils.getDiffDay(new Date(), userSignInLog.getSignedDay()) - 1;
        if (day >= 1) {
            userSignIn.setContinuousSignedDays(1);
            if (userDao.userSignin(userId, 1) <= 0) {
                throw new ApiServiceException("用户签到失败，请重试");
            }
            int continuousSignedDay = userSignIn.getContinuousSignedDays();//连续签到天数
            signInGet(userId, continuousSignedDay);
        }
        if (day < 1) {
            //连续签到
            if (userDao.userContinuitySignin(userId) <= 0) {
                throw new ApiServiceException("用户签到失败，请重试");
            }
            int continuousSignedDay = userSignIn.getContinuousSignedDays() + 1;//连续签到天数
            if (userDao.updateMaxContinuousSignin(userId, continuousSignedDay) <= 0) {
                throw new ApiServiceException("更新最大连续签到失败");
            }
            signInGet(userId, continuousSignedDay);
        }
    }

    //签到后的操作：获得积分和成长值
    private void signInGet(long userId, int continuousSignedDay) throws ApiServiceException {
        int integral = commonFun.getIntegral(continuousSignedDay);//签到获得的积分
        if (userDao.addSignInLog(userId, DateUtils.DateToString(new Date(), "yyyy-MM-dd"), integral) <= 0) {
            throw new ApiServiceException("添加签到日志失败");
        }
        //用户积分增长
        if (userDao.addIntegral(userId, integral) <= 0) {
            throw new ApiServiceException("更新积分失败");
        }
        //用户积分日志
        long integralLogId = IDGenerator.generateById("integralLogId", userId);
        if (userDao.insertIntegralLog(integralLogId, userId, integral, "userSignIn", userId, "用户签到获取积分") <= 0) {
            throw new ApiServiceException("添加积分日志失败");
        }

        //用户成长值增长
        if (userDao.updateGradeGrowUp(userId, integral) <= 0) {
            throw new ApiServiceException("更新用户成长值失败");
        }
        //用户成长日志
        long userGradeLogId = IDGenerator.generateById("userGradeLogId", userId);
        if (userDao.insertUserGradeLog(userGradeLogId, userId, integral, "userSignIn", userId, "用户签到获取成长值") <= 0) {
            throw new ApiServiceException("添加用户成长记录失败");
        }
    }

    //根据登录号获取用户数据
    public User getUserByLoginNo(String loginNo) {
        return userDao.getUserByLoginNo(loginNo);
    }

    public boolean userExist(String loginNo) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(loginNo)) {
            throw new ApiServiceException("登录号不能为空");
        }
        return getUserByLoginNo(loginNo) != null;
    }

    public User userExist(long userId) {
        return userDao.getUser(userId);
    }


    //发送短信
    public boolean sendMessage(String phone) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ApiServiceException("手机号不能为空");
        }

        String incrKey = Long.toString(Long.parseLong(phone), 15) + 6;
        long count = redisUtils.incr(incrKey);
        if (count > 2) {
            redisUtils.expire(incrKey, 60 * 3);
            throw new ApiServiceException("发送短信太过频繁，请稍后再发");
        }

        String code = commonFun.random(4);//四位的随机数
        try {
            if (commonFun.sendMessage(phone, "您好，您的验证码是：" + code + "，5分钟内有效")) {
                String key = getKeyByPhone(phone);
                redisUtils.set(key, code);
                redisUtils.expire(key, 60 * 5);

                redisUtils.del(incrKey);
                return true;
            } else {
                throw new ApiServiceException("短信发送失败，请重试");
            }
        } catch (Exception ex) {
            throw new ApiServiceException("其他异常：" + ex.getMessage());
        }

    }

    //添加用户 第三方登陆
    public User insertUserFirst(User user) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(user.getLoginNo())) {
            throw new ApiServiceException("手机号不能为空");
        }
        if (!StringUtils.isNullOrEmpty(user.getPassword())) {
            user.setPassword("");
        }
        if (userExist(user.getLoginNo())) {
            throw new ApiServiceException("该用户已存在，不能重复注册");
        }

        /*if (user.getUserId() <= 0) {
            long userId = IDGenerator.generate("User");
            user.setUserId(userId);
        }*/
        if (user.getUserId() <= 0) {
            throw new ApiServiceException("用户id不能为空");
        }
        user.setParentUserId(0);
        user.setUserName(user.getUserName());
        user.setMb(user.getLoginNo());//登录号就是手机号
        user.setPassword("");
        user.setPayPassword("");
        user.setPortrait(user.getPortrait());
        user.setUserType(UserType.value(UserType.Single));
        user.setInvitationCode(Long.toHexString(user.getUserId()));//邀请码
        user.setIsActive(true);
        user.setResetPasswordNeeded(true);//是否需要重置密码
        user.setIsAuth(false);//是否认证
        user.setIsCredit(false);//是否开启授信
        user.setCompanyPayment(false);//是否是企业用户
        user.setIntegral(0);
        user.setBalance(BigDecimal.ZERO);
        user.setUsableBalance(BigDecimal.ZERO);
        user.setLockBalance(BigDecimal.ZERO);
        user.setCreditAmount(BigDecimal.ZERO);
        user.setCreditUasbelAmount(BigDecimal.ZERO);
        user.setCreditLimit("");

        if (userDao.insertUser(user) > 0) {
            user = userDao.getUser(user.getUserId());
            return user;
        } else {
            return null;
        }
    }

    //添加用户 注册
    public User insertUser(User user) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(user.getLoginNo())) {
            throw new ApiServiceException("手机号不能为空");
        }
        if (StringUtils.isNullOrEmpty(user.getPassword())) {
            throw new ApiServiceException("登录密码不能为空");
        }
        if (userExist(user.getLoginNo())) {
            throw new ApiServiceException("该用户已存在，不能重复注册");
        }

        if (user.getUserId() <= 0) {
            long userId = IDGenerator.generate("User");
            user.setUserId(userId);
        }
        user.setParentUserId(0);
        user.setUserName(user.getUserName());
        user.setMb(user.getLoginNo());//登录号就是手机号
        String password = DigestUtils.md5Hex(user.getPassword());//加密
        user.setPassword(password);
        user.setPayPassword("");
        user.setPortrait(user.getPortrait());
        user.setUserType(UserType.value(UserType.Single));
        user.setInvitationCode(Long.toHexString(user.getUserId()));//邀请码
        user.setIsActive(true);
        user.setResetPasswordNeeded(false);//是否需要重置密码
        user.setIsAuth(false);//是否认证
        user.setIsCredit(false);//是否开启授信
        user.setCompanyPayment(false);//是否是企业用户
        user.setIntegral(0);
        user.setBalance(BigDecimal.ZERO);
        user.setUsableBalance(BigDecimal.ZERO);
        user.setLockBalance(BigDecimal.ZERO);
        user.setCreditAmount(BigDecimal.ZERO);
        user.setCreditUasbelAmount(BigDecimal.ZERO);
        user.setCreditLimit("");

        if (userDao.insertUser(user) > 0) {
            user = userDao.getUser(user.getUserId());
            return user;
        } else {
            return null;
        }
    }

    //添加第三方用户
    public User insertOtherUser(User user) throws ApiServiceException {
        long userId = IDGenerator.generate("User");
        user.setUserId(userId);
        user.setParentUserId(0);
        user.setUserName(user.getUserName());
//        user.setMb(user.getLoginNo());//登录号就是手机号
        user.setMb("");
        String password = DigestUtils.md5Hex(user.getPassword());//加密
        user.setPassword(password);
        user.setPayPassword("");
        user.setPortrait(user.getPortrait());
        user.setUserType(UserType.value(UserType.Single));
        user.setInvitationCode(Long.toHexString(user.getUserId()));//邀请码
        user.setIsActive(true);
        user.setResetPasswordNeeded(true);//是否需要重置密码
        user.setIsAuth(false);//是否认证
        user.setIsCredit(false);//是否开启授信
        user.setCompanyPayment(false);//是否是企业用户
        user.setIntegral(0);
        user.setBalance(BigDecimal.ZERO);
        user.setUsableBalance(BigDecimal.ZERO);
        user.setLockBalance(BigDecimal.ZERO);
        user.setCreditAmount(BigDecimal.ZERO);
        user.setCreditUasbelAmount(BigDecimal.ZERO);
        user.setCreditLimit("");

        if (userDao.insertUser(user) > 0) {
            user = userDao.getUser(userId);
            return user;
        } else {
            return null;
        }
    }


    //用户名密码登录
    public long passwordLogin(String loginNo, String password) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(loginNo)) {
            throw new ApiServiceException("登录账号不能为空");
        }
        if (StringUtils.isNullOrEmpty(password)) {
            throw new ApiServiceException("登录密码不能为空");
        }
        User user = getUserByLoginNo(loginNo);
        if (user == null) {
            throw new ApiServiceException("用户名或密码错误");
        }
        if (!user.getIsActive()) {
            throw new ApiServiceException("登录账号已被锁定");
        }
        String md5Password = DigestUtils.md5Hex(password);//加密
        if (!user.getPassword().equals(md5Password)) {
            throw new ApiServiceException("用户名或密码错误");
        } else {
            return user.getUserId();
        }
    }

    //短信验证吗登录:如果没有该用户就注册用户
    public long messageLogin(String loginNo, String code) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(loginNo)) {
            throw new ApiServiceException("手机号不能为空");
        }
        if (StringUtils.isNullOrEmpty(code)) {
            throw new ApiServiceException("验证码不能为空");
        }
        checkMessage(loginNo, code);
        redisUtils.del(loginNo);

        //验证码比对成功
        User user = getUserByLoginNo(loginNo);
        if (user == null) {//如果用户不存在
            user = new User();
            user.setLoginNo(loginNo);
            user.setUserName(loginNo);
            if (user.getUserId() <= 0) {
                long userId = IDGenerator.generate("User");
                user.setUserId(userId);
            }
            user = insertUserFirst(user);
        }
        return user.getUserId();
    }

    //绑定第三方账号登录
    @Transactional(rollbackFor = ApiServiceException.class)
    public User bindingLogin(UShare ushare) throws ApiServiceException {
        UserBinding userBinding = userDao.getUserBindingByBindingId(ushare.getUid());
        if (userBinding != null && userBinding.getUserId() > 0) {        //绑定过
            long userId = userBinding.getUserId();
          /*String openId=ushare.getOpenid();
            if(StringUtils.isNullOrEmpty(userBinding.getOpneId())){
                if(userDao.updateUserbindingOpenId(userId,openId)<=0){
                    throw new ApiServiceException("第三方登录失败，清扫后重试！");
                }
            }*/
            User user = userDao.getUser(userId);
            if (StringUtils.isNullOrEmpty(user.getLoginNo())) {
                redisUtils.set(ushare.getUid(), JsonUtils.GsonString(ushare));
                redisUtils.expire(ushare.getUid(), 180);
                return null;
            }
            return user;
        }
        redisUtils.set(ushare.getUid(), JsonUtils.GsonString(ushare));
        redisUtils.expire(ushare.getUid(), 180);
        return null;
    }

    //第三方登录绑定手机号
    @Transactional(rollbackFor = ApiServiceException.class)
    public long bindLoginNo(String loginNo, String uid, int bingdingType) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(uid)) {
            throw new ApiServiceException("uid参数错误");
        }
        if (StringUtils.isNullOrEmpty(loginNo)) {
            throw new ApiServiceException("手机号不能为空");
        }
        String ushareStr = redisUtils.get(uid);
        if (StringUtils.isNullOrEmpty(ushareStr)) {
            throw new ApiServiceException("绑定超时，请重试");
        }
        UShare ushare = JsonUtils.GsonToBean(ushareStr, UShare.class);
        String openId = ushare.getOpenid();
        User user = new User();
        user.setUserName(ushare.getName());
        if (ushare.getGender() == "男") {
            user.setSex(1);
        } else if (ushare.getGender() == "女") {
            user.setSex(2);
        } else {
            user.setSex(0);
        }
        user.setPortrait(ushare.getIconurl());
        user.setLoginNo(loginNo);
        User dbUser = userDao.getUserByLoginNo(loginNo);
        UserBinding userBinding = userDao.getUserBindingByBindingId(uid);
        if (dbUser == null) {
            if (userBinding == null) {
                if (user.getUserId() <= 0) {
                    long userId = IDGenerator.generate("User");
                    user.setUserId(userId);
                }
//                if (userDao.insertUserBinding(user.getUserId(), uid,openId,bingdingType) <= 0) {
                if (userDao.insertUserBinding(user.getUserId(), uid, bingdingType) <= 0) {
                    throw new ApiServiceException("添加第三方登录商户信息失败");
                }
                user = insertUserFirst(user);  //user对象不存在，且绑定对象不存在，插入user对象
            } else {
                if (userBinding.getUserId() <= 0) { //旧数据
                    long userId = IDGenerator.generate("User");
                    userBinding.setUserId(userId);
                    user.setUserId(userBinding.getUserId());
                    user = insertUserFirst(user); //user对象不存在，绑定对象存在，插入user对象
                } else {
                    long userId = userBinding.getUserId();
                    user = updateUserMb(loginNo, userId);
                    /*if(userDao.updateUserMb(loginNo,userId)<=0){
                        throw new ApiServiceException("更改第三方商户信息失败");
                    }*/
                }
            }
        } else {
            user = dbUser;
            if (userBinding == null) {
//                if (userDao.insertUserBinding(user.getUserId(), uid,openId,bingdingType) <= 0) {
                if (userDao.insertUserBinding(user.getUserId(), uid, bingdingType) <= 0) {
                    throw new ApiServiceException("添加第三方登录商户信息失败");
                }
            } else {
                if (user.getUserId() != userBinding.getUserId()) {//修改绑定关系
                    if (userOrderDao.exitUnCompleteUserOrder(userBinding.getUserId()) > 0) {
                        throw new ApiServiceException("第三方用户下有未完成的取餐订单，请取完餐在进行该绑定操作");
                    }
                    if (userDao.deleteUserBinding(userBinding.getUserId(), userBinding.getBindingId()) <= 0) {
                        throw new ApiServiceException("解除第三方登录账号绑定关系失败");
                    }
//                    if (userDao.insertUserBinding(user.getUserId(), uid, openId,bingdingType) <= 0) {
                    if (userDao.insertUserBinding(user.getUserId(), uid, bingdingType) <= 0) {
                        throw new ApiServiceException("添加第三方登录商户信息失败");
                    }
                    redisUtils.del("JwtToken_" + userBinding.getUserId());
                }
            }
        }
        redisUtils.del(uid);
        return user.getUserId();
    }

    //更改手机号，登录名
    public User updateUserMb(String loginNo, long userId) throws ApiServiceException {
        if (userDao.updateUserMb(loginNo, userId) <= 0) {
            throw new ApiServiceException("更改第三方商户信息失败!");
        }
        User user = userDao.getUser(userId);
        return user;
    }

    //绑定第三方账号（微信、支付宝）H5登录
    @Transactional(rollbackFor = ApiServiceException.class)
    public long insertUserBinding(User user, String bindingId, String openId, int bingdingType) throws ApiServiceException {
        UserBinding userBinding = userDao.getUserBindingByBindingId(bindingId);
        if (userBinding != null && userBinding.getUserId() > 0) { //已绑定
            if (StringUtils.isNullOrEmpty(userBinding.getOpneId())) {
                if (userDao.updateUserbindingOpenId(userBinding.getUserId(), openId) <= 0) {
                    throw new ApiServiceException("第三方登录失败，清扫后重试！");
                }
            }
            return userBinding.getUserId();
        }
        user = insertOtherUser(user);
//        if (userDao.insertUserBinding(user.getUserId(), bindingId,openId,bingdingType) <= 0) {
        if (userDao.insertUserBindingOpenId(user.getUserId(), bindingId, openId, bingdingType) <= 0) {
            throw new ApiServiceException("添加第三方登录商户信息失败");
        }

        return user.getUserId();
    }

    //验证验证吗
    public void checkMessage(String phone, String code) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ApiServiceException("手机号不能为空");
        }
        if (StringUtils.isNullOrEmpty(code)) {
            throw new ApiServiceException("验证码不能为空");
        }
        String key = getKeyByPhone(phone);
        String redisCode = redisUtils.get(key);
        if (StringUtils.isNullOrEmpty(code)) {
            throw new ApiServiceException("验证码已过期，请重新发送");
        }
        if (!code.equals(redisCode)) {
            throw new ApiServiceException("验证码错误");
        }
        redisUtils.del(key);
    }

    //设置手机号
    public void setLoginNo(long userId, String loginNo) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(loginNo)) {
            throw new ApiServiceException("手机号不能为空");
        }
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new ApiServiceException("用户不存在");
        }
        User user1 = userDao.getUserByLoginNo(loginNo);
        if (user1 != null) {
            throw new ApiServiceException("该手机号已被使用");
        }

        if (userDao.setLoginNo(userId, loginNo) <= 0) {
            throw new ApiServiceException("修改支付密码失败，请重试");
        }
    }

    //设置支付密码
    public void setPayPassword(long userId, String password) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(password)) {
            throw new ApiServiceException("支付密码不能为空");
        }
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new ApiServiceException("用户不存在");
        }
        String payPassword = DigestUtils.md5Hex(password);//加密
        if (userDao.setPayPassword(userId, payPassword) <= 0) {
            throw new ApiServiceException("修改支付密码失败，请重试");
        }
        String key = Long.toString(userId, 16) + 4;
        String m = redisUtils.get(key);
        redisUtils.del(key);
    }

    //设置登录密码
    public void setLoginPassword(long userId, String password) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(password)) {
            throw new ApiServiceException("登录密码不能为空");
        }
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new ApiServiceException("用户不存在");
        }
        String loginPassword = DigestUtils.md5Hex(password);//加密
        if (userDao.setLoginPassword(userId, loginPassword) <= 0) {
            throw new ApiServiceException("修改登录密码失败，请重试");
        }
    }

    //设置登录密码
    public void setLoginPasswordByPhone(String loginNo, String password) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(loginNo)) {
            throw new ApiServiceException("手机号不能为空");
        }
        if (StringUtils.isNullOrEmpty(password)) {
            throw new ApiServiceException("登录密码不能为空");
        }
        User user = userDao.getUserByLoginNo(loginNo);
        if (user == null) {
            throw new ApiServiceException("用户不存在");
        }
        String loginPassword = DigestUtils.md5Hex(password);//加密
        if (userDao.setLoginPassword(user.getUserId(), loginPassword) <= 0) {
            throw new ApiServiceException("修改登录密码失败，请重试");
        }
    }

    //充值
    @Transactional(rollbackFor = ApiServiceException.class)
    public Object recharge(long userId, String ip, int type, BigDecimal amount) throws ApiServiceException {
        if (amount.compareTo(BigDecimal.ZERO) != 1) {
            throw new ApiServiceException("充值金额必须大于0");
        }
        if (StringUtils.isNullOrEmpty(ip)) {
            throw new ApiServiceException("请求参数错误");
        }

        long rechargeId = IDGenerator.generateById("rechargeId", userId);
        UserRecharge userRecharge = new UserRecharge();
        userRecharge.setRechargeId(rechargeId);
        userRecharge.setUserId(userId);
        userRecharge.setAmount(amount);
        userRecharge.setBcTradeNo(String.valueOf(rechargeId));
        userRecharge.setTradeNo("");
        userRecharge.setPayType(type);
        userRecharge.setPayStatus(1);
        userRecharge.setActualAmount(BigDecimal.ZERO);
        if (userDao.insertUserRecharge(userRecharge) <= 0) {
            throw new ApiServiceException("充值失败，请重试");
        }
        switch (type) {
            case 2://微信充值
                try {
                    if (StringUtils.isNullOrEmpty(ip)) {
                        throw new ApiServiceException("客户端Ip地址不能为空");
                    }
                    WechatPay wechatPay = new WechatPay();
                    Map<String, String> map = wechatPay.payOrder(String.valueOf(rechargeId), "APP", "", amount, "伍饭宝-充值", ip, clientSetting.getPayCallback() + "/webapi/wechat/rechargeNotify");
                    return map;
                } catch (Exception ex) {
                    throw new ApiServiceException("微信充值失败");
                }
            case 3://支付宝充值
                try {
                    AliPay aliPay = new AliPay();
                    return aliPay.appPayOrder(String.valueOf(rechargeId), amount, "伍饭宝-充值", "充值", clientSetting.getPayCallback() + "/webapi/alipay/rechargeNotify");
                } catch (AlipayApiException e) {
                    throw new ApiServiceException("支付宝充值失败:" + e.getErrMsg());
                }
            default:
                throw new ApiServiceException("充值方式错误");
        }
    }

    //充值成功后的通知
    @Transactional(rollbackFor = ApiServiceException.class)
    public void rechargeNotify(long rechargeId, String tradeNo, BigDecimal amount) throws ApiServiceException {
        UserRecharge userRecharge = userDao.getUserRecharges(rechargeId);
        if (userRecharge == null) {
            throw new ApiServiceException("充值记录不存在");
        }
        long userId = userRecharge.getUserId();
        long userCapitalLogId = IDGenerator.generateById("userCapitalLogId", userId);
        if (userDao.insertUserCapital(userCapitalLogId, userId, amount, "recharge", rechargeId, "用户充值") <= 0) {
            throw new ApiServiceException("添加用户资金日志错误");
        }
        if (userDao.updateUserRecharge(userId, rechargeId, tradeNo, amount) <= 0) {
            throw new ApiServiceException("更新充值记录失败");
        }
        if (userDao.recharge(userId, amount) <= 0) {
            throw new ApiServiceException("更新用户资金失败");
        }
    }

    //获取家庭付
    public Data getFamilyUser(long userId) {
        Data data = new Data();
        User user = userDao.getUser(userId);//当前登录用户
        if (user.getParentUserId() > 0) {
            data.put("isMaster", -1);//不是主账号
            Data result = userDao.getFamilyPay(userId);
            if (result.get("portrait") != null) {
                result.put("portrait", commonFun.sourceImage(result.get("portrait").toString()));
            }
            data.put("data", result);
        } else {
            List<Data> datas = userDao.getSonUsers(userId);
            for (Data ret : datas) {
                if (ret.get("portrait") != null) {
                    ret.put("portrait", commonFun.sourceImage(ret.get("portrait").toString()));
                }
            }
            if (datas.size() > 0) {
                data.put("isMaster", 1);//是主账号
            } else {
                data.put("isMaster", 0);
            }
            data.put("data", datas);
        }
        return data;
    }

    //判断绑定用户关系
    public User checkBindUser(long userId, String phone) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ApiServiceException("绑定手机号不能为空");
        }
        User user = userDao.getUserByLoginNo(phone);
        if (user == null) {
            throw new ApiServiceException("绑定的手机用户不存在，请先注册用户");
        }
        if (user.getUserId() == userId) {
            throw new ApiServiceException("不能绑定该手机号");//自己不能绑定自己
        }
        if (user.getParentUserId() != 0) {
            throw new ApiServiceException("该手机用户已经被其他主账号绑定");//不能被多个主账号绑定
        }
        if (userDao.getSonUsers(user.getUserId()).size() > 0) {
            throw new ApiServiceException("该手机用户绑定过其他用户，不能被绑定");//不能绑定主账号
        }
        if (userDao.getFamilyPay(user.getUserId()) != null) {
            throw new ApiServiceException("该用户已经绑定，不能重复绑定");
        }
        return user;
    }

    //绑定用户关系
    @Transactional(rollbackFor = ApiServiceException.class)
    public void bindUser(long userId, String phone, String password) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(password)) {
            throw new ApiServiceException("支付密码不能为空");
        }

        User sonUser = checkBindUser(userId, phone);//验证是否可以绑定

        User user = userDao.getUser(userId);
        String payPassword = DigestUtils.md5Hex(password);//加密
        if (!user.getPayPassword().equals(payPassword)) {
            throw new ApiServiceException(1003, "支付密码错误");
        }
        if (userDao.bindUser(sonUser.getUserId(), userId) <= 0) {
            throw new ApiServiceException("绑定亲密付款失败，请重试");
        }

        BigDecimal totalQuota = BigDecimal.valueOf(600);
        int quotaType = 2;
        boolean limitQuota = true;
        boolean disabled = true;
        BigDecimal consumeQuota = BigDecimal.valueOf(0);
        BigDecimal totalAmount = BigDecimal.valueOf(0);

        if (userDao.insertUserfamilypayrelation(userId, sonUser.getUserId(), quotaType, limitQuota, totalQuota, consumeQuota, totalAmount, true) <= 0) {
            throw new ApiServiceException("绑定亲密付款失败，请重试");
        }
    }

    //解除绑定用户关系
    @Transactional(rollbackFor = ApiServiceException.class)
    public void unBindUser(long userId, long masterUserId) throws ApiServiceException {
        if (userId <= 0 || masterUserId <= 0) {
            throw new ApiServiceException("解除绑定用户id或者主账号id参数异常");
        }
        if (userDao.bindUser(userId, 0) <= 0) {
            throw new ApiServiceException("解除绑定失败，请重试");
        }
        if (userDao.deleteUserfamilypayrelation(userId, masterUserId) <= 0) {
            throw new ApiServiceException("解除绑定失败，请重试");
        }
    }

    //修改限额
    public void setFamilyLimit(long masterUserId, long userId, int type, int amount, boolean islimit) throws ApiServiceException {
        if (masterUserId <= 0 || userId <= 0) {
            throw new ApiServiceException("修改限额用户id或者主账号id参数异常");
        }
        if (userDao.updateUserfamilypayrelation(masterUserId, userId, type, islimit, BigDecimal.valueOf(amount), BigDecimal.ZERO, new Date()) <= 0) {
            throw new ApiServiceException("修改限额失败，请重试");
        }
    }

    public void clearFamilyLimitByDay() throws ApiServiceException {
        if (userDao.clearFamilyLimit(1) <= 0) {
            throw new ApiServiceException("到期重置限额数据失败");
        }
    }

    public void clearFamilyLimitByMonth() throws ApiServiceException {
        if (userDao.clearFamilyLimit(2) <= 0) {
            throw new ApiServiceException("到期重置限额数据失败");
        }
    }

    //获取积分商场商品
    public List<IntegralCommodity> getIntegralCommoditys(int pageStart, int pageSize) {
        List<IntegralCommodity> integralCommodities = userDao.getIntegralCommoditys(pageStart, pageSize);
        for (IntegralCommodity integralCommodity : integralCommodities) {
            integralCommodity.setImageUrl(commonFun.sourceImage(integralCommodity.getImageUrl()));
        }
        return integralCommodities;
    }

    //获取用户积分兑换记录
    public List<IntegralExchange> getUserIntegralexchanges(long userId, int pageStart, int pageSize) {
        return userDao.getUserIntegralexchanges(userId, pageSize, pageStart);
    }

    //获取所有用户最新的积分兑换记录
    public List<Data> getIntegralexchanges() {
        return userDao.getIntegralexchanges();
    }

    //积分兑换商品
    @Transactional(rollbackFor = ApiServiceException.class)
    public void exchangeIntegral(long integralCommodityId, long userId) throws ApiServiceException {
        IntegralCommodity integralCommodity = userDao.getIntegralCommodity(integralCommodityId);
        if (integralCommodity == null) {
            throw new ApiServiceException("兑换的商品不存在");
        }
        int commodityType = integralCommodity.getCommodityType();//积分商场商品类型
        long definition = integralCommodity.getDefinition();
        String commodityName = integralCommodity.getCommodityName();
        BigDecimal integral = integralCommodity.getPrice();//积分数
        //积分商场商品减少
        if (userDao.updateIntegralCommodity(integralCommodityId, 1) <= 0) {
            throw new ApiServiceException("兑换商品失败，请重试");
        }
        //使用积分
        if (userDao.reduceIntegral(userId, integral.intValue()) <= 0) {
            throw new ApiServiceException("积分不足，请下单获取更多积分");
        }

        //插入用户兑换记录
        long integralExchangeId = IDGenerator.generateById("integralExchangeId", userId);
        if (userDao.addIntegralExchangeLog(integralExchangeId, userId, integralCommodityId, integral, commodityName) <= 0) {
            throw new ApiServiceException("添加用户兑换记录失败，请重试");
        }

        //插入积分使用记录
        long integralLogId = IDGenerator.generateById("integralLogId", userId);
        if (userDao.insertIntegralLog(integralLogId, userId, -integral.intValue(), "IntegralExchange", integralExchangeId, commodityName) <= 0) {
            throw new ApiServiceException("添加积分使用记录失败，请重试");
        }

        //优惠券
        if (commodityType == 1) {
            putOutCoupon(userId, definition);
        }
    }

    //发放优惠券
    private void putOutCoupon(long userId, long couponDefinitionId) throws ApiServiceException {
        CouponDefinition couponDefinition = userDao.getCouponDefinition(couponDefinitionId);
        ValidityRule validityRule = JsonUtils.GsonToBean(couponDefinition.getValidityRule(), ValidityRule.class);
        long couponId = IDGenerator.generateById("couponId", userId);
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUserId(userId);
        userCoupon.setCouponDefinitionId(couponDefinitionId);
        userCoupon.setCouponId(couponId);
        userCoupon.setCreateTime(new Date());
        userCoupon.setStartTime(new Date());
        userCoupon.setStatus(1);
        if (validityRule.getActiveTime() > 0) {
            userCoupon.setEndTime(DateUtils.getAfterDay(validityRule.getActiveTime()));
        } else {
            userCoupon.setEndTime(validityRule.getEndTime());
        }
        if (userDao.insertUserCoupon(userCoupon) <= 0) {
            throw new ApiServiceException("发放优惠券失败，请重试");
        }
        if (userDao.gotCoupon(couponDefinitionId) <= 0) {
            throw new ApiServiceException("更新优惠券的领取数量失败，请重试");
        }
    }

    //失效优惠券
    public void invalidUserCoupon() throws ApiServiceException {
        UserCoupon userCoupon = userDao.getInvalidUserCoupon();
        if (userCoupon == null) {
            return;
        }
        if (userDao.invalidCoupon(userCoupon.getCouponId()) <= 0) {
            throw new ApiServiceException("失效优惠券失败");
        }
    }

    //获取抽奖商品数据
    public Data getPrizes(long userId) {
        Data data = new Data();
        List<Prize> prizes = Prize.getPrizes();
        Collections.shuffle(prizes);//混乱排序
        String key = "ExtractPrizeCount_" + userId;
        data.put("prizes", prizes);
        String countStr = redisUtils.get(key);
        if (StringUtils.isNullOrEmpty(countStr)) {
            data.put("count", "8");
        } else {
            data.put("count", countStr);
        }
        return data;
    }

    //随机抽取奖品
    @Transactional(rollbackFor = ApiServiceException.class)
    public Data extractPrize(long userId) throws ApiServiceException {
        Data data = new Data();

        User user = userDao.getUser(userId);
        if (user == null || user.getIntegral() < 5) {
            throw new ApiServiceException(1007, "积分不足，不能抽奖");
        }

        //抽奖
        Prize[] prizes = Prize.getProbabilityPrizes();
        //redisUtils.del("PrizeInit");
        if (!redisUtils.exists("PrizeInit")) {
            for (Prize prize : prizes) {
                redisUtils.set("PrizeCount_" + prize.getId(), String.valueOf(prize.getCount()));
            }
            redisUtils.set("PrizeInit", "1");
        }
        Prize prize = lottery(prizes);
        if (prize == null) {
            throw new ApiServiceException("抽奖失败，请重试");
        }

        int number = 8;//当天抽奖总次数
        String incrKey = "ExtractPrize" + userId;
        long incrCount = redisUtils.incr(incrKey);

        //存储抽奖剩余次数
        String key = "ExtractPrizeCount_" + userId;
        long surplusCount = number - incrCount;
        if (surplusCount <= 0) {
            surplusCount = 0;
        }
        redisUtils.set(key, String.valueOf(surplusCount));

        int minute = (int) DateUtils.getDiffMinutes(DateUtils.getEndTime(), new Date());
        if (minute < 0) {
            minute = 0;
        }
        redisUtils.expire(key, 60 * minute);
        redisUtils.expire(incrKey, 60 * minute);
        if (incrCount > number) {
            throw new ApiServiceException("今日抽奖次数已用完");
        }

        long surplusIntegral = user.getIntegral();
        //使用积分
        if (userDao.reduceIntegral(userId, 5) <= 0) {
            throw new ApiServiceException("抽奖使用积分失败，请重试");
        }
        surplusIntegral = surplusIntegral - 5;
        //添加积分使用记录
        long integralLogId = IDGenerator.generateById("integralLogId", userId);
        if (userDao.insertIntegralLog(integralLogId, userId, -5, "lottery", userId, "使用积分抽奖") <= 0) {
            throw new ApiServiceException("抽奖添加积分使用记录失败，请重试");
        }
        switch (prize.getType()) {
            case 1://积分
                int integral = Integer.parseInt(prize.getValue());
                if (userDao.addIntegral(userId, integral) <= 0) {
                    throw new ApiServiceException("抽奖获得积分失败，请重试");
                }
                surplusIntegral = surplusIntegral + integral;
                long integralLogId1 = IDGenerator.generateById("integralLogId", userId);
                if (userDao.insertIntegralLog(integralLogId1, userId, integral, "lottery", userId, "抽奖获得") <= 0) {
                    throw new ApiServiceException("抽奖添加积分使用记录失败，请重试");
                }
                break;
            case 2://优惠券
                putOutCoupon(userId, Long.parseLong(prize.getValue()));
                break;
            default:
                break;
        }
        //更新剩余数量
        String countStr = redisUtils.get("PrizeCount_" + prize.getId());
        if (StringUtils.isNullOrEmpty(countStr)) {
            redisUtils.del("PrizeInit");
        }
        long count = Long.parseLong(countStr);
        redisUtils.set("PrizeCount_" + prize.getId(), String.valueOf(count - 1));
        data.put("count", surplusCount);//剩余抽奖次数
        data.put("integral", surplusIntegral);//剩余积分
        data.put("prize", prize.getId());
        return data;
    }

    private Prize lottery(Prize[] prizes) {
        int length = 0;
        for (Prize prize : prizes) {
            length += prize.getCount();
        }
        for (int i = 0; i < prizes.length; i++) {
            Random random = new Random();
            int ret = random.nextInt(length);      //获取 0-总数 之间的一个随随机整数
            System.out.println(ret);
            if (ret < prizes[i].getCount()) {
                return prizes[i];                   //如果在当前的概率范围内,得到的就是当前概率
            } else {
                length -= prizes[i].getCount();       //否则减去当前的概率范围,进入下一轮循环
            }
        }
        return null;
    }

    //用户留言
    public void leaveMessage(long userId, String messae) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(messae)) {
            throw new ApiServiceException("留言内容不能为空");
        }
        if (StringUtils.getWordCount(messae) > 200) {
            throw new ApiServiceException("留言内容限定200个字符");
        }
        long messageId = IDGenerator.generateById("messageId", userId);
        if (userDao.insertUserLeaveMessage(userId, messageId, messae) <= 0) {
            throw new ApiServiceException("留言失败，请重试");
        }
    }

    //获取openid
    public String getOpenId(long userId) {
        return userDao.getOpenId(userId);
    }

    public List<Data> getHistoryUserCoupons(long userId, int pageStart, int pageSize) {
        return userDao.getHistoryUserCoupons(userId, pageStart, pageSize);

    }
}
