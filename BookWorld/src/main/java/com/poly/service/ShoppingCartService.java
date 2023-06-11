//Bài 4
package com.poly.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.model.Book;
import com.poly.model.OrderDetail;

public interface ShoppingCartService {
   
    Book add(String id);

    boolean remove(String id);

    Book update(String id, int qty);

    boolean clear();

 
    Collection<OrderDetail> getOrderDetails();

    int getCount();


    double getAmount();
}
