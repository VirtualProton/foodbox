import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_BASE_PATH, FILE } from '../app.constant';

@Injectable({
  providedIn: 'root'
})
export class UploadfileService {
  private apiPath =API_BASE_PATH+FILE;
  constructor(private http:HttpClient) { }

  uploadFile(file:any):Observable<any>{
    let httpHeaders = new HttpHeaders({
    });
    let url = this.apiPath+"upload";
    return this.http.post(url,file,{
      headers:httpHeaders,
      observe:'response'
    })
  }
}
