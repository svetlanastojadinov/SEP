import { Component, OnInit, Input, ViewChild } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { NgForm } from '@angular/forms';

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
  };
  constructor(private activatedRoute:ActivatedRoute,private router:Router) { }
   ngOnInit() {
  }
  confirm( ){
    console.log("ppplacanjee");
    this.creditCard.pan = this.creditCardForm.value.PAN;
    this.creditCard.securityCode = this.creditCardForm.value.cvv;
    this.creditCard.cardHolderName = this.creditCardForm.value.cardHolderName;
    var dateString = this.creditCardForm.value.exy+'-'+this.creditCardForm.value.exm+'-01T00:00:00' 
    this.creditCard.expirationDate = new Date(dateString);

    console.log(this.creditCard);
  }
  
  backToCart(){
  //  this.router.navigate(['homepage']);
  }
  
}
  