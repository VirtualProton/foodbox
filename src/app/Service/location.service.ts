import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { API_BASE_PATH, LOCATION } from '../app.constant';

@Injectable({
  providedIn: 'root'
})


export class LocationService {
  
  selectedLocation = new Subject<any>();

  apiPath = API_BASE_PATH+LOCATION;
  constructor( private http:HttpClient) { }
  fetchAllLocation():Observable<any>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    let url = this.apiPath+"get_location";
    return this.http.get(url,{
      headers:httpHeaders,
      observe:'response'
    })
  }
}
