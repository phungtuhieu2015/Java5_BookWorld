package com.poly.controller.admin;

import java.util.List;
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

import com.poly.dao.AuthorDAO;
import com.poly.model.Author;

@Controller
@RequestMapping("/admin")
public class AuthorsController {

    @Autowired
    AuthorDAO dao;
  
    Boolean form = false;
    Boolean isEdit = false;


    @RequestMapping("/authors")
    public String author(Model model,@RequestParam("p") Optional<Integer> p) {
        model.addAttribute("pageName","authors products");
        model.addAttribute("form", form);

        Author item = new Author();
        model.addAttribute("item", item);


        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Author> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        
        model.addAttribute("isEdit", isEdit);
        return "admin/index-admin";
    }


    @RequestMapping("/authors/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("pageName","categories products");
        model.addAttribute("form", true);

        model.addAttribute("isEdit", true);

        Author item = dao.findById(id).get();
        model.addAttribute("item", item);
        List<Author> items = dao.findAll();
        model.addAttribute("items", items);    
        return "admin/index-admin";
    }

    @RequestMapping("/authors/create")
    public String create(Author item) {
        
        dao.save(item);
        form = false;
        return "redirect:/admin/authors";
    }

    @RequestMapping("/authors/update")
    public String update(Author item,Model model) {
        dao.save(item);
        return "redirect:/admin/authors/edit/" + item.getId();
    }


    @RequestMapping("/authors/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "redirect:/admin/categories";
    }

    @RequestMapping("/authors/reset")
    public String reset(Model model) {
        form = true;
        
        return "redirect:/admin/categories";
    }




}
