package com.store.library.book;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.store.library.category.Category;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idbook;
    
    private String title;
    private String isbn;
    
    private LocalDate releasedate;
    private LocalDate registerdate;
    
    private Integer totalexamplaries;
    
    private String author;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "cat_code")
    private Category category;

    
    public Integer getIdbook() {
        return idbook;
    }

    public void setIdbook( Integer idbook ) {
        this.idbook = idbook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn( String isbn ) {
        this.isbn = isbn;
    }

    public LocalDate getReleasedate() {
        return releasedate;
    }

    public void setReleasedate( LocalDate releasedate ) {
        this.releasedate = releasedate;
    }

    public LocalDate getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate( LocalDate registerdate ) {
        this.registerdate = registerdate;
    }

    public Integer getTotalexamplaries() {
        return totalexamplaries;
    }

    public void setTotalexamplaries( Integer totalexamplaries ) {
        this.totalexamplaries = totalexamplaries;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor( String author ) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory( Category category ) {
        this.category = category;
    }
    
    
    
   
    
}