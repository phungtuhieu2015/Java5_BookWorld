package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.model.Cart;
import com.poly.model.Product;
import com.poly.service.ShoppingCartService;

@Controller
public class GioHangController {
    
    @Autowired
    ShoppingCartService cart; 

    @RequestMapping("/giohang")
    public String view(Model model) {
        model.addAttribute("list", cart.getOrderDetails());
        return "gio-hang";
    }

    @RequestMapping("/cart/add/{id}")
    public String add(@PathVariable("id") String id) {
        cart.add(id);
        return "redirect:/giohang";
    }

    @RequestMapping("/cart/remove/{id}")
    public String remove(@PathVariable("id") String id) {
        cart.remove(Long.parseLong(id) );
        return "redirect:/giohang";
    }

    @RequestMapping("/cart/update/{id}")
    public String update(@PathVariable("id") String id,
            @RequestParam("qty") Integer qty) {
        cart.update(Long.parseLong(id), qty);
        return "redirect:/giohang";
    }

    @RequestMapping("/cart/clear")
    public String clear() {
        cart.clear();
        return "redirect:/giohang";
    }

}
