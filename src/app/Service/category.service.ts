import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_BASE_PATH, CATEGORY } from '../app.constant';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  apiPath = API_BASE_PATH+CATEGORY
  constructor(private http:HttpClient) { }

  fetchCategory(obj:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"get_category";
    return this.http.post(url,obj,{
      headers:httpHeaders,
      observe:'response'
    })
  }
}
