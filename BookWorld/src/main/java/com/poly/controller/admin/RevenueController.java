package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class RevenueController {
    @RequestMapping("/revenue")
    public String revenue(Model model){
        model.addAttribute("pageName", "revenue statistical");
        return "admin/index-admin";
    }
}
