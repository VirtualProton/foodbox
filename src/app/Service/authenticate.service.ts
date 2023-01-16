import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_BASE_PATH, OTP } from '../app.constant';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
  accessToken:any
  apiPath = API_BASE_PATH+OTP
  constructor(private http:HttpClient) { }


  sendOtp(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"send";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }


  verifyOtp(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"verify";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }


  isAuthenticated(){
    if(localStorage.getItem('token') === null){
      return false;
    }else{
      return true;
    }
  }
}
