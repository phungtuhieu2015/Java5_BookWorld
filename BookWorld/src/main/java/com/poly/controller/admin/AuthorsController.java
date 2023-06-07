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

import com.poly.dao.AuthorDAO;
import com.poly.model.Author;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AuthorsController {

    @Autowired
    AuthorDAO dao;

    Boolean form = false;
    Boolean isEdit = false;
    Author author = new Author();

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
        if(author.getId() == null) {
            author = new Author();
        }

        model.addAttribute("form", form);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("author", author);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Author> page = dao.findAll(pageable);   
        model.addAttribute("page", page);

        return "admin/index-admin";
    }

    @RequestMapping("/authors/edit/{id}")
    public String edit(@PathVariable("id") Long id) {
        form = true;
        isEdit = true;
        author = dao.findById(id).get();
        return "redirect:/admin/authors";
    }

    @RequestMapping("/authors/create")
    public String create(@Valid Author author, BindingResult result,Model model) {

        if(result.hasErrors()){
            model.addAttribute("pageName", "authors products");
            model.addAttribute("form", true);
            model.addAttribute("isEdit", false);
            Optional<Integer> p = Optional.of(0);
            Pageable pageable = PageRequest.of(p.orElse(0), 5);
            Page<Author> page = dao.findAll(pageable);     
            model.addAttribute("page", page);
            return "admin/index-admin";
        }


        dao.save(author);
        this.author = new Author();
        return "redirect:/admin/authors";
    }

    @RequestMapping("/authors/update")
    public String update(Author author, Model model) {
        dao.save(author);
        return "redirect:/admin/authors/edit/" + author.getId();
    }

    @RequestMapping("/authors/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.author = new Author();
         form = false;
         isEdit = false;
        dao.deleteById(id);
        return "redirect:/admin/authors";
    }

    @RequestMapping("/authors/reset")
    public String reset(Model model) {
        this.author = new Author();
        form = true;
        isEdit = false;
        return "redirect:/admin/authors";
    }

}
