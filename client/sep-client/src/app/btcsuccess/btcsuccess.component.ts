import { Component, OnInit } from '@angular/core';
import {PaymentBTCService} from './../payment_btc_service/payment.btc.service';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-btcsuccess',
  templateUrl: './btcsuccess.component.html', 
  styleUrls: ['./btcsuccess.component.css']
})
export class BTCsuccessComponent implements OnInit {

  constructor(private paymentService:PaymentBTCService,
    private activatedRoute:ActivatedRoute,private router:Router) { }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      if(params['paymentId'] !== undefined && params['PayerID'] !== undefined) {
        console.log(params['paymentId']);
        console.log(params['PayerID']);
        this.paymentService.completePaymentBTC(params['paymentId'],params['PayerID']).subscribe(
          (data:any)=>{
            console.log(data);
          });
      }
  })
  }

  backToCart(){
    this.router.navigate(['homepage']);
  }

}
