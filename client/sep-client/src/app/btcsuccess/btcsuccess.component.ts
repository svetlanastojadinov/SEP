import { Component, OnInit } from '@angular/core';
import {PaymentBTCService} from './../payment_btc_service/payment.btc.service';
import {Router,ActivatedRoute} from '@angular/router';
import { SharedService } from '../shared_service/shared.service';

@Component({
  selector: 'app-btcsuccess',
  templateUrl: './btcsuccess.component.html', 
  styleUrls: ['./btcsuccess.component.css']
})
export class BTCsuccessComponent implements OnInit {

  constructor(private paymentService:PaymentBTCService,
    private activatedRoute:ActivatedRoute,private router:Router,private sharedService: SharedService) { }
    private url:undefined;
    private orders: any=[];
    private order: any={};
  

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.url=params['url'];
      console.log(this.url);
      this.sharedService.getOneOrder(this.url).subscribe((data)=>{
        this.order=data;
        if(this.order!=null){
          this.order.executed=true;
          console.log(this.order);
          this.paymentService.completePaymentBTC( this.order.merchantOrderId,this.order.buyerUsername).subscribe((data:any)=>{
            console.log(data); 
          });//paymentId,payerId)
        }
      })
    });

    
  }

  backToCart(){
    this.router.navigate(['available']);
  }   

}
