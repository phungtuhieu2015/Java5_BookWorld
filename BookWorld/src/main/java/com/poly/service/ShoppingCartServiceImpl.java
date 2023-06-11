// Bài 4
package com.poly.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poly.model.DB;
import com.poly.model.Order;
import com.poly.model.OrderDetail;
import com.poly.dao.BookDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.model.Book;
import com.poly.model.User;
@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService  {
    Map<String, OrderDetail> map = new HashMap<>();
    @Autowired 
    SessionService session ;

    User user ;
    @Autowired
    BookDAO daoBook;

    @Autowired
    OrderDetailDAO daoDet;
    @Override
    public Book add(String id) {
        Book book = null;
        this.user = session.get("user");
        OrderDetail orderDet ;
         book = daoBook.findById(id).get() ;
         System.out.println("======================="+ this.user);
        if(this.user == null) {
            orderDet = new OrderDetail(null, book, 1, book.getPrice() * 1);
            map.put(id, orderDet);
        } else {
            Order order = new Order();
            System.out.println("+==++++++++++++++++++++++++++++++=");
            orderDet = new OrderDetail(null, book, 1, (book.getPrice() * 1));
            daoDet.save(orderDet);
        }
        return book;
    }

    @Override
    public boolean remove(String id) {
        map.remove(id) ;
        return map.get(id) == null?true: false;
    }

    @Override
    public Book update(String id, int qty) {
        // map.get(id).setSoLuong(qty);
        return new Book();
    }

    @Override
    public boolean clear() {
        map.clear();
        return map.isEmpty();
    }

    @Override
    public Collection<OrderDetail> getOrderDetails() {
        if(user != null) {
            List<OrderDetail> list = daoDet.findAll();
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
        for (OrderDetail OrderDetail : map.values()) {
            // amount += Book.getGia() * Book.getSoLuong();
        }
        return amount;
    }

    
}
