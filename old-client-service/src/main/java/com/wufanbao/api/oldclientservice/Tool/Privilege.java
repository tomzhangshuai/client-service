package com.wufanbao.api.oldclientservice.Tool;

public enum Privilege {
    two("1,2", 3),
    three("1,2,3", 7),
    four("1,2,3,4", 15),;
    private String privilegeList;
    private int privilege;

    Privilege(String privilegeList, int privilege) {
        this.privilegeList = privilegeList;
        this.privilege = privilege;
    }

    public String getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(String privilegeList) {
        this.privilegeList = privilegeList;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
