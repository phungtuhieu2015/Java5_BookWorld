package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GioHangController {
    
    @GetMapping("/giohang")
    public String lienHe(){
        return "gio-hang";
    }
   


}
