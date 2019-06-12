package com.wufanbao.api.oldclientservice.task;

import com.wufanbao.api.oldclientservice.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@EnableScheduling
public class UserOrderRunner extends Thread {
    @Autowired
    private UserOrderService userOrderService;

    //失效订单
    @Scheduled(cron = "0/5 * * * * ? ")
    public void InvodeOrder() throws Exception {
        try {
            //userOrderService.cancellationOrder();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
