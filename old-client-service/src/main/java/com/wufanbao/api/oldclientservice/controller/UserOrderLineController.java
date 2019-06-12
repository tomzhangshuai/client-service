package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.entity.PaymentOrder;
import com.wufanbao.api.oldclientservice.entity.ProductOnline;
import com.wufanbao.api.oldclientservice.entity.State;
import com.wufanbao.api.oldclientservice.entity.UserOrderin;
import com.wufanbao.api.oldclientservice.service.UserOrderLineService;
import com.wufanbao.api.oldclientservice.service.UserOrderService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-08-10
 * Time:14:48
 */
@Controller
@RequestMapping("userorder")
public class UserOrderLineController {

    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserOrderLineService userOrderLineService;
    @Autowired
    private ClientSetting clientSetting;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserOrderLineController.class);

    /**
     * 订单详情
     *
     * @param request
     * @return
     * @editor zhaojing
     */
    @LoginRequired
    @RequestMapping(value = "queryuserorder", method = RequestMethod.POST)
    @ResponseBody
    public Object userOrderLine(HttpServletRequest request) {
        State state = new State();
        try {
            long userOrderId = Long.parseLong(request.getParameter("userOrderId"));
            List<PaymentOrder> paymentOrderList = userOrderLineService.queryOrderLine(userOrderId);
            for (int i = 0; i < paymentOrderList.size(); i++) {
                paymentOrderList.get(i).setImageUrl(clientSetting.getResource() + paymentOrderList.get(i).getImageUrl());
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String shi = simpleDateFormat.format(new Date());
            long haomiao = simpleDateFormat.parse(shi).getTime();
            System.out.println(haomiao);
            state.setSuccess(paymentOrderList);
            state.setDate(haomiao);
            state.setError(1);
        } catch (Exception e) {
            state.setError(1);
            logger.info(e.toString());
            throw new RuntimeException();
        }
        return state;
    }


    /**
     * 待付款
     *
     * @param request
     * @return
     * @editor zhaojing
     */
    @LoginRequired
    @RequestMapping(value = "queryorder", method = RequestMethod.POST)
    @ResponseBody
    public Object userOrder(HttpServletRequest request) {
        State state = new State();
        try {
            long userId = Long.valueOf(request.getParameter("userId"));
            List<UserOrderin> userOrderinList = userOrderLineService.queryOrder(userId);
            for (int i = 0; i < userOrderinList.size(); i++) {
                List<ProductOnline> productOnlineList = userOrderService.getProductOnline(userOrderinList.get(i).getUserOrderId());
                for (int j = 0; j < productOnlineList.size(); j++) {
                    String[] arr = productOnlineList.get(j).getAttachImageUrls().toString().split(";");
                    //productOnlineList.get(j).get
                    productOnlineList.get(j).setImage60(clientSetting.getResource() + arr[0]);
                }
                userOrderinList.get(i).setProductOnlineList(productOnlineList);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String shi = simpleDateFormat.format(new Date());
            long haomiao = simpleDateFormat.parse(shi).getTime();
            System.out.println(haomiao);
            state.setSuccess(userOrderinList);
            state.setDate(haomiao);
            state.setError(1);
        } catch (Exception e) {
            state.setError(1);
            logger.info(e.toString());
            throw new RuntimeException();
        }
        return state;
    }


}
