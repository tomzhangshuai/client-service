package com.wufanbao.api.payservice.response.ali;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.IResponseHandle;
import com.wufanbao.api.payservice.response.wx.ResWxJSPay;

public class AliPrePayQueryHandle implements IResponseHandle {
    private ResAliPrePayQuery.Data data;

    public void setData(ResAliPrePayQuery.Data data) {
        this.data = data;
    }

    @Override
    public void decodeData(ResponseBody responseBody) throws Exception {
        ResponseData responseData = new Gson().fromJson(responseBody.getData(), new TypeToken<ResponseData<ResAliPrePayQuery>>() {
        }.getType());
        if (responseData.getCode() != "100") {
            throw new Exception(responseData.getMessage());
        }
        ResAliPrePayQuery resAliPrePayQuery = (ResAliPrePayQuery) responseData.getBiz_content();

        ResAliPrePayQuery.Data data = new ResAliPrePayQuery.Data();
        data.setResponseData(responseData);
        data.setResponseBody(responseBody);
        setData(data);
    }

    @Override
    public ResAliPrePayQuery.Data getData() {
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
