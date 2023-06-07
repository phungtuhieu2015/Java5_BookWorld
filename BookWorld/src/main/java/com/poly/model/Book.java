
package com.poly.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Books")

public class Book {
    
    @Id
    String id;

    String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    Publisher publisher;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    Date createdDate;

    Double price;
    
    Integer quantity;

    String image;

    Boolean available;

    String description;


    @OneToMany(mappedBy = "book", cascade=CascadeType.REFRESH)
    List<OrderDetail> orderDetail;


    @OneToMany(mappedBy = "book", cascade=CascadeType.REFRESH)
    List<AuthorBook> authorBook;

    @OneToMany(mappedBy = "book", cascade=CascadeType.REFRESH)
    List<Share> share;

    @OneToMany(mappedBy = "book", cascade=CascadeType.REFRESH)
    List<Favorite> favorite;

}
