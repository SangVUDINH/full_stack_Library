import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { Customer } from '../models/customer';
import { Mail } from '../models/Mail';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.scss']
})
export class CustomerPageComponent implements OnInit {

  public searchCustomerResult: Customer[]=[];
  searchFormCustomer  : FormGroup;
  customerForm        : FormGroup;
  emailForm        : FormGroup;

  isCreate: boolean =true;
  customerUpdate: Customer;
  customerEmail: Customer;

  constructor(private formBuilder: FormBuilder, private customerService: CustomerService) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(){
    this.customerForm = this.formBuilder.group({
      firstname:'',
      lastname:'',
      job:'',
      address:'',
      email:''
    });

    this.searchFormCustomer = this.formBuilder.group({
      typeSearchBy:'lastName',
      searchValue:''
    })

    this.emailForm = this.formBuilder.group({
      emailSubject:'',
      emailContent:''
    })
  }

  onSubmitFormSearch(){
    console.log(this.searchFormCustomer.value);

    if(!this.searchFormCustomer.valid || this.searchFormCustomer.value['searchValue'] ===""){
      //window.alert("erreur de recherche !");
    } else {
      this.searchCustomerResult.length=0;
      if (this.searchFormCustomer.value['typeSearchBy'] ==="email") {
        this.customerService.searchCustomerByEmail(this.searchFormCustomer.value['searchValue']).subscribe(
          result => {
            if (result && result != null){
              this.searchCustomerResult=this.searchCustomerResult.concat(result);
            }
          }, error => {
            console.log (error);
          }
        );
      } else if (this.searchFormCustomer.value['typeSearchBy'] ==="lastName"){
        this.customerService.searchCustomerByLastName(this.searchFormCustomer.value['searchValue']).subscribe(
          result => {
            if (result && result != null){
              this.searchCustomerResult=this.searchCustomerResult.concat(result);
            }
          }, error => {
            console.log (error);
          }
        );
      }
    }
  }

  onSubmitForm(){
    const formValue = this.customerForm.value;
    const newCustomer = new Customer();

    newCustomer.firstname=formValue['firstname'];
    newCustomer.lastname=formValue['lastname'];
    newCustomer.job=formValue['date_sortie'];
    newCustomer.address=formValue['address'];
    newCustomer.email=formValue['email'];


    this.customerService.saveCustomer(newCustomer).subscribe(
      (result:Customer)=>{
        if(result.id){
          console.log("new customer has been added");
          
          this.onSubmitFormSearch();
          this.customerForm.reset();
          this.closeDialog();
        }
      },
      error => {
        console.log(error);
        if(error.status==409){
          window.alert("le client existe deja dans la base !");
        }
      }
    );
  }


  onSubmitFormUpdate(){
    const formValue = this.customerForm.value;
    const newCustomer = new Customer();

    newCustomer.id= this.customerUpdate.id;

    newCustomer.firstname=formValue['firstname'];
    newCustomer.lastname=formValue['lastname'];
    newCustomer.job=formValue['date_sortie'];
    newCustomer.address=formValue['address'];
    newCustomer.email=formValue['email'];

    this.customerService.updateCustomer(newCustomer).subscribe(
      (result:Customer)=>{
        if(result.id){
          console.log("updated");
          
          this.customerForm.reset();
          this.closeDialog();

          this.onSubmitFormSearch();
        }
      },
      error => {
        console.log(error);
        if(error.status==409){
          window.alert("conflict !");
        }
      }
    );
  }

  deleteCustomer(customer:Customer){
    this.customerService.deleteCustomer(customer).subscribe(
      result => {
        for(var i = 0; i< this.searchCustomerResult.length; i++){
          if (this.searchCustomerResult[i].id === customer.id) {
            this.searchCustomerResult.splice(i,1);
          }
        }
        console.log(result);
      }, error =>{
        console.log(error);
      }
    );
  }

  openAddCustomerDialog(customer?:Customer){
    if (customer !=null){
      this.isCreate=false;
      this.customerUpdate=customer;

      this.customerForm = this.formBuilder.group({
        firstname:customer.firstname,
        lastname:customer.lastname,
        job:customer.job,
        address:customer.address,
        email:customer.email,
      });
    } else {
      this.isCreate=true;
    }

    var addCustomerDialog:any = <any>document.getElementById('formDialog');  

    if(typeof addCustomerDialog.showModal === "function"){
      addCustomerDialog.showModal();
    } else {
      console.log("error navigateur !");
    }

  }

  closeDialog(){
    this.customerForm.reset();
    var addCustomerDialog:any = <any>document.getElementById('formDialog');
    addCustomerDialog.close();
  }


  openEmailCustomerDialog(customer:Customer){
    this.customerEmail=customer;

    if (customer.email !=null){
      
      var EmailCustomerDialog:any = <any>document.getElementById('formEmailDialog');  

      if(typeof EmailCustomerDialog.showModal === "function"){
        EmailCustomerDialog.showModal();
      } else {
        console.log("error navigateur !");
      }      
    } else {
      window.alert("no email !");
    }

    

  }

  onSubmitEmailForm(){
    const mail: Mail = new Mail();

    const formValue = this.emailForm.value;
    mail.customerId= this.customerEmail.id;
    mail.emailSubject=formValue['emailSubject'];
    mail.emailContent=formValue['emailContent'];


    this.customerService.sendEmail(mail).subscribe(
      (result:boolean)=>{
        if(result){
          console.log("Email sended");

          this.emailForm.reset();
          this.closeEmailDialog();
        }
      },
      error => {
        console.log(error);        
      }
    );
  }

  closeEmailDialog(){
    this.emailForm.reset();
    var EmailCustomerDialog:any = <any>document.getElementById('formEmailDialog');
    EmailCustomerDialog.close();
  }
}


