package com.poly.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.CategoryDAO;
import com.poly.model.Category;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class CategoriesController {

    @Autowired
    CategoryDAO dao;

    Boolean form = false;
    Boolean isEdit = false;

    Category category = new Category();

    String isSuccess = "";

    @RequestMapping("/categories")
    public String categories(Model model, @RequestParam("p") Optional<Integer> p ) {
        model.addAttribute("pageName", "categories products");
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
        if(category.getId() == null) {
            category = new Category();
        }

        if(isSuccess.equals("Create")){
            model.addAttribute("message", "Thêm danh mục thành công");
        }else if(isSuccess.equals("Update")){
            model.addAttribute("message", "Cập nhật danh mục thành công");
        }else if(isSuccess.equals("Delete")){
            model.addAttribute("message", "Xóa danh mục thành công");
        }

        isSuccess = "";
        model.addAttribute("form", form);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("category", category);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = dao.findAll(pageable);     
        model.addAttribute("page", page);

        return "admin/index-admin";
    }

    @RequestMapping("/categories/edit/{id}")
    public String edit(@PathVariable("id") Long id) {
        form = true;
        isEdit = true;
        category = dao.findById(id).get();
        return "redirect:/admin/categories";
    }

    public String formError(Category category,BindingResult result,Model model, Boolean isEdit, Boolean CheckCategoryName){
             model.addAttribute("pageName", "categories products");
            model.addAttribute("form", true);
            model.addAttribute("isEdit", isEdit);
            Optional<Integer> p = Optional.of(0);
            Pageable pageable = PageRequest.of(p.orElse(0), 5);
            Page<Category> page = dao.findAll(pageable);     
            model.addAttribute("page", page);
            model.addAttribute("CheckCategoryName", CheckCategoryName);
            return "admin/index-admin";
    }

    @RequestMapping("/categories/create")
    public String create(@Valid Category category,BindingResult result,Model model) {   

        if(result.hasErrors()){     
            return this.formError(category, result, model,false,false);
        }
      
        Category cCheckName = dao.findByCategoryName(category.getCategoryName());
         if(cCheckName != null){
             return this.formError(category, result, model,false,true);
         }else{
            dao.save(category);
            this.category = new Category();
            isSuccess = "Create";
         }
        
       
        return "redirect:/admin/categories";
    }

    @RequestMapping("/categories/update")
    public String update(@Valid Category category, BindingResult result, Model model) {
          if(result.hasErrors()){     
            return this.formError(category, result, model,true,false);
        }

        Category cCheckName = dao.findByCategoryName(category.getCategoryName());
         if(cCheckName != null){
             return this.formError(category, result, model,true,true);
         }else{
            dao.save(category);
            isSuccess = "Update";
            this.category = category;
         }
        return "redirect:/admin/categories" ;
    }

    @RequestMapping("/categories/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        this.category = new Category();
        form = false;
        isEdit = false;
        dao.deleteById(id);
        isSuccess = "Delete";
        return "redirect:/admin/categories";
    }

    @RequestMapping("/categories/reset")
    public String reset(Model model) {
        this.category = new Category();
        form = true;
        isEdit = false;
        return "redirect:/admin/categories";
    }

}
