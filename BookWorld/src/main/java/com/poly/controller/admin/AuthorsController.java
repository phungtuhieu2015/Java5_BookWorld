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
    Author item = new Author();

    @RequestMapping("/authors")
    public String author(Model model, @RequestParam("p") Optional<Integer> p) {
        model.addAttribute("pageName", "authors products");

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
            item = new Author();
        }

        model.addAttribute("form", form);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("item", item);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Author> page = dao.findAll(pageable);   
        model.addAttribute("page", page);

        return "admin/index-admin";
    }

    @RequestMapping("/authors/edit/{id}")
    public String edit(@PathVariable("id") Long id) {
        form = true;
        isEdit = true;
        item = dao.findById(id).get();
        return "redirect:/admin/authors";
    }

    @RequestMapping("/authors/create")
    public String create(Author item) {
        dao.save(item);
        this.item = new Author();
        return "redirect:/admin/authors";
    }

    @RequestMapping("/authors/update")
    public String update(Author item, Model model) {
        dao.save(item);
        return "redirect:/admin/authors/edit/" + item.getId();
    }

    @RequestMapping("/authors/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.item = new Author();
         form = false;
         isEdit = false;
        dao.deleteById(id);
        return "redirect:/admin/authors";
    }

    @RequestMapping("/authors/reset")
    public String reset(Model model) {
        this.item = new Author();
        form = true;
        isEdit = false;
        return "redirect:/admin/authors";
    }

}
