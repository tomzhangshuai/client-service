package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.BaseController;
import com.wufanbao.api.oldclientservice.entity.ProductInfo;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.service.HomePageService;
import com.wufanbao.api.oldclientservice.service.ScanAQRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 家庭付
 *
 * @author Wang Zhiyuan
 * @editor zhaojing
 **/
@RequestMapping(value = "HomePageController")
@Controller
public class HomePageController extends BaseController {
    @Autowired
    private ScanAQRCodeService scanAQRCodeService;
    @Autowired
    private HomePageService homePageService;

    /**
     * 已经开放的城市
     *
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editro zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "getOpenCity", method = RequestMethod.POST)
    public Object getOpenArea() {
        return homePageService.getOpenCity();
    }

    /**
     * 获取广告信息
     *
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "getAppAd", method = RequestMethod.POST)
    public Object getAppAd() throws IOException {
        return homePageService.getAppAd();
    }

    /**
     * 获取附近五台机器的信息还有机器上的商品信息
     *
     * @param name 城市名称
     * @param x    经线
     * @param y    纬线
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "getNearMachine5ProductInfo", method = RequestMethod.POST)
    private Object getNearMachine5ProductInfo(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "x", required = false) BigDecimal x, @RequestParam(value = "y", required = false) BigDecimal y, @RequestParam(value = "machineId", required = false) Long machineId) {
        if (machineId != null) {
            return homePageService.getProductInfoByMachineId(machineId);
        }
        return homePageService.getNearMachine5ProductInfo(name, x, y);
    }

    /**
     * 首页扫一扫
     *
     * @param machineId
     * @param userId
     * @return
     * @throws Exception
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "scanCode", method = RequestMethod.POST)
    public Object scanCode(Long machineId, Long userId) throws Exception {
        ResponseInfo responseInfo = scanAQRCodeService.scanCode(machineId, userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 订单扫码
     *
     * @param machineId
     * @param userOrderId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "orderScanCode", method = RequestMethod.POST)
    public Object orderScanCode(long machineId, long userOrderId) throws Exception {
        ResponseInfo responseInfo = scanAQRCodeService.orderScanCode(machineId, userOrderId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }


    /**
     * 根据定位地址提供最近的机器
     *
     * @param name 城市名称
     * @param x    经线
     * @param y    纬线
     * @return java.lang.Object
     * @author Wang Zhiyuan
     */
    @ResponseBody
    @RequestMapping(value = "getNearMachine", method = RequestMethod.POST)
    public Object getNearMachine(String name, BigDecimal x, BigDecimal y) {
        return homePageService.getNearMachine(name, x, y);
    }

    /**
     * 根据机器id提供机器上的商品
     *
     * @param machineId 机器ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     */
    @ResponseBody
    @RequestMapping(value = "getProductInfo", method = RequestMethod.POST)
    private Object getProductInfo(long machineId) {
        List<ProductInfo> productInfoList = homePageService.getProductInfo(machineId);
        return productInfoList;
    }

    /**
     * 查询离我最近的五台机器
     *
     * @param name 城市名称
     * @param x    经线
     * @param y    纬线
     * @return java.lang.Object
     * @author Wang Zhiyuan
     */
    @ResponseBody
    @RequestMapping(value = "getNearMachine5", method = RequestMethod.POST)
    private Object getNearMachine5(String name, BigDecimal x, BigDecimal y) {
        return homePageService.getNearMachine5(name, x, y);
    }

    /**
     * 根据已开通城市名称查询某台机器上商品信息
     *
     * @param areaName 城市名称
     * @return java.lang.Object
     * @author Wang Zhiyuan
     */
    @ResponseBody
    @RequestMapping(value = "getNearMachineByAddsProductInfo", method = RequestMethod.POST)
    public Object getNearMachineByAddsProductInfo(String areaName) {
        return homePageService.getNearMachineByAddsProductInfo(areaName);
    }

    /**
     * 版本控制
     *
     * @return java.lang.Object
     * @author Wang Zhiyuan
     */
    @ResponseBody
    @RequestMapping(value = "versionCode", method = RequestMethod.POST)
    public Object versionCode() {
        ResponseInfo responseInfo = homePageService.versionCode();
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 根据城市查询 机器信息
     *
     * @param areaId 区域ID
     * @param x      经线
     * @param y      纬线
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @date 2018/4/16
     */
    @ResponseBody
    @RequestMapping(value = "getMachineByAreaId", method = RequestMethod.POST)
    public Object getMachineByAreaId(int areaId, BigDecimal x, BigDecimal y) {
        ResponseInfo responseInfo = homePageService.getMachineByAreaId(areaId, x, y);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }


}
