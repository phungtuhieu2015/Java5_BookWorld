package com.poly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class AuthorsBooks_PK {

    @Column(name = "author_id" ,insertable = false, updatable = false)
    Long authorId;
    
    @Column(name = "book_id" ,insertable = false, updatable = false)
    String bookId;
}
 