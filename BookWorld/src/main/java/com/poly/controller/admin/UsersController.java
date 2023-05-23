package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UsersController {
    @RequestMapping("/users")
    public String soldeBooks(Model model){
        model.addAttribute("pageName", "users");
        return "admin/index-admin";
    }
}
