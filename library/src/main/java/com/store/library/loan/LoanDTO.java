package com.store.library.loan;

import java.time.LocalDate;

import com.store.library.book.BookDTO;
import com.store.library.customer.CustomerDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "Loan Model")
public class LoanDTO implements Comparable<LoanDTO>  {
    
    @ApiModelProperty(value = "Book concerned by the loan")
    private BookDTO bookDTO = new BookDTO();
    
    @ApiModelProperty(value = "Customer concerned by the loan")
    private CustomerDTO customerDTO = new CustomerDTO();
    
    @ApiModelProperty(value = "Loan begining date")
    private LocalDate loanbegindate;
    
    @ApiModelProperty(value = "Loan ending date")
    private LocalDate loanenddate;
    
    private LoanStatus status;
    
    
    
    public LoanStatus getStatus() {
        return status;
    }
    public void setStatus( LoanStatus status ) {
        this.status = status;
    }
    public BookDTO getBookDTO() {
        return bookDTO;
    }
    public void setBookDTO( BookDTO bookDTO ) {
        this.bookDTO = bookDTO;
    }
    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }
    public void setCustomerDTO( CustomerDTO customerDTO ) {
        this.customerDTO = customerDTO;
    }
    public LocalDate getLoanbegindate() {
        return loanbegindate;
    }
    public void setLoanbegindate( LocalDate loanbegindate ) {
        this.loanbegindate = loanbegindate;
    }
    public LocalDate getLoanenddate() {
        return loanenddate;
    }
    public void setLoanenddate( LocalDate loanenddate ) {
        this.loanenddate = loanenddate;
    }
    @Override
    public int compareTo( LoanDTO o ) {
        return o.getLoanbegindate().compareTo(this.loanbegindate);
    }
    
    

}
