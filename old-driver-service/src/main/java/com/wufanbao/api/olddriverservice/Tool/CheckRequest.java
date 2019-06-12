package com.wufanbao.api.olddriverservice.Tool;

import com.wufanbao.api.olddriverservice.DriverSetting;
import com.wufanbao.api.olddriverservice.entity.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckRequest {
    @Autowired
    private DriverSetting driverSetting;

    public int checkRequest(RequestInfo requestInfo) {
        String key = driverSetting.getVersion();// ResourceBundle.getBundle("IP").getString(requestInfo.getVersion());
        GetMD5 getMD5 = new GetMD5();
        String getDigital = getMD5.getMD5(requestInfo.getRequestId() + requestInfo.getData() + requestInfo.getRequestTime() + key);
        System.out.println(getDigital);
        System.out.println(requestInfo.getDigital() + "sss");
        if (getDigital.equals(requestInfo.getDigital())) {
            return 1;
        } else {
            return 0;
        }
    }
}
