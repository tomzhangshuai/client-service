package com.wufanbao.api.distributionservice.controllers;

import com.wufanbao.api.distributionservice.config.Setting;
import com.wufanbao.api.distributionservice.entities.Employee;
import com.wufanbao.api.distributionservice.resolvers.Custom;
import com.wufanbao.api.distributionservice.resolvers.User;
import com.wufanbao.api.distributionservice.services.EmployeeService;
import com.wufanbao.api.distributionservice.tools.Md5;
import com.wufanbao.api.distributionservice.tools.TokenFactor;
import com.wufanbao.api.distributionservice.transfer.EmployeeInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private Setting setting;

    /**
     * 登录
     * @param request 请求
     * @param employee 登录信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/employee/login","/employeeLogin/employeeLoginInfo"}, method = RequestMethod.POST)
    public Object login(HttpServletRequest request, @Custom Employee employee) throws Exception
    {
        System.out.println(request.getRequestURL());
        TokenFactor tokenFactor=new TokenFactor();
        tokenFactor.setUserAgentMd5(Md5.digital(request.getHeader("User-Agent")));
       return employeeService.login(tokenFactor,employee.getMb(),employee.getPassWord());
    }

    /**
     * 登出 清除用户token缓存
     * @param employeeInfo 用户信息
     * @return
     */
    @RequestMapping(value = "/employee/logout", method = RequestMethod.POST)
    public Object logout(@User EmployeeInfo employeeInfo) {
      if(employeeInfo==null) {
          return new Object();
      }
      employeeService.logout(employeeInfo);
      return new Object();
    }
}
