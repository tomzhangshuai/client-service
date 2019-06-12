package com.wufanbao.api.olddriverservice.controller;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.olddriverservice.Tool.ReturnParameter;
import com.wufanbao.api.olddriverservice.entity.Employee;
import com.wufanbao.api.olddriverservice.entity.RequestInfo;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;
import com.wufanbao.api.olddriverservice.service.HistoricalShippingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "historicalShippingOrder")
public class HistoricalShippingOrderController {
    @Autowired
    private HistoricalShippingOrderService historicalShippingOrderService;
    @Autowired
    private ReturnParameter returnParameter;

    /**
     * 查询历史订单
     *
     * @param request 员工信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "historicalShippingOrderInfo", method = RequestMethod.POST)
    public Object getHistoricalShippingOrderInfo(String request) {

        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        //CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        //if (count==1){
        Employee employee = JSON.parseObject(requestInfo.getData(), Employee.class);
        ResponseInfo responseInfo1 = historicalShippingOrderService.getHistoricalShippingOrder(employee);
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        // }else{
        //数字签名验证异常
        //   responseInfo =returnParameter.responserInfoErro();
        // }
        return responseInfo;
    }
}
