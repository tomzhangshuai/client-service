package com.wufanbao.api.clientservice.controller;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.clientservice.common.*;
import com.wufanbao.api.clientservice.common.wechat.WechatAuth;
import com.wufanbao.api.clientservice.config.ClientSetting;
import com.wufanbao.api.clientservice.entity.*;
import com.wufanbao.api.clientservice.service.ApiServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController extends BaseController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private WechatAuth wechatAuth;
    @Autowired
    private ClientSetting clientSetting;

    @GetMapping("/")
    public String index() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        logger.error(dateFormat.format(new Date()));
        return dateFormat.format(new Date());
    }
    @PostMapping("versionControl")
    public ResponseData getVersionControl(String data){
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String versionCode=map.get("versionCode");
        String versionTypestr=map.get("versionType");
        int versionType=Integer.parseInt(versionTypestr);
        if(StringUtils.isNullOrEmpty(versionCode)||StringUtils.isNullOrEmpty(versionTypestr)){
            responseData.error("获取当前版本信息失败，请稍后重试").sign(null);
        }
        try {
            Data result = appADService.getVersionControl(versionCode,versionType);
            return responseData.success().sign(result);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        }
    }
    /**
     * 获取服务的版本
     *
     * @return
     */
    @GetMapping("version")
    public String getVersion() {
        String c = clientSetting.getPayCallback();
        return clientSetting.getVersion();
    }


    /**
     * 获取开放城市
     *
     * @return
     */
    @PostMapping("getCitys")
    public ResponseData getCitys() {
        return responseData.success().sign(companyService.getCitys());
    }

    /**
     * 获取广告信息
     *
     * @return
     */
    @PostMapping("getAppADS")
    public ResponseData getAppADS() {
        try {
            List<AppAD> appADS = appADService.getAppADs();
            return responseData.success().sign(appADS);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    /**
     * 获取附近5台机器
     *
     * @param data：{"areaName":"杭州市","x":"",y:""} {"areaName":"杭州市","x":"",y:"","machineId":"1"}
     * @return
     */
    @PostMapping("getMachineProducts")
    public ResponseData getMachineProducts(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String areaName = map.get("areaName");
        String yStr = map.get("y");
        String xStr = map.get("x");
        try {
            if (StringUtils.isNullOrEmpty(areaName) || StringUtils.isNullOrEmpty(xStr) || StringUtils.isNullOrEmpty(yStr)) {
                logger.error("附近位置参数异常，城市名"+areaName+",X坐标："+xStr+",Y坐标："+yStr);
                return responseData.error("附近位置参数异常，城市名"+areaName+",X坐标："+xStr+",Y坐标："+yStr).sign(null);
            }
            long machineId = 0;
            if (map.containsKey("machineId") && !StringUtils.isNullOrEmpty(map.get("machineId").toString())) {
                machineId = Long.parseLong(map.get("machineId").toString());
            }
            BigDecimal x = new BigDecimal(xStr);
            BigDecimal y = new BigDecimal(yStr);
            List<Data> datas = machineService.getMachineProducts(areaName, x, y);
            return responseData.success().sign(datas);
        } catch (ApiServiceException e) {
            logger.error(commonFun.getStackTraceInfo(e));
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            logger.error(commonFun.getStackTraceInfo(ex));
            return responseData.error(ex).sign(null);
        }
    }

    //根据机器id获取附近机器
    @PostMapping("getMachineProductsByMachineId")
    public ResponseData getMachineProductsByMachineId(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String machineIdStr = map.get("machineId");
        try {
            if (StringUtils.isNullOrEmpty(machineIdStr)) {
                return responseData.error("附近机器参数异常").sign(null);
            }
            long machineId = Long.parseLong(map.get("machineId").toString());
            return responseData.success().sign(machineService.getMachineProductsByMachineId(machineId));
        } catch (ApiServiceException e) {
            logger.error(commonFun.getStackTraceInfo(e));
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            logger.error(commonFun.getStackTraceInfo(ex));
            return responseData.error(ex).sign(null);
        }
    }

    ////根据所在区域获取机器
    @PostMapping("getMachineByAreaId")
    public ResponseData getMachineByAreaId(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String areaIdStr = map.get("areaId");
        String yStr = map.get("y");
        String xStr = map.get("x");
        try {
            if (StringUtils.isNullOrEmpty(areaIdStr) || StringUtils.isNullOrEmpty(xStr) || StringUtils.isNullOrEmpty(yStr)) {
                return responseData.error("机器位置参数异常").sign(null);
            }
            int areaId = Integer.parseInt(areaIdStr);
            BigDecimal x = new BigDecimal(xStr);
            BigDecimal y = new BigDecimal(yStr);
            return responseData.success().sign(machineService.getMachineByArearId(areaId, x, y));
        } catch (Exception ex) {
            logger.error(commonFun.getStackTraceInfo(ex));
            return responseData.error(ex).sign(null);
        }
    }

    //获取发现列表
    @PostMapping("getAppDiscovers")
    public ResponseData getAppDiscovers() {
        try {
            List<AppDiscover> appDiscovers = appADService.getAppDiscovers();
            return responseData.success().sign(appDiscovers);
        } catch (Exception ex) {
            logger.error(commonFun.getStackTraceInfo(ex));
            return responseData.error(ex).sign(null);
        }
    }

    //获取发现列表
    @PostMapping("getAppDiscover")
    public ResponseData getAppDiscover(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String appDiscoverIdStr = map.get("appDiscoverId");
        try {
            if (StringUtils.isNullOrEmpty(appDiscoverIdStr)) {
                return responseData.error("发现列表参数异常").sign(null);
            }
            long appDiscoverId = Long.parseLong(appDiscoverIdStr);
            AppDiscover appDiscover = appADService.getAppDiscover(appDiscoverId);
            return responseData.success().sign(appDiscover);
        } catch (Exception ex) {
            logger.error(commonFun.getStackTraceInfo(ex));
            return responseData.error(ex).sign(null);
        }
    }

    //扫一扫
    @PostMapping("/auth/scan")
    public ResponseData scan(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String qrcode = map.get("qrcode");
        if (StringUtils.isNullOrEmpty(qrcode)) {
            return responseData.error("请先扫机器二维码").sign(null);
        }
        if (!qrcode.contains(("machineId"))) {
            return responseData.error("无效的机器二维码").sign(null);
        }
        long machineId = 0;
        try {
            String[] qrcodes = qrcode.split("machineId=");
            machineId = Long.parseLong(qrcodes[1]);
        } catch (Exception ex) {
            return responseData.error("无效的机器二维码").sign(null);
        }
        if (machineId <= 0) {
            return responseData.error("无效的机器二维码").sign(null);
        }
        try {
            return responseData.success().sign(userOrderService.scanMachine(userId, machineId));
        } catch (ApiServiceException e) {
            logger.error(commonFun.getStackTraceInfo(e));
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            logger.error(commonFun.getStackTraceInfo(ex));
            return responseData.error(ex).sign(null);
        }
    }

    //微信公众号，url签名
    @PostMapping("getWxConfig")
    public ResponseData getWxSign(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String url = map.get("url");
        try {
            if (StringUtils.isNullOrEmpty(url)) {
                return responseData.error("url签名参数异常").sign(null);
            }
            return responseData.success().sign(wechatAuth.getConfig(url));
        } catch (Exception ex) {
            logger.error(commonFun.getStackTraceInfo(ex));
            return responseData.error(ex).sign(null);
        }
    }

}
