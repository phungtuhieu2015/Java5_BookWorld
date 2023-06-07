// Bài 4
package com.poly.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService  {
    Map<String, Product> map = new HashMap<>();
    @Override
    public Product add(String id) {
        Product product = DB.products.get(id);
        map.put(id, product);
        return product;
    }

    @Override
    public boolean remove(String id) {
        map.remove(id) ;
        return map.get(id) == null?true: false;
    }

    @Override
    public Product update(String id, int qty) {
        map.get(id).setSoLuong(qty);
        return map.get(id);
    }

    @Override
    public boolean clear() {
        map.clear();
        return map.isEmpty();
    }

    @Override
    public Collection<Product> getProducts() {
        return map.values();
    }

    @Override
    public int getCount() {
        return map.size();
    }

    @Override
    public double getAmount() {
        double amount = 0;
        for (Product Product : map.values()) {
            amount += Product.getGia() * Product.getSoLuong();
        }
        return amount;
    }
    
}
