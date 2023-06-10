package com.poly.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.OrderDAO;
import com.poly.model.StatusOrder;

@Controller
@RequestMapping("/admin")
public class OrdersCanceled {
    @Autowired
    OrderDAO dao;
    StatusOrder status;
    @RequestMapping("/orders-canceled")
    public String ordersShipped(Model model,@RequestParam("p") Optional<Integer> p) {
        model.addAttribute("pageName", "orders-canceled orders");
         Pageable pageable = PageRequest.of( p.orElse(0), 5);
        Page page = dao.findByStatus(StatusOrder.CANCELED,pageable);
        model.addAttribute("page", page);
        return "admin/index-admin";
    }
}
