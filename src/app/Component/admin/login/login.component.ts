import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminAuthService } from 'src/app/Service/admin-auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public errMsg:any;
  public loginForm:any
  constructor( private _fb: FormBuilder,
                private adminAuthService:AdminAuthService,
                private router: Router) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(){
    this.loginForm = this._fb.group({
      userName:[null, Validators.required],
      password:[null, Validators.required]
    })
  }
  validateLogin(){
    
    this.adminAuthService.authenticate(this.loginForm.value).subscribe(
      (res:any)=>{
       let isLogin = res.body.islogin 
       if(isLogin){
        localStorage.setItem('isLogin',isLogin)
        this.adminAuthService.fecthStoreDetails(this.loginForm.value).subscribe(
          (res)=>{
           let storeDetail = res.body.data;
           localStorage.setItem('storeLocation',storeDetail[0].location);
           localStorage.setItem('storeUserName',storeDetail[0].userName);
          }
        )
        this.router.navigateByUrl("/dashboard")
       }else{
        this.errMsg = "Invalid Username or Password"
       }
      }
    )
  }
}
