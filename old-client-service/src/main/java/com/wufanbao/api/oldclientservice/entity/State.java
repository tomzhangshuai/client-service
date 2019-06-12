package com.wufanbao.api.oldclientservice.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-08-05
 * Time:15:17
 */
public class State {
    private List success;
    private Integer error;
    private long date;
    private List dianzan;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List getDianzan() {
        return dianzan;
    }

    public void setDianzan(List dianzan) {
        this.dianzan = dianzan;
    }

    public List getSuccess() {
        return success;
    }

    public void setSuccess(List success) {
        this.success = success;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }
}
