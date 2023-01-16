import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_BASE_PATH, ORDER } from '../app.constant';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  auth_token = localStorage.getItem("token")
  apiPath = API_BASE_PATH+ORDER;
  constructor(private http:HttpClient) { }

  fetchUserOrder(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization': "Bearer "+this.auth_token
    });
    let url = this.apiPath+"user_orders";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }

  fetchLocationOrder(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"get_orders";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }

  addOrder(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json',
      'Authorization': "Bearer "+this.auth_token
    });
    let url = this.apiPath+"add_orders";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }
}
