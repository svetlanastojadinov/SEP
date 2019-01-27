import { Component, OnInit, Input, ViewChild } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { NgForm } from '@angular/forms';
import { PaymentCardService } from '../payment_card_service/payment.card.service';
import {SharedService} from './../shared_service/shared.service';

@Component({
    selector: 'app-bank-card-info',
    templateUrl: './bank-card-info.html', 
    styleUrls: ['./bank-card-info.css']
  })
  export class BankCardInfoComponent implements OnInit {
    @ViewChild('f') creditCardForm: NgForm;
    creditCard = {
    pan: '',
    securityCode: '',
    cardHolderName: '',
    expirationDate: {}
  }
  url=undefined;
  private transactions: any=[];
  private transaction: any={};
  
  constructor(
    private sharedService: SharedService,
    private activatedRoute:ActivatedRoute,
    private router:Router,
    private paymentCardService: PaymentCardService) { }
   ngOnInit() {


    this.activatedRoute.params.subscribe(params => {
      this.url=params['url'];
    });
    this.sharedService.getTransactions().subscribe(
      (data:any)=> {
        this.transactions=data;
      });
    this.sharedService.getOneTransaction(this.url).subscribe(
      (data:any)=>{
        this.transaction=data;
      });
  }
  confirm( ){

    this.creditCard.pan = this.creditCardForm.value.PAN;
    this.creditCard.securityCode = this.creditCardForm.value.cvv;
    this.creditCard.cardHolderName = this.creditCardForm.value.cardHolderName;
    var dateString = this.creditCardForm.value.exy+'-'+this.creditCardForm.value.exm+'-01T00:00:00' 
    this.creditCard.expirationDate = new Date(dateString);

    console.log(this.creditCard);

    //id transakcije je poslat 

    this.paymentCardService.completePaymentCard(this.url, this.creditCard).subscribe( //123 je id nadjene transakcije
      (data:any) => {
        const url: string = data.redirect_url;
        window.location.href = url; 
      });
  }
  
  backToCart(){
    window.location.href="http://localhost:4200";
  }
  
}
  