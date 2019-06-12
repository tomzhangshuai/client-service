package com.wufanbao.api.oldclientservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.oldclientservice.Tool.DateUtil;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.dao.GetUserInfoDao;
import com.wufanbao.api.oldclientservice.dao.InsertInvitationCodeDao;
import com.wufanbao.api.oldclientservice.dao.UserCouponDao;
import com.wufanbao.api.oldclientservice.dao.UserRegisteredDao;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.UserRegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User:Wangshihao
 * Date:2017-08-01
 * Time:11:11
 */

//业务实现类，负责和dao层交互
@Transactional
@Service
public class UserRegisteredServiceImpl implements UserRegisteredService {

    @Autowired
    private UserRegisteredDao userRegisteredDao;
    @Autowired
    private GetUserInfoDao getUserInfoDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private InsertInvitationCodeDao insertInvitationCodeDao;

    @Override
    public Integer add(UserRegistered userRegistered) {
        return userRegisteredDao.add(userRegistered);
    }

    @Override
    public UserRegistered check(String mB) {
        return userRegisteredDao.checkMB(mB);
    }

    @Override
    public int updateBalance(long userId, double balance, double usableBalance) {
        return userRegisteredDao.updateBalance(userId, balance, usableBalance);
    }

    @Override
    public UserRegistered querybalance(long userId) {
        return userRegisteredDao.querybalance(userId);
    }

    @Override
    public UserRegistered queryPayPwd(long userId, String paypassword) {
        return userRegisteredDao.queryPayPwd(userId, paypassword);
    }

    /**
     * 添加一个用户
     *
     * @param mb       手机号
     * @param password 登录密码
     * @return com.wufanbao.api.oldclientservice.tool.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/4/13
     */
    @Override
    public ResponseInfo addUser(String mb, String password, long userId) {
        ResponseInfo responseInfo = new ResponseInfo();
        // 用户ID
        long newUserId = IDGenerator.generate("User", 0);
        //根据id生成邀请码
        String invitationCode = Long.toHexString(newUserId);
        Long user = getUserInfoDao.getUserIdByMb(mb);
        if (user != null) {
            responseInfo.setCode(1013);
            return responseInfo;
        }
        UserInvitation userInvitation = new UserInvitation();
        //根据邀请人userId生成邀请表id
        long invitationId = IDGenerator.generateById("invitationId", newUserId);
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
        count += getUserCoupon(233828089857L, newUserId);
        //五块钱的优惠券
        count += getUserCoupon(233838379009L, newUserId);
        count += getUserCoupon(233838379009L, newUserId);
        //添加一个用户
        count += userRegisteredDao.addUser(userId, 0L, mb, mb, password, "", 1, mb, 0, 0, 0, 0, invitationCode, 1, 0, 1, 0, 0, 0, 0, 0, 1);
        if (count == 8) {
            responseInfo.setCode(1);
            return responseInfo;
        }
        //用户注册失败
        responseInfo.setCode(1012);
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return responseInfo;
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
        userCoupon.setEndTime(DateUtil.getCouponEndTime(validityRules.getActiveTime()));
        int count = userCouponDao.insertUserCoupon(userCoupon);
        int count1 = userCouponDao.gotCoupon(couponDefinitionId);
        int count2 = count + count1;
        return count2;
    }
}
