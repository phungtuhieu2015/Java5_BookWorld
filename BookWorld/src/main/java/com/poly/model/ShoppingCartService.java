//Bài 4
package com.poly.model;

import java.util.Collection;

public interface ShoppingCartService {
   
    Product add(String id);


    boolean remove(String id);

 
    Product update(String id, int qty);

    boolean clear();

 
    Collection<Product> getProducts();


    int getCount();


    double getAmount();
}
