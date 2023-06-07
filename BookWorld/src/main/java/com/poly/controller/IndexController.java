package com.poly.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.BookDAO;
import com.poly.model.Book;


@Controller

public class IndexController {

    @Autowired
    BookDAO dao;
    
    Book book = new Book();

    @RequestMapping("/index")
    public String index(Model model, @RequestParam("p") Optional<Integer> p){

        Pageable pageable = PageRequest.of(p.orElse(0), 8);
        Page<Book> page = dao.findAll(pageable);   
        model.addAttribute("page", page);

        return "index";
        
    }
}
