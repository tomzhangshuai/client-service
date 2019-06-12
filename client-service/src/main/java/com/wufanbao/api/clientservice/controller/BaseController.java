package com.wufanbao.api.clientservice.controller;

import com.wufanbao.api.clientservice.common.CommonFun;
import com.wufanbao.api.clientservice.common.RedisUtils;
import com.wufanbao.api.clientservice.common.ResponseData;
import com.wufanbao.api.clientservice.config.Audience;
import com.wufanbao.api.clientservice.config.ClientSetting;
import com.wufanbao.api.clientservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 控制器基础类
 */
public class BaseController {
    @Autowired
    protected ClientSetting clientSetting;
    @Autowired
    protected CommonFun commonFun;
    @Autowired
    protected RedisUtils redisUtils;
    @Autowired
    protected Audience audience;
    @Autowired
    protected CompanyService companyService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected MachineService machineService;
    @Autowired
    protected ProductService productService;
    @Autowired
    protected AppADService appADService;
    @Autowired
    protected UserOrderService userOrderService;
    @Autowired
    protected ResponseData responseData;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
