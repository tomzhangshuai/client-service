package com.wufanbao.api.clientservice.runner;

import com.wufanbao.api.clientservice.common.CommonFun;
import com.wufanbao.api.clientservice.common.Data;
import com.wufanbao.api.clientservice.common.RedisUtils;
import com.wufanbao.api.clientservice.common.StringUtils;
import com.wufanbao.api.clientservice.entity.Machine;
import com.wufanbao.api.clientservice.entity.UserOrderLine;
import com.wufanbao.api.clientservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class MessageSendRunner {
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private CommonFun commonFun;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ProductoffService productoffService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private  MachineService machineService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String templateIdOverDue="kPBCWfVHpGxPdfIzhCLaNWFbmm-gL4FqWn9h_o0GiuY";
    private final String templateIdInvode="ovmRlu308MwvOyrfJQz9bKHTC9uuAQCiWXswZHNZBOk";

    private  final String overDueMessage="客官，小伍正在为您准备餐食，请及时取餐。";
    private  final String invodeMessage="客官，餐食已在取餐口，请及时取餐。";

    //订单即将过期 极光推送
    @Scheduled(cron = "5/20 * * * * ? ")
    public void OverdueOrder() throws  Exception {
        List<Data> userOrders = userOrderService.getOverdueUserOrder();
        if (userOrders.size() == 0) {
            return;
        }
        for (Data userOrder : userOrders) {
            int originateid = Integer.parseInt(userOrder.get("originateId").toString());
            if (originateid == 0 || originateid == 5) {
                continue;
            }
            //微信扫一扫支付
            if (originateid == 4) {
                long userId = Long.parseLong(userOrder.get("userId").toString());
                long machineId=Long.parseLong(userOrder.get("machineId").toString());
                Machine machine = machineService.getMachine(machineId);
                //订单号码
                long userOrderId=Long.parseLong(String.valueOf(userOrder.get("userOrderId")));
                //机器名称
                String machineName=machine.getPutMachineName();
                List<UserOrderLine> userOrderLines=userOrderService.getUserOrderLine(userOrderId);
                if(userOrderLines.size()==0){
                    continue;
                }
                //出餐数量
                int quantityint=0;
                for (UserOrderLine userOrderLine : userOrderLines) {
                    quantityint+=userOrderLine.getQuantity();
                }
                String quantity=String.valueOf(quantityint);
                //取餐号码
//                String takeNo=String.valueOf(userOrder.get("takeNo"));
                String takeNo="无";
                //获取openId
                String openId = userService.getOpenId(userId);
                if (StringUtils.isNullOrEmpty(openId)) {
                    continue;
                }
                try {
                    //消息推送
                    userOrderService.updateMessageStatus(userOrderId);
                    userOrderService.sendTemplateClaim(userOrderId, openId, quantity, machineName, takeNo, templateIdOverDue,overDueMessage);
                    logger.info("pushsuccess" + openId);
                } catch (Exception ex) {
                    logger.info("pushfaile" + userOrderId);
                    logger.error(commonFun.getStackTraceInfo(ex));
                    continue;
                }
            }
            //功能暂时不使用
            //APP内支付
            if (originateid == 1 || originateid == 2 || originateid == 3) {
                Map<String, String> param = new HashMap<>();
                String mb = String.valueOf(userOrder.get("mb"));
                if (StringUtils.isNullOrEmpty(mb)) {
                    continue;
                }
                param.put("id", mb);
                String userOrderIdstr = String.valueOf(userOrder.get("userOrderId"));
                param.put("msg", "订单" + userOrderIdstr + "快过期了，请及时取单");
                param.put("title", "订单信息提醒");
                param.put("type","2");
                param.put("userOrderId", userOrderIdstr);
                try {
                    userOrderService.updateMessageStatus(Long.parseLong(userOrderIdstr));
                    messageService.jpushAll(param);
                    logger.info("pushsuccess" + userOrderIdstr);
                } catch (Exception ex) {
                    logger.info("pushfaile");
                    logger.error(commonFun.getStackTraceInfo(ex));
                    continue;
                }
            }

        }
    }

    //超过5分钟未取餐，微信模板消息推送
    @Scheduled(cron = "0/10 * * * * ? ")
    public void InvodeOrder() throws Exception {
        List<Data> productOffs = productoffService.getProductoff();
        if(productOffs.size()==0){
            return;
        }
        for (Data productOff:productOffs){
            if (Long.parseLong(productOff.get("messageStatus").toString())!=0) {
                continue;
            }
            String productOffId=productOff.get("productOffId").toString();
            if(StringUtils.isNullOrEmpty(productOffId)){
                continue;
            }
            //订单id
            Long userOrderId=Long.parseLong(productOff.get("sourceid").toString());
            if(StringUtils.isNullOrEmpty(userOrderId.toString())){
                continue;
            }
            //出餐数量
            String quantity =String.valueOf(productOff.get("quantity"));
            //机器名称
            String machineName=String.valueOf(productOff.get("putMachineName"));
            if(StringUtils.isNullOrEmpty(quantity)||StringUtils.isNullOrEmpty(machineName)){
                continue;
            }
            //获取手机号和订单来源,openid
            Data userOrder=userOrderService.getMessage(userOrderId);
            if(null==userOrder){
                continue;
            }
            int originateid=Integer.parseInt(userOrder.get("originateid").toString());
            if(originateid==0||originateid==5){
                continue;
            }
            String takeNo=userOrder.get("takeNo").toString();
            if(Integer.parseInt(userOrder.get("status").toString())<4){
                continue;
            }
            //微信扫一扫支付
            if(originateid==4){
                long userId=Long.parseLong(userOrder.get("userId").toString());
                //获取openId
                String openId=userService.getOpenId(userId);
                if(StringUtils.isNullOrEmpty(openId)){
                    continue;
                }
                try {
                    //消息推送
                    productoffService.updateMessageStatus(userOrderId);
                    userOrderService.sendTemplateClaim(userOrderId,openId,quantity,machineName,takeNo,templateIdInvode,invodeMessage);
                    logger.info("取餐提醒推送成功，订单"+originateid);
                } catch (Exception ex) {
                    logger.info("推送失败"+userOrderId);
                    logger.error(commonFun.getStackTraceInfo(ex));
                    continue;
                }
            }
            //APP内支付 暂时不使用
            if(originateid==1||originateid==2||originateid==3){
                String mb=userOrder.get("mb").toString();
                if(StringUtils.isNullOrEmpty(mb)){
                    continue;
                }
                Map<String,String>param=new HashMap<>();
                param.put("id",mb);
                param.put("msg","客官，餐食已在取餐口，请及时取餐！");
                param.put("title","取餐提醒");
                param.put("type","2");
                param.put("userOrderId",userOrderId.toString());
                try {
                    productoffService.updateMessageStatus(userOrderId);
                    messageService.jpushAll(param);
                    logger.info("取餐提醒推送成功，订单"+userOrderId);
                }catch (Exception ex){
                    logger.error(commonFun.getStackTraceInfo(ex));
                    continue;
                }
            }
        }
    }
}
