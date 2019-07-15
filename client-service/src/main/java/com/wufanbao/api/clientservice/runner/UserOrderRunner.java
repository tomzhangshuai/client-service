package com.wufanbao.api.clientservice.runner;

import com.wufanbao.api.clientservice.common.*;
import com.wufanbao.api.clientservice.entity.Machine;
import com.wufanbao.api.clientservice.entity.ProductPrepare;
import com.wufanbao.api.clientservice.entity.UserOrder;
import com.wufanbao.api.clientservice.service.MachineService;
import com.wufanbao.api.clientservice.service.ProductoffService;
import com.wufanbao.api.clientservice.service.UserOrderService;
import com.wufanbao.api.clientservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class UserOrderRunner extends Thread {

    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private CommonFun commonFun;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private MachineService machineService;
    @Autowired
    private ProductoffService productoffService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //失效订单,退款(90分钟)
    @Scheduled(cron = "0/5 * * * * ? ")
    public void InvodeOrder() throws Exception {
        long userOrderId = 0;
        List<UserOrder> userOrders = userOrderService.getInvalidUserOrder(userOrderId);
        if(userOrders.size()==0){
            return;
        }
        for (UserOrder userOrder:userOrders){
            if (userOrder == null || userOrder.getUserOrderId() <= 0) {
                continue;
            }
            try {
                userOrderService.refundUserOrder(userOrder.getUserOrderId());
            } catch (Exception ex) {
                logger.error(commonFun.getStackTraceInfo(ex));
                continue;
            }
        }
    }
    //自动取消订单（3分钟未支付成功，自动取消）
    @Scheduled(cron = "0/5 * * * * ? ")
    public void CancleOrder() throws Exception {
        String key = "Runner_CancleOrder";
        long userOrderId = 0;
        String userOrderIdStr = redisUtils.get(key);
        if (!StringUtils.isNullOrEmpty(userOrderIdStr)) {
            userOrderId = Long.parseLong(userOrderIdStr);
        }
        //获取待支付订单status=2
        List<UserOrder> userOrders = userOrderService.getToPayUserOrder(userOrderId);
        if(userOrders.size()==0){
            return;
        }
        for (UserOrder userOrder : userOrders){
            if (userOrder == null) {
                continue;
            }
            if (DateUtils.getDiffMinutes(new Date(), userOrder.getCreateTime()) < 3) {
                continue;
            }
            //如果微信或者支付宝支付
            if (userOrder.getPayType() == 2 || userOrder.getPayType() == 3) {
                //查询订单的支付状态,返回status=3的订单；若等0 返回订单；
                userOrder = userOrderService.payQuery(userOrder.getUserOrderId(), userOrder.getPayType());
            }
            if (userOrder.getStatus() >= 3) {
                continue;
            }
            try {
                redisUtils.set(key, String.valueOf(userOrder.getUserOrderId()));
                redisUtils.expire(key, 180);
                userOrderService.cancleUserOrder(userOrder.getUserOrderId());
                redisUtils.del(key);
            } catch (Exception ex) {
                logger.error(commonFun.getStackTraceInfo(ex));
                redisUtils.del(key);
                continue;
            }
        }
    }

    //超过一天餐食制作中的自动退款
    @Scheduled(cron = "0/30 * * * * ? ")
    public void RefundOrder() throws Exception{
        long userOrderId = 0;
        List<UserOrder> userOrders = userOrderService.getMakingUserOrder(userOrderId);
        if(userOrders.size()==0){
            return;
        }
        for (UserOrder userOrder : userOrders) {
            if (userOrder == null || userOrder.getUserOrderId() <= 0) {
                continue;
            }
            if (userOrder.getTakeTime() == null) {
                continue;
            }
            //维护中的机器才可以进行退款
            Machine machine = machineService.getMachine(userOrder.getMachineId());
            if (!machine.getInMaintenance()) {
                continue;
            }
            try {
                userOrderService.refundUserOrder(userOrder.getUserOrderId());
            } catch(Exception ex){
                logger.error(commonFun.getStackTraceInfo(ex));
                continue;
            }
        }
    }
    //与制作库存故障复原
    @Scheduled(cron = "0/30 * * * * ? ")
    public void repaireProductPrepares() throws Exception {
        List<ProductPrepare> productPrepares = productoffService.getProductPrepare();
        if(productPrepares.size()==0){
            return;
        }
        for (ProductPrepare productPrepare : productPrepares) {
            if(productPrepare.getRepairStatus()==1){
                continue;
            }
            long productOffId = productPrepare.getProductOffId();
            long machineId=productoffService.getMachineId(productOffId);
            Machine machine = machineService.getMachine(machineId);
            if (!machine.getInMaintenance()) {
                continue;
            }
            try {
                userOrderService.repaireProductPrepares(productPrepare,machineId);
            } catch(Exception ex){
                logger.error(commonFun.getStackTraceInfo(ex));
                continue;
            }
        }
    }

}
