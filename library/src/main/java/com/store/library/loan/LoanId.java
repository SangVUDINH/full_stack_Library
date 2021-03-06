package com.store.library.loan;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.store.library.book.Book;
import com.store.library.customer.Customer;

@Embeddable
public class LoanId implements Serializable{
    private static final long serialVersionUID = 3912193101593832821L;
    
    @ManyToOne
    private Book book;
    @ManyToOne
    private Customer customer;
    private LocalDateTime creationdatetime;
    
    
           
    public LoanId( Book book, Customer customer) {
        super();
        this.book = book;
        this.customer = customer;
        this.creationdatetime = LocalDateTime.now();
       
    }
    
    public LoanId() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Book getBook() {
        return book;
    }
    public void setBook( Book book ) {
        this.book = book;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer( Customer customer ) {
        this.customer = customer;
    }
    public LocalDateTime getCreationdatetime() {
        return creationdatetime;
    }
    public void setCreationdatetime( LocalDateTime creationdatetime ) {
        this.creationdatetime = creationdatetime;
    }
    
    
}
