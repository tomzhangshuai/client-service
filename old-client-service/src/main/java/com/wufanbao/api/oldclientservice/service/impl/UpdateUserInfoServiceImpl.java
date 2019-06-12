package com.wufanbao.api.oldclientservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.dao.UpdateUserInfoDao;
import com.wufanbao.api.oldclientservice.dao.UserGradeDao;
import com.wufanbao.api.oldclientservice.dao.UserIntegralDao;
import com.wufanbao.api.oldclientservice.dao.UserOrderDao;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.entity.UserIntegralLog;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import com.wufanbao.api.oldclientservice.service.UpdateUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:WangZhiyuan
 * Data:2017/08/02
 * Time:14:35
 */
@Service
public class UpdateUserInfoServiceImpl implements UpdateUserInfoService {
    @Autowired
    private UpdateUserInfoDao dao;
    @Autowired
    private UserIntegralDao userIntegralDao;
    @Autowired
    private UserGradeDao userGradeDao;
    @Autowired
    private UserOrderDao userOrderDao;

    @Override
    public int updateUserName(UserRegistered vo) {
        int count = dao.updateUserName(vo);
        return count;
    }

    @Override
    public int updateUserMb(UserRegistered vo) {
        int count = dao.updateUserMb(vo);
        return count;
    }

    @Override
    public int updateUserPayPassword(UserRegistered vo) {
        int count = dao.updateUserPayPassword(vo);
        return count;
    }

    @Override
    public int updateUserPassword(UserRegistered vo) {
        int count = dao.updateUserPassword(vo);
        return count;
    }

    /**
     * 根据手机号修改密码
     *
     * @param mB
     * @return
     */
    @Override
    public int updateUserPasswordByMb(String mB, String passWord) {
        return dao.updateUserPasswordByMb(mB, passWord);
    }

    /**
     * 更新用户信息
     *
     * @param sex
     * @param birthday
     * @param breakFast
     * @param lunch
     * @param dinner
     * @return
     */
    @Override
    @Transactional
    public ResponseInfo updateUserSex(int sex, Date birthday, Date breakFast, Date lunch, Date dinner, long userId) {
        System.out.println(sex + " " + birthday + " " + breakFast + " " + lunch + " " + dinner);
        int count = dao.updateUserSex(sex, birthday, breakFast, lunch, dinner, userId);
        // int count=dao.updateUserSex(sex,birthday,breakFast,lunch,dinner,userId);
        ResponseInfo responseInfo = new ResponseInfo();
        if (count > 0) {
            String sourceType = userGradeDao.getUserGradeLog(userId, "用户完善信息");
            if (sourceType == null) {
                int count3 = userIntegralDao.integralGrowUp(userId, 10);
                //用户积分日志
                long integralLogId = IDGenerator.generateById("integralLogId", userId);
                int count4 = userIntegralDao.insertIntegralLog(integralLogId, userId, 10, "用户完善信息", userId, "用户完善信息获取积分");
                //用户及奖励表id
                long rewardId = IDGenerator.generateById("rewardId", userId);
                //10积分
                BigDecimal integral = BigDecimal.valueOf(10);
                int count7 = userOrderDao.awardLog(rewardId, userId, "appDiscover", userId, new Date(), integral, 4, "浏览发现");//用户奖励表
                //用户成长值增长
                int count5 = userGradeDao.gradeGrowUp(userId, 10);
                //用户成长日志
                long userGradeLogId = IDGenerator.generateById("userGradeLogId", userId);
                int count6 = userGradeDao.insertGradeLog(userGradeLogId, userId, 10, "用户完善信息", userId, "用户完善信息成长值");
                if (count3 + count4 + count5 + count6 + count7 == 5) {
                    responseInfo.setCode(1);
                    return responseInfo;
                } else {
                    responseInfo.setCode(1020);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return responseInfo;
                }
            } else {
                responseInfo.setCode(1);
                return responseInfo;
            }
        } else {
            responseInfo.setCode(1020);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        //用户积分增长
        return responseInfo;
    }

    /**
     * 用户信息完善程度
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseInfo perfectDegree(long userId) {
        UserRegistered userRegistered = dao.perfectDegree(userId);
        ResponseInfo responseInfo = new ResponseInfo();
        Map map = new HashMap();
        if (userRegistered != null) {
            if (userRegistered.getSex() != 0 && userRegistered.getBirthday() != null && userRegistered.getBreakFast() != null && userRegistered.getLunch() != null && userRegistered.getDinner() != null) {
                responseInfo.setCode(1);
            } else {
                responseInfo.setCode(1030);
            }
        } else {
            responseInfo.setCode(1031);
        }
        return responseInfo;
    }

    /**
     * 用户积分使用记录
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseInfo getUserIntegralLog(long userId) {
        List<UserIntegralLog> userIntegralLogList = userIntegralDao.getUserIntegralLog(userId);
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(1);
        responseInfo.setData(userIntegralLogList);
        return responseInfo;
    }

    public Object getuuu(long userId, long userOrderId) {
        //用户添加积分
        int quantity = 0;
        int count3 = userIntegralDao.integralGrowUp(userId, quantity);
        long integralLogId = IDGenerator.generateById("integralLogId", userId);
        int count4 = userIntegralDao.insertIntegralLog(integralLogId, userId, quantity, "userOrder", userOrderId, "用户订单完成获取积分");
        long userGradeLogId = IDGenerator.generateById("userGradeLogId", userId);
        int count6 = userGradeDao.insertGradeLog(userGradeLogId, userId, quantity, "userOrder", userOrderId, "用户订单完成获取成长值");
        //10积分
        BigDecimal integral = BigDecimal.valueOf(quantity);
        //用户及奖励表id
        long rewardId = IDGenerator.generateById("rewardId", userId);
        int count7 = userOrderDao.awardLog(rewardId, userId, "appDiscover", userId, new Date(), integral, 4, "订单完成");//用户奖励表
        return null;
    }


}
