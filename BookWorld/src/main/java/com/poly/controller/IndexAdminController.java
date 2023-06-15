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
    @Autowired
    private IndexController indexController;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("pageName", "content-index index");
        indexController.checkUsers(model);
        return "admin/index-admin";
    }
}
