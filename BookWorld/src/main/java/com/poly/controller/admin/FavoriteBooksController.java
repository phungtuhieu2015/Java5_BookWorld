package com.poly.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.BookDAO;
import com.poly.model.BookFavorite;

@Controller
@RequestMapping("/admin")
public class FavoriteBooksController {


    @Autowired 
    BookDAO dao;
    @RequestMapping("/favorite-books")
    public String favoriteBooks(Model model) {
        model.addAttribute("pageName","favorite-books statistical");

        List<BookFavorite> items = dao.getBookFavorite();
        model.addAttribute("items", items);
        
        return "admin/index-admin";
    }
}
