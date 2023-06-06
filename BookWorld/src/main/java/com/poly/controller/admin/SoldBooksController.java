package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class SoldBooksController {
    @RequestMapping("/sold-books")
    public String soldeBooks(Model model){
        model.addAttribute("pageName", "sold-books statistical");
        return "admin/index-admin";
    }


//     @Autowired
//   UserDAO dao;
//   @Autowired
//   SessionService session; 
//   Boolean form = false;
//   Boolean isEdit = false;
  


//   @RequestMapping("/sold-books")
//   public String Users(Model model) {
//     model.addAttribute("pageName", "sold-books statistical");
//       model.addAttribute("form", form);
//       User item = new User();
//       model.addAttribute("item", item);
//       List<User> items = dao.findAll();
//       model.addAttribute("items", items);
      
//       model.addAttribute("isEdit", isEdit);
      
//       return "admin/index-admin";
//   }
}
