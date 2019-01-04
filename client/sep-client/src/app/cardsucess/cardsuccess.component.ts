import { Component, OnInit } from '@angular/core';
import {PaymentBTCService} from './../payment_btc_service/payment.btc.service';
import {Router,ActivatedRoute} from '@angular/router';
import { PaymentCardService } from '../payment_card_service/payment.card.service';

@Component({
  selector: 'app-cardsuccess',
  templateUrl: './cardsuccess.component.html', 
  styleUrls: ['./cardsuccess.component.css']
})
export class CardSuccessComponent implements OnInit {

  constructor(private paymentService:PaymentCardService,
    private activatedRoute:ActivatedRoute,private router:Router) { }
 
  ngOnInit() {
    console.log("zavrseno placanje");
  }

  backToCart(){
    this.router.navigate(['homepage']);
  }

}
