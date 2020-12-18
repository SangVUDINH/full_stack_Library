import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  loadCategories(): Observable<Category[]>{
    let headers = new HttpHeaders();
    headers.append('content-type', 'application/json');
    headers.append('accept', 'application/json');
    //headers.append('Access-Control-Allow-Origin','*',);   

    return this.http.get<Category[]>('http://localhost:8080/rest/category/api/all',{headers: headers});
  }



}
