// Bài 4
package com.poly.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.dao.BookDAO;
import com.poly.dao.CartDAO;
import com.poly.model.Book;
import com.poly.model.Cart;
import com.poly.model.User;
@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService  {
    Map<Long, Cart> map = new HashMap<>();
    @Autowired 
    SessionService session ;

    User user ;
    @Autowired
    BookDAO daoBook;

    @Autowired
    CartDAO daoCart;
    @Override
    public Cart add(String id) {
        Book book = null;
        Cart cart ;
        this.user = session.get("user");
        book = daoBook.findById(id).get() ;
        if(this.user == null) {
            cart = new Cart(Long.parseLong(id.substring(1)),this.user, book, 1, book.getPrice() * 1);
            map.put(Long.parseLong(id.substring(1)), cart);
        } else {
            cart = new Cart( this.user, book, 1, book.getPrice() * 1);
            daoCart.save(cart);
        }
        return cart;
    }

    @Override
    public void remove(Long id) {
        if(this.user != null) {
           Cart crm =  daoCart.findById(id).get();
           daoCart.delete(crm);
        } else {
             map.remove( id) ;
        }
        
    }

    @Override
    public Cart update(Long id, Integer qty) {
        if(this.user !=null) {
            Cart cart = daoCart.findById(id).get();
            cart.setQuantity(qty);
            cart.setTotalPrice(qty * cart.getBook().getPrice());
            daoCart.save(cart);
            return cart;
        } 
            map.get(id).setQuantity(qty);
            map.get(id).setTotalPrice(qty * map.get(id).getBook().getPrice());
        return  map.get(id);
    }

    @Override
    public void clear() {
        if(this.user != null) {
            daoCart.deleteAll();
        } else {
            map.clear();
        }
       
    }

    @Override
    public Collection<Cart> getOrderDetails() {
        this.user = session.get("user");
        if(user != null) {
            List<Cart> list = daoCart.findAll();
            return list;
        } 
        return map.values();
    }

    @Override
    public int getCount() {
        return map.size();
    }

    @Override
    public double getAmount() {
        double amount = 0;
        for (Cart cart : map.values()) {
            // amount += Cart.getGia() * Cart.getSoLuong();
        }
        return amount;
    }

    
}
