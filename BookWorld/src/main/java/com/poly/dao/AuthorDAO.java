package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Author;

public interface AuthorDAO extends JpaRepository <Author,Integer> {
    
}
