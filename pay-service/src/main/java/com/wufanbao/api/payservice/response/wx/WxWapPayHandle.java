package com.wufanbao.api.payservice.response.wx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.payservice.common.ResponseBody;
import com.wufanbao.api.payservice.common.ResponseData;
import com.wufanbao.api.payservice.response.IResponseHandle;

public class WxWapPayHandle implements IResponseHandle {
    private ResWxWapPay.Data data;

    public void setData(ResWxWapPay.Data data) {
        this.data = data;
    }

    @Override
    public void decodeData(ResponseBody responseBody) throws Exception {
        ResponseData responseData = new Gson().fromJson(responseBody.getData(), new TypeToken<ResponseData<ResWxWapPay>>() {
        }.getType());
        if (responseData.getCode() != "100") {
            throw new Exception(responseData.getMessage());
        }
        ResWxWapPay resWxWapPay = (ResWxWapPay) responseData.getBiz_content();
        ResWxWapPay.PayInfo payInfo = new Gson().fromJson(resWxWapPay.getPay_info(), ResWxWapPay.PayInfo.class);

        ResWxWapPay.Data data = new ResWxWapPay.Data();
        data.setPayInfo(payInfo);
        data.setResponseData(responseData);
        data.setResponseBody(responseBody);
        setData(data);
    }

    @Override
    public ResWxWapPay.Data getData() {
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
