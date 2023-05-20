package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookworld")
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
