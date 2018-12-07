import { Component, OnInit } from '@angular/core';
import {SharedService} from './../shared_service/shared.service';
import { DatePipe } from '@angular/common';
import {PaymentService} from './../payment_service/payment.service';
import {Router,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
  providers: [DatePipe]
})
export class HomepageComponent implements OnInit {

  private magazines: any = [];
  private articles: any = [];
  private paymentMethods: any = [];
  private indicator: string = "";
  private selectedPaymentMode: string = "PAYPAL";
  private order: any = {};
  private selectedId: number = 0;

  constructor(
    private sharedService:SharedService, 
    private datePipe: DatePipe,
    private paymentService:PaymentService,
    private router:Router 
  ) { }

  ngOnInit() {
    this.sharedService.getMagazines().subscribe(
      (data:any)=> {
        this.magazines=data;
      });
    this.sharedService.getArticles().subscribe(
      (data:any)=> {
        this.articles=data;
      });
    this.sharedService.getPaymentMethos().subscribe(
      (data:any)=> {
        this.paymentMethods=data;
      });
  }

  setIndicator(indicator: string, id:number){
    this.indicator=indicator;
    this.selectedId=id;
  }

  choose(type:string){
    this.selectedPaymentMode=type;
  }

  buy() {
    
    if(this.indicator === 'magazine'){
      this.sharedService.getOneMagazine(this.selectedId).subscribe(
        (data:any)=>{
          this.order.magazine=data;
          this.order.article=null;
        })
    }
    if(this.indicator === 'article'){
      this.sharedService.getOneArticle(this.selectedId).subscribe(
        (data:any)=>{
          this.order.magazine=null;
          this.order.article=data;
        })
    }

    if(this.selectedPaymentMode === "PAYPAL") {
      this.paymentService.makePayment("50",this.order).subscribe(
        (data:any) => {
          const url: string = data.redirect_url;
          window.location.href = url;
        }
      );
    }
  }

}
