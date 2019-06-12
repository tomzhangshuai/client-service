package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.Tool.RandomNum;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.controller.SMSInterface;
import com.wufanbao.api.oldclientservice.dao.*;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.FamilyPayService;
import com.wufanbao.api.oldclientservice.service.TakeMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class FamilyPayServiceImpl implements FamilyPayService {
    @Autowired
    private UserLoginDao userLoginDao;
    @Autowired
    private FamilyPayDao familyPayDao;
    @Autowired
    private UpdateUserInfoDao updateUserInfoDao;
    @Autowired
    private UserCapitalLogDao userCapitalLogDao;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private UserRegisteredDao userRegisteredDao;
    @Autowired
    private GetUserInfoDao getUserInfoDao;
    @Autowired
    private TakeMealService takeMealService;
    @Autowired
    private ClientSetting clientSetting;

    /**
     * 绑定用户
     *
     * @param mb     手机号码
     * @param userId 用户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     */
    @Override
    public ResponseInfo userBindingInfo(String mb, long userId) {
        ResponseInfo responseInfo = new ResponseInfo();
        Map map = new HashMap();
        UserRegistered userRegistered = new UserRegistered();
        try {
            //查询子用户是否注册
            userRegistered = userLoginDao.checkUserMb(mb);
        } catch (Exception e) {
            userRegistered = null;
        }
        //准备绑定的子账户存在
        if (userRegistered != null) {
            map.put("userName", userRegistered.getUserName());
            map.put("userId", userRegistered.getUserId());
            //不能绑定自己的账户
            if (userRegistered.getUserId() == userId) {
                responseInfo.setCode(1042);
                responseInfo.setData(map);
                return responseInfo;
            }
            UserFamilyPayRelation userFamilyPayRelation1 = familyPayDao.ifMasterUser(userRegistered.getUserId());
            if (userFamilyPayRelation1 != null) {
                responseInfo.setCode(1044);
                responseInfo.setData(map);
                return responseInfo;
            }

            UserFamilyPayRelation userFamily = new UserFamilyPayRelation();
            try {
                //
                userFamily = familyPayDao.getUserFamilyInfo(userRegistered.getUserId(), userId);
            } catch (Exception e) {
                userFamily = null;
            }
            if (userFamily != null) {
                responseInfo.setCode(1043);
                responseInfo.setData(map);
                return responseInfo;
            }

            long parentUserId = userRegistered.getParentUserId();
            if (parentUserId == 0) {
                UserFamilyPayRelation userFamilyPayRelation = new UserFamilyPayRelation();
                try {
                    userFamilyPayRelation = familyPayDao.getUserFamilyPayInfo(userRegistered.getUserId());
                } catch (Exception e) {
                    userFamilyPayRelation = null;
                }
                if (userFamilyPayRelation == null) {
                    responseInfo.setCode(1);
                    responseInfo.setData(map);
                    return responseInfo;
                } else {
                    responseInfo.setCode(1040);
                    responseInfo.setData(map);
                    return responseInfo;
                }
            } else {
                responseInfo.setCode(1040);
                responseInfo.setData(map);
                return responseInfo;
            }
        } else {
            responseInfo.setCode(1041);
            responseInfo.setData(map);
        }
        return responseInfo;
    }

    /**
     * 给子账户手机号发送验证码
     *
     * @param Mb
     * @return
     * @throws IOException
     */
    @Override
    public ResponseInfo checkMb(String Mb) throws IOException {
        UserRegistered uvo = new UserRegistered();
        uvo.setmB(Mb);
        Map map = new HashMap();
        ResponseInfo responseInfo = new ResponseInfo();
        RandomNum randomNum = new RandomNum();
        String code = randomNum.random(4);
        System.out.println("验证码:" + code);
        System.out.println(">>>>>>>>>>>><<<<<<" + Mb);
        String str = Long.toString(Long.parseLong(Mb), 12) + 6;
        System.out.println(str);
        try {
            String sss = redisUtils.get(str);
            if (Integer.parseInt(sss) < 3) {
                if (redisUtils.get(str) != null) {
                    redisUtils.incr(str);
                    redisUtils.set(Mb, code);
                    redisUtils.expire(Mb, 300);
                    SMSInterface smsInterface = new SMSInterface();
                    Object status = smsInterface.SMS(Mb, code);
                    System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                    responseInfo.setCode(1);
                    return responseInfo;
                }
            } else if (Integer.parseInt(sss) == 3) {
                responseInfo.setCode(1110);
                return responseInfo;
            }
        } catch (Exception e) {
            if (redisUtils.get(str) == null) {
                redisUtils.incr(str);
                redisUtils.expire(str, 300);
                redisUtils.set(Mb, code);
                redisUtils.expire(Mb, 300);
                SMSInterface smsInterface = new SMSInterface();
                Object status = smsInterface.SMS(Mb, code);
                System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                responseInfo.setCode(1);
                return responseInfo;
            }
        }
        responseInfo.setCode(1);
        return responseInfo;
    }

    /**
     * 验证码确认如果成功就插入家庭付成员
     *
     * @param Mb
     * @param code
     * @return
     */
    @Override
    public ResponseInfo checkCode(String Mb, String code, long userId, long sonUserId) {
        ResponseInfo responseInfo = new ResponseInfo();
        BigDecimal ss = BigDecimal.valueOf(0);
        try {
            String i = redisUtils.get(Mb);
            String s = code;
            if (i != null) {
                if (i.equals(s)) {
                    redisUtils.del(Mb);
                    int count = familyPayDao.updateParentId(sonUserId, userId);
                    int count1 = familyPayDao.insertFamilyPayInfo(userId, sonUserId, 0, false, ss, ss, ss, true);
                    count1 = count1 + count;
                    if (count1 == 2) {
                        responseInfo.setCode(1);
                        return responseInfo;
                    } else {
                        responseInfo.setCode(1091);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return responseInfo;
                    }
                } else {
                    responseInfo.setCode(1090);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return responseInfo;
                }
            }
        } catch (RuntimeException e) {

        }
        responseInfo.setCode(1090);
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return responseInfo;
    }

    /**
     * 查询主账户下的子账户信息
     *
     * @param masterUserId
     * @return
     */
    @Override
    public ResponseInfo selectUserFamilyPayInfo(long masterUserId) {
        Long parentId = userRegisteredDao.getUserParentId(masterUserId);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Map> familyPayInfoList = new ArrayList<>();
        Map map = new HashMap();
        try {
            if (parentId == 0 || parentId == null) {
                familyPayInfoList = familyPayDao.selectUserFamilyPayInfo(masterUserId);
                map.put("ifMasterUser", 1);
                map.put("familyPayInfoList", familyPayInfoList);
            } else {
                //查询子账户用户信息
                Map sonUserFamilyInfo = familyPayDao.userFamilyPayInfo(masterUserId);
                if (sonUserFamilyInfo != null) {
                    sonUserFamilyInfo.put("masterPortrait", clientSetting.getResource() + sonUserFamilyInfo.get("masterPortrait").toString());
                }
                map.put("ifMasterUser", 0);
                map.put("sonUserFamilyInfo", sonUserFamilyInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (familyPayInfoList != null) {
            for (int i = 0; i < familyPayInfoList.size(); i++) {
                String portrait = familyPayInfoList.get(i).get("portrait") == null ? "" : familyPayInfoList.get(i).get("portrait").toString();
                familyPayInfoList.get(i).put("portrait", clientSetting.getResource() + portrait);
            }
            responseInfo.setCode(1);
            responseInfo.setData(map);
            return responseInfo;
        }
        return responseInfo;
    }

    /**
     * 删除用户家庭付信息
     *
     * @param userId
     * @param masterUserId
     * @return
     */
    @Override
    public ResponseInfo deleteSonUser(long userId, long masterUserId) {
        ResponseInfo responseInfo = new ResponseInfo();
        int count = familyPayDao.deleteSonUser(userId, masterUserId);
        int count1 = familyPayDao.updateParentId(userId, 0);
        count = count + count1;
        if (count == 2) {
            responseInfo.setCode(1);
            return responseInfo;
        } else {
            responseInfo.setCode(1060);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return responseInfo;
        }
    }

    /**
     * 修改用户账户配置
     *
     * @param quotaType
     * @param limitQuota
     * @param totalQuota
     * @param disabled
     * @param userId
     * @param masterUserId
     * @return
     */
    @Override
    public ResponseInfo updateFamilyPayInfo(Integer quotaType, Boolean limitQuota, BigDecimal totalQuota, Boolean disabled, long userId, long masterUserId) {

        int count = 0;
        try {
            count = familyPayDao.updateFamilyPayInfo(quotaType, limitQuota, totalQuota, BigDecimal.valueOf(0), disabled, userId, masterUserId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseInfo responseInfo = new ResponseInfo();
        Map map = new HashMap();
        if (count > 0) {
            responseInfo.setCode(1);
            return responseInfo;
        } else {
            responseInfo.setCode(1050);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return responseInfo;
        }
    }

    /**
     * 家庭付ss
     *
     * @param userId
     * @param amount
     * @param userOrderId
     * @return
     */
    @Override
    public Map<String, Integer> familyPay(long userId, BigDecimal amount, long userOrderId) {
        Map<String, Integer> map = new HashMap();
        BigDecimal dd = BigDecimal.valueOf(0);
        //获取用户家庭付信息
        Map userFamilyInfo = familyPayDao.getParentBalance(userId);
        //主账户的余额
        BigDecimal masterBalance = BigDecimal.valueOf(Double.parseDouble(userFamilyInfo.get("usableBalance").toString()));
        //主账户Id
        //用户可以使用家庭付余额
        BigDecimal userBalance = BigDecimal.valueOf(Double.parseDouble((userFamilyInfo.get("totalQuota").toString()))).subtract(BigDecimal.valueOf(Double.parseDouble(userFamilyInfo.get("consumeQuota").toString())));
        long masterUserId = Long.parseLong(userFamilyInfo.get("masterUserId").toString());
        //用户家庭付额度类型
        int quotaType = Integer.parseInt(userFamilyInfo.get("quotaType").toString());
        if (userBalance.doubleValue() >= amount.doubleValue() || quotaType == 0) {
            //付款金额小于账户限额 （全额）
            if (amount.doubleValue() <= masterBalance.doubleValue() || quotaType == 0) {
                //修改用户余额
                int count1 = 0;
                if (quotaType != 0) {
                    count1 = familyPayDao.updateUserFamilyPay(userId, amount);
                } else {
                    count1 = 1;
                }
                //修改主账户余额
                int count2 = 0;
                try {
                    count2 = updateUserInfoDao.updateMasterBalance(masterUserId, amount.negate());
                } catch (Exception e) {
                    e.printStackTrace();
                    count2 = 0;
                }
                // 插入主账户消费记录
                long capitalLogId = IDGenerator.generateById("capitalLogId", masterUserId);
                BigDecimal ss = amount.negate();
                //插入主账户消费
                int count3 = userCapitalLogDao.insertUserCapital(capitalLogId, masterUserId, ss, "userOrder", userOrderId, "亲密付");
                int count5 = userOrderDao.updateUserOrderType(userOrderId, 3, dd, amount, new Date(), 4);
                splitUserOrder(userOrderId, userId);
                int count4 = count1 + count2 + count3 + count5;
                if (count4 == 4) {
                    map.put("submit", 1);
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    if (amount.doubleValue() > masterBalance.doubleValue()) {
                        map.put("submit", 2);
                    } else {
                        map.put("submit", 0);
                    }
                }
            } else {
                map.put("submit", 2);
            }
            //全款
            map.put("type", 1);
            return map;
        }
        if (amount.doubleValue() > userBalance.doubleValue() && quotaType != 0) {
            if (userBalance.doubleValue() <= masterBalance.doubleValue()) {
                //修改用户余额
                int count1 = familyPayDao.updateUserFamilyPay(userId, userBalance);
                //修改公司余额
                int count2 = 0;
                try {
                    count2 = updateUserInfoDao.updateMasterBalance(masterUserId, userBalance.negate());
                } catch (Exception e) {
                    count2 = 0;
                }
                // 插入企业付记录
                long capitalLogId = IDGenerator.generateById("capitalLogId", userId);
                //插入主账户消费
                int count3 = userCapitalLogDao.insertUserCapital(capitalLogId, masterUserId, userBalance.negate(), "userOrder", userOrderId, "亲密付");
                int count5 = userOrderDao.updateUserOrderType(userOrderId, 2, amount.subtract(userBalance), amount, null, 0);
                int count4 = count1 + count3 + count2 + count5;
                if (count4 == 4) {
                    map.put("submit", 1);
                } else {
                    map.put("submit", 0);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            } else {
                //家庭月不足
                map.put("submit", 2);
            }
            //部分款
            map.put("type", 2);

        }
        return map;
    }

    /**
     * 确认绑定
     *
     * @param userId      用户ID
     * @param mb          用户手机号
     * @param payPassword 支付密码
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/6/26
     */
    @Override
    public ResponseInfo confirmBindingFamily(long userId, String mb, String payPassword) {
        ResponseInfo responseInfo = new ResponseInfo();
        UserRegistered userRegistered = userRegisteredDao.queryPayPwd(userId, payPassword);
        if (userRegistered == null) {
            responseInfo.setCode(2054);
            return responseInfo;
        }
        BigDecimal totalQuota = BigDecimal.valueOf(600);
        BigDecimal consumeQuota = BigDecimal.valueOf(0);

        if (userRegistered.getParentUserId() != 0) {
            responseInfo.setCode(2052);
            return responseInfo;
        }
        //是否可以查询到用户
        if (userRegistered != null) {
            Long sonUserId = getUserInfoDao.getUserIdByMb(mb);
            if (sonUserId != null) {
                UserFamilyPayRelation userFamilyPayRelation = familyPayDao.iFFamilyPay(sonUserId);
                UserFamilyPayRelation userFamilyPayRelation1 = familyPayDao.ifMasterUser(sonUserId);
                if (userFamilyPayRelation1 != null) {
                    responseInfo.setCode(2055);
                    return responseInfo;
                }
                if (userFamilyPayRelation != null) {
                    responseInfo.setCode(2053);
                    return responseInfo;
                }
                int count = familyPayDao.insertFamilyPayInfo(userId, sonUserId, 2, true, totalQuota, consumeQuota, consumeQuota, true);
                count += familyPayDao.updateParentId(sonUserId, userId);
                if (count != 2) {
                    responseInfo.setCode(2050);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return responseInfo;
                }
            }
        }
        responseInfo.setCode(1);
        return responseInfo;
    }

    /**
     * 解绑用户关系
     *
     * @param userId          用户ID
     * @param relevanceUserId 父ID或者子ID
     * @return com.allpha.entity.ResponseInfo
     */
    @Override
    public ResponseInfo releaseRelationship(long userId, Long relevanceUserId) {
        int count = 0;
        if (relevanceUserId != null) {
            count = familyPayDao.deleteSonUser(relevanceUserId, userId);
            count += familyPayDao.updateParentId(relevanceUserId, 0);
        } else {
            UserRegistered userRegistered = updateUserInfoDao.perfectDegree(userId);
            count = familyPayDao.deleteSonUser(userId, userRegistered.getParentUserId());
            count += familyPayDao.updateParentId(userId, 0);
        }

        ResponseInfo responseInfo = new ResponseInfo();
        if (count == 2) {
            responseInfo.setCode(1);
        } else {
            responseInfo.setCode(2051);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return responseInfo;
    }

    /**
     * 拆分订单
     *
     * @param userOrderId
     * @param userId
     */
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



