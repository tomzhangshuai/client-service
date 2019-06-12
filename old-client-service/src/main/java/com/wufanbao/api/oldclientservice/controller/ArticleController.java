package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.entity.AppDiscover;
import com.wufanbao.api.oldclientservice.service.AppDiscoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:17:37
 *
 * @editor zhaojing
 */
@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private AppDiscoverService appDiscoverService;
    @Autowired
    private ClientSetting clientSetting;

    /**
     * 发现-列表
     *
     * @return
     */
    @PostMapping("queryAppDiscover")
    @ResponseBody
    public Object queryAppDiscover() {
        Map map = new HashMap();
        try {
            List<AppDiscover> appDiscovers = appDiscoverService.queryAppDiscover();
            for (int i = 0; i < appDiscovers.size(); i++) {
                appDiscovers.get(i).setImageUrl(clientSetting.getResource() + appDiscovers.get(i).getImageUrl());
            }
            if (appDiscovers.size() != 0) {
                map.put("data", appDiscovers);
                map.put("status", 1);
                return map;
            } else {
                map.put("data", appDiscovers);
                map.put("status", 2);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
            return map;
        }
    }

}
