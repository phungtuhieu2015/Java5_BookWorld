package com.poly.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.CategoryDAO;
import com.poly.model.Category;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class CategoriesController {

    @Autowired
    CategoryDAO dao;

    Boolean form = false;
    Boolean isEdit = false;

    Category category = new Category();

    @RequestMapping("/categories")
    public String categories(Model model, @RequestParam("p") Optional<Integer> p ) {
        model.addAttribute("pageName", "categories products");
        if(p.isPresent()){
            //check khi bấm phân trang khi đang sửa
            if(isEdit == true){          
                 form = false;
            }
            // check khi bấm phân trang sau khi bấm reset  
             if(form == true ){
                 form = false;
                 isEdit = false;
             }
        }
        // check mặc định tránh bị ghi đè 
        if( (form == false && isEdit == false)  ){
             form = false;
             isEdit = false;
        } 
        if(category.getId() == null) {
            category = new Category();
        }
        model.addAttribute("form", form);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("category", category);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = dao.findAll(pageable);     
        model.addAttribute("page", page);

        return "admin/index-admin";
    }

    @RequestMapping("/categories/edit/{id}")
    public String edit(@PathVariable("id") Long id) {
        form = true;
        isEdit = true;
        category = dao.findById(id).get();
        return "redirect:/admin/categories";
    }

    @RequestMapping("/categories/create")
    public String create(@Valid Category category,BindingResult result,Model model) {   

        if(result.hasErrors()){     
            model.addAttribute("pageName", "categories products");
            model.addAttribute("form", true);
            model.addAttribute("isEdit", false);
            Optional<Integer> p = Optional.of(0);
            Pageable pageable = PageRequest.of(p.orElse(0), 5);
            Page<Category> page = dao.findAll(pageable);     
            model.addAttribute("page", page);
            return "admin/index-admin";
        }

        dao.save(category);
        this.category = new Category();
        return "redirect:/admin/categories";
    }

    @RequestMapping("/categories/update")
    public String update(Category category) {
        dao.save(category);
        return "redirect:/admin/categories/edit/" + category.getId();
    }

    @RequestMapping("/categories/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.category = new Category();
        form = false;
        isEdit = false;
        dao.deleteById(id);
        return "redirect:/admin/categories";
    }

    @RequestMapping("/categories/reset")
    public String reset(Model model) {
        this.category = new Category();
        form = true;
        isEdit = false;
        return "redirect:/admin/categories";
    }

}
