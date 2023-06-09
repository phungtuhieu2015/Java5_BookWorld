package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.model.Category;
import com.poly.model.User;

@Repository
public interface CategoryDAO extends JpaRepository<Category,Long> {


    // @Query("SELECT c FROM Category c WHERE c.categoryName=:categoryName")
    // Category findByCategoryName(@Param("categoryName") String categoryName);

    
    Category  findByCategoryName( String categoryName);
}
