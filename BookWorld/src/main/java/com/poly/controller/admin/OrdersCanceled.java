package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrdersCanceled {
    @RequestMapping("/orders-canceled")
    public String ordersShipped(Model model) {
        model.addAttribute("pageName", "orders-canceled");
        return "admin/index-admin";
    }
}
