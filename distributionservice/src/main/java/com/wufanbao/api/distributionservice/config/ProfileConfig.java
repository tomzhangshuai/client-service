package com.wufanbao.api.distributionservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * ProfileConfig
 */
@Configuration
public class ProfileConfig {

    /**
     * 本地环境
     */
    public static final String LOCAL_PROFILE = "local";

    /**
     * 开发环境
     */
    public static final String DEV_PROFILE = "dev";


    /**
     * 测试环境
     */
    public static final String TEST_PROFILE = "test";

    /**
     * 生产环境
     */
    public static final String PRO_PROFILE = "pro";

    /**
     * context 请求
     */
    @Autowired
    private ApplicationContext context;


    /**
     * 获取当前环境
     * @return
     */
    public String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }
}