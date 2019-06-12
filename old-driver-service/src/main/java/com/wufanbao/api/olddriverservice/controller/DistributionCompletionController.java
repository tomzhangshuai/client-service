package com.wufanbao.api.olddriverservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.wufanbao.api.olddriverservice.Tool.ReturnParameter;
import com.wufanbao.api.olddriverservice.entity.DistributionCompletion;
import com.wufanbao.api.olddriverservice.entity.RequestInfo;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;
import com.wufanbao.api.olddriverservice.entity.SupplementOrder;
import com.wufanbao.api.olddriverservice.service.DistributionCompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-10-26
 * Time:9:53
 */
@Controller
@RequestMapping("distributionCompletion")
public class DistributionCompletionController {
    @Autowired
    private DistributionCompletionService distributionCompletionService;
    @Autowired
    private ReturnParameter returnParameter;

    /**
     * 补货单完成详情
     *
     * @param request
     * @return
     */
    @PostMapping("distributionCompletionAll")
    @ResponseBody
    public Object DistributionCompletionAll(String request, HttpServletRequest req) throws ServletException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String body = IOUtils.readAll(reader);
        String name = req.getParameter("name");
        ResponseInfo responseInfo = new ResponseInfo();
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        long supplementOrderId = JSON.parseObject(requestInfo.getData(), SupplementOrder.class).getSupplementOrderId();
        List<DistributionCompletion> distributionCompletionList = distributionCompletionService.queryDistributionCompletion(supplementOrderId);
        if (distributionCompletionList.size() != 0) {
            responseInfo.setCode(1);
            responseInfo.setData(distributionCompletionList);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;
        }
        responseInfo.setCode(2291);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }
}
