package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.AuthorBook;

public interface AuthorBookDAO extends JpaRepository<AuthorBook,Long> {
    
}
