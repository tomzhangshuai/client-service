package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// EmployeeClient,用户的访问的Client
public class EmployeeClient {
    //EmployeeId,
    private long employeeId;
    //ServerId,
    private String serverId;
    //CreateTime,
    private Date createTime;
    //UserAgent,
    private String userAgent;
    //Ip,
    private String ip;
    //Total,
    private int total;

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getServerId() {
        return this.serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
