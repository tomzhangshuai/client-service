package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserGradePrivilege,用户等级特权
public class UserGradePrivilege {
    //UserGradeId,
    private long userGradeId;
    //特权,
    private GradePrivilege privilege;
    //特权内容,
    private String content;
    //描述,
    private String description;

    public long getUserGradeId() {
        return this.userGradeId;
    }

    public void setUserGradeId(long userGradeId) {
        this.userGradeId = userGradeId;
    }

    public GradePrivilege getPrivilege() {
        return this.privilege;
    }

    public void setPrivilege(GradePrivilege privilege) {
        this.privilege = privilege;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
