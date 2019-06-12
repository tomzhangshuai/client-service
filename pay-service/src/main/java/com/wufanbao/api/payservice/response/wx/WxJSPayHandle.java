package com.wufanbao.api.payservice.response.wx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.IResponseHandle;
import com.wufanbao.api.payservice.response.wx.ResWxJSPay;

public class WxJSPayHandle implements IResponseHandle {
    private ResWxJSPay.Data data;

    public void setData(ResWxJSPay.Data data) {
        this.data = data;
    }

    @Override
    public void decodeData(ResponseBody responseBody) throws Exception {

        ResponseData responseData = new Gson().fromJson(responseBody.getData(), new TypeToken<ResponseData<ResWxJSPay>>() {
        }.getType());
        if (!responseData.getCode().equals("100")) {
            throw new Exception(responseData.getMessage());
        }
        ResWxJSPay resWxJSPay = (ResWxJSPay) responseData.getBiz_content();
        ResWxJSPay.PayInfo payInfo = new Gson().fromJson(resWxJSPay.getPay_info(), ResWxJSPay.PayInfo.class);

        ResWxJSPay.Data data = new ResWxJSPay.Data();
        data.setPayInfo(payInfo);
        data.setResponseData(responseData);
        data.setResponseBody(responseBody);
        setData(data);
    }

    @Override
    public ResWxJSPay.Data getData() {
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
