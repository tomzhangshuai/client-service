package com.wufanbao.api.olddriverservice.entity;

import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-09-21
 * Time:14:29
 */
public class GoodsMax {
    private Object data;
    private Map goods;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map getGoods() {
        return goods;
    }

    public void setGoods(Map goods) {
        this.goods = goods;
    }
}
