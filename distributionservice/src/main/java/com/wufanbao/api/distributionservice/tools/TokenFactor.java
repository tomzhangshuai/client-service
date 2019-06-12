package com.wufanbao.api.distributionservice.tools;

import java.util.Date;

/**
 * token 因子
 */
public class TokenFactor {
    /**
     * userAgent MD5数字摘要
     */
    private String userAgentMd5;
    /**
     * 员工ID
     */
    private String employeeId;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 是否有效
     */
    private boolean isEffectived;


    public TokenFactor(){
        Date now=new Date();
        createTime=String.valueOf(now.getTime());
    }

    public boolean isEffectived() {
        return isEffectived;
    }
    public void setEffectived(boolean effectived) {
        isEffectived = effectived;
    }
    public String getUserAgentMd5() {
        return userAgentMd5;
    }
    public void setUserAgentMd5(String userAgentMd5) {
        this.userAgentMd5 = userAgentMd5;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TokenFactor{" +
                "userAgentMd5='" + userAgentMd5 + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isEffectived=" + isEffectived +
                '}';
    }
}
