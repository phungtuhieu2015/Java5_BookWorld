package com.poly.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.poly.model.User;
import com.poly.service.SessionService;

@Controller
public class GioithieuController {

    @Autowired
    SessionService session;

    @GetMapping("/gioithieu")
    public String gioithieu(Model model, @RequestParam("p") Optional<Integer> p) {
       User user = session.get("user");
        // System.out.println(user.getUsername()+"sssssssssssss");
        if (user == null)

        {
             model.addAttribute("user", user);
            model.addAttribute("checkLG", false);
        } else {
             model.addAttribute("user", user);
            
            model.addAttribute("checkLG", true);
        }
        return "gioi-thieu";
    }
}