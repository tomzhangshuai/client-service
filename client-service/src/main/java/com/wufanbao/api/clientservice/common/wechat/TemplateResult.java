package com.wufanbao.api.clientservice.common.wechat;

public class TemplateResult {
    private  int  errcode;
    private String  errmsg;
    private  long  msgid;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public void setMsgid(long msgid){
        this.msgid=msgid;
    }

    public long getMsgid() {
        return msgid;
    }
}
