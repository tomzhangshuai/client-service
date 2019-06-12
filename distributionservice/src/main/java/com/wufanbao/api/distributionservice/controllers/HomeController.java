package com.wufanbao.api.distributionservice.controllers;

import com.wufanbao.api.distributionservice.returnValueHandles.IgnoreReturnHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    /**
     * 版本号
     */
    @Value("${version}")
    private String version;

    /**
     * 项目名称
     */
    @Value("${name}")
    private String name;

    /**
     * 项目描述
     */
    @Value("${description}")
    private String description;

    /**
     * 项目版本信息
     * @return
     */
    @RequestMapping(value = {"/","/index","/version"})
    @IgnoreReturnHandler
    public Map index()
    {
        Map<String,String> map=new HashMap<>();
        map.put("Project",name);
        map.put("Version",version);
        map.put("description",description);
        return map;
    }
}
