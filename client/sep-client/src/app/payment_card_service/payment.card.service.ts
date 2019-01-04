import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentCardService {

  constructor(private http:HttpClient) { }

  makePaymentCard(order) {
    return this.http.post('api/card/make/payment',order);
  }
  completePaymentCard(paymentId, payerId) {
    // console.log("***********************"+payerId);
      return this.http.post('api/card/complete/payment?paymentId=' + paymentId + '&payerId=' + payerId , {});
    }

}
