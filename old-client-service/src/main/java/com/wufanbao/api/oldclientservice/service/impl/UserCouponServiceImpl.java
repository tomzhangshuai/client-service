package com.wufanbao.api.oldclientservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.Distance;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.Tool.RandomNum;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.controller.SMSInterface;
import com.wufanbao.api.oldclientservice.dao.*;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.UserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * User:WangZhiyuan
 * Data:2017/08/02
 * Time:14:35
 */
@Transactional
@Service
public class UserCouponServiceImpl implements UserCouponService {
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private UserLoginDao userLoginDao;
    @Autowired
    private UserRegisteredDao userRegisteredDao;
    @Autowired
    private InsertInvitationCodeDao insertInvitationCodeDao;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private FamilyPayDao familyPayDao;
    @Autowired
    private AppDiscoverDao appDiscoverDao;
    @Autowired
    private HomePageDao homePageDao;
    @Autowired
    private ClientSetting clientSetting;

    @Override
    public List<UserCoupon> userCouponDaoRow(long userId) {
        return userCouponDao.userCouponDaoRow(userId);
    }

    /**
     * 获取所有优惠券的信息
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public Map getUserCouponInfo(long userId) {
        //作废优惠券
        userCouponDao.cancellationCoupon();
        Map map = new HashMap();
        try {
            //根据用户id查询所有优惠券的信息
            List<UserCouponInfo> uCIList = userCouponDao.getUserCouponInfo(userId);
            for (UserCouponInfo userCouponInfo : uCIList) {
                //日期描述
                String dayDescribe = getDayDescribe(userCouponInfo.getEndTime());
                userCouponInfo.setDayDescribe(dayDescribe);
                String notice = userCouponInfo.getNotice();
                notice = notice.replaceAll(";", "\n");
                userCouponInfo.setNotice(notice);
                String matters = userCouponInfo.getMatters();
                matters = matters.replaceAll(";", "\n");
                userCouponInfo.setMatters(matters);
            }
            String paytype = redisUtils.get(String.valueOf(userId) + "paytype");
            if (paytype != null) {
                map.put("paytype", paytype);
            } else {
                map.put("paytype", -1);
            }
            //判断是否有优惠券
            if (uCIList.size() > 0) {
                //用户有优惠券
                map.put("type", 1);
                map.put("uCIList", uCIList);
            } else {
                //没有优惠券
                map.put("type", 0);
                map.put("uCIList", uCIList);
            }
        } catch (RuntimeException e) {
        }
        return map;
    }

    /**
     * 日期描述
     *
     * @param endTime 结束时间
     * @return java.lang.String
     * @author Wang Zhiyuan
     * @date 2018/5/9
     */
    public String getDayDescribe(Date endTime) {
        Date newTime = new Date();
        long day = (endTime.getTime() - newTime.getTime()) / (24 * 60 * 60 * 1000);
        if (day <= 1) {
            return "今日到期";
        }
        if (day > 1 && day <= 2) {
            return "明日到期";
        }
        if (day > 2 && day <= 3) {
            return "还剩3日到期";
        }
        return "";
    }

