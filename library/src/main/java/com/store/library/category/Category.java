package com.store.library.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.library.book.Book;

@Entity
@Table(name = "category")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    private String label;
    
    @JsonIgnore
    @OneToMany( targetEntity=Book.class, mappedBy="category" )
    private List<Book> books = new ArrayList<>();
    
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Category( Integer code, String label ) {
        super();
        this.code = code;
        this.label = label;
    }

    

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks( List<Book> books ) {
        this.books = books;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode( Integer code ) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel( String label ) {
        this.label = label;
    }
    
    
    

}
