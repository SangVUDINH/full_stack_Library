package com.store.library.customer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.library.book.BookDTO;

@RestController
@RequestMapping("rest/customer/api")
public class CustomerRestController {

    @Autowired
    private CustomerServiceImpl customerService;
    
    @GetMapping("/searchByLastName")
    public ResponseEntity<List<CustomerDTO>> searchCustomerByLastName(@RequestParam("lastname") String lastname){
        
        List<Customer> customers = customerService.findCustomerByLastName( lastname );
        if(!CollectionUtils.isEmpty( customers )) {
            customers.remove( Collections.singleton( null ) );
            List<CustomerDTO> customerDTOs = customers.stream().map( customer ->{
                return mapCustumerToCustomerDTO(customer);
            }).collect(Collectors.toList());
            
            return new ResponseEntity<List<CustomerDTO>>(customerDTOs, HttpStatus.OK);
        }
        
        
        return new ResponseEntity<List<CustomerDTO>>(HttpStatus.NO_CONTENT);
    }
    
    
    @PostMapping("addCustomer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTORequest){
//        Customer existingCustomer = customerService.findCustomerById( customerDTORequest.getId() );
//        if(existingCustomer !=null) {
//            return new ResponseEntity<CustomerDTO>(HttpStatus.CONFLICT);
//        }
        
        Customer customerRequest = mapCustomerDTOToCustomer(customerDTORequest);
        Customer customer = customerService.saveCustomer( customerRequest );
        
        if(customer !=null && customer.getId() !=null) {
            CustomerDTO customerDTO = mapCustumerToCustomerDTO(customer);
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK); 
        }
        
        
        return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_MODIFIED);
    }

    private Customer mapCustomerDTOToCustomer( CustomerDTO customerDTORequest ) {
        ModelMapper mapper = new ModelMapper();        
        Customer customer = mapper.map( customerDTORequest,Customer.class );        
        return customer;
    }

    private CustomerDTO mapCustumerToCustomerDTO( Customer customer ) {               
        ModelMapper mapper = new ModelMapper();        
        CustomerDTO customerDTO = mapper.map( customer,CustomerDTO.class );        
        return customerDTO;
    }
    
    
}
