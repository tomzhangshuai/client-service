package com.wufanbao.api.oldclientservice.controller.RabbitMQ;//package com.wufanbao.api.oldclientservice.controller.RabbitMQ;
//
//import com.alibaba.fastjson.JSON;
//import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
//import com.wufanbao.api.oldclientservice.dao.UserCouponDao;
//import com.wufanbao.api.oldclientservice.entity.*;
//import org.json.JSONObject;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageListener;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//*
// * User:Wangshihao
// * Date:2017-10-11
// * Time:15:32
//
//
//public class InviteRewardsImpl implements MessageListener {
//
//*
//     * 邀请用户奖励
//     * @param
//     * @return
//
//
//    @Override
//    public void onMessage(Message message) {
//        String sss = new String(message.getBody());
//        JSONObject jsonObject = new JSONObject(sss);
//        String orderId = jsonObject.getString("orderId");
//        Map map=new HashMap();
//        UserInvitation userInvitation=userCouponDao.getUserInvitation(Long.valueOf(orderId));
//        long rewardId= IDGenerator.generateById("rewardId",userInvitation.getUserId());
//        long invitationId=userCouponDao.getInvitationId(userInvitation.getInvitedUserId(),userInvitation.getUserId());
//        BigDecimal value= new BigDecimal("20.000000");
//        UserReward userReward=new UserReward();
//        userReward.setRewardId(rewardId);
//        userReward.setUserId(userInvitation.getUserId());
//        userReward.setSourceType("userInvitation");
//        userReward.setSourceId(invitationId);
//        userReward.setCreateTime(new Date());
//        userReward.setRewardType(2);
//        userReward.setRewardValue(value);
//        userReward.setReward("20元");
//        if (userInvitation==null){
//            map.put("returnState",0);
//        }else{
//            int x=0;
//            for (int i = 0; i <4 ; i++) {
//                int count=getUserCoupon(233838379008l,userInvitation.getUserId());
//                x=x+count;
//            }
//            System.out.println(x);
//            if (x==8){
//                userCouponDao.insertUserReward(userReward);
//                map.put("returnState",1);
//            }else{
//                map.put("returnState",2);
//            }
//        }
//    }
//}
