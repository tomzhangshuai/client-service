package com.wufanbao.api.payservicedemo.controller;

import com.wufanbao.api.payservice.common.Helper;
import com.wufanbao.api.payservice.common.RequestData;
import com.wufanbao.api.payservice.common.Sign;
import com.wufanbao.api.payservice.request.wx.WXApi;
import com.wufanbao.api.payservice.response.wx.ResWxJSPay;
import com.wufanbao.api.payservice.response.wx.WxJSPayHandle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PayController {
    @GetMapping("/")
    public String hello() {
        return "hello pay,version:2018-07-17";
    }


    @ResponseBody
    @RequestMapping(value = "jspay", method = RequestMethod.GET)
    public String JsPay() {
        try {
            RequestData requestData = WXApi.jspay();
            //String sing = Sign.getSign(requestData);
            //return Helper.request(requestData, sing);
            RequestData wxJsrequestData = WXApi.jspay("", "", System.currentTimeMillis() + "", 0.01, 0, "http://www.baidu.com", "", "", "", "", "20201227091010", "", "");
            WxJSPayHandle handle = new WxJSPayHandle();
            Helper.request(requestData, handle);
            ResWxJSPay.Data wxJsData = handle.getData();
            ResWxJSPay.PayInfo payInfo = wxJsData.getPayInfo();
            return wxJsData.getResponseBody().toString();
        } catch (Exception e) {
            return "";
        }
    }
}
