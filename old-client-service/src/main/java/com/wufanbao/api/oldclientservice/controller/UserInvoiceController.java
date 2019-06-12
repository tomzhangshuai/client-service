package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.entity.UserInvoice;
import com.wufanbao.api.oldclientservice.entity.UserInvoiceOrder;
import com.wufanbao.api.oldclientservice.service.UserInvoiceOrderService;
import com.wufanbao.api.oldclientservice.service.UserInvoiceService;
import com.wufanbao.api.oldclientservice.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:WangZhiyuan
 */
@Controller
@RequestMapping(value = "UserInvoice")
public class UserInvoiceController {
    @Autowired
    private UserInvoiceService userInvoiceService;
    @Autowired
    private UserInvoiceOrderService userInvoiceOrderService;
    @Autowired
    private UserOrderService userOrderService;

}
