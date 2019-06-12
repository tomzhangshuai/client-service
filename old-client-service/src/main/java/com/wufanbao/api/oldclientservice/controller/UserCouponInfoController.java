package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.BaseController;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.entity.UserOrder;
import com.wufanbao.api.oldclientservice.service.UserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * User:WangZhiyuan
 */
@Controller
@RequestMapping(value = "userCoupon")
public class UserCouponInfoController extends BaseController {
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private ClientSetting clientSetting;

    /**
     * 发现分享标题图片
     *
     * @param appDiscoverId
     * @return
     * @edito zhaojing
     */
    @RequestMapping(value = "appDiscoverShare", method = RequestMethod.POST)
    @ResponseBody
    public Object appDiscoverShare(long appDiscoverId) {
        ResponseInfo responseInfo = userCouponService.appDiscoverShare(appDiscoverId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 获取可用的优惠券
     *
     * @param userOrder         用户订单信息
     * @param productGlobalList 订单商品信息
     * @param areaName          区域地质
     * @return
     * @throws Exception
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getUseUserCouponInfo", method = RequestMethod.POST)
    public Object getUseUserCouponInfo(UserOrder userOrder, String productGlobalList, String areaName) throws Exception {
        return userCouponService.getUseUserCouponInfo(userOrder, productGlobalList, areaName);
    }

    /**
     * 获取所有优惠券的信息
     *
     * @param userId 用户id
     * @return
     * @eidtor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "userCouponInfo", method = RequestMethod.POST)
    public Object getUserCouponInfo(long userId) {
        return userCouponService.getUserCouponInfo(userId);
    }

    /**
     * 优惠券详情
     *
     * @param x
     * @param y
     * @param areaName
     * @param couponId
     * @param userId
     * @return
     * @eidtor zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "userCouponDetails", method = RequestMethod.POST)
    public Object userCouponDetails(String x, String y, String areaName, long couponId, long userId) {
        ResponseInfo responseInfo = userCouponService.userCouponDetails(x, y, areaName, couponId, userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 分享链接
     *
     * @param
     * @return
     * @edirot zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "shareLinks", method = RequestMethod.POST)
    public Map shareLinks(long userId) {
        Map map = new HashMap();
        String url = clientSetting.getShareLinkUrl() + "?userId=" + userId;
        String content = "这有安全、健康、快捷、味美、价优的便当，让你好吃到痛哭流泪……";
        String title = "20元无门槛新人礼包从天而降，伍饭宝请你吃点好玩的！";
        //String picture="http://192.168.2.250:7000/AlphaWuFan/image/portrait/24326584795137.png";
        map.put("shareLinks", url);
        map.put("content", content);
        map.put("title", title);
        map.put("picture", clientSetting.getShareLinkImages());
        map.put("returnState", 1);
        return map;
    }


    /**
     * 用户注册送优惠券
     *
     * @param userId
     * @param mb
     * @param code
     * @return
     */
    // @ResponseBody
    @RequestMapping(value = "getInviteRegister", method = RequestMethod.POST)
    public void getInviteRegister(long userId, String mb, String code, HttpServletResponse response) throws IOException {
        Map map = userCouponService.inviteRegister(userId, mb, code);
        PrintWriter out = response.getWriter();
        int count = Integer.parseInt(map.get("returnState").toString());
        out.print(count);
    }

    /**
     * 用户邀请成功领取优惠券
     *
     * @param userOrderId
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "InviteRewards", method = RequestMethod.POST)
    public Map InviteRewards(long userOrderId) {
        return userCouponService.InviteRewards(userOrderId);
    }


    @RequestMapping(value = "inviteCheckMb", method = RequestMethod.POST)
    public void checkMb(String mb, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Map map = userCouponService.checkMb(mb);
        int count = Integer.parseInt(map.get("succeed").toString());
        if (count == 1) {
            out.print(true);
        } else {
            out.print(false);
        }
    }
}
