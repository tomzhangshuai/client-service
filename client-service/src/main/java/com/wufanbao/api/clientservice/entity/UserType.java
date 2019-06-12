package com.wufanbao.api.clientservice.entity;

//UserType
//alpha
public enum UserType {
    //普通用户:1
    Single(1),
    //集团:2
    Group(2),
    //家庭:3
    Family(3);

    private final int value;

    //构造方法必须是private或者默认
    private UserType(int value) {
        this.value = value;
    }

    public static int value(UserType value) {
        switch (value) {
            case Single:
                return 1;
            case Group:
                return 2;
            case Family:
                return 3;
            default:
                return 0;
        }
    }
}
