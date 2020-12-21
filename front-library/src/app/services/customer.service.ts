import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }
  URLHttp ="http://localhost:8080";

  saveCustomer(customer:Customer): Observable<Customer>{
    return this.http.post<Customer>(this.URLHttp+'/rest/customer/api/addCustomer', customer);
    
  }

  updateCustomer(customer:Customer): Observable<Customer>{
    return this.http.put<Customer>(this.URLHttp+'/rest/customer/api/updateCustomer', customer);    
  }

  deleteCustomer(customer:Customer): Observable<string>{
    return this.http.delete<string>(this.URLHttp+'/rest/customer/deleteCustomer/'+ customer.id);
  }

  searchCustomerByEmail(email: string): Observable<Customer[]>{
    return this.http.get<Customer[]>(this.URLHttp+'/rest/customer/api/searchByEmail?email='+email);
  }

  searchCustomerByLastName(lastname: string): Observable<Customer[]>{
    return this.http.get<Customer[]>(this.URLHttp+'/rest/customer/api/searchByLastName?lastname='+lastname);
  }

}
