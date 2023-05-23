package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class FavoriteBooksController {
    @RequestMapping("/favorite-books")
    public String author(Model model) {
        model.addAttribute("pageName","favorite-books");
        return "admin/index-admin";
    }
}
