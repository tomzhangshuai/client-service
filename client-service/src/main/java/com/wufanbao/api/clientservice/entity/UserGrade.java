package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserGrade,会员等级
public class UserGrade {
    //UserGradeId,
    private long userGradeId;
    //等级名称,
    private String gradeName;
    //等级排序,
    private int gradeNum;
    //开始值,
    private int startValue;
    //结束值,
    private int endValue;
    //等级图片,
    private String gradeImages;
    //等级特权,
    private int privilege;

    public long getUserGradeId() {
        return this.userGradeId;
    }

    public void setUserGradeId(long userGradeId) {
        this.userGradeId = userGradeId;
    }

    public String getGradeName() {
        return this.gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getGradeNum() {
        return this.gradeNum;
    }

    public void setGradeNum(int gradeNum) {
        this.gradeNum = gradeNum;
    }

    public int getStartValue() {
        return this.startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public int getEndValue() {
        return this.endValue;
    }

    public void setEndValue(int endValue) {
        this.endValue = endValue;
    }

    public String getGradeImages() {
        return this.gradeImages;
    }

    public void setGradeImages(String gradeImages) {
        this.gradeImages = gradeImages;
    }

    public int getPrivilege() {
        return this.privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

}
