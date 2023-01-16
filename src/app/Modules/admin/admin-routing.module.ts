import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from 'src/app/Component/admin/dashboard/dashboard.component';
import { DishesComponent } from 'src/app/Component/admin/dishes/dishes.component';
import { OrderComponent } from 'src/app/Component/admin/order/order.component';
import { ResetComponent } from 'src/app/Component/admin/reset/reset.component';

const routes: Routes = [
  {
    path: "",
    component:DashboardComponent,
    children:[{path:"dishes",component:DishesComponent},
              {path:"orders",component:OrderComponent},
              {path:"reset_password",component:ResetComponent},
              {path:'**',redirectTo:"/dashboard/dishes"},
              {path:"",redirectTo:"/dashboard/dishes",pathMatch:'full'}]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
