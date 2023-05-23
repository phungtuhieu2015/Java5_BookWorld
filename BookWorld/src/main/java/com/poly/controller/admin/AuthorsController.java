package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AuthorsController {
    @RequestMapping("/authors")
    public String author(Model model) {
        model.addAttribute("pageName","authors");
        return "admin/index-admin";
    }
}
