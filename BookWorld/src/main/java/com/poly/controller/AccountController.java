package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.model.user;
import com.poly.utils.ParamService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    ParamService paramService;

    
    @RequestMapping("/login")
    public String doLogin(@ModelAttribute("user") user account,Model model){

        return "login";
    }

    @RequestMapping("/login/save")
    public String postLogin(@Valid @ModelAttribute("user") user account ,BindingResult result){

        if(result.hasErrors()){
            return"login";
        }
        return"login";
    }


    @RequestMapping("/forgot-password")
    public String doForgotPassword( @ModelAttribute("user") user account,Model model){

        return "forgot-password";
    }
    @RequestMapping("/forgot-password/save")
    public String postForgotPassword(@Valid @ModelAttribute("user") user account ,BindingResult result){

        if(result.hasErrors()){


            return"forgot-password";

        }
        return"forgot-password";
    }



    @RequestMapping("/change-password")
    public String doChangePassword(@ModelAttribute("user") user account,Model model){

        return "change-password";
    } 
    @RequestMapping("/change-password/save")
    public String postChangePassword(@Valid @ModelAttribute("user") user account ,BindingResult result,Model model){

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
    public String doSignUp( @ModelAttribute("user") user account,Model model){

        return "sign-up";
    }
    @RequestMapping("/sign-up/save")
    public String postSignUp(@Valid @ModelAttribute("user") user account ,BindingResult result){

        if(result.hasErrors()){
            return"sign-up";
        }
        return"sign-up";
    }

}
