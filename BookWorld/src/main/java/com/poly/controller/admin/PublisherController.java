package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class PublisherController {
    @RequestMapping("/publisher")
    public String publisher(Model model) {
        model.addAttribute("pageName","publisher");
        return "admin/index-admin";
    }
}
