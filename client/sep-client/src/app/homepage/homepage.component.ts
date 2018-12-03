import { Component, OnInit } from '@angular/core';
import {SharedService} from './../shared_service/shared.service';
import { DatePipe } from '@angular/common';

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

  constructor(private sharedService:SharedService, private datePipe: DatePipe) { }

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
    this.order.paymentType=this.selectedPaymentMode;
    this.order.price=1000.00;
    this.order.dateOfTransaction = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    this.order.payerUsername = "Buduci logovani korisnik/ci";
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

    //console.log(this.order);
    this.sharedService.buy(this.order).subscribe(
      (data:any)=> {
        console.log("VRATIO SEEEE")
      }
    )
    //this.order.
   // console.log(this.selectedPaymentMode);
   // console.log(this.indicator);  // magazine or article
    //alert("TREBA SADA IMPLEMENTIRATI")
  }

}
