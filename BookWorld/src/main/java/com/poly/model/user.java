package com.poly.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Getter
// @Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
	
  @Id
  String username;

  String password;
  
  String fullName;

  String phone;

  String email;

  String address;

  String image;

  Boolean admin;

  Boolean activated;


  @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
  List<Share> share ;

  @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
  List<Favorite> favorite ;

  @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
  List<Order> order ;
    
}
