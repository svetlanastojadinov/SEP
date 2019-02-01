import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor(private router: Router, private http: HttpClient) { }

  getTransactions(){
    return this.http.get("/api/transactions");
  }

  getOneTransaction(id:number){
    return this.http.get("/api/transactions/"+id);
  }
}
