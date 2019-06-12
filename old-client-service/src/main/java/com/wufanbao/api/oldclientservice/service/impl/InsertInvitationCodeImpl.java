package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.dao.InsertInvitationCodeDao;
import com.wufanbao.api.oldclientservice.entity.UserInvitation;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import com.wufanbao.api.oldclientservice.entity.UserReward;
import com.wufanbao.api.oldclientservice.entity.UserRewardInfo;
import com.wufanbao.api.oldclientservice.service.InsertInvitationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * user:WangZhiyuan
 */
@Service
public class InsertInvitationCodeImpl implements InsertInvitationCodeService {
    @Autowired
    private InsertInvitationCodeDao insertInvitationCodeDao;

    /**
     * 根据用户id去查询邀请成功的用户被邀请
     *
     * @param invitedUserId 邀请码
     * @return List 成功邀请的信息
     */
    @Override
    public List<UserInvitation> getUserInvitation(long invitedUserId) {
        return insertInvitationCodeDao.getUserInvitation(invitedUserId);
    }

    /**
     * 填写邀请码
     *
     * @param invitationCode 邀请人的邀请码
     * @param userId         被邀请人的用户id
     * @return map 执行结果
     */
    @Override
    @Transactional
    public Map InsertInvitationCode(String invitationCode, long userId) {
        //根据邀请码获取邀请用户的userId
        UserRegistered userRegistered = insertInvitationCodeDao.getUserId(invitationCode);
        long initiativeUserId;
        try {
            initiativeUserId = userRegistered.getUserId();
        } catch (Exception e) {
            initiativeUserId = 0;
        }
        //判断邀请码是否存在
        Map map = new HashMap();
        if (initiativeUserId == 0) {
            //不存在
            map.put("insetType", 2);
        } else {
            //判断是不是用户自己的邀请码
            if (initiativeUserId == userId) {
                //不能使用自己的邀请码
                map.put("insetType", 3);
            } else {
                UserInvitation userInvitation = new UserInvitation();
                long invitationId = IDGenerator.generateById("invitationId", initiativeUserId);//根据邀请人userId生成邀请表id
                userInvitation.setInvitationId(invitationId);//邀请表的id 应该是一个随机生成的邀请码
                userInvitation.setUserId(initiativeUserId);//邀请人的id
                userInvitation.setInvitedUserId(userId);//被邀请人的id
                userInvitation.setInviteType(1);//被邀请的类型 1 通过填写邀请码来邀请
                userInvitation.setInviteInfo("填写邀请码邀请");//邀请方式的描述
                int count = insertInvitationCodeDao.insertUserInvitation(userInvitation);//将邀请信息插入邀请表中
                //判断插入是否成功
                if (count > 0) {
                    //插入成功
                    map.put("insetType", 1);
                } else {
                    //插入失败
                    map.put("insetType", 0);
                }
            }
        }
        return map;
    }

    /**
     * 奖励轮播
     *
     * @return
     */
    @Override
    public Map rewardRotation() {
        List<UserReward> userRewardList = insertInvitationCodeDao.getRewardInfo();
        Map map = new HashMap();
        List list = new ArrayList();
        for (int i = 0; i < userRewardList.size(); i++) {
            String mb = userRewardList.get(i).getSourceType();//手机号
            String afterFour = mb.substring(mb.length() - 4, mb.length());//手机号后四位
            String reward = userRewardList.get(i).getReward();//优惠券金额
            System.out.println(reward);
            String head = "尾号";
            String body = "的用户邀请了1位好友 获得";
            String foot = "优惠券";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(head);
            stringBuilder.append(afterFour);
            stringBuilder.append(body);
            stringBuilder.append(foot);
            list.add(stringBuilder);
        }
        if (list.size() > 0) {
            map.put("rewardList", list);
            map.put("returnState", 1);
        } else {
            map.put("rewardList", list);
            map.put("returnState", 0);
        }
        return map;
    }

    /**
     * 奖励记录
     *
     * @param userId
     * @return
     */
    @Override
    public Map rewardRecord(long userId) {
        int num = 0;
        int rewardValue = 0;
        Map map = new HashMap();
        List<UserRewardInfo> userRewardInfoList = insertInvitationCodeDao.rewardRecord(userId);
        for (int i = 0; i < userRewardInfoList.size(); i++) {
            if (userRewardInfoList.get(i).getCreateTime() != null) {
                userRewardInfoList.get(i).setDescribe("完成首单");
                num++;
                rewardValue = rewardValue + (int) Math.floor(userRewardInfoList.get(i).getRewardValue().doubleValue());
            } else {
                userRewardInfoList.get(i).setDescribe("未完成首单");
                userRewardInfoList.get(i).setReward("未完成");
            }
        }
        if (userRewardInfoList.size() > 0) {
            map.put("userRewardInfoList", userRewardInfoList);
            map.put("rewardValue", rewardValue);
            map.put("num", num);
            map.put("returnState", 1);
        } else {
            map.put("userRewardInfoList", userRewardInfoList);
            map.put("rewardValue", rewardValue);
            map.put("num", num);
            map.put("returnState", 0);
        }
        return map;
    }
}
