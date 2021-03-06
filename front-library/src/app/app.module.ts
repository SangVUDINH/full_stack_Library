import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookPageComponent } from './book-page/book-page.component';
import { CustomerPageComponent } from './customer-page/customer-page.component';
import { LoanPageComponent } from './loan-page/loan-page.component';
import { HttpClientModule } from '@angular/common/http';
import { MenuComponent } from './menu/menu.component';
import { LandingComponent } from './landing/landing.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BookService } from './services/book.service';
import { CustomerService } from './services/customer.service';
import { LoanService } from './services/loan.service';

@NgModule({
  declarations: [
    AppComponent,
    BookPageComponent,
    CustomerPageComponent,
    LoanPageComponent,
    MenuComponent,
    LandingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
    
  ],
  providers: [BookService, CustomerService, LoanService],
  bootstrap: [AppComponent]
})
export class AppModule { }
