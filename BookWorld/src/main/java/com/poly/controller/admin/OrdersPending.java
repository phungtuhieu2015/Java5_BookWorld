package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrdersPending {
    @RequestMapping("orders-pending")
    public String ordersPending(Model model){
        model.addAttribute("pageName", "orders-pending orders");
        return "admin/index-admin";
    }
}
