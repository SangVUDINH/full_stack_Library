package com.store.library.customer;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Customer Model")
public class CustomerDTO  {
    
    @ApiModelProperty(value = "Customer id")
    private Integer id;
    
    @ApiModelProperty(value = "Customer first name")
    private String firstname;
    
    @ApiModelProperty(value = "Customer last name")
    private String lastname;
    
    @ApiModelProperty(value = "Customer job")
    private String job;
    
    @ApiModelProperty(value = "Customer address")
    private String address;
    
    @ApiModelProperty(value = "Customer email")
    private String email;
    


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

 
    
}
