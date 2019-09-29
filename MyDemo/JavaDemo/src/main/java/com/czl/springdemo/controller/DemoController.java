package com.czl.springdemo.controller;

import com.czl.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
    @Autowired
    private IService service;

    @RequestMapping("/index.do")
    public String index(){
        service.executeBusinessA();
        return "index";
    }
}