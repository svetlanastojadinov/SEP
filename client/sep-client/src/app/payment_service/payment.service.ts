import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http:HttpClient) { }

  makePayment(sum, order:any) {
    console.log(order)
    return this.http.post('api/paypal/make/payment?sum='+sum, {order:order});
  }

  completePayment(paymentId, payerId) {
    console.log(payerId);
    return this.http.post('api/paypal/complete/payment?paymentId=' + paymentId + '&payerId=' + payerId , {});
  }
}
