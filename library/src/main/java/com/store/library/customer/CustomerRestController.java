package com.store.library.customer;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("rest/customer/api")
@Api(value = "Customer Rest Controller: contains all operations for managing customers")
public class CustomerRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);

    @Autowired
    private CustomerServiceImpl customerService;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/searchByLastName")
    @ApiOperation(value="Search a customer in the Library by its Last name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfull research"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity<List<CustomerDTO>> searchCustomerByLastName(@RequestParam("lastname") String lastname){
        
        List<Customer> customers = customerService.findCustomerByLastName( lastname );
        if(!CollectionUtils.isEmpty( customers )) {
            customers.remove( Collections.singleton( null ) );
            List<CustomerDTO> customerDTOs = customers.stream().map( customer ->{
                return mapCustomerToCustomerDTO(customer);
            }).collect(Collectors.toList());
            
            return new ResponseEntity<List<CustomerDTO>>(customerDTOs, HttpStatus.OK);
        }
        
        
        return new ResponseEntity<List<CustomerDTO>>(HttpStatus.NO_CONTENT);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/searchByEmail")
    @ApiOperation(value="Search a customer in the Library by its email", response = CustomerDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfull research"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity<CustomerDTO> searchCustomerByEmail(@RequestParam("email") String email) {

        Customer customer = customerService.findCustomerByEmail(email);
        if (customer != null) {
            CustomerDTO customerDTO = mapCustomerToCustomerDTO(customer);
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
        }
        return new ResponseEntity<CustomerDTO>(HttpStatus.NO_CONTENT);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("addCustomer")
    @ApiOperation(value = "Add a new Customer in the Library", response = CustomerDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 409, message = "Conflict: the customer already exist"),
            @ApiResponse(code = 201, message = "Created: the customer is successfully inserted"),
            @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully inserted") })
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTORequest){
        Customer existingCustomer = customerService.findCustomerByEmail( customerDTORequest.getEmail());
        if(existingCustomer !=null) {
            return new ResponseEntity<CustomerDTO>(HttpStatus.CONFLICT);
        }
        
        Customer customerRequest = mapCustomerDTOToCustomer(customerDTORequest);
        Customer customer = customerService.saveCustomer( customerRequest );
        
        if(customer !=null && customer.getId() !=null) {
            CustomerDTO customerDTO = mapCustomerToCustomerDTO(customer);
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK); 
        }       
      
        return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_MODIFIED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateCustomer")
    @ApiOperation(value = "Update/Modify an existing customer in the Library", response = CustomerDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the customer does not exist"),
            @ApiResponse(code = 200, message = "Ok: the customer is successfully updated"),
            @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully updated") })
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTORequest){
        
        if(!customerService.checkIfIdexists( customerDTORequest.getId() )) {
            return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
        }
        
        Customer customerRequest = mapCustomerDTOToCustomer(customerDTORequest);        
        Customer customerResponse = customerService.updateCustomer( customerRequest );
        
        if(customerResponse != null) {
            CustomerDTO customerDTO = mapCustomerToCustomerDTO(customerResponse);
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
        }
        
        return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_MODIFIED);
    }
    
    
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteCustomer/{customerid}")
    @ApiOperation(value = "Delete a customer in the Library, if the customer does not exist, nothing is done", response = String.class)
    @ApiResponse(code = 204, message = "No Content: customer sucessfully deleted")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerid){
        customerService.deleteCustomer( customerid );
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/paginatedSearch")
    @ApiOperation(value="List customers of the Library in a paginated way", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfully listed"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity<List<CustomerDTO>> searchCustomers(@RequestParam("beginPage") int beginPage,
            @RequestParam("endPage") int endPage) {
        //, UriComponentsBuilder uriComponentBuilder
        Page<Customer> customers = customerService.getPaginatedCustomersList(beginPage, endPage);
        if (customers != null) {
            List<CustomerDTO> customerDTOs = customers.stream().map(customer -> {
                return mapCustomerToCustomerDTO(customer);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<CustomerDTO>>(customerDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<CustomerDTO>>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/sendEmailToCustomer")
    @ApiOperation(value="Send an email to customer of the Library", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: Email successfully sent"),
            @ApiResponse(code = 404, message = "Not Found: no customer found, or wrong email"),
            @ApiResponse(code = 403, message = "Forbidden: Email cannot be sent")
    })
    public ResponseEntity<Boolean> sendMailToCustomer(@RequestBody MailDTO mailDTO){
        Customer customer = customerService.findCustomerById( mailDTO.getCustomerId() );
        if(customer == null) {
            String errorMessage="customer NOT FOUND";
            LOGGER.info( errorMessage );
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        } else if( customer != null && customer.getEmail() == null){
            String errorMessage="email NOT FOUND";
            LOGGER.info( errorMessage );
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        }
        System.out.println(mailDTO);
        System.out.println(customer.getEmail());
        
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(mailDTO.MAIL_FROM);
        mail.setTo( customer.getEmail() );
        mail.setSentDate( new Date() );
        mail.setSubject( mailDTO.getEmailSubject() );
        mail.setText( mailDTO.getEmailContent() );
        
        try {
            javaMailSender.send(mail);
        } catch (MailException e) {
            System.out.println(e);
            return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
        }
        
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
          
    private Customer mapCustomerDTOToCustomer( CustomerDTO customerDTORequest ) {
        ModelMapper mapper = new ModelMapper();        
        Customer customer = mapper.map( customerDTORequest,Customer.class );        
        return customer;
    }

    private CustomerDTO mapCustomerToCustomerDTO( Customer customer ) {               
        ModelMapper mapper = new ModelMapper();        
        CustomerDTO customerDTO = mapper.map( customer,CustomerDTO.class );        
        return customerDTO;
    }
    
    
}
