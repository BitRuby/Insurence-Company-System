package com.youlife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Demo {
    @RequestMapping("/demo")
    public String demo(){
        return "demo.html";
    }
}
