package com.poly.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.dao.BookDAO;
import com.poly.dao.CategoryDAO;
import com.poly.dao.PublisherDAO;
import com.poly.model.Book;
import com.poly.model.Category;
import com.poly.model.Publisher;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class ProductsController {
    @Autowired
    BookDAO dao;
    @Autowired
    CategoryDAO daoCat;

    @Autowired
    PublisherDAO daoPub;

    @Autowired
    ServletContext app;

    Book book =  new Book();
    boolean form = false;
    boolean isEdit = false;

    String uploadDirectory = "static/assets/img/";
    String oldImg ;
    @RequestMapping("/products")
    public String products(Model model,@RequestParam("p") Optional<Integer> p) {

        model.addAttribute("pageName", "products products");
        if(p.isPresent()){  
            // check phân trang khi đang edit
            if(isEdit) {
                form = false;
            }
            //check phân trang sau khi reset form
            if(form){
                form = false;
                isEdit = false;
            }
        }
        // check mặc định tránh bị ghi đè
        if( (form == false && isEdit == false)){
            form = false;
            isEdit = false;
        } 
            if( this.book.getId() == null){
                this.book = new Book();
            }
        model.addAttribute("form", this.form);
        model.addAttribute("isEdit", this.isEdit);
        Pageable pageable = PageRequest.of( p.orElse(0), 5);
        Page page = dao.findAll(pageable);
        List<Category> listCat = daoCat.findAll();
        List<Publisher> listPub = daoPub.findAll();
        model.addAttribute("listPub", listPub);
        model.addAttribute("listCat", listCat);
        model.addAttribute("book",this.book);
        model.addAttribute("page", page);
        return "admin/index-admin"; 
    }
 
    @RequestMapping("/products/form-errors")
    public String formErrors(@Valid Book book,BindingResult result,Model model) {
        model.addAttribute("pageName", "products products");
        model.addAttribute("form", true); 
        model.addAttribute("isEdit", false);
        Pageable pageable = PageRequest.of( 0, 5);
        Page page = dao.findAll(pageable);
        List<Category> listCat = daoCat.findAll();
        List<Publisher> listPub = daoPub.findAll();
        model.addAttribute("listPub", listPub);
        model.addAttribute("listCat", listCat);
        model.addAttribute("book", book);
        model.addAttribute("page", page);
        return "admin/index-admin";  
    }
    @RequestMapping("/products/create")
    public String create(@Valid Book book,BindingResult result,Model model,@RequestParam("fileImage") MultipartFile fileImage) {
        boolean isError  = false;
        if(dao.existsById(book.getId())){
            isError = true;    
            model.addAttribute("isExist", "(*) Mã sách đã tồn tại");
        }
        if(fileImage.isEmpty() ){
             isError = true;
            model.addAttribute("errorImg", "(*) Vui lòng chọn ảnh");
        }
        if(isError || result.hasErrors()) {
             return "forward:/admin/products/form-errors";
        }
         if(!fileImage.isEmpty()) {        
            String fileName = fileImage.getOriginalFilename();
            
            try {
                Path path = Paths.get(new ClassPathResource(uploadDirectory).getURI());
                fileImage.transferTo(path.resolve(fileName).toFile());
                book.setImage(fileName);
                dao.save(book);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        } 
        return "redirect:/admin/products";  
    }

    @RequestMapping("/products/edit/{id}")
    public String edit(@PathVariable("id") String id) {
        this.book = dao.findById(id).get();
        this.oldImg = this.book.getImage();
        this.form = true;
        this.isEdit = true;
        return "redirect:/admin/products"; 
    }

    @RequestMapping("/products/reset")
    public String reset() {
        this.book = new Book();
        this.form = true;
        this.isEdit = false;
        return "redirect:/admin/products"; 
    }
    @RequestMapping("/products/update")
    public String update(@Valid Book book,BindingResult result,Model model,@RequestParam("fileImage") MultipartFile fileImage) {
        if (result.hasErrors()) {
            return "forward:/admin/products/form-errors";
        }
        book.setCreatedDate(new Date());
        if(fileImage.isEmpty()) {
                book.setImage(oldImg);
                dao.save(book);
        } else {
            String fileName = fileImage.getOriginalFilename();
            try {
                Path path = Paths.get(new ClassPathResource(uploadDirectory).getURI());
                fileImage.transferTo(path.resolve(fileName).toFile());
                System.out.println(path.resolve(fileName).toFile().getAbsolutePath());
                book.setImage(fileName);
                dao.save(book);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        } 
        this.book = book;
        return "redirect:/admin/products"; 
    }

    @RequestMapping("/products/delete/{id}")
    public String delete(Model model , Book book,@PathVariable("id") String id) {
        model.addAttribute("pageName", "products products");
        book = dao.findById(id).get();
        dao.delete(book);
        book = new Book();
        this.form = false;
        this.isEdit = false;
        return "redirect:/admin/products"; 
    }
    
}
