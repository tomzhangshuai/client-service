package com.wufanbao.api.oldclientservice.controller.RabbitMQ;//package com.wufanbao.api.oldclientservice.controller.RabbitMQ;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.TimerTask;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
///**
// * User:Wangshihao
// * Date:2017-10-10
// * Time:9:41
// *消费接收
// */
//
//public class CouponsService {
//    private static long start;
//
//    public static void main(String[] args)
//    {
//        kkk(1000);
//        kkk(2000);
//        kkk(7000);
//
//    }
//
//    public static void kkk(int num){
//        /**
//         * 使用工厂方法初始化一个ScheduledThreadPool
//         */
//        ScheduledExecutorService newScheduledThreadPool = Executors
//                .newScheduledThreadPool(5);
//
//        TimerTask task1 = new TimerTask()
//        {
//            @Override
//            public void run()
//            {
//                System.out.println("task1 invoked ! "+ (System.currentTimeMillis() - start));
//            }
//        };
//        start = System.currentTimeMillis();
//        newScheduledThreadPool.schedule(task1, num, TimeUnit.MILLISECONDS);
//        //newScheduledThreadPool.schedule(task2, 3000, TimeUnit.MILLISECONDS);
//        newScheduledThreadPool.shutdown();
//    }
//}
