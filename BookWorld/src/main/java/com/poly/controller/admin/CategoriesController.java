package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CategoriesController {
    @RequestMapping("/categories")
    public String publisher(Model model) {
        model.addAttribute("pageName","categories products");
        return "admin/index-admin";
    }
}
