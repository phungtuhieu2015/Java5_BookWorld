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

import com.poly.dao.PublisherDAO;
import com.poly.model.Publisher;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class PublishersController {

    @Autowired
    PublisherDAO dao;
   
    Boolean form = false;
    Boolean isEdit = false;

    Publisher publisher = new Publisher();

    @RequestMapping("/publishers")
    public String publisher(Model model, @RequestParam("p") Optional<Integer> p) {
        model.addAttribute("pageName", "publishers products");
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
        if(publisher.getId() == null) {
            publisher = new Publisher();
        }
        model.addAttribute("form", form);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("publisher", publisher);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Publisher> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        
        return "admin/index-admin";
    }

    @RequestMapping("/publishers/edit/{id}")
    public String edit(@PathVariable("id") Long id) {    
        form = true;
        isEdit = true;     
        publisher = dao.findById(id).get();
        return "redirect:/admin/publishers";
    }

    @RequestMapping("/publishers/create")
    public String create(@Valid Publisher publisher,BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("pageName", "publishers products");
            model.addAttribute("form", true);
            model.addAttribute("isEdit", false);
            Optional<Integer> p = Optional.of(0);
            Pageable pageable = PageRequest.of(p.orElse(0), 5);
            Page<Publisher> page = dao.findAll(pageable);     
            model.addAttribute("page", page);
            return "admin/index-admin";
        }


        dao.save(publisher);
        this.publisher = new Publisher();
        return "redirect:/admin/publishers";
    }

    @RequestMapping("/publishers/update")
    public String update(Publisher publisher, Model model) {
        dao.save(publisher);
        return "redirect:/admin/publishers/edit/" + publisher.getId();
    }

    @RequestMapping("/publishers/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.publisher = new Publisher();
        form = false;
        isEdit = false;
        dao.deleteById(id);
        return "redirect:/admin/publishers";
    }

    @RequestMapping("/publishers/reset")
    public String reset(Model model) {
        this.publisher = new Publisher();
        form = true;
        isEdit = false;
        return "redirect:/admin/publishers";
    }

}
