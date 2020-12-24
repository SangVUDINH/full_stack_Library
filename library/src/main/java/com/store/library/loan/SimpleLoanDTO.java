package com.store.library.loan;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "Simple Loan Model")
public class SimpleLoanDTO {
   @ApiModelProperty(value = "Book id concerned by the loan")
   private Integer bookid;

   @ApiModelProperty(value = "Customer id concerned by the loan")
   private Integer customerid;
   
   @ApiModelProperty(value = "Loan begining date")
   private LocalDate begindate;
   
   @ApiModelProperty(value = "Loan ending date")
   private LocalDate enddate;
     
   
public SimpleLoanDTO() {
    super();
    // TODO Auto-generated constructor stub
}
public SimpleLoanDTO( Integer bookid, Integer customerid, LocalDate begindate, LocalDate enddate ) {
    super();
    this.bookid = bookid;
    this.customerid = customerid;
    this.begindate = begindate;
    this.enddate = enddate;
}
public Integer getBookid() {
    return bookid;
}
public void setBookid( Integer bookid ) {
    this.bookid = bookid;
}
public Integer getCustomerid() {
    return customerid;
}
public void setCustomerid( Integer customerid ) {
    this.customerid = customerid;
}
public LocalDate getBegindate() {
    return begindate;
}
public void setBegindate( LocalDate begindate ) {
    this.begindate = begindate;
}
public LocalDate getEnddate() {
    return enddate;
}
public void setEnddate( LocalDate enddate ) {
    this.enddate = enddate;
}
   
   
    
    

}
