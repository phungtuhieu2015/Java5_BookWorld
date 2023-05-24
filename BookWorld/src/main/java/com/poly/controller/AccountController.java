package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @RequestMapping("/login")
    public String doLogin(Model model){

        return "login";
    }
    @RequestMapping("/forgot-password")
    public String doForgotPassword(Model model){

        return "forgot-password";
    }
    @RequestMapping("/change-password")
    public String doChangePassword(Model model){

        return "change-password";
    } 
    @RequestMapping("/sign-up")
    public String doSignUp(Model model){

        return "sign-up";
    }

}
