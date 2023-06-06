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

import com.poly.dao.PublisherDAO;
import com.poly.model.Publisher;

@Controller
@RequestMapping("/admin")
public class PublishersController {

    @Autowired
    PublisherDAO dao;
   
    Boolean form = false;
    Boolean isEdit = false;

    Publisher item = new Publisher();

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
        if(item.getId() == null) {
            item = new Publisher();
        }
        model.addAttribute("form", form);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("item", item);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Publisher> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        
        return "admin/index-admin";
    }

    @RequestMapping("/publishers/edit/{id}")
    public String edit(@PathVariable("id") Long id) {    
        form = true;
        isEdit = true;     
        item = dao.findById(id).get();
        return "redirect:/admin/publishers";
    }

    @RequestMapping("/publishers/create")
    public String create(Publisher item) {
        dao.save(item);
        this.item = new Publisher();
        return "redirect:/admin/publishers";
    }

    @RequestMapping("/publishers/update")
    public String update(Publisher item, Model model) {
        dao.save(item);
        return "redirect:/admin/publishers/edit/" + item.getId();
    }

    @RequestMapping("/publishers/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.item = new Publisher();
        form = false;
        isEdit = false;
        dao.deleteById(id);
        return "redirect:/admin/publishers";
    }

    @RequestMapping("/publishers/reset")
    public String reset(Model model) {
        this.item = new Publisher();
        form = true;
        isEdit = false;
        return "redirect:/admin/publishers";
    }

}
