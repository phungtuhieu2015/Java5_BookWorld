package com.poly.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.BookDAO;
import com.poly.model.ReportSoldBooks;
import com.poly.model.User;
import com.poly.service.SessionService;


@Controller
@RequestMapping("/admin")
public class SoldBooksController {
        @Autowired
    SessionService session;
    @Autowired 
    BookDAO dao;
    @RequestMapping("/sold-books")
    public String soldeBooks(Model model){
        model.addAttribute("pageName", "sold-books statistical");
              User user = session.get("user");
        model.addAttribute("user", user);
        List<ReportSoldBooks> items = dao.getSoldBooks();
        model.addAttribute("items", items);
        return "admin/index-admin";
    }



}
