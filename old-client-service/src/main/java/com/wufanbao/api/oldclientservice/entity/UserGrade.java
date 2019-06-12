package com.wufanbao.api.oldclientservice.entity;

public class UserGrade {
    private long userGradeId;//用户等级id
    private String gradeName;//用户等级名称
    private int gradeNum;//用户等级
    private int startValue;//开始值
    private int endValue;//结束值
    private String gradeImages;//等级图片
    private int privilege;//枚举值
    private int gradeValue;

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public long getUserGradeId() {
        return userGradeId;
    }

    public void setUserGradeId(long userGradeId) {
        this.userGradeId = userGradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(int gradeNum) {
        this.gradeNum = gradeNum;
    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public int getEndValue() {
        return endValue;
    }

    public void setEndValue(int endValue) {
        this.endValue = endValue;
    }

    public String getGradeImages() {
        return gradeImages;
    }

    public void setGradeImages(String gradeImages) {
        this.gradeImages = gradeImages;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
