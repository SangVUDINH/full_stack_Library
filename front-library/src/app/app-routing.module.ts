import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookPageComponent } from './book-page/book-page.component';
import { CustomerPageComponent } from './customer-page/customer-page.component';
import { LandingComponent } from './landing/landing.component';
import { LoanPageComponent } from './loan-page/loan-page.component';


const routes: Routes = [
  { path:"books", component:BookPageComponent},
  { path:"customers", component:CustomerPageComponent},
  { path:"loans", component:LoanPageComponent},
  { path:"", component:LandingComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
