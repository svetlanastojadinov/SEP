import { Component, OnInit } from '@angular/core';
import {PaymentCardService} from './../payment_card_service/payment.card.service';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
    selector: 'app-bank-card-info',
    templateUrl: './bank-card-info.html', 
    styleUrls: ['./bank-card-info.css']
  })
  export class BankCardInfoComponent implements OnInit {
  
    constructor(private paymentService:PaymentCardService,
      private activatedRoute:ActivatedRoute,private router:Router) { }
  
    ngOnInit() {
    
    }
  
    backToCart(){
      this.router.navigate(['homepage']);
    }
  
  }
  