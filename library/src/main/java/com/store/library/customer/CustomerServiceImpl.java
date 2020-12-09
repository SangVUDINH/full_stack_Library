package com.store.library.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements ICustomerService {
    
    @Autowired
    private ICustomerDao customerDao;

    @Override
    public Customer saveCustomer( Customer customer ) {
        return  customerDao.save( customer );
    }

    @Override
    public Customer updateCustomer( Customer customer ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCustomer( Integer customerId ) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean checkIfIdexists( Integer id ) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Customer findCustomerByEmail( String email ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Customer> findCustomerByLastName( String lastName ) {
        return customerDao.findCustomerByLastnameIgnoreCase(lastName);
    }

    @Override
    public Customer findCustomerById( Integer customerId ) {
        // TODO Auto-generated method stub
        return customerDao.findById( customerId ).orElse( null );
    }

    @Override
    public Page<Customer> getPaginatedCustomersList( int begin, int end ) {
        // TODO Auto-generated method stub
        return null;
    }

}
