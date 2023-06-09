package com.poly.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.dao.UserDAO;
import com.poly.model.Book;
import com.poly.model.User;
import com.poly.service.SessionService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class UsersController {

  @Autowired
  UserDAO dao;
  @Autowired
  SessionService session;
  @Autowired
  ServletContext app;
  Boolean form = false;
  Boolean isEdit = false;

  User user = new User();

  @RequestMapping("/users")
  public String Users(Model model, @RequestParam("p") Optional<Integer> p) {
    model.addAttribute("pageName", "users user");
    if (p.isPresent()) {
      // check khi bấm phân trang khi đang sửa
      if (isEdit == true) {
        form = false;
      }
      // check khi bấm phân trang sau khi bấm reset
      if (form == true) {
        form = false;
        isEdit = false;
      }
    }
    // check mặc định tránh bị ghi đè
    if ((form == false && isEdit == false)) {
      form = false;
      isEdit = false;
    }
    if (user.getUsername() == null) {
      user = new User();
    }
    model.addAttribute("form", true);
    model.addAttribute("isEdit", isEdit);
    model.addAttribute("user", user);

    Pageable pageable = PageRequest.of(p.orElse(0), 5);
    Page<User> page = dao.findAll(pageable);
    model.addAttribute("page", page);

    return "admin/index-admin";
  }

  @RequestMapping("/users/edit/{username}")
  public String edit(@PathVariable("username") String username) {
    form = true;
    isEdit = true;
    user = dao.findById(username).get();
    return "redirect:/admin/users";
  }

  @RequestMapping("/users/update")
  public String update(@Valid User user, BindingResult result, Model model) {
    if (result.hasErrors()) {

      Optional<Integer> p = Optional.of(0);
      model.addAttribute("pageName", "users user");
      model.addAttribute("form", true);
      model.addAttribute("isEdit", true);
      Pageable pageable = PageRequest.of(p.orElse(0), 5);
      Page<User> page = dao.findAll(pageable);
      model.addAttribute("page", page);
      return "admin/index-admin";
    }
    dao.save(user);
    return "redirect:/admin/users/edit/" + user.getUsername();

  }


  @RequestMapping("/users/reset")
  public String reset(Model model) {
    this.user = new User();
    form = true;
    isEdit = false;
    return "redirect:/admin/users";
  }

}
