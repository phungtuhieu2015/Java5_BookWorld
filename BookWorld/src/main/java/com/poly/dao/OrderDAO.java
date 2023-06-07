package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Order;

public interface OrderDAO extends JpaRepository <Order,String> {
    
}
