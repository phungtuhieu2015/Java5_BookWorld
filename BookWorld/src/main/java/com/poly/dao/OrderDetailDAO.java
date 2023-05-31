package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.OrderDetail;

public interface OrderDetailDAO extends JpaRepository <OrderDetail,Integer> {
    
}
