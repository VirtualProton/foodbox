import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_BASE_PATH, USER } from '../app.constant';
import { AuthenticateService } from './authenticate.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  auth_token = localStorage.getItem("token")
  apiPath = API_BASE_PATH+USER
  constructor(private http:HttpClient,private authenticateService:AuthenticateService) { }

  fetchUserDetails(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization': "Bearer "+this.auth_token
    });
    let url = this.apiPath+"get_userdetails";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }

  addUserDetails(obj:any){
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization': "Bearer "+this.auth_token
    });
    let url = this.apiPath+"add_userdetails";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }

}
