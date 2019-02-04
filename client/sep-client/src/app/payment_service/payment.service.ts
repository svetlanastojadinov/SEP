import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }

  makePayment(order: any) {
    return this.http.post('api/paypal/make/payment', order);
  }

  completePayment(paymentId, payerId) {
    return this.http.post('api/paypal/complete/payment?paymentId=' + paymentId + '&payerId=' + payerId, {});
  }
}
