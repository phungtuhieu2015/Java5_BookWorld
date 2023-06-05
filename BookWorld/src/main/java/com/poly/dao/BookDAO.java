package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Book;

public interface BookDAO extends JpaRepository<Book,String> {
    
}
