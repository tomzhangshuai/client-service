package com.wufanbao.api.oldclientservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.Tool.PrizeCount;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.dao.*;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.UserGrowUpAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.apache.commons.codec.binary.Base64;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.nio.ByteBuffer;


@Transactional
@Service
/**
 * @program: alphaWuFan
 * @description: 请求工具类
 * @author: Wang Zhiyuan
 * @create:
 **/
public class UserGrowUpAwardServiceImpl implements UserGrowUpAwardService {
    @Autowired
    private SignInDao signInDao;
    @Autowired
    private UserIntegralDao userIntegralDao;
    @Autowired
    private UserGradeDao userGradeDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private GetUserInfoDao getUserInfoDao;
    @Autowired
    private ClientSetting clientSetting;

    private static ByteBuffer buffer = ByteBuffer.allocate(8);

    @Override
    public ResponseInfo userSignIn(long userId) {
        ResponseInfo responseInfo = new ResponseInfo();
        //签到
        int count = signInDao.signIn(userId);
        //用户签到信息
        Map userSignIn = signInDao.selectUserSignIn(userId);
        //用户能获得多少积分
        int integral = userIntegral(Integer.valueOf(userSignIn.get("continuousSignedDays").toString()));
        //签到记录
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-MM-dd");
        String signedDay = simpleDateFormat.format(new Date());
        int count2 = 0;
        try {
            count2 = signInDao.signInLog(userId, signedDay, integral);
        } catch (Exception e) {
            responseInfo.setCode(1011);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return responseInfo;
        }
        //用户积分增长
        int count3 = userIntegralDao.integralGrowUp(userId, integral);
        //用户积分日志
        long integralLogId = IDGenerator.generateById("integralLogId", userId);
        int count4 = userIntegralDao.insertIntegralLog(integralLogId, userId, integral, "用户签到", userId, "用户签到获取积分");
        //用户成长值增长
        int count5 = userGradeDao.gradeGrowUp(userId, integral);
        //用户成长日志
        long userGradeLogId = IDGenerator.generateById("userGradeLogId", userId);
        int count6 = userGradeDao.insertGradeLog(userGradeLogId, userId, integral, "用户签到", userId, "用户签到获取成长值");
        //预期结果
        int num = 5;
        if (count > 0) {
            int count7 = count2 + count3 + count4 + count5 + count6;
            if (count7 == num) {
                responseInfo.setCode(1);
            } else {
                responseInfo.setCode(1010);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } else {
            responseInfo.setCode(1010);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return responseInfo;
    }

    /**
     * 用户签到信息
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseInfo getUserSignIn(long userId) {
        ResponseInfo responseInfo = new ResponseInfo();
        //用户签到信息
        Map userSignIn = signInDao.selectUserSignIn(userId);
        System.out.println(userSignIn);
        if (userSignIn != null) {
            userSignIn.put("integral", userIntegral(Integer.valueOf(userSignIn.get("continuousSignedDays").toString())));
            responseInfo.setCode(1);
            responseInfo.setMessage("成功");
            responseInfo.setData(userSignIn);
        } else {
            responseInfo.setCode(1011);
            responseInfo.setMessage("获取签到信息出错");
        }
        return responseInfo;
    }

    /**
     * @Description: 全部用户兑换记录
     * @Param: []
     * @return: com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     */
    @Override
    public ResponseInfo allIntegralExchange() {
        ResponseInfo responseInfo = new ResponseInfo();
        List<Map> allIntegralExchange = userIntegralDao.allIntegralExchange();
        if (allIntegralExchange != null) {
            for (int i = 0; i < allIntegralExchange.size(); i++) {
                Map map = allIntegralExchange.get(i);
                String portrait = map.get("portrait").toString();
                portrait = clientSetting.getResource() + portrait;
                map.put("portrait", portrait);
            }
            responseInfo.setCode(1);
            responseInfo.setData(allIntegralExchange);
        } else {
            responseInfo.setCode(1);
        }
        return responseInfo;
    }

    /**
     * @param userId
     * @Description: 用户兑换记录
     * @Param: [userId]
     * @return: com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     */
    @Override
    public ResponseInfo userIntegralExchange(long userId) {
        ResponseInfo responseInfo = new ResponseInfo();
        List<Map> userIntegralExchange = userIntegralDao.userIntegralExchange(userId);
        if (userIntegralExchange != null) {
            responseInfo.setCode(1);
            responseInfo.setData(userIntegralExchange);
        } else {
            responseInfo.setCode(1);
        }
        return responseInfo;
    }

    /**
     * 积分商城商品
     *
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/2/26
     */
    @Override
    public ResponseInfo integralCommodity() {
        ResponseInfo responseInfo = new ResponseInfo();
        List<Map> integralCommodity = userIntegralDao.integralCommodity();
        for (Map map : integralCommodity) {
            map.put("imageUrl", clientSetting.getResource() + map.get("imageUrl").toString());
        }
        if (integralCommodity != null) {
            responseInfo.setCode(1);
            responseInfo.setData(integralCommodity);
        } else {
            responseInfo.setCode(1);
        }
        return responseInfo;
    }

    /**
     * @param userId
     * @param integralCommodityId
     * @param amount
     * @param quantity
     * @Description: 兑换积分商城商品
     * @Param: [userId, integralCommodityId, amount, quantity]
     * @return: com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     */
    @Override
    public ResponseInfo exchangeCommodity(long userId, long integralCommodityId, BigDecimal amount, int quantity) {
        ResponseInfo responseInfo = new ResponseInfo();
        if (checkPrice(integralCommodityId, amount)) {
            Map commodityMap = userIntegralDao.getIntegralCommodity(integralCommodityId);
            //商品类型
            int commodityType = Integer.parseInt(commodityMap.get("commodityType").toString());
            //商品备注
            String description = commodityMap.get("description").toString();
            String definition = commodityMap.get("definition").toString();
            String commodityName = commodityMap.get("commodityName").toString();
            //减少商品
            int count = userIntegralDao.updateCommodity(integralCommodityId, quantity);
            //扣除用户积分
            int count1 = userIntegralDao.integralGrowUp(userId, -amount.intValue());
            long integralExchangeId = IDGenerator.generateById("integralExchangeId", userId);
            //插入用户兑换记录
            int count2 = userIntegralDao.addIntegralExchangeLog(integralExchangeId, userId, integralCommodityId, amount, commodityName);
            long integralLogId = IDGenerator.generateById("integralLogId", userId);
            int count3 = userIntegralDao.insertIntegralLog(integralLogId, userId, -amount.intValue(), "IntegralExchange", integralExchangeId, commodityName);
            if (commodityType == 1) {
                //1 给用户发放优惠券
                userCouponAdd(Long.parseLong(definition), userId);
            }
            //预期结果
            int num = 4;
            if (count + count1 + count2 + count3 == num) {
                responseInfo.setCode(1);
            } else {
                responseInfo.setCode(1120);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return responseInfo;
    }

    /**
     * 扫描二维码
     *
     * @param userId 用户ID
     * @param tempId 二维码Id
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/3/21
     */
    @Override
    public ResponseInfo scanAQRCode(long userId, String tempId) {
        ResponseInfo responseInfo = new ResponseInfo();
        //判断用户今天有没有获取优惠券
        boolean ifGet = userGradeDao.ifGet(userId, 233838379010L);
        byte[] bytes = Base64.decodeBase64(tempId);
        long tempId1 = byteToLong(bytes);
        if (ifGet) {
            responseInfo.setCode(2031);
            return responseInfo;
        }
        //给用户发放一张优惠券
        int count1 = Coupon(233838379010L, userId);
        if (count1 == 2) {
            //修改二维码状态
            int count = userCouponDao.updateTemp(tempId1, userId);
            if (count == 1) {
                responseInfo.setCode(1);
                return responseInfo;
            } else {
                responseInfo.setCode(2030);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return responseInfo;
            }
        } else {
            responseInfo.setCode(2030);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return responseInfo;
        }
    }

    /**
     * 用户抽奖
     *
     * @param userId 用户Id
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/4/16
     */
    @Override
    public ResponseInfo lottery(long userId) {
        String str = userId + "lottery";
        int strr = Integer.parseInt(redisUtils.get(str) == null ? "0" : redisUtils.get(str));
        ResponseInfo responseInfo = new ResponseInfo();
        Map map = new HashMap();
        if (strr < 8) {
            if (strr == 0) {
                redisUtils.set(str, "0");
                redisUtils.expire(str, todayResidueSecond());
            }
            List<Prize> prizeList = new Prize().listPrize();
            PrizeCount a = new PrizeCount();
            int selected = a.getPrizeIndex(prizeList);
            Prize prize = prizeList.get(selected);
            //扣除用户积分
            int count = userIntegralDao.integralGrowUp(userId, -5);
            long integralLogId = IDGenerator.generateById("integralLogId", userId);
            count += userIntegralDao.insertIntegralLog(integralLogId, userId, -5, "lottery", userId, "抽奖消耗");
            if (count < 2) {
                int integral = getUserInfoDao.getUserInfo(userId).getIntegral();
                map.put("residueDegree", 8 - strr);
                map.put("integral", integral);
                responseInfo.setData(map);
                responseInfo.setCode(2041);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return responseInfo;
            }
            if (prize.getType() == 1) {
                if (userCouponAdd(prize.getCouponId(), userId)) {
                    count += 2;
                }
            }
            if (prize.getType() == 2) {
                count += userIntegralDao.integralGrowUp(userId, prize.getIntegral());
                long integralLogId1 = IDGenerator.generateById("integralLogId", userId);
                count += userIntegralDao.insertIntegralLog(integralLogId1, userId, prize.getIntegral(), "lottery", userId, "抽奖奖励");
            }
            if (!(count == 4)) {
                int integral = getUserInfoDao.getUserInfo(userId).getIntegral();
                map.put("residueDegree", 8 - strr);
                map.put("integral", integral);
                responseInfo.setData(map);
                responseInfo.setCode(2040);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return responseInfo;
            }
            redisUtils.incr(str);
            int integral = getUserInfoDao.getUserInfo(userId).getIntegral();
            map.put("prize", prize);
            map.put("residueDegree", 7 - strr);
            map.put("integral", integral);
            responseInfo.setData(map);
            responseInfo.setCode(1);
            return responseInfo;
        }
        int integral = getUserInfoDao.getUserInfo(userId).getIntegral();
        map.put("residueDegree", 7 - strr);
        map.put("integral", integral);
        responseInfo.setData(map);
        responseInfo.setCode(2042);
        return responseInfo;
    }

    public int todayResidueSecond() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date date = cal.getTime();
        long second = (date.getTime() - System.currentTimeMillis()) / 1000;
        return Integer.parseInt(String.valueOf(second));
    }

    /**
     * 剩余次数
     *
     * @param userId 用户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/5/16
     */
    @Override
    public ResponseInfo residueDegree(long userId) {
        String lottery = redisUtils.get(userId + "lottery");
        int strr = 0;
        if (lottery != null && lottery.length() > 0) {
            strr = Integer.parseInt(lottery);
        }
        int integral = getUserInfoDao.getUserInfo(userId).getIntegral();
        Map map = new HashMap();
        map.put("residueDegree", 8 - strr);
        map.put("integral", integral);
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(1);
        responseInfo.setData(map);
        return responseInfo;
    }

    @Override
    public String resetTheNumberOfDraws(String str) {
        int count = 1;
        try {
            redisUtils.del(str);
        } catch (Exception e) {
            count = 0;
        }
        return String.valueOf(count);
    }

    public static long byteToLong(byte[] buf) {
        long value = 0;
        for (int j = 0; j < buf.length; j++) {
            if (buf[j] == 0) {
                continue;
            }
            if (buf[j] < 0) {
                value |= (256L + buf[j]) << (j * 8);
            } else {
                value |= (long) buf[j] << (j * 8);
            }
        }
        return value;
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
     * @Description: 给用户添加优惠券
     * @Param: [couponDefinitionId, userId]
     * @return: int
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/28
     */
    public boolean userCouponAdd(long couponDefinitionId, long userId) {
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
        //预期结果
        int num = 2;
        if (count2 == num) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 计算优惠券的时间
     * @Param: [actieTime]
     * @return: java.util.Date
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/28
     */
    public Date getCouponEndTime(int activeTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //+activeTime今天的时间加activeTime天
        calendar.add(Calendar.DAY_OF_MONTH, +activeTime);
        Date date = calendar.getTime();
        return date;
    }

    public boolean checkPrice(long integralCommodityId, BigDecimal amount) {
        Map commodityMap = userIntegralDao.getIntegralCommodity(integralCommodityId);
        if (commodityMap == null) {
            return false;
        }
        double amount1 = Double.parseDouble(commodityMap.get("price").toString());
        if (amount.doubleValue() != amount1) {
            return false;
        }
        return true;
    }

    /**
     * 根据连续签到天数获取用户应得积分和成长值
     *
     * @param continuousSignedDays
     * @return
     */
    public int userIntegral(int continuousSignedDays) {
        int day = 5;
        if (continuousSignedDays <= day) {
            switch (continuousSignedDays) {
                case 1:
                    return 1;
                case 2:
                    return 3;
                case 3:
                    return 5;
                case 4:
                    return 7;
                case 5:
                    return 9;
                default:
            }
        }
        return 9;
    }
}
