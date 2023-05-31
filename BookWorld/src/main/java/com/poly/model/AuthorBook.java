package com.poly.model;




import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Authors_Books")
public class AuthorBook {
    
    @EmbeddedId
	private AuthorsBooks_PK id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;


    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

}
