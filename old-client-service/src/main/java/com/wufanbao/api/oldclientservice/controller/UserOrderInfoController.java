package com.wufanbao.api.oldclientservice.controller;


import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.AES_256;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User:WangZhiyuan
 */
@Controller
@RequestMapping("userOrderInfo")
public class UserOrderInfoController {
    @Autowired
    private UserOrderService UserOrderService;
    @Autowired
    private ClientSetting clientSetting;


    /**
     * 获取用户所有订单的信息
     *
     * @param userId
     * @return
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getAllOrderInfo", method = RequestMethod.POST)
    public Object getAllOrderInfo(long userId) {
        List<OrderInfo> orderInfoList = UserOrderService.getAllOrderInfo(userId);
        for (int i = 0; i < orderInfoList.size(); i++) {
            List<ProductOnline> productOnlineList = UserOrderService.getProductOnline(orderInfoList.get(i).getUserOrderId());
            for (int j = 0; j < productOnlineList.size(); j++) {
                String[] arr = productOnlineList.get(j).getAttachImageUrls().toString().split(";");
                //productOnlineList.get(j).get
                productOnlineList.get(j).setImage60(clientSetting.getResource() + arr[0]);
            }
            orderInfoList.get(i).setProductOnlineList(productOnlineList);
            orderInfoList.get(i).setNewDate(new Date());
            int machineType = UserOrderService.machineType(Long.valueOf(orderInfoList.get(i).getMachineId()));
            orderInfoList.get(i).setMachineType(machineType);

        }
        UserParticularOrderInfo userParticularOrderInfo = new UserParticularOrderInfo();
        if (orderInfoList.size() > 0) {
            userParticularOrderInfo.setOrderInfoList(orderInfoList);
            userParticularOrderInfo.setType(1);
            return userParticularOrderInfo;
        } else {
            userParticularOrderInfo.setType(0);
            return userParticularOrderInfo;
        }
    }

    /**
     * 根据订单id去查询该订单详情
     *
     * @param userOrderId
     * @return
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getParticularOrderInfoByOrderId", method = RequestMethod.POST)
    public Object getParticularOrderInfoByOrderId(long userOrderId) throws Exception {
        String data = "";
        List<OrderInfo> orderInfoList = UserOrderService.getOrderInfoByOrderId(userOrderId);
        for (int i = 0; i < orderInfoList.size(); i++) {
            List<ProductOnline> productOnlineList = UserOrderService.getProductOnline(orderInfoList.get(i).getUserOrderId());
            for (int j = 0; j < productOnlineList.size(); j++) {
                String[] arr = productOnlineList.get(j).getAttachImageUrls().toString().split(";");
                //productOnlineList.get(j).get
                productOnlineList.get(j).setImage60(clientSetting.getResource() + arr[0]);
            }
            orderInfoList.get(i).setProductOnlineList(productOnlineList);
            System.out.println(orderInfoList.get(i).getProductOnlineList().get(0).getProductGlobalId() + "   商品id");
            orderInfoList.get(i).setNewDate(UserOrderService.selectTime());
            System.out.println(orderInfoList.get(i).getNewDate() + "      " + orderInfoList.get(i).getPayTime());
            OrderInfo orderInfo = orderInfoList.get(i);
            int machineType = UserOrderService.machineType(Long.valueOf(orderInfoList.get(i).getMachineId()));
            orderInfoList.get(i).setMachineType(machineType);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createTime = orderInfo.getCreateTime();
            String createTime1 = simpleDateFormat.format(createTime);
            System.out.println("下单时间" + createTime1);
            //加密
            AES_256 aes_256 = new AES_256();
            //拼接加密字符串
            String context = userOrderId + "," + createTime1;
            System.out.println("明文" + context);
            byte[] datas = aes_256.Encrypt(clientSetting.getAES256Key(), context);
            data = new BASE64Encoder().encode(datas);
            System.out.println("加密之后的字符串：" + data);
        }
        UserParticularOrderInfo userParticularOrderInfo = new UserParticularOrderInfo();
        if (orderInfoList.size() > 0) {
            userParticularOrderInfo.setOrderInfoList(orderInfoList);
            userParticularOrderInfo.setType(1);
            userParticularOrderInfo.setEncryptedOrder("01," + data);
            return userParticularOrderInfo;
        } else {
            userParticularOrderInfo.setType(0);
            return userParticularOrderInfo;
        }
    }


    /**
     * 获取待取餐订单的信息
     *
     * @param userId
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getUserParticularOrderInfo", method = RequestMethod.POST)
    public Object getUserParticularOrderInfo(long userId) {
        List<OrderInfo> orderInfoList = UserOrderService.getOrderInfo(userId);
        for (int i = 0; i < orderInfoList.size(); i++) {
            orderInfoList.get(i).getStatus();
            List<ProductOnline> productOnlineList = UserOrderService.getProductOnline(orderInfoList.get(i).getUserOrderId());
            for (int j = 0; j < productOnlineList.size(); j++) {
                String[] arr = productOnlineList.get(j).getAttachImageUrls().toString().split(";");
                //productOnlineList.get(j).get
                productOnlineList.get(j).setImage60(clientSetting.getResource() + arr[0]);
            }
            orderInfoList.get(i).setProductOnlineList(productOnlineList);
            orderInfoList.get(i).setNewDate(new Date());
            int machineType = UserOrderService.machineType(Long.valueOf(orderInfoList.get(i).getMachineId()));
            orderInfoList.get(i).setMachineType(machineType);
        }
        UserParticularOrderInfo userParticularOrderInfo = new UserParticularOrderInfo();

        if (orderInfoList.size() > 0) {
            userParticularOrderInfo.setOrderInfoList(orderInfoList);
            userParticularOrderInfo.setType(1);
            return userParticularOrderInfo;
        } else {
            userParticularOrderInfo.setType(0);
            return userParticularOrderInfo;
        }
    }


}
