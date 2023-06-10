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

    @RequestMapping("/forgot-password")
    public String doForgotPassword(User account,Model model){

        return "forgot-password";
    }
    @RequestMapping("/forgot-password/save")
    public String postForgotPassword( User account ){

        System.out.println(account.getEmail());

        User user1 = dao.findByEmail(account.getEmail());


       // System.out.println("check12312312312: "+user1.getEmail());

        if(user1 != null){
                user1.setPassword(generateRandomString());
                dao.save(user1);
                try {
                    mailer.send(user1.getEmail(), "RESET PASSWORD",
                     "<div style='font-family: Arial, sans-serif; line-height: 1.6; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd;'>"
                       +" <h2 style='margin-top: 0;'>Đặt lại mật khẩu</h2>"
                       +" <p>Xin chào ,<strong>"+user1.getUsername()+"</strong></p>"
                       +" <p>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu của bạn. Để tiếp tục quá trình đặt lại mật khẩu, vui lòng làm"
                       +"     theo hướng dẫn dưới đây:</p>"
                       +" <ol>"
                       +"     <li>Sao chép mật khẩu mới được reset ngẫu nhiên sau đây:</li>"
                       +"     <p"
                       +"         style='background-color: #f5f5f5; padding: 10px; border: 1px solid #ddd; font-family: Courier New, Courier, monospace; font-size: 14px; color: red; font-weight: 900; text-align: center;'>"
                       +"         "+user1.getPassword()+"</p>"
                       +"     <li>Tiếp theo, bấm vào <a href='http://localhost:8081/account/login'"
                       +"             style='text-decoration: none; background-color: #4CAF50; color: white; padding: 8px 20px; border-radius: 5px; font-weight: bold;'>đăng"
                       +"             nhập</a></li>"
                       +" </ol>"
                       +" <p>Để đảm bảo an toàn cho tài khoản của bạn, hãy thay đổi mật khẩu ngay sau khi đăng nhập.</p>"
                       +" <p>Nếu bạn không khởi tạo yêu cầu đặt lại mật khẩu này, xin vui lòng bỏ qua email này. Tài khoản của bạn vẫn an"
                       +"     toàn.</p>"
                       +" <div style='margin-top: 30px; padding-top: 20px; border-top: 1px solid #ddd; font-size: 14px; color: #888;'>"
                       +"     <p>Trân trọng,</p>"
                       +"     <p>Success 202 - BOOKWORLD</p>"
                       +"     <p>&copy; 2023 Tất cả các quyền được bảo lưu.</p>"
                       +" </div>"
                   +" </div>","");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
        }



        return"forgot-password";
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
