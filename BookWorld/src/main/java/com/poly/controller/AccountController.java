package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookworld")
public class AccountController {
    @RequestMapping("/login")
    public String doLogin(Model model){

        return "login";
    }
}
