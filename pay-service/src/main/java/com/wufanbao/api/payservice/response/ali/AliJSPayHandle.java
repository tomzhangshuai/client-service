package com.wufanbao.api.payservice.response.ali;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.IResponseHandle;
import com.wufanbao.api.payservice.response.wx.ResWxJSPay;

public class AliJSPayHandle implements IResponseHandle {
    private ResAliJSPay.Data data;

    public void setData(ResAliJSPay.Data data) {
        this.data = data;
    }

    @Override
    public void decodeData(ResponseBody responseBody) throws Exception {
        ResponseData responseData = new Gson().fromJson(responseBody.getData(), new TypeToken<ResponseData<ResAliJSPay>>() {
        }.getType());
        if (responseData.getCode() != "100") {
            throw new Exception(responseData.getMessage());
        }
        ResAliJSPay resAliJSPay = (ResAliJSPay) responseData.getBiz_content();
        ResAliJSPay.PayInfo payInfo = new Gson().fromJson(resAliJSPay.getPay_info(), ResAliJSPay.PayInfo.class);

        ResAliJSPay.Data data = new ResAliJSPay.Data();
        data.setPayInfo(payInfo);
        data.setResponseData(responseData);
        data.setResponseBody(responseBody);
        setData(data);
    }

    @Override
    public ResAliJSPay.Data getData() {
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
