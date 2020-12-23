import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { forkJoin } from 'rxjs';
import { Book } from '../models/book';
import { Customer } from '../models/customer';
import { Loan } from '../models/loan';
import { SimpleLoan } from '../models/simpleLoanDTO';
import { LoanService } from '../services/loan.service';

@Component({
  selector: 'app-loan-page',
  templateUrl: './loan-page.component.html',
  styleUrls: ['./loan-page.component.scss']
})
export class LoanPageComponent implements OnInit {

  public types = ['Email','Maximum date'];
  public displayType:string ="Email";

  public headsTab =['ISBN', 'TITRE', 'EMAIL','NOM', 'DATE DEBUT', 'DATE FIN'];

  public searchLoansResult: Loan[] =[];

  searchLoanForm : FormGroup;
  loanForm : FormGroup;

  URLHttp ="http://localhost:8080";

  constructor(private http: HttpClient, private formBuilder: FormBuilder,
    private loanService:LoanService) { }
  ngOnInit(): void {
    this.initForm();
  }

  initForm(){
    this.searchLoanForm = this.formBuilder.group({
      typeSearchBy:'Email',
      searchValue:''
    });

    this.loanForm = this.formBuilder.group({
      isbn:'',
      email:'',
      begindate:'',
      enddate:''
    });
  }

  clearSearchValue(){
    this.searchLoanForm.patchValue({
      searchValue:''
    });
  }

  onSubmitFormSearch(){
    console.log(this.searchLoanForm.value);

    if(!this.searchLoanForm.valid || this.searchLoanForm.value['searchValue'] ===""){
      //window.alert("erreur de recherche !");
    } else {
      this.searchLoansResult.length=0;
      if (this.searchLoanForm.value['typeSearchBy'] ==="Email") {
        this.loanService.searchLoansByEmail(this.searchLoanForm.value['searchValue']).subscribe(
          result => {
            if (result && result != null){
              this.searchLoansResult=this.searchLoansResult.concat(result);

              console.log(result);
            }
          }, error => {
            console.log (error);
          });
        }      
        else if (this.searchLoanForm.value['typeSearchBy'] ==="Maximum date") {
          this.loanService.searchLoansByMaxDate(this.searchLoanForm.value['searchValue']).subscribe(
            result => {
              if (result && result != null){
                this.searchLoansResult=this.searchLoansResult.concat(result);

                console.log(result);
              }
            }, error => {
              console.log (error);
            });
          }
      }
    }

    openAddLoanDialog(){  
      var addLoanDialog:any = <any>document.getElementById('formDialog');  
  
      if(typeof addLoanDialog.showModal === "function"){
        addLoanDialog.showModal();
      } else {
        console.log("error navigateur !");
      }
  
    }
  
    closeDialog(){
      this.loanForm.reset();
      var addLoanDialog:any = <any>document.getElementById('formDialog');
      addLoanDialog.close();
    }

    onSubmitForm(){
      const formValue = this.loanForm.value;
      const newLoan = new SimpleLoan();

      let book = this.http.get<Book>(this.URLHttp+'/rest/book/api/searchByIsbn?isbn='+formValue['isbn']);
      let customer = this.http.get<Customer>(this.URLHttp+'/rest/customer/api/searchByEmail?email='+formValue['email']);
      forkJoin([book, customer]).subscribe( results=>{
        if ((results[0] && results[0].idbook) && (results[1] && results[1].id)){
          newLoan.bookid=results[0].idbook;
          newLoan.customerid=results[1].id;
          newLoan.begindate=formValue['begindate'];
          newLoan.enddate=formValue['enddate'];

          this.loanService.saveLoan(newLoan).subscribe(
            (result:boolean)=>{
              if(result){
                console.log("new loan has been added");
                
                this.onSubmitFormSearch();
                this.loanForm.reset();
                this.closeDialog();
              }
            },
            error => {
              console.log(error);
              if(error.status==409){
                window.alert("le pret existe deja dans la base !");
              }
            }
          );
        }else{
          window.alert("erreur du livre ou client !");
        }
      });      
    }

    closeLoan(loan:Loan){
      let simpleLoan: SimpleLoan = new SimpleLoan();
      simpleLoan.bookid= loan.bookDTO.idbook;
      simpleLoan.customerid= loan.customerDTO.id;
      simpleLoan.begindate= loan.loanBeginDate;
      simpleLoan.enddate= loan.loanEndDate;  

      this.loanService.closeLoan(simpleLoan).subscribe( result=>{
        if(result){
          console.log("fermeture sucesss !");
        }
      }), error =>{
        console.log(error);
      }
    }
  

}
