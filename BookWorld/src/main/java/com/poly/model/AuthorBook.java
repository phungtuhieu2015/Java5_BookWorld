package com.poly.model;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Authors_Books")
public class AuthorBook {
    
    @EmbeddedId
	private AuthorsBooks_PK id;

    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    Author author;


    @ManyToOne
    @JoinColumn(name = "book_id",insertable = false, updatable = false)
    Book book;

}
