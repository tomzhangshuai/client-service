package com.wufanbao.api.payservice.response.wx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.IResponseHandle;

public class WxAppPayHandle implements IResponseHandle {
    private ResWxAppPay.Data data;

    public void setData(ResWxAppPay.Data data) {
        this.data = data;
    }

    @Override
    public void decodeData(ResponseBody responseBody) throws Exception {
        ResponseData responseData = new Gson().fromJson(responseBody.getData(), new TypeToken<ResponseData<ResWxAppPay>>() {
        }.getType());
        if (responseData.getCode() != "100") {
            throw new Exception(responseData.getMessage());
        }
        ResWxAppPay resWxAppPay = (ResWxAppPay) responseData.getBiz_content();
        ResWxAppPay.PayInfo payInfo = new Gson().fromJson(resWxAppPay.getPay_info(), ResWxAppPay.PayInfo.class);

        ResWxAppPay.Data data = new ResWxAppPay.Data();
        data.setPayInfo(payInfo);
        data.setResponseData(responseData);
        data.setResponseBody(responseBody);
        setData(data);

    }

    @Override
    public ResWxAppPay.Data getData() {
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
