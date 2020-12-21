import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { error } from 'protractor';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { Book } from '../models/book';
import { Category } from '../models/category';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book-page',
  templateUrl: './book-page.component.html',
  styleUrls: ['./book-page.component.scss']
})
export class BookPageComponent implements OnInit {
  public categories: Category[] =[];
  public searchBookResult: Book[]=[];

  livreForm : FormGroup;
  searchForm : FormGroup;

  isCreate = true;
  bookUpdate : Book;

  constructor(private bookService:BookService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.loadCategories();
    this.initForm();
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

  initForm () {
    this.livreForm = this.formBuilder.group({
      titre:'',
      isbn:'',
      date_sortie:'',
      auteur:'',
      category:'',
      totalexamplaire:'',
    });

    this.searchForm = this.formBuilder.group({
      typeSearchBy:'isbn',
      searchValue:'',
    });
  }

  onSubmitForm(){
    const formValue = this.livreForm.value;
    const newBook = new Book();

    newBook.title=formValue['titre'];
    newBook.isbn=formValue['isbn'];
    newBook.releaseDate=formValue['date_sortie'];
    newBook.totalExamplaries=formValue['totalexamplaire'];
    newBook.author=formValue['auteur'];
    newBook.category=this.getCategory(formValue['category']);

    this.bookService.saveBook(newBook).subscribe(
      (result:Book)=>{
        if(result.idbook){
          console.log("new book has been added");
          
          this.onSubmitFormSearch();
          this.livreForm.reset();
          this.closeDialog();
        }
      },
      error => {
        console.log(error);
        if(error.status==409){
          window.alert("le livre existe deja dans la base !");
        }
      }
    );
  }

  onSubmitFormUpdate(){
    const formValue = this.livreForm.value;
    const newBook = new Book();

    newBook.idbook= this.bookUpdate.idbook;

    newBook.title=formValue['titre'];
    newBook.isbn=formValue['isbn'];
    newBook.releaseDate=formValue['date_sortie'];
    newBook.totalExamplaries=formValue['totalexamplaire'];
    newBook.author=formValue['auteur'];
    newBook.category=this.getCategory(formValue['category']);

    this.bookService.updateBook(newBook).subscribe(
      (result:Book)=>{
        if(result.idbook){
          console.log("updated");
          
          this.livreForm.reset();
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

  openAddBookDialog(book?:Book){
    console.log(book);

    if (book !=null){
      this.isCreate=false;
      this.bookUpdate=book;
      this.livreForm = this.formBuilder.group({
        titre:  book.title,
        isbn:   book.isbn,
        date_sortie:  book.releaseDate,
        auteur:       book.author,
        category:     book.category.label,
        totalexamplaire:  book.totalExamplaries
      });
    } else {
      this.isCreate=true;
    }

    var addBookDialog:any = <any>document.getElementById('formDialog');  

    if(typeof addBookDialog.showModal === "function"){
        addBookDialog.showModal();
    } else {
      console.log("error navigateur !");
    }

  }

  closeDialog(){
    this.livreForm.reset();
    var addBookDialog:any = <any>document.getElementById('formDialog');
    addBookDialog.close();
  }

  searchBooksByType(){
    if(!this.searchForm.valid || this.searchForm.value['searchValue'] ===""){
      console.log("ERREUR searchform ");
    } else {
      console.log(this.searchForm.value);
      this.searchBookResult.length=0;
      if(this.searchForm.value['typeSearchBy'] ==="isbn"){
        this.bookService.searchBookByIsbn(this.searchForm.value['searchValue']).subscribe(
          result=>{
            if (result && result != null){
              this.searchBookResult=this.searchBookResult.concat(result);
              return;
            }
          },
          error => {
            console.log(error);
          }
        );
      } else if (this.searchForm.value['typeSearchBy'] ==="title"){
        this.bookService.searchBookByTitle(this.searchForm.value['searchValue']).subscribe(
          result=>{
            if (result && result != null){
              this.searchBookResult=this.searchBookResult.concat(result);
              console.log(this.searchBookResult);
            }
          },
          error => {
            console.log(error);
          }
        );
      }
    
    }
  }

  deleteBook(book:Book){
    this.bookService.deletBook(book).subscribe(
      result => {
        for(var i = 0; i< this.searchBookResult.length; i++){
          if (this.searchBookResult[i].idbook === book.idbook) {
            this.searchBookResult.splice(i,1);
          }
        }

        console.log(result);
      }, error =>{
        console.log(error);
      }
    );
  }

  onSubmitFormSearch(){    
    this.searchBooksByType();
  }

  getCategory(label: string){    
    for (let element of this.categories){
      if (label = element.label){
        return element;
      }
    }
    return null;
  }

}
