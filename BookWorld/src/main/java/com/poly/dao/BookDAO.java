package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.model.Book;
import com.poly.model.BookFavorite;

public interface BookDAO extends JpaRepository<Book, String> {

    @Query("SELECT new com.poly.model.BookFavorite(b.title,b.category.categoryName,count(f),min(f.likedDate),max(f.likedDate)) "
            + " FROM Book b JOIN b.favorite f "
            + " GROUP BY b.category.categoryName,b")
    List<BookFavorite> getBookFavorite();
}
