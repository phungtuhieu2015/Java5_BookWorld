package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Publisher;

public interface PublisherDAO extends JpaRepository<Publisher,Integer> {
    
}
