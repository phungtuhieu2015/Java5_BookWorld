package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductsController {
    @RequestMapping("/products")
    public String products(Model model) {
        model.addAttribute("pageName", "products products");
        return "admin/index-admin";
    }
}
