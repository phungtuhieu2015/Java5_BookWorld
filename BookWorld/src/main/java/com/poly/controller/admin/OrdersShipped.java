package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrdersShipped {
    @RequestMapping("/orders-shipped")
    public String ordersShipped(Model model) {
        model.addAttribute("pageName", "orders-shipped");
        return "admin/index-admin";
    }
}
