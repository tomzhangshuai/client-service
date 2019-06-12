package com.wufanbao.api.oldclientservice.entity;

public class UserGradePrivilege {
    private long userGradeId;
    private int privilege;
    private String content;
    private String description;

    public long getUserGradeId() {
        return userGradeId;
    }

    public void setUserGradeId(long userGradeId) {
        this.userGradeId = userGradeId;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
