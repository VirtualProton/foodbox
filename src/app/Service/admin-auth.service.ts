import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_BASE_PATH, STORE } from '../app.constant';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthService {
  apiPath =API_BASE_PATH+STORE;

  constructor(private http:HttpClient) { }

  authenticate(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"auth";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }

  fecthStoreDetails(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"getDetails";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }
}
