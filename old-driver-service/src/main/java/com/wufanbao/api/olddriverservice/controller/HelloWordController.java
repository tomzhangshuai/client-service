package com.wufanbao.api.olddriverservice.controller;

import com.wufanbao.api.olddriverservice.DriverSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
    @Autowired
    private DriverSetting driverSetting;

    @GetMapping("V")
    public String version() {
        return driverSetting.getVersion();
    }
}
