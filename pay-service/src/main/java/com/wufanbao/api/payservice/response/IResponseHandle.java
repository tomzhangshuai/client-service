package com.wufanbao.api.payservice.response;


import com.wufanbao.api.payservice.common.ResponseBody;

public interface IResponseHandle<T> {
    void decodeData(ResponseBody responseBody) throws Exception;

    T getData();

    String GetResponseDataStr();

    String getTimestamp();
}
