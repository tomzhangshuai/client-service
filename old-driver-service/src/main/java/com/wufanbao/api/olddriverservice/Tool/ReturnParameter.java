package com.wufanbao.api.olddriverservice.Tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wufanbao.api.olddriverservice.DriverSetting;
import com.wufanbao.api.olddriverservice.entity.DataNull;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 组装返回参数
 */
@Component
public class ReturnParameter {

    @Autowired
    private DriverSetting driverSetting;

    //根据code获取message
    public String getCodeValue(int code) {
        Map map = new HashMap();
        for (CodeValue codeValue : CodeValue.values()
                ) {
            map.put(codeValue.getCode(), codeValue.getMessage());
        }
        return map.get(code).toString();
    }

    public ResponseInfo returnRequestInfo(ResponseInfo responseInfo, String version) {
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //根据版本号取对应的key
        String key = driverSetting.getVersion();  //ResourceBundle.getBundle("IP").getString(version);
        responseInfo.setMessage(getCodeValue(responseInfo.getCode()));
        responseInfo.setResponseTime(sdf.format(new Date()));
        if (responseInfo.getData() == null) {
            DataNull dataNull = new DataNull();
            responseInfo.setData(dataNull);
        }
        //加密数字摘要
        GetMD5 getMD5 = new GetMD5();
        String data = JSON.toJSONString(responseInfo.getData(), SerializerFeature.DisableCircularReferenceDetect);

        //System.out.println("data"+data);
        responseInfo.setData(data);
        String digital = getMD5.getMD5(responseInfo.getCode() + responseInfo.getMessage() + data + responseInfo.getResponseTime() + key);
        //System.out.println(responseInfo.getData().toString()+"safsdf");
        responseInfo.setDigital(digital);
        //System.out.println(digital+"加密");
        return responseInfo;
    }

    public ResponseInfo responserInfoErro() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(4);
        responseInfo.setMessage(getCodeValue((4)));
        responseInfo.setResponseTime(sdf.format(new Date()));
        responseInfo.setData("");
        responseInfo.setDigital("");
        return responseInfo;
    }

}
