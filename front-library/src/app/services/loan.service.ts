import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Loan } from '../models/loan';
import { SimpleLoan } from '../models/simpleLoanDTO';

@Injectable({
  providedIn: 'root'
})
export class LoanService {
  

  URLHttp ="http://localhost:8080";

  constructor(private http: HttpClient) { }

  searchLoansByEmail(email:string): Observable<Loan[]>{
    return this.http.get<Loan[]>(this.URLHttp+'/rest/loan/api/customerLoans?email='+email);
  }

  searchLoansByMaxDate(date:string): Observable<Loan[]>{
    return this.http.get<Loan[]>(this.URLHttp+'/rest/loan/api/maxEndDate?date='+date);
  }

  saveLoan(simpleLoan: SimpleLoan): Observable<boolean>{
    return this.http.post<boolean>(this.URLHttp+'/rest/loan/api/addLoan', simpleLoan);
  }

  closeLoan(simpleLoan: SimpleLoan): Observable<Boolean>{
    return this.http.post<Boolean>(this.URLHttp+'/rest/loan/api/closeLoan', simpleLoan);
}
}
