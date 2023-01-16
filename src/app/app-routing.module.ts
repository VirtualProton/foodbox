import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Component/admin/login/login.component';
import { CheckoutComponent } from './Component/user/checkout/checkout.component';
// import { DashboardComponent } from './Component/admin/dashboard/dashboard.component';
import { HomeComponent } from './Component/user/home/home.component';
import { UserdashboardComponent } from './Component/user/userdashboard/userdashboard.component';

const routes: Routes = [
  {
    path:'user',
    component:HomeComponent
  
  },
  {
    path:'dashboard',
    loadChildren:()=>import('./Modules/admin/admin.module').then((m)=>m.AdminModule)
  },
  {
    path:'admin',
    component:LoginComponent
  },
  {
    path:'user/dashboard',
    loadChildren:()=>import('./Modules/user/user-routing.module').then((m)=>m.UserRoutingModule)
  },
  // {
  //   path:'user/dashboard',
  //   component:UserdashboardComponent
  // },
  {
    path:'checkout',
    component:CheckoutComponent
  },
  {
    path:"**",
    redirectTo:'/user'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
