package com.poly.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.dao.UserDAO;
import com.poly.model.User;
import com.poly.service.MailerServiceImpl;
import com.poly.service.ParamService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    ParamService paramService;

    @Autowired
    UserDAO dao;

    @Autowired
    MailerServiceImpl mailer;

    User user = new User();

    @ResponseBody
    @RequestMapping("/dinhdaubuoirerach")
    public List<User> dinhga(){
        return dao.findAll();
    }

    
    @RequestMapping("/login")
    public String doLogin(@ModelAttribute("user") User account,Model model){

        return "login";
    }

    // @RequestMapping("/login/save")
    // public String postLogin(@Valid @ModelAttribute("user") User account ,BindingResult result,Model model,RedirectAttributes params){
    //     System.out.println(account.getId());
    //     System.out.println(account.getPassword());
    //     if(result.hasErrors()){
    //         if(account.getId().equalsIgnoreCase("admin") && account.getPassword().equalsIgnoreCase("admin")){
    //             return "redirect:/admin/index";
    //         }else{
    //           if(!account.getId().isBlank() && !account.getPassword().isBlank()){

    //             model.addAttribute("checkLG", true);
    //             return"/index";
    //           }
    //         }
    //     }
    //     return"login";
    // }


    @RequestMapping("/forgot-password")
    public String doForgotPassword(User account,Model model){

        return "forgot-password";
    }
    @RequestMapping("/forgot-password/save")
    public String postForgotPassword( User account ){
        for (User u : dao.findAll()) {
            if(u.getEmail().equalsIgnoreCase(account.getEmail())){
                user = dao.findById(u.getUsername()).get();
                System.out.println("resetttttttttttttttttttttttttt: "+generateRandomString());
                user.setPassword(generateRandomString());
                dao.save(user);
                try {
                    mailer.send(user.getEmail(), "RESET PASSWORD", "MẬT KHẨU CỦA BẠN LÀ: "+ user.getPassword());



                } catch (MessagingException e) {

                    e.printStackTrace();
                }

                break;
            }else{
                System.out.println("khôngkhôngkhôngkhôngkhôngkhôngkhông đã tìm thấy");
            }
        }
        

        return"forgot-password";
    }
  
    public String generateRandomString() {
        int length = 20;
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }



    @RequestMapping("/change-password")
    public String doChangePassword(@ModelAttribute("user") User account,Model model){

        return "change-password";
    } 
    @RequestMapping("/change-password/save")
    public String postChangePassword(@Valid @ModelAttribute("user") User account ,BindingResult result,Model model){

        if(result.hasErrors()){
            
            String newPass = paramService.getString("newPassword", "");
            String confirmPass = paramService.getString("confirmPassword", "");

            if(newPass.trim().isBlank()){
                 model.addAttribute("mesNP", false);
            }
            if(confirmPass.trim().isBlank()){
                model.addAttribute("mesCP", false);
            }else{
                if(!newPass.equals(confirmPass)){
                    model.addAttribute("message", false);
                }
            }
          

            
            return"change-password";
        }



        return"change-password";
    }





    @RequestMapping("/sign-up")
    public String doSignUp( @ModelAttribute("user") User account,Model model){

        return "sign-up";
    }
    @RequestMapping("/sign-up/save")
    public String doSignUp(@Valid @ModelAttribute("user") User account ,BindingResult result){

        if(result.hasErrors()){
            return"sign-up";
        }
        return"sign-up";
    }
    @RequestMapping("/profile")
    public String doMyProfile(Model model){

        return"edit-profile";
    }

}
