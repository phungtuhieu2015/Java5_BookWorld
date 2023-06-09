package com.poly.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.dao.BookDAO;
import com.poly.model.Book;
import com.poly.model.MailInfo;
import com.poly.service.MailerServiceImpl;

import jakarta.mail.MessagingException;


@Controller

public class IndexController {

    @Autowired
    BookDAO dao;
    
   
    @Autowired
    MailerServiceImpl mailer;

    Book book = new Book();
    @RequestMapping("/index")
    public String index(Model model, @RequestParam("p") Optional<Integer> p){

        Pageable pageable = PageRequest.of(p.orElse(0), 8);
        Page<Book> page = dao.findAll(pageable);   
        model.addAttribute("page", page);

        return "index";
        
    }
    

    @ResponseBody
    @RequestMapping("/share/{bookId}")
    public String edit(@PathVariable("bookId") String bookId,Model model,@RequestParam("emailShare") String to) {

        System.out.println(bookId);
        book = dao.findById(bookId).get();
        System.out.println(book.getTitle());
      
        try {
            MailInfo mail = new MailInfo();
            mail.setBody("<table style='border-collapse: collapse; width: 80%; margin: 0 auto; border: 1px solid #000;'>"
            +" <thead>"
                +"<tr> "
                +"<th style=' border: 1px solid #000; padding: 8px;'>Id sách</th>"
                +"<th style='border: 1px solid #000; padding: 8px;'>Tên sách</th>"
                +"<th style='border: 1px solid #000; padding: 8px;'>Giá</th>"
                +"<th style='border: 1px solid #000; padding: 8px;'>Mô tả sách</th>"
                +"</tr>"
                +"</thead>"
                +"<tbody>"
                +"    <tr>"
                +"        <td style='border: 1px solid #000; padding: 8px;'>"+book.getId()+"</td>"
                +"        <td style='border: 1px solid #000; padding: 8px;'>"+book.getTitle()+"</td>"
                +"        <td style='border: 1px solid #000; padding: 8px;'>"+book.getPrice()+"</td>"
                +"        <td style='border: 1px solid #000; padding: 8px;'>"+book.getDescription()+"</td>"
                +"    </tr>"
                +"    <!-- Các dòng dữ liệu khác -->"
                +" </tbody>"
                +" </table>"
                +" <div style='width: 15%;margin: 0 auto; margin-top: 20px;'>"
                +" <a href='http://localhost:8081/chitiet/"+book.getId()+ "' "
                +"    style='display: inline-block; padding: 10px 20px; background-color: #FF0000; color: #FFFFFF; text-decoration: none; border-radius: 4px; font-weight: bold;'>Chi"
                +"    tiết sản phẩm</a>"
                +"</div>");

            System.out.println(mail.getBody());
            mailer.send(to, "Gửi Email mè", mail.getBody() );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
       
        return "Mail của bạn sẽ được gửi đi trong giây lát";
    }
}
