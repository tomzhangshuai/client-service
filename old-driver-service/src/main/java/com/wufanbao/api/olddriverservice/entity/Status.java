package com.wufanbao.api.olddriverservice.entity;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-09-19
 * Time:19:50
 */
public class Status {
    private List data;
    private int status;
    private List msg;

    public List getMsg() {
        return msg;
    }

    public void setMsg(List msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
