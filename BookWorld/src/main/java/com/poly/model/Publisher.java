package com.poly.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @NotEmpty(message = "Publisher name is required")
    String publisherName;
    
    @NotBlank(message = "Address is required")
    String address;

    @NotBlank(message = "City is required")
    String city;

    @NotEmpty(message = "Country is required")
    String country;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email;
    
    @Pattern(regexp = "^(09|03)\\d{8}$", message = "Phone number must be 10 digits and start with 09 or 03")
    String phone;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.REFRESH)
    List<Book> book;

}
