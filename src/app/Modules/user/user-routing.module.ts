import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderHistoryComponent } from 'src/app/Component/user/order-history/order-history.component';
import { UserProfileComponent } from 'src/app/Component/user/user-profile/user-profile.component';
import { UserdashboardComponent } from 'src/app/Component/user/userdashboard/userdashboard.component';

const routes: Routes = [{
  path:"",
  component:UserdashboardComponent,
  children:[{path:"profile",component:UserProfileComponent},
            {path:"orderhistory",component:OrderHistoryComponent},
            {path:'**',redirectTo:"orderhistory",pathMatch:'full'},
            {path:"",redirectTo:"orderhistory",pathMatch:'full'}]
}]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
