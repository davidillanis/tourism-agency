import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SaleServiceComponent } from './components/sale-service/sale-service.component';
import { SalePackageComponent } from './components/sale-package/sale-package.component';
import { MainComponent } from './components/main/main.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ModalServiceComponent } from './services/external/modal-service/modal-service.component';
import { EmployeComponent } from './components/employee/employee.component';

const appRoutes:Routes=[
  {path:'home', component:MainComponent},
  {path:'', component:MainComponent},
  {path:'package', component:SalePackageComponent},
  {path:'service', component:SaleServiceComponent},
  {path:'employee', component:EmployeComponent},
  {path:'**', component:MainComponent}
]


@NgModule({
  declarations: [
    AppComponent,
    SaleServiceComponent,
    SalePackageComponent,
    MainComponent,
    ModalServiceComponent,
    EmployeComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
