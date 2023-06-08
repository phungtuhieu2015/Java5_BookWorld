package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.dao.UserDAO;
import com.poly.model.User;
import com.poly.service.ParamService;
import com.poly.service.SessionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserDAO dao;

    @Autowired
    SessionService session;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("message", ""); // Thêm dòng này để tránh lỗi khi trang được render lần đầu
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            User user = dao.getOne(username);
            if (!user.getPassword().equals(password)) {
                model.addAttribute("message", "Invalid password!");
            } else {
                String uri = session.get("security-uri");
    
                if (uri != null) {
                    return "redirect:" + uri;
                }
    
                if (user.getAdmin()) {
                    // Đăng nhập với vai trò admin
                    // Thực hiện các thao tác cho admin
                    
                    model.addAttribute("message", "Login Success! (Admin)");
                    return "redirect:/admin/index";
                } else {
                    // Đăng nhập với vai trò user
                    // Thực hiện các thao tác cho user
                    model.addAttribute("message", "Login Success! (User)");
                    return "redirect:/index";
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", "Invalid username!");
        }
        return "login";
    }
    
}
    //  @RequestMapping("/login/save")
    //  public String postLogin(@Valid @ModelAttribute("user") User account ,BindingResult result,Model model,RedirectAttributes params){
    //      System.out.println(account.getId());
    //      System.out.println(account.getPassword());
    //      if(result.hasErrors()){
    //          if(account.getId().equalsIgnoreCase("admin") && account.getPassword().equalsIgnoreCase("admin")){
    //              return "redirect:/admin/index";
    //          }else{
    //            if(!account.getId().isBlank() && !account.getPassword().isBlank()){

    //              model.addAttribute("checkLG", true);
    //              return"/index";
    //            }
    //          }
    //      }
    //      return"login";
    //  }


//     @RequestMapping("/forgot-password")
//     public String doForgotPassword( @ModelAttribute("user") User account,Model model){

//         return "forgot-password";
//     }
//     @RequestMapping("/forgot-password/save")
//     public String postForgotPassword(@Valid @ModelAttribute("user") User account ,BindingResult result){

//         if(result.hasErrors()){
//             return"forgot-password";
//         }
//         return"forgot-password";
//     }



//     @RequestMapping("/change-password")
//     public String doChangePassword(@ModelAttribute("user") User account,Model model){

//         return "change-password";
//     } 
//     @RequestMapping("/change-password/save")
//     public String postChangePassword(@Valid @ModelAttribute("user") User account ,BindingResult result,Model model){

//         if(result.hasErrors()){
            
//             String newPass = paramService.getString("newPassword", "");
//             String confirmPass = paramService.getString("confirmPassword", "");

//             if(newPass.trim().isBlank()){
//                  model.addAttribute("mesNP", false);
//             }
//             if(confirmPass.trim().isBlank()){
//                 model.addAttribute("mesCP", false);
//             }else{
//                 if(!newPass.equals(confirmPass)){
//                     model.addAttribute("message", false);
//                 }
//             }
          

            
//             return"change-password";
//         }



//         return"change-password";
//     }





//     @RequestMapping("/sign-up")
//     public String doSignUp( @ModelAttribute("user") User account,Model model){

//         return "sign-up";
//     }
//     @RequestMapping("/sign-up/save")
//     public String doSignUp(@Valid @ModelAttribute("user") User account ,BindingResult result){

//         if(result.hasErrors()){
//             return"sign-up";
//         }
//         return"sign-up";
//     }
//     @RequestMapping("/profile")
//     public String doMyProfile(Model model){

//         return"edit-profile";
//     }

// }
