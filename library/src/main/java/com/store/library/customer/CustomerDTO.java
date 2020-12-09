package com.store.library.customer;

import java.time.LocalDate;

public class CustomerDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String job;
    private String address;
    private String email;
    private LocalDate creationdate;
    
    
    public CustomerDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public CustomerDTO( Integer id, String firstname, String lastname, String job, String address, String email,
            LocalDate creationdate ) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.job = job;
        this.address = address;
        this.email = email;
        this.creationdate = creationdate;
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
    
    
    
}
