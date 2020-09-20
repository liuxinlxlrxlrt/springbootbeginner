package com.mayikt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    @Value("${mayikt.url}")
    private String url;

    @RequestMapping("/getUrl")
    public String getUrl(){
        return url;
    }
}
