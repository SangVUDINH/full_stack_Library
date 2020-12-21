import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../models/book';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  
   URLHttp ="http://localhost:8080";

  constructor(private http: HttpClient) { }

  loadCategories(): Observable<Category[]>{
    let headers = new HttpHeaders();
    headers.append('content-type', 'application/json');
    headers.append('accept', 'application/json');  

    return this.http.get<Category[]>(this.URLHttp+'/rest/category/api/all',{headers: headers});
  }

  saveBook(book: Book){
    return this.http.post<Book>(this.URLHttp+'/rest/book/api/addBook',book);
  }

  searchBookByIsbn(isbn: string){
    return this.http.get<Book>(this.URLHttp+'/rest/book/api/searchByIsbn?isbn='+isbn);
  }

  searchBookByTitle(title: string){
    return this.http.get<Book>(this.URLHttp+'/rest/book/api/searchByTitle?title='+title);
  }

  deletBook(book:Book): Observable<string>{
    return this.http.delete<string>(this.URLHttp+'/rest/book/api/deleteBook/'+book.idbook);
  }

  updateBook(newBook: Book): Observable<Book> {
    return this.http.put<Book>(this.URLHttp+'/rest/book/api/updateBook', newBook);
  }

}
