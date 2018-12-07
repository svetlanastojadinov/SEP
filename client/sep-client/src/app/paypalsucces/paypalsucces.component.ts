import { Component, OnInit } from '@angular/core';
import {PaymentService} from './../payment_service/payment.service';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-paypalsucces',
  templateUrl: './paypalsucces.component.html', 
  styleUrls: ['./paypalsucces.component.css']
})
export class PaypalsuccesComponent implements OnInit {

  constructor(private paymentService:PaymentService,
    private activatedRoute:ActivatedRoute,private router:Router) { }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      if(params['paymentId'] !== undefined && params['PayerID'] !== undefined) {
        console.log(params['paymentId']);
        console.log(params['PayerID']);
        this.paymentService.completePayment(params['paymentId'],params['PayerID']).subscribe(
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
