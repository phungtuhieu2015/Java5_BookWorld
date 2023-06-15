package com.poly.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.OrderDAO;
import com.poly.model.Order;
import com.poly.model.StatusOrder;
import com.poly.model.User;
import com.poly.service.SessionService;

@Controller
    @RequestMapping("/user")
public class OrdersByUserController {
    @Autowired
    OrderDAO dao;
    @Autowired
    SessionService session;
    boolean isError = false;
    StatusOrder statusOrder;
    String typeSuccess = "";
    @RequestMapping("/orders") 
    public String list(Model model,@RequestParam("p") Optional<Integer> p) {
        User user = session.get("user");
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page page = dao.findByUser(user, pageable);
        if(isError == true) {
            model.addAttribute("error", "(*) Vui lòng nhập lí do hủy đơn");
        }
        model.addAttribute("page",page);
        model.addAttribute("order", new Order());
        isError = false;
        String messageSuccess = null;
        switch (this.typeSuccess) {
            case "canceled-order":
                messageSuccess = "Đã hủy đơn";
                break;
            default:
                break;
        }
        model.addAttribute("message", messageSuccess);
        this.typeSuccess = "";
        return "danh-sach-don-hang";
    }
    @RequestMapping("/cancel-order") 
    public String cancel(Order currentOrder,Model model,@RequestParam("reason") Optional<String> reason) {
        User user = session.get("user");
        Order order = dao.findById(currentOrder.getId()).get();
        if(order.getStatus() == statusOrder.PENDING) {
            if(currentOrder.getCancellationReason() == null) {
                isError = true;
                 return "redirect:/user/orders";
            } else {
                order.setCancellationDate(new Date());
                order.setCancellationReason(currentOrder.getCancellationReason());
                order.setStatus(statusOrder.CANCELED);
                dao.save(order);
                this.typeSuccess = "canceled-order";
            }
               
        }
        Pageable pageable = PageRequest.of(0, 5);
        Page page = dao.findByUser(user, pageable);
        model.addAttribute("page",page);
        return "redirect:/user/orders";
    }
}
