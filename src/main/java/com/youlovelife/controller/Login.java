package com.youlovelife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {

    @RequestMapping("/login")
    public String demo() {
        return "login.html";
    }
}
