import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentBTCService {

  constructor(private http:HttpClient) { }

  makePaymentBTC(order) {
    return this.http.post('api/bitcoin', order);
  }

  completePaymentBTC(paymentId, payerId) {
 //   console.log(payerId);
  //  return this.http.post('api/paypal/complete/payment?paymentId=' + paymentId + '&payerId=' + payerId , {});
  }
}
