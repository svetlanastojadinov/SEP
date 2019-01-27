import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { PaymentCardService } from 'src/app/payment_card_service/payment.card.service';

@Component({
  selector: 'app-cardsuccess',
  templateUrl: './cardsuccess.component.html', 
  styleUrls: ['./cardsuccess.component.css']
})
export class CardSuccessComponent implements OnInit {

  constructor(private paymentService:PaymentCardService,
    private activatedRoute:ActivatedRoute,private router:Router) { }
 
  ngOnInit() {
    this.paymentService.completePaymentCard(1,"payerId").subscribe(
      (data:any)=>{
        console.log(data); 
      });//paymentId,payerId
  }

  backToCart(){
    this.router.navigate(['homepage']);
  }

}
