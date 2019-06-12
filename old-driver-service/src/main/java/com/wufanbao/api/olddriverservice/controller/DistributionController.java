package com.wufanbao.api.olddriverservice.controller;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.olddriverservice.Tool.ReturnParameter;
import com.wufanbao.api.olddriverservice.entity.*;
import com.wufanbao.api.olddriverservice.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-09-07
 * Time:18:34
 */
@Controller
@RequestMapping("distribution")
public class DistributionController {

    @Autowired
    private DistributionService distributionService;
    @Autowired
    private ReturnParameter returnParameter;

    /**
     * 配送接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "distributionLine", method = RequestMethod.POST)
    @ResponseBody
    public Object DistributionLine(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        DistributionOrder distributionOrder = JSON.parseObject(requestInfo.getData(), DistributionOrder.class);
        List<Distribution> distributionList = distributionService.queryDistribution(distributionOrder.getDistributionOrderId());
        if (distributionList.size() != 0) {
            responseInfo.setCode(1);
            responseInfo.setData(distributionList);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        } else {
            responseInfo.setCode(2201);
            responseInfo.setData(distributionList);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        }
        //组装返回体
        //responseInfo =returnParameter.returnRequestInfo(responseInfo1,requestInfo.getVersion());
        // }else{
        //数字签名验证异常
        //    responseInfo =returnParameter.responserInfoErro();
        // }
    /*    return responseInfo;
        ResponseInfo<List> responseInfo = new ResponseInfo();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Distribution> distributionList = distributionService.queryDistribution(distributionOrderId);
        if (distributionList.size() != 0) {
            responseInfo.setCode(1);
            responseInfo.setData(distributionList);
            responseInfo.setResponseTime(simpleDateFormat.format(new Date()));
            responseInfo.setMessage("");
            GetMD5 getMD5 = new GetMD5();
            String digital = getMD5.getMD5("1"+""+distributionList+ responseInfo.getResponseTime()+"1.0");
            responseInfo.setDigital(digital);*/
        return responseInfo;

    }

    /**
     * 配送单信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "distributionProductLine", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo actualQuantity(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        DistributionOrder distributionOrder = JSON.parseObject(requestInfo.getData(), DistributionOrder.class);
        DistributionProductLine distributionProductLine = distributionService.actualQuantity(distributionOrder.getDistributionOrderId());
        Store store = distributionService.getStoreInfo(distributionOrder.getDistributionOrderId());
        Map map = new HashMap();
        if (distributionProductLine != null) {
            map.put("distributionProductLine", distributionProductLine);
            map.put("store", store);
            responseInfo.setCode(1);
            responseInfo.setData(map);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        } else {
            map.put("distributionProductLine", distributionProductLine);
            map.put("store", store);
            responseInfo.setCode(2211);
            responseInfo.setData(map);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        }
        return responseInfo;
    }
}
