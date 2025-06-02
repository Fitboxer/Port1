import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CalcComponent } from './calc/calc.component';
import { ExcelComponent } from './excel/excel.component';
import { CreateUserComponent } from './create-user/create-user.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'calc', component: CalcComponent },
  { path: 'excel', component: ExcelComponent },
  { path: 'create-user', component: CreateUserComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
