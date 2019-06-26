package com.wufanbao.api.distributionservice.controllers;

import com.wufanbao.api.distributionservice.config.Code;
import com.wufanbao.api.distributionservice.config.CodeException;
import com.wufanbao.api.distributionservice.entities.VersionControl;
import com.wufanbao.api.distributionservice.resolvers.Custom;
import com.wufanbao.api.distributionservice.returnValueHandles.IgnoreReturnHandler;
import com.wufanbao.api.distributionservice.services.EmployeeService;
import com.wufanbao.api.distributionservice.services.HomeService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @Autowired
    private HomeService homeService;
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
    @RequestMapping(value = {"/versionControl"},method = RequestMethod.POST)
    public Object getVersionControl(@Custom VersionControl versionControl) throws Exception {
        String versionCode = versionControl.getVersionCode();
        int versionType = versionControl.getVersionType();
        String versionTypestr=String.valueOf(versionType);
        if(StringUtil.isNullOrEmpty(versionCode)||StringUtil.isNullOrEmpty(versionTypestr)){
            throw new CodeException(Code.dataError.getCode(),"获取当前版本信息失败，请稍后重试");
        }
        return homeService.getVersionControl(versionCode,versionType);
    }
}
