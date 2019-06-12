package com.wufanbao.api.payservice.response.ali;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.IResponseHandle;

public class AliQueryHandle implements IResponseHandle {
    private ResAliQuery.Data data;

    public void setData(ResAliQuery.Data data) {
        this.data = data;
    }

    @Override
    public void decodeData(ResponseBody responseBody) throws Exception {
        ResponseData responseData = new Gson().fromJson(responseBody.getData(), new TypeToken<ResponseData<ResAliQuery>>() {
        }.getType());
        if (responseData.getCode() != "100") {
            throw new Exception(responseData.getMessage());
        }
        ResAliQuery resAliQuery = (ResAliQuery) responseData.getBiz_content();

        ResAliQuery.Data data = new ResAliQuery.Data();
        data.setResponseData(responseData);
        data.setResponseBody(responseBody);
        setData(data);
    }

    @Override
    public ResAliQuery.Data getData() {
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
