package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookworld")
public class ChiTietController {
    
    @GetMapping("/chitiet")
    public String lienHe(){
        return "chi-tiet";
    }
}
