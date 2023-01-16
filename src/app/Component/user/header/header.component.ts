import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { SearchService } from 'src/app/Service/search.service';
import { CartService } from 'src/app/Service/cart.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LocationService } from 'src/app/Service/location.service';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthenticateService } from 'src/app/Service/authenticate.service';
import { AuthGuard } from 'src/app/Common/guard/auth.guard';
import { UserService } from 'src/app/Service/user.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  public userDetails:any=[];
  public isLoggedIn:any;
  public isNewUser:any = true;
  public loginForm:any;
  public otpForm:any
  public selectedLocation:any = "hyderabad";
  public allLocation:any =[];
  cartItemCount: any;
  cartItem: any;
  subTotal:any =0;
  //public textSearch:any;
  @ViewChild('myModel', { read: TemplateRef })
  myModel!: TemplateRef<any>
  @ViewChild('login', { read: TemplateRef })
  loginModel!: TemplateRef<any>
  @ViewChild('cart', { read: TemplateRef })
  cartModel!: TemplateRef<any>
  productModel: any;
  constructor(
    private searchService :SearchService,
    private modelSevice: NgbModal,
    private cartService:CartService,
    private locationService:LocationService,
    private _fb: FormBuilder,
    private authenticateService:AuthenticateService,
    private authGuard:AuthGuard,
    private userService:UserService,
    private router: Router

  ) {
      this.cartService.cartSubject.subscribe((data:any)=>{
      this.cartItemCount= data.length;
      this.cartItemFun(); 
     })
   }
   
  ngOnInit(): void {
    this.isAuth();
    this.getUserDetails();
    this.initilizeloginForm();
    this.initilizeOtpForm();
    this.getLocation();
    this.cartItemFun();
  }
  initilizeloginForm(){
    this.loginForm = this._fb.group({
      phoneNo:[null, Validators.required]
    })
   }
   initilizeOtpForm(){
    this.otpForm = this._fb.group({
      otp:[null, Validators.required]
    })
   }
  getVal(val:any){
    this.searchService.searchSubject.next(val);
  } 

  cartItemFun(){
    this.subTotal =0;
    this.cartItem= JSON.parse(localStorage.getItem('localCart') as string);
    this.cartItemCount = this.cartItem.length
    for(let i =0; i<this.cartItemCount;i++){
      this.subTotal = this.subTotal+this.cartItem[i].quantity * this.cartItem[i].price
    }
  }

  SubTotal(){
    this.subTotal =0;
    for(let i =0; i<this.cartItemCount;i++){
      this.subTotal = this.subTotal+this.cartItem[i].quantity * this.cartItem[i].price
    }
  }

  openModel(ref: any) {
    this.modelSevice.open(ref, {
      size: 'md',
      backdrop: 'static',
      keyboard: false,
      windowClass: 'custom-model'
    });
  }

  opencart() {
    if(this.isLoggedIn){
      this.openModel(this.cartModel)
    }else{
      this.openModel(this.loginModel)
    }
    // this.openModel(ref)
  }
  openDashboard(){
    if(!this.isLoggedIn){
      this.openModel(this.loginModel)
    }else{
      this.router.navigateByUrl("user/dashboard")
    }
  }

  openCheckout(){
    if(this.isLoggedIn){
      // console.log("here")
      this.closeModel();
      this.router.navigateByUrl("/checkout")
    }
  }
  closeModel() {
    this.modelSevice.dismissAll();
  }

  resetForm(){
    this.loginForm.reset()
    this.otpForm.reset()
  }

  increaseQuantity(product:any){
    for(let i =0; i<this.cartItemCount;i++){
      if(this.cartItem[i].id == product.id){
        if(this.cartItem[i].quantity<6){
          this.cartItem[i].quantity++;
          localStorage.setItem('localCart', JSON.stringify(this.cartItem))
          this.SubTotal();
        }
      }
    }
  }
  decreaseQuantity(product:any){
    for(let i =0; i<this.cartItemCount;i++){
      if(this.cartItem[i].id == product.id){
        if(this.cartItem[i].quantity>1){
          this.cartItem[i].quantity--;
          localStorage.setItem('localCart', JSON.stringify(this.cartItem))
          this.SubTotal();
        }else if(this.cartItem[i].quantity==1){
          this.cartItem.splice(i,1);
          localStorage.setItem('localCart', JSON.stringify(this.cartItem))
          this.cartItemFun()
        }
      }
    }
  }
  removeItem(product:any){
    for(let i =0; i<this.cartItemCount;i++){
      if(this.cartItem[i].id == product.id){
        this.cartItem.splice(i,1);
          localStorage.setItem('localCart', JSON.stringify(this.cartItem))
          this.cartItemFun()
      }
    }
    if(this.cartItemCount == 0){
      localStorage.clear();
    }
  }

  getLocation(){
    this.locationService.fetchAllLocation().subscribe(
      (res)=>{
        if(res.status == 200){
          this.allLocation = res.body.data
        }
      }
    )
  }

  onSelectLocation(){
    this.locationService.selectedLocation.next(this.selectedLocation);
    localStorage.setItem('selectedLocation', this.selectedLocation);
  }

  loginform(refModal:any){
    this.openModel(refModal);
  }
  sendOtp(refModal:any){
    console.log(this.loginForm.value.phoneNo)
    this.closeModel();
        this.openModel(refModal);
    this.authenticateService.sendOtp({phoneNo:"+91"+this.loginForm.value.phoneNo}).subscribe(
      (res)=>{
        if(res.status==200){
          this.closeModel();
          this.openModel(refModal);
        }
      }
    )
  }

  verifyOtp(){
    let data={
      userName:this.loginForm.value.phoneNo,
      password:this.otpForm.value.otp
    }
    console.log(data)
    this.authenticateService.verifyOtp(data).subscribe(
      (res)=>{
        if(res.status == 200){
          let token = res.body.data
          this.closeModel();
          this.resetForm();
          this.isNewUser = token.newUser;
          localStorage.setItem('token',token.key);
          localStorage.setItem('userName',data.userName);
          this.isAuth(); 
          this.getUserDetails();
          if(this.isNewUser){
            console.log("new User");
          }
        }else if(res.status == 401){
          console.log(res.body.error)
        }
      }
    )
  }

  getUserDetails(){
    if(this.isLoggedIn){
      let data ={
        userName: localStorage.getItem('userName')
      }
      this.userService.fetchUserDetails(data).subscribe(
        (res)=>{
          if(res.status==200){
            this.userDetails = res.body.data
            this.selectedLocation = this.userDetails[0].city
          }
        }
      )
    }
  }
  get f1(){
    return this.loginForm.controls
  }
  get f2(){
    return this.otpForm.controls
  }


  isAuth(){
    this.isLoggedIn =this.authGuard.canActivate();
  }
}
