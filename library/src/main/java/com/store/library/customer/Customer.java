package com.store.library.customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.library.loan.Loan;

@Entity
@Table(name="customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private String firstname;
    private String lastname;
    private String job;
    private String address;
    private String email;
    private LocalDate creationdate;  
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.customer", cascade = CascadeType.ALL)
    List<Loan> loans = new ArrayList<>();

    
    public Customer( Integer id, String firstname, String lastname, String job, String address, String email) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.job = job;
        this.address = address;
        this.email = email;
        
    }
    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public List<Loan> getLoans() {
        return loans;
    }
    public void setLoans( List<Loan> loans ) {
        this.loans = loans;
    }
    public Integer getId() {
        return id;
    }
    public void setId( Integer id ) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname( String firstname ) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname( String lastname ) {
        this.lastname = lastname;
    }
    public String getJob() {
        return job;
    }
    public void setJob( String job ) {
        this.job = job;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress( String address ) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail( String email ) {
        this.email = email;
    }
    public LocalDate getCreationdate() {
        return creationdate;
    }
    public void setCreationdate( LocalDate creationdate ) {
        this.creationdate = creationdate;
    }
    
    @Override
    public int hashCode() {
        final int prime=31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());        
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
    
    

}
