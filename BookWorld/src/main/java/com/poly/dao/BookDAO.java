package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.model.Book;

public interface BookDAO extends JpaRepository<Book, String> {

    @Query("SELECT new com.poly.model.FavoriteBook(b.title, b.category.categoryName, COUNT(f), MIN(f.likedDate), MAX(f.likedDate)) "
        + " FROM Book b JOIN b.favorites f "
        + " GROUP BY b.category.categoryName, b"
        + " ORDER BY COUNT(f) DESC")
    Page getBookFavorite(Pageable pageable);


    
}
