package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/bookworld")
public class LienHeController {

    
    @GetMapping("/lienhe")
    public String lienHe(){
        return "lien-he";
    }
}
