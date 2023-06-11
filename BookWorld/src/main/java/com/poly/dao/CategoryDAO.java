package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.model.Book;
import com.poly.model.Category;
import com.poly.model.User;

@Repository
public interface CategoryDAO extends JpaRepository<Category,Long> {


   

    Category  findByCategoryName( String categoryName);

  // @Query( value = "select * from Categories where id in (Select category_id from books  )",nativeQuery = true)
  //   List<Category> findAllCategories();



}
