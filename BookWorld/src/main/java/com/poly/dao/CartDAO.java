package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.model.Cart;
import com.poly.model.User;

@Repository
public interface CartDAO extends JpaRepository<Cart,String> {
    List<Cart> findByUserAndStatus(User user,Integer status); 
}
