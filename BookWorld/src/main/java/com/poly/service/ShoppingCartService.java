//Bài 4
package com.poly.service;

import java.util.Collection;
import com.poly.model.Cart;

public interface ShoppingCartService {
   
    Cart add(String id);

    void remove(Long id);

    Cart update(Long id, Integer qty);

    void clear();

 
    Collection<Cart> getOrderDetails();

    int getCount();


    double getAmount();
}
