package com.poly.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        // if(book.getId() == null) {
            if( book.getId() == null){
                book = new Book();
            }
        // }
        model.addAttribute("form", form);
        model.addAttribute("isEdit", isEdit);
        Pageable pageable = PageRequest.of( p.orElse(0), 5);
        Page page = dao.findAll(pageable);
        List<Category> listCat = daoCat.findAll();
        List<Publisher> listPub = daoPub.findAll();
        model.addAttribute("listPub", listPub);
        model.addAttribute("listCat", listCat);
        model.addAttribute("book", book);
        model.addAttribute("page", page);
        return "admin/index-admin"; 
    }
    // String oldImg ;
    // @RequestMapping("/products/forerror")
    // public String products(Model model,@RequestParam("p") Optional<Integer> p) {
    //     File dir = new File(app.getRealPath("/img")) ;
	// 	if(!dir.exists()){
	// 		dir.mkdirs();
	// 	}
    //     model.addAttribute("pageName", "products products");
    //     if(p.isPresent()){  
    //         // check phân trang khi đang edit
    //         if(isEdit) {
    //             form = false;
    //         }
    //         //check phân trang sau khi reset form
    //         if(form){
    //             form = false;
    //             isEdit = false;
    //         }
    //     }
    //     // check mặc định tránh bị ghi đè
    //     if( (form == false && isEdit == false)){
    //         form = false;
    //         isEdit = false;
    //     } 
    //     // if(book.getId() == null) {
    //         if( book.getId() == null){
    //             book = new Book();
    //         }
    //     // }
    //     model.addAttribute("form", form);
    //     model.addAttribute("isEdit", isEdit);
    //     Pageable pageable = PageRequest.of( p.orElse(0), 5);
    //     Page page = dao.findAll(pageable);
    //     List<Category> listCat = daoCat.findAll();
    //     List<Publisher> listPub = daoPub.findAll();
    //     model.addAttribute("listPub", listPub);
    //     model.addAttribute("listCat", listCat);
    //     model.addAttribute("book", book);
    //     model.addAttribute("page", page);
    //     System.out.println(page.getContent());
    //     return "admin/index-admin"; 
    // }
   
    @RequestMapping("/products/create")
    public String create(@Valid Book book,BindingResult result,Model model,@RequestParam("p") Optional<Integer> p) {
        model.addAttribute("pageName", "products products");
       
        model.addAttribute("form", true); 
        model.addAttribute("isEdit", true);
        Pageable pageable = PageRequest.of( p.orElse(0), 5);
        Page page = dao.findAll(pageable);
        List<Category> listCat = daoCat.findAll();
        List<Publisher> listPub = daoPub.findAll();
        model.addAttribute("listPub", listPub);
        model.addAttribute("listCat", listCat);
        model.addAttribute("book", book);
        model.addAttribute("page", page);
        System.out.println(result.hasErrors());
        if (result.hasErrors()) {
            model.addAttribute("message", "lỗi");
        }
      
        return "admin/index-admin";  
    }

    @RequestMapping("/products/edit/{id}")
    public String edit(@PathVariable("id") String id) {
        this.book = dao.findById(id).get();
        oldImg = this.book.getImage();
        form = true;
        isEdit = true;
        return "redirect:/admin/products"; 
    }

    @RequestMapping("/products/reset")
    public String reset() {
        this.book = new Book();
        form = true;
        isEdit = false;
        return "redirect:/admin/products"; 
    }
    @RequestMapping("/products/update")
    public String update(Book book,@RequestParam("fileImage") MultipartFile fileImage) {
     
        if(fileImage.isEmpty()) {
                book.setImage(oldImg);
                dao.save(book);
        } else {
            String fileName = fileImage.getOriginalFilename();
            String uploadDirectory = "static/admin/img/";
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
        form = false;
        isEdit = false;
        return "redirect:/admin/products"; 
    }
    
}
