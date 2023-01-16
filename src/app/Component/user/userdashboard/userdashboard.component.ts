import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthGuard } from 'src/app/Common/guard/auth.guard';
import { UserService } from 'src/app/Service/user.service';

@Component({
  selector: 'app-userdashboard',
  templateUrl: './userdashboard.component.html',
  styleUrls: ['./userdashboard.component.scss']
})
export class UserdashboardComponent implements OnInit {
  
  public profile_img:any;
  constructor(private router: Router,
    private authGuard:AuthGuard, private userService:UserService,) { }

  ngOnInit(): void {
    this.getUserDetails()
  }
  getUserDetails(){
    if(this.authGuard.canActivate()){
      let data ={
        userName: localStorage.getItem('userName')
      }
      this.userService.fetchUserDetails(data).subscribe(
        (res)=>{
          if(res.status==200){
            let userDetails = res.body.data
            this.profile_img = userDetails[0].profileImgUrl
          }
        }
      )
    }
  }
  logout(){
    localStorage.removeItem("token");
    this.router.navigateByUrl('/user')
  }
}
