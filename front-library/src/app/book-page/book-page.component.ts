import { Component, OnInit } from '@angular/core';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { Category } from '../models/category';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book-page',
  templateUrl: './book-page.component.html',
  styleUrls: ['./book-page.component.scss']
})
export class BookPageComponent implements OnInit {
  public categories: Category[] =[{"code":"10","label":"test"}];

  constructor(private bookService:BookService) { }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories() {
    this.bookService.loadCategories().subscribe(
      (result: Category[])=>{

          console.log(result);
          console.log("result");
          this.categories.push.apply(this.categories, result);

      },
      error =>{
        console.log(error);
      }
    );
  }

}
