package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.model.Cart;

@Repository
public interface CartDAO extends JpaRepository<Cart,Long> {
    
}
