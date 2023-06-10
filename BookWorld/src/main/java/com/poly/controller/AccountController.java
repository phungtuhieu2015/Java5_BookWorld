package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.UserDAO;
import com.poly.model.User;
import com.poly.service.SessionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserDAO dao;

    Boolean isSuccess = false;
    @Autowired
    SessionService session;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid User user, BindingResult bindingResult,
            @RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        if (isSuccess.equals("login")) {

        }
        // if (bindingResult.hasErrors()) {
        // // System.out.println(bindingResult.getFieldValue(password));
        // return "login";
        // }
        List<User> users = dao.findAll();
        for (User user2 : users) {
            if (username.equalsIgnoreCase(user2.getUsername())) {
                if (password.equalsIgnoreCase(user2.getPassword())) {
                    if (user2.getAdmin()) {
                        session.set("user", user2);
                        isSuccess = true;
                        return "redirect:/admin/index";
                    } else {
                        session.set("user", user2);
                        isSuccess = true;
                        return "redirect:/index";
                    }
                }
            }
        }
        model.addAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!");

        // Khi không tìm thấy người dùng hoặc mật khẩu không khớp
        // bindingResult.rejectValue("username", "error.user", "Tài Khoản là bắt buộc");
        // bindingResult.rejectValue("password", "error.user", "Mật Khẩu là bắt buộc");
        return "login";
    }

    @GetMapping("/sign-up")
    public String doSignUp(@ModelAttribute("user") User user, BindingResult result, Model model) {

        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String douSignUp(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {

            return "sign-up";
        }

        List<User> users = dao.findAll();
        for (User b : users) {
            if (b.getUsername().equalsIgnoreCase(user.getUsername())) {
                String successMessage = "ID đã tồn tại !";
                model.addAttribute("failed", successMessage);
                return "sign-up";
            }

        }
        for (User b : users) {
            if (b.getEmail().equalsIgnoreCase(user.getEmail())) {
                String successMessage = "gmail đã tồn tại !";
                model.addAttribute("failed", successMessage);
                return "sign-up";
            }
        }
        user.setActivated(true);
        dao.save(user);

        return "login";
    }

}

// @RequestMapping("/forgot-password")
// public String doForgotPassword( @ModelAttribute("user") User account,Model
// model){

// return "forgot-password";
// }
// @RequestMapping("/forgot-password/save")
// public String postForgotPassword(@Valid @ModelAttribute("user") User account
// ,BindingResult result){

// if(result.hasErrors()){
// return"forgot-password";
// }
// return"forgot-password";
// }

// @RequestMapping("/change-password")
// public String doChangePassword(@ModelAttribute("user") User account,Model
// model){

// return "change-password";
// }
// @RequestMapping("/change-password/save")
// public String postChangePassword(@Valid @ModelAttribute("user") User account
// ,BindingResult result,Model model){

// if(result.hasErrors()){

// String newPass = paramService.getString("newPassword", "");
// String confirmPass = paramService.getString("confirmPassword", "");

// if(newPass.trim().isBlank()){
// model.addAttribute("mesNP", false);
// }
// if(confirmPass.trim().isBlank()){
// model.addAttribute("mesCP", false);
// }else{
// if(!newPass.equals(confirmPass)){
// model.addAttribute("message", false);
// }
// }

// return"change-password";
// }

// return"change-password";
// }

// }
