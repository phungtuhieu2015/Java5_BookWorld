package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Author;

public interface AuthorDAO extends JpaRepository <Author,Long> {
    List<Author> findByIdNotIn(List<Long> AuthorsId);
}
