package com.wufanbao.api.payservice.response.wx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.IResponseHandle;

public class WxRefundHandle implements IResponseHandle {
    private ResWxRefund.Data data;

    public void setData(ResWxRefund.Data data) {
        this.data = data;
    }

    @Override
    public void decodeData(ResponseBody responseBody) throws Exception {
        ResponseData responseData = new Gson().fromJson(responseBody.getData(), new TypeToken<ResponseData<ResWxRefund>>() {
        }.getType());
        if (responseData.getCode() != "100") {
            throw new Exception(responseData.getMessage());
        }
        ResWxRefund resWxRefund = (ResWxRefund) responseData.getBiz_content();


        ResWxRefund.Data data = new ResWxRefund.Data();
        data.setResponseData(responseData);
        data.setResponseBody(responseBody);
        setData(data);
    }

    @Override
    public ResWxRefund.Data getData() {
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
