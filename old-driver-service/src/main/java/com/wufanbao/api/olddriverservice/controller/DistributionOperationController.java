package com.wufanbao.api.olddriverservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wufanbao.api.olddriverservice.Tool.ReturnParameter;
import com.wufanbao.api.olddriverservice.entity.*;
import com.wufanbao.api.olddriverservice.service.DistributionOperationService;
import com.wufanbao.api.olddriverservice.service.MachineStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("distributionOperation")
public class DistributionOperationController {
    @Autowired
    private DistributionOperationService distributionOperationService;
    @Autowired
    private MachineStorageService machineStorageService;
    @Autowired
    private ReturnParameter returnParameter;

    /**
     * 出发
     * @param request 配送单信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "beganDelivery", method = RequestMethod.POST)
    public Object beganDelivery(String request) {
        System.out.println(request);
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();
        // if (count==1){
        DistributionOrder distributionOrder = JSON.parseObject(requestInfo.getData(), DistributionOrder.class);
        ResponseInfo responseInfo1 = distributionOperationService.beganDelivery(distributionOrder);
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        // }else{
        //数字签名验证异常
        //    responseInfo =returnParameter.responserInfoErro();
        // }
        return responseInfo;
    }

    /**
     * 终止
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "terminationDistribution", method = RequestMethod.POST)
    public Object terminationDistribution(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        //  CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        DistributionOrder distributionOrder = JSON.parseObject(requestInfo.getData(), DistributionOrder.class);

        ResponseInfo responseInfo1 = distributionOperationService.terminationDistribution(distributionOrder);
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        // }else{
        //数字签名验证异常
        //   responseInfo =returnParameter.responserInfoErro();
        //}
        return responseInfo;
    }


    /**
     * 确认订单信息
     *
     * @param request 配送单信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "confirmDeliveryOrder", method = RequestMethod.POST)
    public Object confirmDeliveryOrder(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        DistributionOrder distributionOrder = JSON.parseObject(requestInfo.getData(), DistributionOrder.class);
        ResponseInfo responseInfo1 = distributionOperationService.confirmDeliveryOrder(distributionOrder);
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        // }else{
        //数字签名验证异常
        //  responseInfo =returnParameter.responserInfoErro();
        // }
        return responseInfo;


    }

    /**
     * select d.*,
     * 返仓确认
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "confirmAnOrder", method = RequestMethod.POST)
    public Object confirmAnOrder(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        String quantityGather = JSONObject.parseObject(requestInfo.getData()).getString("quantityGather").toString();

        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        DistributionOrder distributionOrder = JSON.parseObject(requestInfo.getData(), DistributionOrder.class);
        ResponseInfo responseInfo1 = distributionOperationService.confirmAnOrder(distributionOrder, quantityGather);
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        // }else{
        //数字签名验证异常
        // responseInfo =returnParameter.responserInfoErro();
        //  }
        return responseInfo;
    }
    //测试

    /**
     * 填仓图
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cargoSortingDiagram", method = RequestMethod.POST)
    public Object cargoSortingDiagram(String request) {

        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        //CheckRequest checkRequest=new CheckRequest();
        //int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        SupplementOrder supplementOrder = JSON.parseObject(requestInfo.getData(), SupplementOrder.class);
        ResponseInfo responseInfo1 = machineStorageService.optimalSolution(supplementOrder.getSupplementOrderId(), supplementOrder.getMachineId());
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        //}else{
        //数字签名验证异常
        //    responseInfo =returnParameter.responserInfoErro();
        // }
        return responseInfo;
    }

    /**
     * 刷新我的信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDistributionOrderByEmployeeId", method = RequestMethod.POST)
    public Object getDistributionOrderByEmployeeId(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        //  CheckRequest checkRequest=new CheckRequest();
        //  int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        //  if (count==1){
        Employee employee = JSON.parseObject(requestInfo.getData(), Employee.class);
        ResponseInfo responseInfo1 = distributionOperationService.getDistributionOrderByEmployeeId(employee.getEmployeeId());
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        //  }else{
        //数字签名验证异常
        //     responseInfo =returnParameter.responserInfoErro();
        //  }
        return responseInfo;
    }

    /**
     * 获取理想的填仓图
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getIdealDrawing", method = RequestMethod.POST)
    public Object getIdealDrawing(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        //int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        SupplementOrder supplementOrder = JSON.parseObject(requestInfo.getData(), SupplementOrder.class);
        ResponseInfo responseInfo1 = machineStorageService.idealDrawing(supplementOrder.getSupplementOrderId(), supplementOrder.getMachineId());
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        // }else{
        //数字签名验证异常
        //   responseInfo =returnParameter.responserInfoErro();
        // }
        return responseInfo;
    }

    /**
     * 获取机器表格数据
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "machineTable", method = RequestMethod.POST)
    public Object machineTable(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        ResponseInfo responseInfo = new ResponseInfo();

        MachineModel machineModel = JSON.parseObject(requestInfo.getData(), MachineModel.class);
        responseInfo = machineStorageService.machineTable(machineModel.getMachineId());
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    /**
     * 更新仓库内商品的位置
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "arrangeGoods", method = RequestMethod.POST)
    public Object arrangeGoods(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        String machineStorageList = JSONObject.parseObject(requestInfo.getData()).getString("machineStorageList").toString();
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        //SupplementOrder supplementOrder=JSON.parseObject(requestInfo.getData(),SupplementOrder.class);
        ResponseInfo responseInfo1 = machineStorageService.arrangeGoods(machineStorageList);
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        //  }else{
        //数字签名验证异常
        //     responseInfo =returnParameter.responserInfoErro();
        // }
        return responseInfo;
        // return machineStorageService.arrangeGoods(machineStorageList);
    }

    /**
     * 插入
     */
    @ResponseBody
    @RequestMapping(value = "insertGoods", method = RequestMethod.POST)
    public Map insertGoods(long machineId) {
        return machineStorageService.insertGoods(machineId);
    }
}
