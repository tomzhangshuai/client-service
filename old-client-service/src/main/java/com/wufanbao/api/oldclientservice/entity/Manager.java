package com.wufanbao.api.oldclientservice.entity;

/**
 * User:Wangshihao
 * Date:2017-07-31
 * Time:9:43
 */
//实体类
public class Manager {
    private Integer managerId;
    private String managerName;
    private String loginNo;
    private String password;

    public Manager(Integer managerId, String managerName, String loginNo, String password) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.loginNo = loginNo;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                '}';
    }

    public Manager(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
