import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgImageSliderModule } from 'ng-image-slider';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './Component/user/home/home.component';
import { HeaderComponent } from './Component/user/header/header.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CarouselComponent } from './Component/user/carousel/carousel.component';
import { MenuComponent } from './Component/user/menu/menu.component';
import { CategoryComponent } from './Component/user/category/category.component';
import { UiSwitchModule } from 'ngx-ui-switch';
import { BodyComponent } from './Component/user/body/body.component';
import { HttpClientModule } from '@angular/common/http';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
// import { NgRatingBarModule } from 'ng-rating-bar';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { DashboardComponent } from './Component/admin/dashboard/dashboard.component';
import { NavbarComponent } from './Component/admin/navbar/navbar.component';
import { DishesComponent } from './Component/admin/dishes/dishes.component';
import { OrderComponent } from './Component/admin/order/order.component';
import { ResetComponent } from './Component/admin/reset/reset.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserdashboardComponent } from './Component/user/userdashboard/userdashboard.component';
import { CheckoutComponent } from './Component/user/checkout/checkout.component';
import { OrderHistoryComponent } from './Component/user/order-history/order-history.component';
import { UserProfileComponent } from './Component/user/user-profile/user-profile.component';
import { LoginComponent } from './Component/admin/login/login.component';
import { DatePipe} from '@angular/common';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    CarouselComponent,
    MenuComponent,
    CategoryComponent,
    BodyComponent,
    DashboardComponent,
    NavbarComponent,
    DishesComponent,
    OrderComponent,
    ResetComponent,
    UserdashboardComponent,
    CheckoutComponent,
    OrderHistoryComponent,
    UserProfileComponent,
    LoginComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    NgImageSliderModule,
    UiSwitchModule,
    NgbRatingModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    FormsModule, 
    ReactiveFormsModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
