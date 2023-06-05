package com.poly.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.UserDAO;
import com.poly.model.User;
import com.poly.service.SessionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class UsersController {

  @Autowired
  UserDAO dao;
  @Autowired
  SessionService session; 
  Boolean form = false;
  Boolean isEdit = false;
  


  @RequestMapping("/users")
  public String Users(Model model) {
    model.addAttribute("pageName", "users user");
      model.addAttribute("form", form);
      User item = new User();
      model.addAttribute("item", item);
      List<User> items = dao.findAll();
      model.addAttribute("items", items);
      
      model.addAttribute("isEdit", isEdit);
      
      return "admin/index-admin";
  }

  
 @RequestMapping("/users/edit/{username}")
 public String edit(Model model, @PathVariable("username") String username) {
  model.addAttribute("pageName", "users user");
     model.addAttribute("form", true);

     model.addAttribute("isEdit", true);
     User item = dao.findById(username).get();
     model.addAttribute("item", item);
     List<User> items = dao.findAll();
     model.addAttribute("items", items);    
     return "admin/index-admin";
 }

  // @RequestMapping("/users/create")
  // public String create(User item) {
  //     dao.save(item);
  //     form = false;
  //     return "admin/index-admin";
  // }

  // @RequestMapping("/users/update")
  //    public String update(User item,Model model) {
  //        dao.save(item);
  //        return "redirect:/admin/users/edit/" + item.getUsername();
  //    }


    //  @RequestMapping("/users/delete/{username}")
    //  public String delete(@PathVariable("username") String username) {
    //      dao.deleteById(username);
    //      return "redirect:/admin/users";
    //  }

    @RequestMapping("/users/reset")
    public String reset(Model model) {
       form = true;
       
         return "redirect:/admin/users";    
        }

}
