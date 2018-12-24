import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentBTCService {

  constructor(private http:HttpClient) { }

  makePaymentBTC(sum, order) {
    return this.http.post('api/bitcoin/make/payment?sum='+sum, {order:order});
  }

  completePaymentBTC(paymentId, payerId) {
  // console.log("***********************"+payerId);
    return this.http.post('api/bitcoin/complete/payment?paymentId=' + paymentId + '&payerId=' + payerId , {});
  }
}
