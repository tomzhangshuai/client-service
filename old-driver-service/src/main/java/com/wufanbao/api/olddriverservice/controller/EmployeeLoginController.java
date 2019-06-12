package com.wufanbao.api.olddriverservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.wufanbao.api.olddriverservice.DriverSetting;
import com.wufanbao.api.olddriverservice.Tool.CheckRequest;
import com.wufanbao.api.olddriverservice.Tool.ReturnParameter;
import com.wufanbao.api.olddriverservice.entity.Employee;
import com.wufanbao.api.olddriverservice.entity.RequestInfo;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;
import com.wufanbao.api.olddriverservice.service.EmployeeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RequestMapping(value = "employeeLogin")
@Controller
public class EmployeeLoginController {
    @Autowired
    private EmployeeLoginService employeeLoginService;
    @Autowired
    private ReturnParameter returnParameter;

    /**
     * 员工登录
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @ResponseBody
    @RequestMapping(value = "employeeLoginInfo", method = RequestMethod.POST)
    public Object employeeLogin(String request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        CheckRequest checkRequest = new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();
        //  if (count==1){
        Employee employee = JSON.parseObject(requestInfo.getData(), Employee.class);
        ResponseInfo responseInfo1 = employeeLoginService.employeeLogin(employee.getMb(), employee.getPassWord());
        System.out.println(responseInfo1.getData());
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        // }else{
        //数字签名验证异常
        //responseInfo =returnParameter.responserInfoErro();
        // }
        String ss = JSON.toJSONString(responseInfo);
        System.out.println(JSON.parseObject(ss, ResponseInfo.class, Feature.OrderedField).getData());
        System.out.println(ss + "我的");
        return responseInfo;

    }

    /**
     * 根据用户id查询用户信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "distributionInfo", method = RequestMethod.POST)
    public Object distributionInfo(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        //CheckRequest checkRequest=new CheckRequest();
        //int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();
        //if (count==1){
        Employee employee = JSON.parseObject(requestInfo.getData(), Employee.class);
        ResponseInfo responseInfo1 = employeeLoginService.distributionInfo(employee.getEmployeeId());
        //组装返回体
        responseInfo = returnParameter.returnRequestInfo(responseInfo1, requestInfo.getVersion());
        //}else{
        //数字签名验证异常
        //   responseInfo =returnParameter.responserInfoErro();
        // }
        return responseInfo;

    }
}
