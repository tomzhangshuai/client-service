package com.wufanbao.api.olddriverservice.controller;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.olddriverservice.Tool.ReturnParameter;
import com.wufanbao.api.olddriverservice.entity.RequestInfo;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;
import com.wufanbao.api.olddriverservice.entity.SupplementDetails;
import com.wufanbao.api.olddriverservice.entity.SupplementOrder;
import com.wufanbao.api.olddriverservice.service.SupplementDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-12
 * Time:10:08
 */
@Controller
@RequestMapping("supplementDetails")
public class SupplementDetailsController {

    @Autowired
    private SupplementDetailsService supplementDetailsService;
    @Autowired
    private ReturnParameter returnParameter;

    /**
     * 补货单详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "supplementDetailsInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object SupplementDetailsInfo(String request) {

        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        //int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        SupplementOrder supplementOrder = JSON.parseObject(requestInfo.getData(), SupplementOrder.class);
        long supplementOrderId = supplementOrder.getSupplementOrderId();
        List<SupplementDetails> supplementDetailsList = supplementDetailsService.querySupplementDetails(supplementOrder.getSupplementOrderId());
        if (supplementOrderId != 0) {
            if (supplementDetailsList.size() != 0) {
                responseInfo.setCode(1);
                responseInfo.setData(supplementDetailsList);
                responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
                /*Status status = new Status();
                status.setData(supplementDetailsList);
                status.setStatus(0);*/
                return responseInfo;
            } else if (supplementDetailsList.size() == 0) {
                responseInfo.setCode(2222);
                responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
                return responseInfo;
            }
        }
        responseInfo.setCode(2221);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }
}
