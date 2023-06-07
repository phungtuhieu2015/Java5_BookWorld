package com.poly.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
	
  @Id
  String username;
  
  //@NotBlank(message = "Category name is required" )
  // @Size(min = 8, message = "{Password needs at least 8 characters}")
  String password;
  
   @NotBlank(message = "fullName is required")
  String fullName;

  // @NotBlank(message = "phone is required")
  // @Pattern(regexp = "^(09|03)\\d{8}$", message = "Phone number must be 10 digits and start with 09 or 03")
  String phone;

  // @NotBlank(message = "Email is required")
  // @Email(message = "Invalid email format")
  String email;

  // @NotBlank(message = "address is required")
  String address;

  String image;

  Boolean admin;

  Boolean activated;


  @OneToMany(mappedBy = "user", cascade=CascadeType.REFRESH)
  List<Share> share ;

  @OneToMany(mappedBy = "user", cascade=CascadeType.REFRESH)
  List<Favorite> favorite ;

  @OneToMany(mappedBy = "user", cascade=CascadeType.REFRESH)
  List<Order> order ;
    
}
