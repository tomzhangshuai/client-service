package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * User:WangZhiyuan
 * Date:2017-08-01
 * Time:10:36
 */
public interface UserLoginService {
    /**
     * 根据手机号密码登录
     *
     * @param loginNo        手机号码
     * @param password       密码
     * @param registrationId 推送ID
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @date 2018/4/9
     */
    Map getUserLogin(String loginNo, String password, String registrationId);

    /**
     * 给手机发验证码
     *
     * @param Mb 手机号
     * @return
     */
    Map sendMessage(String Mb) throws IOException;

    /**
     * 快速登录
     *
     * @param Mb   手机号
     * @param code 验证码
     * @return
     */
    Map quickLogin(String Mb, String code, String registrationId, String openId);

    UserRegistered checkUserMb(String mB);

    ResponseInfo userGradeInfo(long userId);

    /**
     * 通过 bindingId登录
     *
     * @param bindingId 用户绑定ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/4/17
     */
    ResponseInfo loginByBindingId(String bindingId);

    /**
     * 通过binding
     *
     * @param bindingId   绑定ID
     * @param bindingType 绑定ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/5/14
     */
    ResponseInfo thirdPartyLogin(String bindingId, String bindingType);

    /**
     * 绑定手机号
     *
     * @param bindingId   绑定ID
     * @param bindingType 绑定类型
     * @param mb          手机号
     * @param code        验证码
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/5/14
     */
    ResponseInfo thirdPartyLoginBindingMb(String bindingId, int bindingType, String mb, String code, long userId);
}
