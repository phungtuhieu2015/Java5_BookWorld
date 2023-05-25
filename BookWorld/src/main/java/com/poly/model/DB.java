package com.poly.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class DB {
    public static Map<Integer, book> items = new HashMap<>();
    static {
    items.put(1, new book("1", "Sach 1", 10, "Sach1.jpg" ));
    items.put(2, new book("2", "Sach 2", 10, "Sach2.jpg" ));
    items.put(3, new book("3", "Sach 3", 10, "Sach3.jpg" ));
    items.put(4, new book("4", "Sach 4", 10, "Sach4.jpg" ));
    items.put(5, new book("5", "Sach 5", 10, "Sach5.jpg" ));
    items.put(6, new book("6", "Sach 6", 10, "Sach6.jpg" ));
    items.put(7, new book("7", "Sach 7", 10, "Sach7.jpg" ));
    items.put(8, new book("8", "Sach 8", 10, "Sach8.jpg" ));
   
    }
}
