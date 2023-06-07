package com.poly.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class GioithieuController {

    
    @GetMapping("/gioithieu")
    public String gioithieu(){
        return "gioi-thieu";
    }
}