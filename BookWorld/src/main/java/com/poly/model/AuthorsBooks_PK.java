package com.poly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AuthorsBooks_PK {

    @Column(name = "author_id" ,insertable = false, updatable = false)
    String authorId;
    
    @Column(name = "book_id" ,insertable = false, updatable = false)
    String bookId;
}
 