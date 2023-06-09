package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.model.User;

public interface UserDAO extends JpaRepository<User,String> {

    @Query("SELECT u FROM User u WHERE u.email=:email")
    User  findByEmaill(@Param("email") String email);
   
}