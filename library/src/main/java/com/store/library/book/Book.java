package com.store.library.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.library.category.Category;
import com.store.library.loan.Loan;

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
    
    @ManyToOne
    @JoinColumn(name = "cat_code")
    private Category category;

    @JsonIgnore
    @OneToMany(targetEntity=Loan.class,fetch = FetchType.LAZY, mappedBy = "pk.book", cascade = CascadeType.ALL)
    List<Loan> loans = new ArrayList<>();
    
    
    
    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans( List<Loan> loans ) {
        this.loans = loans;
    }

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
