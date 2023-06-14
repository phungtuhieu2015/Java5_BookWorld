package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.model.User;
import com.poly.service.SessionService;

@Controller
@RequestMapping("/admin")
public class IndexAdminController {
    @Autowired
    SessionService session;

    @RequestMapping("/index")
    public String index(Model model) {
        User user = session.get("user");

        model.addAttribute("pageName", "content-index index");
        model.addAttribute("user", user);
        return "admin/index-admin";
    }
}
