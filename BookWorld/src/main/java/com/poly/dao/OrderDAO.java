package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Order;

public interface OrderDAO extends JpaRepository <Order,String> {
    
    Page findByStatus(Integer status, Pageable pageable);
}
