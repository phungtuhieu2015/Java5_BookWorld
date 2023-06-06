package com.poly.controller.admin;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.CategoryDAO;
import com.poly.model.Category;
import com.poly.service.SessionService;

@Controller
@RequestMapping("/admin")
public class CategoriesController {

    @Autowired
    CategoryDAO dao;

    Boolean form = false;
    Boolean isEdit = false;

    Category item = new Category();

    @RequestMapping("/categories")
    public String categories(Model model, @RequestParam("p") Optional<Integer> p) {
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
        if(item.getId() == null) {
            item = new Category();
        }
        model.addAttribute("form", form);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("item", item);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = dao.findAll(pageable);     
        model.addAttribute("page", page);

        return "admin/index-admin";
    }

    @RequestMapping("/categories/edit/{id}")
    public String edit(@PathVariable("id") Long id) {
        form = true;
        isEdit = true;
        item = dao.findById(id).get();
        return "redirect:/admin/categories";
    }

    @RequestMapping("/categories/create")
    public String create(Category item) {
        dao.save(item);
        this.item = new Category();
        return "redirect:/admin/categories";
    }

    @RequestMapping("/categories/update")
    public String update(Category item) {
        dao.save(item);
        return "redirect:/admin/categories/edit/" + item.getId();
    }

    @RequestMapping("/categories/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.item = new Category();
        form = false;
        isEdit = false;
        dao.deleteById(id);
        return "redirect:/admin/categories";
    }

    @RequestMapping("/categories/reset")
    public String reset(Model model) {
        this.item = new Category();
        form = true;
        isEdit = false;
        return "redirect:/admin/categories";
    }

}
