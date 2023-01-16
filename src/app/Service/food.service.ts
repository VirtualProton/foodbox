import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_BASE_PATH, FOOD } from '../app.constant';
@Injectable({
  providedIn: 'root'
})
export class FoodService {
  apiPath = API_BASE_PATH+FOOD
  constructor(private http:HttpClient) { }

  fetchFood(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"get_food";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }

  addFood(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"add_food";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }

  updateFood(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"update_food";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }
  deleteFood(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"delete_food";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }
}
