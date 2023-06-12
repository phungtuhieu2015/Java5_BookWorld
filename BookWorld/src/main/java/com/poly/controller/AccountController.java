package com.poly.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.dao.UserDAO;
import com.poly.model.User;
import com.poly.service.MailerServiceImpl;
import com.poly.service.SessionService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserDAO dao;

    String oldImg;
    @Autowired
    MailerServiceImpl mailer;

    User user = new User();

    Boolean isSuccess = false;

    @Autowired
    SessionService session;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        // System.out.println("sssssssssssssssssssssssssssssssssssssssss");
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid User user, BindingResult bindingResult,
            @RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        if (isSuccess.equals("login")) {

        }
           User s = session.get("user");
        // System.out.println(user.getUsername()+"sssssssssssss");
        if (s == null)

        {
            model.addAttribute("user", user);
            model.addAttribute("checkLG", false);
        } else {
            model.addAttribute("user", user);

            model.addAttribute("checkLG", true);
        }
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
        user.setActivated(true);
        dao.save(user);

        return "login";
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

    @RequestMapping("/forgot-password")
    public String doForgotPassword(User account, Model model) {

        return "forgot-password";
    }

    @RequestMapping("/forgot-password/save")
    public String postForgotPassword(User account) {

        System.out.println(account.getEmail());

        User user1 = dao.findByEmail(account.getEmail());

        // System.out.println("check12312312312: "+user1.getEmail());

        if (user1 != null) {
            user1.setPassword(generateRandomString());
            dao.save(user1);
            try {
                mailer.send(user1.getEmail(), "RESET PASSWORD",
                        "<div style='font-family: Arial, sans-serif; line-height: 1.6; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd;'>"
                                + " <h2 style='margin-top: 0;'>Đặt lại mật khẩu</h2>"
                                + " <p>Xin chào ,<strong>" + user1.getUsername() + "</strong></p>"
                                + " <p>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu của bạn. Để tiếp tục quá trình đặt lại mật khẩu, vui lòng làm"
                                + "     theo hướng dẫn dưới đây:</p>"
                                + " <ol>"
                                + "     <li>Sao chép mật khẩu mới được reset ngẫu nhiên sau đây:</li>"
                                + "     <p"
                                + "         style='background-color: #f5f5f5; padding: 10px; border: 1px solid #ddd; font-family: Courier New, Courier, monospace; font-size: 14px; color: red; font-weight: 900; text-align: center;'>"
                                + "         " + user1.getPassword() + "</p>"
                                + "     <li>Tiếp theo, bấm vào <a href='http://localhost:8081/account/login'"
                                + "             style='text-decoration: none; background-color: #4CAF50; color: white; padding: 8px 20px; border-radius: 5px; font-weight: bold;'>đăng"
                                + "             nhập</a></li>"
                                + " </ol>"
                                + " <p>Để đảm bảo an toàn cho tài khoản của bạn, hãy thay đổi mật khẩu ngay sau khi đăng nhập.</p>"
                                + " <p>Nếu bạn không khởi tạo yêu cầu đặt lại mật khẩu này, xin vui lòng bỏ qua email này. Tài khoản của bạn vẫn an"
                                + "     toàn.</p>"
                                + " <div style='margin-top: 30px; padding-top: 20px; border-top: 1px solid #ddd; font-size: 14px; color: #888;'>"
                                + "     <p>Trân trọng,</p>"
                                + "     <p>Success 202 - BOOKWORLD</p>"
                                + "     <p>&copy; 2023 Tất cả các quyền được bảo lưu.</p>"
                                + " </div>"
                                + " </div>",
                        "");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return "forgot-password";
    }

    @GetMapping("/change-password")
    public String showChangePasswordForm(@ModelAttribute("user") User account, BindingResult result, Model model) {
        if (result.hasErrors()) {

        }
        user = session.get("user");
        // System.out.println(user.getUsername()+"sssssssssssss");
        if (user == null)

        {
            model.addAttribute("user", user);
            model.addAttribute("checkLG", false);
        } else {
            model.addAttribute("user", user);

            model.addAttribute("checkLG", true);
        }
        model.addAttribute("user", user);
        return "change-password";

    }

    @PostMapping("/change-password")
public String processChangePasswordForm(@ModelAttribute("user") User account, @RequestParam("pw") String pw,
        @RequestParam("confirmPassword") String confirmPassword, BindingResult result, Model model) {
    if (result.hasErrors()) {
        // Xử lý lỗi nếu có
    }
    

    User user = session.get("user");
    if (user.getPassword().equals(account.getPassword())) {
        if (!pw.equals(confirmPassword)) {
            // Xử lý khi mật khẩu mới và xác nhận mật khẩu không trùng nhau
            model.addAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không trùng nhau");
            return "/change-password";
        }
        
        user.setPassword(pw);
        dao.save(user); 
        return "redirect:/account/login";
    }

    return "/change-password";
}


    // @PostMapping("/change-password")
    // public String processChangePasswordForm(@ModelAttribute("user") User account,@RequestParam("pw") String pw, BindingResult result,
    //         Model model) {
    //     if (result.hasErrors()) {
    //     }
    //     User user = new User();
    //     user = session.get("user");
    //     if (user.getPassword().equals(account.getPassword())) {
         
    //         user.setPassword(pw);
    //         dao.save(user); 
    //         return "redirect:/account/login";
            
    //     }
         
    //     return "/change-password";
    // }

    @GetMapping("/profile")
    public String doMyProfile(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {

        }
        User users = session.get("user");
        // System.out.println(user.getUsername()+"sssssssssssss");
        if (users == null)

        {
            model.addAttribute("user", user);
            model.addAttribute("checkLG", false);
        } else {
            model.addAttribute("user", user);

            model.addAttribute("checkLG", true);
        }
        user = session.get("user");
        model.addAttribute("user", user);
        return "edit-profile";
    }

    @PostMapping("/profile")
    public String doMyProfilesave(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
            @RequestParam("fileImage") MultipartFile fileImage) {
        if (result.hasErrors()) {

        }
      
        if (fileImage.isEmpty()) {
            user.setImage(oldImg);
            dao.save(user);
        } else {
            String fileName = fileImage.getOriginalFilename();
            String uploadDirectory = "static/assets/img/";
            try {
                Path path = Paths.get(new ClassPathResource(uploadDirectory).getURI());
                fileImage.transferTo(path.resolve(fileName).toFile());
                System.out.println(path.resolve(fileName).toFile().getAbsolutePath());
                user.setImage(fileName);
                isSuccess = true;
                dao.save(user);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }

        user.setActivated(false);
        

        
           User users = session.get("user");
        if (users == null)
        {
            model.addAttribute("user", user);
            model.addAttribute("checkLG", false);
        } else {
            model.addAttribute("user", user);

            model.addAttribute("checkLG", true);
        }
        return "edit-profile";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Xóa phiên
        }
        return "redirect:/index";
    }

}
