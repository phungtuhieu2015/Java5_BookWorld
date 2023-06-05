package com.poly.controller.admin;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.CategoryDAO;
import com.poly.model.Category;
import com.poly.service.SessionService;

@Controller
@RequestMapping("/admin")
public class CategoriesController {

    @Autowired
    CategoryDAO dao;
    @Autowired
    SessionService session; 
    Boolean form = false;
    Boolean isEdit = false;


    @RequestMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("pageName","categories products");
        model.addAttribute("form", form);
        Category item = new Category();
        model.addAttribute("item", item);
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);
        
        model.addAttribute("isEdit", isEdit);
        
        return "admin/index-admin";
    }

    
    @RequestMapping("/categories/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("pageName","categories products");
        model.addAttribute("form", true);

        model.addAttribute("isEdit", true);

        Category item = dao.findById(id).get();
        model.addAttribute("item", item);
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);    
        return "admin/index-admin";
    }

    @RequestMapping("/categories/create")
    public String create(Category item) {
        dao.save(item);
        form = false;
        return "redirect:/admin/categories";
    }

    @RequestMapping("/categories/update")
    public String update(Category item,Model model) {
        dao.save(item);
        return "redirect:/admin/categories/edit/" + item.getId();
    }


    @RequestMapping("/categories/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "redirect:/admin/categories";
    }

    @RequestMapping("/categories/reset")
    public String reset(Model model) {
        form = true;
        
        return "redirect:/admin/categories";
    }


}
