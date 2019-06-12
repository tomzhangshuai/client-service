package com.wufanbao.api.olddriverservice.controller;

import com.wufanbao.api.olddriverservice.service.ReplenishmentPrepareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: AlphaDriver
 * @description: 补货准备工作
 * @author: Wang Zhiyuan
 * @create: 2018-03-24 16:31
 **/
@Controller
@RequestMapping("replenishmentPrepare")
public class ReplenishmentPrepareController {
    @Autowired
    private ReplenishmentPrepareService replenishmentPrepareService;

    @RequestMapping("showMachineInfo")
    @ResponseBody
    public Object showMachineInfo(long machineId) {
        Map map = new HashMap();
        List<Map> mapList = replenishmentPrepareService.showMachineInfo(machineId);
        map.put("person", mapList);
        return map;
    }

    @RequestMapping(value = "preparatoryWork", method = RequestMethod.POST)
    @ResponseBody
    public Object preparatoryWork(String machineId, String product, String loginNo) {
        return replenishmentPrepareService.preparatoryWork(machineId, product, loginNo);
    }
}
