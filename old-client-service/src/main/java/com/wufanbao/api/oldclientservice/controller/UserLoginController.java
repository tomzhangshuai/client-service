package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.BaseController;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.Tool.RequestValidationCode;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 用户登录
 *
 * @author Wang Zhiyuan
 * @date 2017/08/01
 */
@Controller
@RequestMapping(value = "UserLogin", method = RequestMethod.POST)
public class UserLoginController extends BaseController {
    @Autowired
    private UserLoginService userLoginService;

    /**
     * 用户等级特权
     *
     * @param userId 用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @eidtor zhaojing
     */
    @LoginRequired
    @RequestMapping(value = "userGradeInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object userGradeInfo(long userId) {
        ResponseInfo responseInfo = userLoginService.userGradeInfo(userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 第三方登录绑定
     *
     * @param bindingId   绑定ID
     * @param bindingType 绑定类型
     * @param mb          手机号
     * @param code        验证码
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @date 2018/5/14
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "thirdPartyLoginBindingMb", method = RequestMethod.POST)
    public Object thirdPartyLoginBindingMb(String bindingId, int bindingType, String mb, String code) {
        ResponseInfo responseInfo = userLoginService.thirdPartyLoginBindingMb(bindingId, bindingType, mb, code, 0);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 绑定发送验证码
     *
     * @param mb 手机号
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @date 2018/5/15
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "sendCodeBinding", method = RequestMethod.POST)
    public Object sendCodeBinding(String mb) throws Exception {
        Map map = userLoginService.sendMessage(mb);
        return map;
    }

    /**
     * 验证用户登录
     *
     * @param LoginNo  手机号
     * @param Password 密码
     * @return 存放着用户信息的Map
     * @editor zhaojing
     */
    @RequestMapping(value = "getUserLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserLogin(String LoginNo, String Password, String registrationId, HttpServletResponse response) throws Exception {
        Map map = userLoginService.getUserLogin(LoginNo, Password, registrationId);
        String md5;
        try {
            md5 = map.get("token").toString();
        } catch (Exception e) {
            md5 = null;
        }
        if (md5 != null) {
            response.setHeader("token", md5);
        } else {
            response.setHeader("token", "ERRO");
        }
        return map;
    }

    /**
     * 第三方登录获取用户Id
     *
     * @param bindingId   绑定ID
     * @param bindingType 绑定类型:1-微信，2-QQ
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @date 2018/5/14
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "thirdPartyLogin", method = RequestMethod.POST)
    public Object thirdPartyLogin(String bindingId, String bindingType) {
        ResponseInfo responseInfo = userLoginService.thirdPartyLogin(bindingId, bindingType);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 快速登陆
     *
     * @param Mb   手机号
     * @param code 验证码
     * @return Map 用户数据 ，或者错误状态
     * @editor zhaojing
     */
    @RequestMapping(value = "quickLogin", method = RequestMethod.POST)
    @ResponseBody
    public Object quickLogin(String Mb, String code, String registrationId, @RequestParam(value = "bindingId", required = false) String bindingId, HttpServletResponse response) {
        Map map = userLoginService.quickLogin(Mb, code, registrationId, bindingId);
        String md5;
        try {
            md5 = map.get("token").toString();
        } catch (Exception e) {
            md5 = null;
        }
        if (md5 != null) {
            response.setHeader("token", md5);
        } else {
            response.setHeader("token", "ERRO");
        }
        return map;
    }

    /**
     * 发送验证码
     *
     * @param Mb 手机号
     * @return Map 手机号是否已经注册
     * @editor zhaojing 快捷登录发送短信验证码
     */
    @RequestMapping(value = "checkMb", method = RequestMethod.POST)
    @ResponseBody
    @RequestValidationCode
    public Object checkMb(String Mb) throws IOException {
        return userLoginService.sendMessage(Mb);
    }


    /**
     * 通过BindingID登录 微信
     *
     * @param bindingId bindingId
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @date 2018/4/17
     */
    @ResponseBody
    @RequestMapping(value = "loginByBindingId", method = RequestMethod.POST)
    public Object loginByBindingId(String bindingId) {
        ResponseInfo responseInfo = userLoginService.loginByBindingId(bindingId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }
}