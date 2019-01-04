import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentCardService {

  constructor(private http:HttpClient) { }

  completePaymentCard(paymentId,creditCard) {
    console.log("ovde smo");
    return this.http.post('/api/transactions/' + paymentId , creditCard);
  }


}
