package com.store.library.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerDao extends JpaRepository<Customer,Integer> {

    public Customer findCustomerByEmailIgnoreCase(String email);
    
    public List<Customer> findCustomerByLastnameIgnoreCase(String lastName);

    public List<Customer> findCustomerByLastnameLikeIgnoreCase( String string );

}
