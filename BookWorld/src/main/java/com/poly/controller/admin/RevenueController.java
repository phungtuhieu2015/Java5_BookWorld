package com.poly.controller.admin;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.OrderDetailDAO;
import com.poly.model.ReportRevenue;

@Controller
@RequestMapping("/admin")
public class RevenueController {
    @Autowired
    OrderDetailDAO dao;
    @RequestMapping("/revenue")
    public String revenue(Model model, @RequestParam("startDate") Optional<Date> startDate,
    @RequestParam("endDate") Optional<Date> endDate,@RequestParam("p") Optional<Integer> p){
        model.addAttribute("pageName", "revenue statistical");
        LocalDate localDate = LocalDate.of(1975, 1, 1);
        Date startDateDefault = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date currentDateDefault = new Date();
        Pageable pageable = PageRequest.of( p.orElse(0), 5);
        Page page = dao.findByRevenueDate(startDate.orElse(startDateDefault),endDate.orElse(currentDateDefault) , pageable);
         model.addAttribute("page", page);
        return "admin/index-admin";
    }
}
