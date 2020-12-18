import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookPageComponent } from './book-page/book-page.component';
import { LandingComponent } from './landing/landing.component';
import { MenuComponent } from './menu/menu.component';

const routes: Routes = [
  { path:"books", component:BookPageComponent},
  { path:"", component:LandingComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
