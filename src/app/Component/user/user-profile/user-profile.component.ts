import { Component, OnInit } from '@angular/core';
import { AuthGuard } from 'src/app/Common/guard/auth.guard';
import { UserService } from 'src/app/Service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  public userDetails:any =[];
  constructor(private authGuard:AuthGuard, private userService:UserService) { }

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
            this.userDetails = res.body.data
          }
        }
      )
    }
  }

}
