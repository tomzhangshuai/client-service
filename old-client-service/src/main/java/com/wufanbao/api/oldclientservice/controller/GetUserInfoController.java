package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.BaseController;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.service.GetUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息
 *
 * @author WangZhiyuan
 * @eidtor zhaojing 只在确认订单页面有使用
 */
@Controller
@RequestMapping("UserInfo")
public class GetUserInfoController extends BaseController {
    @Autowired
    private GetUserInfoService getUserInfoService;

    /**
     * 根据用户userId 获取用户当前便当币数量
     *
     * @param userId 用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @RequestMapping(value = "getUserUsableBalance", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserUsableBalance(long userId) {
        return getUserInfoService.getUserUsableBalance(userId);
    }

    /**
     * 取餐时间
     *
     * @param userId
     * @return
     * @throws Exception
     * @eitor zhaojing
     */
    @RequestMapping(value = "oftenTime", method = RequestMethod.POST)
    @ResponseBody
    public Object oftenTime(long userId) throws Exception {
        ResponseInfo responseInfo = getUserInfoService.oftenTime(userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 验证用户登录
     *
     * @param userId    用户ID
     * @param binDingId 绑定用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @date 2018/3/5
     * @editor zhaojing 获取用户信息／支付密码
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "GetUserInfo", method = RequestMethod.POST)
    public Object getUserInfo(@RequestParam(value = "userId", required = false) Long userId, @RequestParam(value = "bindingId", required = false) String binDingId) throws Exception {
        Map map = new HashMap();
        try {
            map = getUserInfoService.getUserInfoService(userId, binDingId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
