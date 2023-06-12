package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.AuthorBook;
import com.poly.model.Book;

public interface AuthorBookDAO extends JpaRepository<AuthorBook,Long> {


   
   List<AuthorBook> findByBook(Book book);
    
}
