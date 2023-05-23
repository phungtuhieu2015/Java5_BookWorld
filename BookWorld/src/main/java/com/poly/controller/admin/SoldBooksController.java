package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class SoldBooksController {
    @RequestMapping("/sold-books")
    public String soldeBooks(Model model){
        model.addAttribute("pageName", "sold-books");
        return "admin/index-admin";
    }
}
