package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.model.DB;

@Controller

public class IndexController {
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("items", DB.items.values());
        return "index";
        
    }
}
