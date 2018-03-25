package com.youlovelife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Main {
    @RequestMapping("/main")
    public String demo(){
        return "main.html";
    }
}
