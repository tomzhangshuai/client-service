package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.BaseController;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.service.FamilyPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 家庭付
 *
 * @author Wang Zhiyuan
 **/

@Controller
@RequestMapping(value = "familyPay", method = RequestMethod.POST)
public class FamilyPayController extends BaseController {
    @Autowired
    private FamilyPayService familyPayService;


    /**
     * 查询家庭付用户绑定信息
     *
     * @param masterUserId 主用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @eidtor zhaojing
     */
//    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "selectUserFamilyPayInfo", method = RequestMethod.POST)
    public Object selectUserFamilyPayInfo(long masterUserId) {
        ResponseInfo responseInfo = familyPayService.selectUserFamilyPayInfo(masterUserId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 修改子账户信息
     *
     * @param quotaType    家庭付类型
     * @param limitQuota   是否限额
     * @param totalQuota   总额度
     * @param disabled     是否启用
     * @param userId       用户ID
     * @param masterUserId 主用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "updateFamilyPayInfo", method = RequestMethod.POST)
    public Object updateFamilyPayInfo(@RequestParam(value = "quotaType", required = false) Integer quotaType,
                                      @RequestParam(value = "limitQuota", required = false) Boolean limitQuota,
                                      @RequestParam(value = "totalQuota", required = false) BigDecimal totalQuota,
                                      @RequestParam(value = "disabled", required = false) Boolean disabled,
                                      long userId, long masterUserId) {
        ResponseInfo responseInfo = familyPayService.updateFamilyPayInfo(quotaType, limitQuota, totalQuota, disabled, userId, masterUserId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 用户解除绑定
     *
     * @param userId          当前用户ID
     * @param relevanceUserId 如果是主用户需要传子账户ID 如果是子账户则不需要
     * @return java.lang.Object
     * @date 2018/6/27
     * @editor zhaojing
     */
//    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "releaseRelationship", method = RequestMethod.POST)
    public Object releaseRelationship(long userId, @RequestParam(value = "relevanceUserId", required = false) Long relevanceUserId) {
        ResponseInfo responseInfo = familyPayService.releaseRelationship(userId, relevanceUserId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 绑定用户
     *
     * @param Mb     用户手机号
     * @param userId
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @editor zhaojing 是否可以绑定
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "userBindingInfo", method = RequestMethod.POST)
    public Object userBindingInfo(String Mb, long userId) {
        ResponseInfo responseInfo = familyPayService.userBindingInfo(Mb, userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 用户绑定家庭付成员
     *
     * @param userId      用户ID
     * @param mb          手机号
     * @param payPassword 支付密码
     * @return java.lang.Object
     * @date 2018/6/26
     * @editor zhaojing 绑定子帐户
     */
//    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "confirmBindingFamily", method = RequestMethod.POST)
    public Object confirmBindingFamily(long userId, String mb, String payPassword) {
        ResponseInfo responseInfo = familyPayService.confirmBindingFamily(userId, mb, payPassword);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }


    /**
     * 给手机号发送验证码
     *
     * @param Mb 手机号
     * @return java.lang.Object
     * @author Wang Zhiyuan
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "checkMb", method = RequestMethod.POST)
    public Object checkMb(String Mb) throws IOException {
        ResponseInfo responseInfo = familyPayService.checkMb(Mb);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 验证手机号验证码，并绑定子账户
     *
     * @param Mb        用户手机号
     * @param code      验证码
     * @param userId    用户ID
     * @param sonUserId 子用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "checkCode", method = RequestMethod.POST)
    public Object checkCode(String Mb, String code, long userId, long sonUserId) {
        ResponseInfo responseInfo = familyPayService.checkCode(Mb, code, userId, sonUserId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 删除主账号的子账号
     *
     * @param userId       用户ID
     * @param masterUserId 主用户ID
     * @return java.lang.Object
     * @author Wang Zhiyuan
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "deleteSonUser", method = RequestMethod.POST)
    public Object deleteSonUser(long userId, long masterUserId) {
        ResponseInfo responseInfo = familyPayService.deleteSonUser(userId, masterUserId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }


}
