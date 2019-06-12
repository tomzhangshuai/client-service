package com.wufanbao.api.olddriverservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ViewPageController {
    @GetMapping("version")
    public String version() {
        return "/version";
    }

    @GetMapping("product")
    public String product() {

        return "/machine-product";
    }
}
