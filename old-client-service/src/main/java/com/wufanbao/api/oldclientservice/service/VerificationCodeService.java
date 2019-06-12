package com.wufanbao.api.oldclientservice.service;

import java.io.IOException;

/**
 * @program: alphaWuFan
 * @description: 验证码
 * @author: Wang Zhiyuan
 * @create: 2018-03-28 14:39
 **/
public interface VerificationCodeService {

    Object sendVerificationCode(String Mb) throws IOException;
}
