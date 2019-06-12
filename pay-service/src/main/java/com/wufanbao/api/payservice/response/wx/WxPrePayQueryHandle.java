package com.wufanbao.api.payservice.response.wx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.IResponseHandle;

public class WxPrePayQueryHandle implements IResponseHandle {
    private ResWxPrePayQuery.Data data;

    public void setData(ResWxPrePayQuery.Data data) {
        this.data = data;
    }

    @Override
    public void decodeData(ResponseBody responseBody) throws Exception {
        ResponseData responseData = new Gson().fromJson(responseBody.getData(), new TypeToken<ResponseData<ResWxPrePayQuery>>() {
        }.getType());
        if (responseData.getCode() != "100") {
            throw new Exception(responseData.getMessage());
        }
        ResWxPrePayQuery resWxPrePayQuery = (ResWxPrePayQuery) responseData.getBiz_content();


        ResWxPrePayQuery.Data data = new ResWxPrePayQuery.Data();
        data.setResponseData(responseData);
        data.setResponseBody(responseBody);
        setData(data);
    }

    @Override
    public ResWxPrePayQuery.Data getData() {
        return this.data;
    }

    @Override
    public String GetResponseDataStr() {
        return this.data.getResponseBody().getData();
    }

    @Override
    public String getTimestamp() {
        return this.data.getResponseData().getTimestamp();
    }
}
