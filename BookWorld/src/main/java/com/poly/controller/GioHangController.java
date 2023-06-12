package com.poly.controller;

import java.util.List;
import java.util.Optional;

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
import com.poly.model.User;
import com.poly.service.SessionService;
import com.poly.service.ShoppingCartService;

@Controller
public class GioHangController {
    
    @Autowired
    ShoppingCartService cart; 

    @Autowired
    SessionService session;

    boolean isError = false;
    boolean isEmptyCarts = false;
    @RequestMapping("/giohang")
    public String view(Model model) {
        model.addAttribute("list", cart.getCarts()); 
        model.addAttribute("total", cart.getTotal());
        if(isError) {
           
            model.addAttribute("error", "Vui lòng nhập thông tin");
        } 
        if(isEmptyCarts) {
            model.addAttribute("isEmptyCarts", "Chưa có sản phẩm trong giỏ hàng");
        }
        isError = false;
        isEmptyCarts = false;
        return "gio-hang";
    }

    @RequestMapping("/cart/add/{id}")
    public String add(@PathVariable("id") String id) {
        cart.add(id);
        return "redirect:/giohang";
    }

    @RequestMapping("/cart/remove/{id}")
    public String remove(@PathVariable("id") String id) {
        cart.remove(id );
        return "redirect:/giohang";
    }

    @RequestMapping("/cart/update/{id}")
    public String update(@PathVariable("id") String id,
            @RequestParam("qty") Integer qty) {
        cart.update(id, qty);
        return "redirect:/giohang";
    }

    @RequestMapping("/cart/clear")
    public String clear() {
        cart.clear();
        return "redirect:/giohang";
    }

    @RequestMapping("/cart/payment")
    public String payment(User user,@RequestParam("paymentMethod") Optional<String> param) {
            isError = false;
            if(session.get("user") == null) {
                return"redirect:/account/login";
            } else {
                if(user.getPhone().isBlank()) {
                    isError = true;
                } if(user.getAddress().isBlank()){
                    isError = true;
                }
                List<Cart> list = (List<Cart>) cart.getCarts();

                System.out.println("==========================================="+list);
                if(list == null){
                    isEmptyCarts = true;
                }
                if(isError == true || isEmptyCarts == true) {
                    return "redirect:/giohang";
                }
                User currentUser = session.get("user");
                currentUser.setFullName(user.getFullName());
                currentUser.setPhone(user.getPhone());
                currentUser.setAddress(user.getAddress());
                Boolean paymentMethod = true;
                if(param.equals("online")){
                        paymentMethod = false;
                }
                cart.payment(currentUser, paymentMethod );
            }
        return "redirect:/giohang";
    }

}
