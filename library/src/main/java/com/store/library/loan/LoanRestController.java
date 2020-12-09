package com.store.library.loan;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/loan/api")
public class LoanRestController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoanRestController.class);
    
    @Autowired 
    private LoanServiceImpl loanService;
    
    @GetMapping("/allloans")
    
    public ResponseEntity<List<Loan>> getAllLoans(){
        
        List<Loan> loans = loanService.getAllLoan();
        if(!CollectionUtils.isEmpty( loans )) {
            loans.removeAll( Collections.singleton( null ) );
            return new ResponseEntity<List<Loan>>(loans,HttpStatus.OK);
        }
        
        
        return new ResponseEntity<List<Loan>>(HttpStatus.NO_CONTENT);
    }
    
}