    /**
     * 可以使用的复合时间条件的优惠券
     *
     * @param userOrder         用户订单信息
     * @param productGlobalList 订单内详细商品
     * @return
     */
    @Override
    public Map getUseUserCouponInfo(UserOrder userOrder, String productGlobalList, String areaName) {
        Map map = new HashMap();
        //获取符合时间条件可用的优惠券
        try {
            List<UserCouponInfo> uCIList = userCouponDao.getUseUserCouponInfo(userOrder.getUserId());
            List<UserCouponInfo> uCIList2 = new ArrayList<>();
            //获取用户订单的商品信息
            List<UserOrderLine> productGlobalInfo = JSON.parseArray(productGlobalList, UserOrderLine.class);
            int companyPayment = userOrderDao.getCompanyPayment(userOrder.getUserId());
            UserQuota userQuota = new UserQuota();
            UserQuota userQuota1 = new UserQuota();
            userQuota1.setUserId(0);
            UserFamilyPayRelation userFamilyPayRelation = new UserFamilyPayRelation();
            UserFamilyPayRelation userFamilyPayRelation1 = new UserFamilyPayRelation();
            try {
                userFamilyPayRelation = familyPayDao.iFFamilyPay(userOrder.getUserId());
            } catch (Exception e) {
                userFamilyPayRelation = null;
            }
            if (userFamilyPayRelation != null) {
                map.put("userFamilyPayRelation", userFamilyPayRelation);
            } else {
                userFamilyPayRelation1.setUserId(0);
                map.put("userFamilyPayRelation", userFamilyPayRelation1);
            }
            if (companyPayment == 1) {
                try {
                    userQuota = userCouponDao.getUserQuota(userOrder.getUserId());
                } catch (Exception e) {
                    userQuota = null;
                }
                if (userQuota != null) {
                    map.put("balance", userQuota.getTotalQuota().subtract(userQuota.getConsumeQuota()));
                    map.put("quotaType", userQuota.getQuotaType());
                    map.put("userQuota", userQuota);
                } else {
                    map.put("userQuota", userQuota1);
                }
            } else {
                map.put("balance", 0);
                map.put("quotaType", 3);
                map.put("userQuota", userQuota1);
            }
            long areaId = userCouponDao.getAreaId(areaName);
            // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            //Date date=sdf.parse(sdf.format(new Date()));
            for (int i = 0; i < uCIList.size(); i++) {
                //获取使用规则并转化成实体类
                UseRule useRule = JSON.parseObject(uCIList.get(i).getUseRule(), UseRule.class);
                Integer qualifiedMoney = useRule.getQualifiedMoney().intValue();
                //满足区域id或者没有区域id的限制
                if (areaId == useRule.getAreaId() || useRule.getAreaId() == 0) {
                    //符不符合价格
                    if (userOrder.getAmount() >= qualifiedMoney || qualifiedMoney == 0) {
                        //符不符合时间
                        // if((date.after(useRule.getStartTime()) && date.before(useRule.getEndTime()))||(useRule.getStartTime()==null &&useRule.getEndTime()==null)||(date.after(useRule.getStartTime()) &&useRule.getEndTime()==null)||(useRule.getStartTime()==null && date.before(useRule.getEndTime()))) {
                        //商品订单里面含不含必买商品
                        if (useRule.getProductGlobalId() != 0) {
                            for (int j = 0; j < productGlobalInfo.size(); j++) {
                                if (productGlobalInfo.get(j).getProductGlobalId() == useRule.getProductGlobalId()) {
                                    uCIList2.add(uCIList.get(i));
                                }
                            }
                        } else {//没有限制必买商品的话
                            uCIList2.add(uCIList.get(i));
                        }
                        //  }
                    }
                }
            }

            try {
                String payType = redisUtils.get(String.valueOf(userOrder.getUserId()) + "paytype");
                if (payType != null) {
                    map.put("paytype", Integer.valueOf(payType));
                } else {
                    map.put("paytype", -1);
                }
                if (uCIList2.size() > 0) {
                    map.put("type", 1);
                    map.put("uCIList2", uCIList2);
                    map.put("uCIList2Row", uCIList2.size());
                } else {
                    map.put("type", 0);
                    map.put("uCIList2", uCIList2);
                    map.put("uCIList2Row", uCIList2.size());
                }
            } catch (Exception e) {
                map.put("paytype", -1);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public int updateCoupon(long userId, long couponId, int status) {
        return userCouponDao.updateCoupon(userId, couponId, status);
    }

    /**
     * 邀请注册的链接
     *
     * @param userId 用户id
     * @param mb     手机号
     * @param code   验证码
     * @return
     */
    @Override
    public Map inviteRegister(long userId, String mb, String code) {
        Map map = new HashMap();
        try {
            String i = redisUtils.get(mb);
            String s = code;
            if (i != null) {
                if (i.equals(s)) {
                    if (userLoginDao.checkUserMb(mb) == null) {
                        redisUtils.del(mb);
                        //在网页上面注册
                        long newUserId = IDGenerator.generate("User", 0);
                        UserRegistered userRegistered = new UserRegistered();
                        userRegistered.setUserId(newUserId);
                        userRegistered.setParentUserId(0);
                        userRegistered.setUserName(mb);
                        userRegistered.setLoginNo(mb);
                        userRegistered.setPassWord("");
                        userRegistered.setPayPassWord("");
                        userRegistered.setUserType(1);
                        userRegistered.setmB(mb);
                        userRegistered.setEmail("");
                        userRegistered.setIntegral(0);
                        userRegistered.setBalance(0);
                        userRegistered.setUsableBalance(0);
                        userRegistered.setLockBalance(0);
                        //根据id生成邀请码
                        String invitationCode = Long.toHexString(newUserId);
                        //邀请码
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
                        userRegisteredDao.add(userRegistered);
                        UserInvitation userInvitation = new UserInvitation();
                        //根据邀请人userId生成邀请表id
                        long invitationId = IDGenerator.generateById("invitationId", userId);
                        //邀请表的id 应该是一个随机生成的邀请码
                        userInvitation.setInvitationId(invitationId);
                        //邀请人的id
                        userInvitation.setUserId(userId);
                        //被邀请人的id
                        userInvitation.setInvitedUserId(newUserId);
                        //被邀请的类型 1 通过填写邀请码来邀请 2,通过邀请注册分享链接的方式
                        userInvitation.setInviteType(2);
                        //邀请方式的描述
                        userInvitation.setInviteInfo("分享链接邀请");
                        //将邀请信息插入邀请表
                        int count = insertInvitationCodeDao.insertUserInvitation(userInvitation);
                        //十块钱的优惠券
                        int count1 = getUserCoupon(233828089857L, newUserId);
                        //五块钱的优惠券
                        int count2 = getUserCoupon(233838379009L, newUserId);
                        int count3 = getUserCoupon(233838379009L, newUserId);
                        int count4 = count1 + count2 + count3;
                        if (count4 == 6) {
                            //成功注册
                            map.put("returnState", 1);
                        } else {
                            map.put("returnState", 2);
                        }
                    } else {
                        map.put("returnState", 3);
                    }
                } else {
                    //验证码错误
                    map.put("returnState", 0);
                }
            } else {
                //验证码错误
                map.put("returnState", 0);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();

        }
        return map;
    }

    public int getUserCoupon(long couponDefinitionId, long userId) {
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
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 验证手机号是否注册没有注册的时候给用户发送验证码
     *
     * @param mb 手机号
     * @return
     * @throws IOException
     */
    public Map checkMb(String mb) throws IOException {
        Map map = new HashMap();
        if (userLoginDao.checkUserMb(mb) == null) {
            //没有被注册
            RandomNum randomNum = new RandomNum();
            String code = randomNum.random(4);
            System.out.println("验证码:" + code);
            System.out.println(">>>>>>>>>>>><<<<<<" + mb);
            String str = Long.toString(Long.parseLong(mb), 12) + 5;
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
                        map.put("succeed", 1);
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
                    map.put("succeed", 1);
                }
            }
        } else {
            //已经被注册
            map.put("succeed", 0);
        }
        return map;
    }

    /**
     * 邀请用户奖励
     *
     * @param userOrderId
     * @return
     */
    @Override
    public Map InviteRewards(long userOrderId) {
        Map map = new HashMap();
        UserInvitation userInvitation = userCouponDao.getUserInvitation(userOrderId);
        long rewardId = IDGenerator.generateById("rewardId", userInvitation.getUserId());
        long invitationId = userCouponDao.getInvitationId(userInvitation.getInvitedUserId(), userInvitation.getUserId());
        BigDecimal value = new BigDecimal("20.000000");
        UserReward userReward = new UserReward();
        userReward.setRewardId(rewardId);
        userReward.setUserId(userInvitation.getUserId());
        userReward.setSourceType("userInvitation");
        userReward.setSourceId(invitationId);
        userReward.setCreateTime(new Date());
        userReward.setRewardType(2);
        userReward.setRewardValue(value);
        userReward.setReward("20元");
        if (userInvitation == null) {
            map.put("returnState", 0);
        } else {
            int x = 0;
            for (int i = 0; i < 4; i++) {
                int count = getUserCoupon(233838379008L, userInvitation.getUserId());
                x = x + count;
            }
            System.out.println(x);
            if (x == 8) {
                userCouponDao.insertUserReward(userReward);
                map.put("returnState", 1);
            } else {
                map.put("returnState", 2);
            }
        }
        return map;
    }

    /**
     * 获取发现分享数据
     *
     * @param appDiscoverId
     * @return
     */
    @Override
    public ResponseInfo appDiscoverShare(long appDiscoverId) {
        Map map = appDiscoverDao.appDiscoverShare(appDiscoverId);
        ResponseInfo responseInfo = new ResponseInfo();
        if (map != null) {
            map.put("shareImageUrl", clientSetting.getResource() + map.get("shareImageUrl").toString());
            map.put("imageUrl", clientSetting.getResource() + map.get("imageUrl").toString());
            responseInfo.setCode(1);
            responseInfo.setData(map);
            return responseInfo;
        }
        responseInfo.setCode(1);
        responseInfo.setData(map);
        return responseInfo;
    }

    /**
     * 用户优惠券详情
     *
     * @param x        经线
     * @param y        纬线
     * @param areaName 城市名称
     * @param userId   用户ID
     * @param couponId 优惠券定义表ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/5/10
     */
    @Override
    public ResponseInfo userCouponDetails(String x, String y, String areaName, long couponId, long userId) {
        Map userCouponInfoMap = userCouponDao.getUserCouponInfoByCouponId(userId, couponId);
        //使用须知
        String notice = userCouponInfoMap.get("notice").toString();
        String[] noticeArr = notice.split(";");
        notice = notice.replaceAll(";", "\n");
        //注意事项
        String matters = userCouponInfoMap.get("matters").toString();
        String[] mattersArr = matters.split(";");
        matters = matters.replaceAll(";", "\n");
        UseRule useRule = JSON.parseObject(userCouponInfoMap.get("useRule").toString(), UseRule.class);
        Machine machine = homePageDao.getNearMachine(areaName, BigDecimal.valueOf(Double.valueOf(x)), BigDecimal.valueOf(Double.valueOf(y)));
        List<ProductInfo> productInfoList = homePageDao.getProductInfo(machine.getMachineId());
        List<ProductInfo> productInfoList1 = new ArrayList<>();
        ProductInfo productInfo1 = new ProductInfo();
        productInfoList1.add(productInfo1);
        long areaId = userCouponDao.getAreaId(areaName);
        for (ProductInfo productInfo : productInfoList) {
            double onlinePrice = productInfo.getOnLinePrice() / 100;
            boolean couponUserRuleCheck = couponUserRuleCheck(useRule, areaId, onlinePrice, productInfo.getProductGlobalId());
            long createTime = productInfo.getCreateTime().getTime();
            long createTime1 = 0L;
            if (couponUserRuleCheck) {
                try {
                    createTime1 = productInfoList1.get(0).getCreateTime() == null ? 0 : productInfoList1.get(0).getCreateTime().getTime();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (createTime < createTime1 || createTime1 == 0) {
                    productInfo.setOnLinePrice(productInfo.getOnLinePrice() / 100);
                    productInfoList1.add(0, productInfo);

                }
            }
        }
        Map machineInfoMap = new HashMap();
        double distance = Distance.GetDistance(BigDecimal.valueOf(Double.valueOf(x)), BigDecimal.valueOf(Double.valueOf(y)), machine.getX(), machine.getY());
        machineInfoMap.put("machineId", machine.getMachineId());
        machineInfoMap.put("machineAddress", machine.getAddress());
        machineInfoMap.put("distance", distance);
        machineInfoMap.put("x", machine.getX());
        machineInfoMap.put("y", machine.getY());
        machineInfoMap.put("inMaintenance", machine.isInMaintenance());
        productInfoList1.get(0).setImage60(clientSetting.getResource() + productInfoList1.get(0).getImage60());
        productInfoList1.get(0).setImageUrl(clientSetting.getResource() + productInfoList1.get(0).getImageUrl());
        Map map = new HashMap();
        map.put("machine", machineInfoMap);
        map.put("productInfo", productInfoList1.get(0));
        map.put("noticeArr", noticeArr);
        map.put("mattersArr", mattersArr);
        map.put("notice", notice);
        map.put("matters", matters);
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(1);
        responseInfo.setData(map);
        return responseInfo;
    }

    /**
     * 优惠券规则检测
     *
     * @param useRule
     * @param areaId
     * @param amount
     * @param productGlobalId
     * @return boolean
     * @author Wang Zhiyuan
     * @date 2018/5/10
     */
    public boolean couponUserRuleCheck(UseRule useRule, long areaId, double amount, long productGlobalId) {
        long useRuleAreaId = useRule.getAreaId();
        double qualifiedMoney = useRule.getQualifiedMoney().doubleValue();
        long userRuleProductGlobalId = useRule.getProductGlobalId();
        //地区限制
        if (areaId != useRuleAreaId && useRuleAreaId != 0) {
            return false;
        }
        //金额限制
        if (amount < qualifiedMoney && qualifiedMoney != 0) {
            return false;
        }
        if (userRuleProductGlobalId != 0 && userRuleProductGlobalId != productGlobalId) {
            return false;
        }
        return true;
    }

}

