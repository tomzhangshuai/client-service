package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.entity.Manager;
import com.wufanbao.api.oldclientservice.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * User:Wangshihao
 * Date:2017-07-31
 * Time:10:12
 */
@Controller
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("list")
    @ResponseBody()
    public Object managerList() {
        List<Manager> managerList = managerService.getSelect();
        Map map = new HashMap();
        map.put("to", managerList);
        return map;
    }
}
