package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AuthorController {
    @RequestMapping("/author")
    public String author(Model model) {
        model.addAttribute("pageName","author");
        return "admin/index-admin";
    }
}
