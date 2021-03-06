import { Component, OnInit } from '@angular/core';
import { SharedService } from './../shared_service/shared.service';
import { DatePipe } from '@angular/common';
import { PaymentService } from './../payment_service/payment.service';
import { PaymentBTCService } from './../payment_btc_service/payment.btc.service';
import { PaymentCardService } from './../payment_card_service/payment.card.service';
import { Router, ActivatedRoute } from '@angular/router';

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

  private idForDelete: any = '';
  private indicatorForDelete: string = '';

  private loadingArticles: boolean = true;
  private loadingMagazines: boolean = true;

  private removingArticle: boolean = false;
  private removingMagazine: boolean = false;

  constructor(
    private sharedService: SharedService,
    private datePipe: DatePipe,
    private paymentService: PaymentService,
    private paymentBTCService: PaymentBTCService,
    private paymentCardService: PaymentCardService,
    private router: Router
  ) { }

  ngOnInit() {

    this.sharedService.getCart().subscribe(
      (data: any) => {
        this.magazines = data.magazines;
        this.articles = data.articles;
        this.loadingArticles = false;
        this.loadingMagazines = false;
      });
    this.sharedService.getPaymentMethos().subscribe(
      (data: any) => {
        this.paymentMethods = data;
      });
  }

  setIndicator(indicator: string, id: number) {
    this.indicator = indicator;
    this.selectedId = id;
    this.getDataOrder();
  }

  choose(type: string) {
    this.selectedPaymentMode = type;
  }

  buy() {
    this.order.paymentType = this.selectedPaymentMode;
    if (this.selectedPaymentMode === "PAYPAL") {
      this.paymentService.makePayment(this.order).subscribe(
        (data: any) => {
          const url: string = data.redirect_url;
          window.location.href = url;
        }
      );
    }
    if (this.selectedPaymentMode === "BITCOIN") {
      this.paymentBTCService.makePaymentBTC(this.order).subscribe(
        (data: any) => {

          const url: string = data.redirect_url;
          window.location.href = url;
        }
      );
    }
    if (this.selectedPaymentMode === "CARD") {
      console.log("placanje");
      this.paymentCardService.makePaymentCard(this.order).subscribe(
        (data: any) => {
          const url: string = data.redirect_url;
          window.location.href = url;
        }
      );
    }
  }
  getDataOrder() {
    this.order.merchantTimestamp = new Date();
    this.order.merchantPassword = 'merchantPass';
    this.order.merchantId = '';
    this.order.merchantOrderId = '';
    this.order.amount = 0;
    this.order.executed = false;
    this.order.buyerUsername=localStorage.getItem("username");

    if (this.indicator === 'magazine') {
      this.sharedService.getOneMagazine(this.selectedId).subscribe(
        (data: any) => {
          this.order.magazine = data;
          this.order.amount = data.price;
          this.order.merchantId = data.author; 
          this.order.article = null;
          console.log("magazine");
        })
    }
    if (this.indicator === 'article') {
      this.sharedService.getOneArticle(this.selectedId).subscribe(
        (data: any) => {
          this.order.magazine = null;
          this.order.article = data;
          this.order.amount = data.price;
          this.order.merchantId = data.author;
          console.log("article");
        })
    }
  }

  setIndicatorForDelete(indicator: string, id: number) {
    this.idForDelete = id;
    this.indicatorForDelete = indicator;
  }

  remove() {
    if (this.indicatorForDelete === 'article') {
      this.removingArticle = true;
      this.sharedService.removeArticle(this.idForDelete).subscribe(
        data => {
          this.articles = this.articles.filter(article => article.id !== data);
          this.removingArticle = false;
        },
        err => {
          this.removingArticle = false;
          alert('Doslo je do greske. Pogledaj konzolu')
          console.log(err)
        }
      )
    }

    if (this.indicatorForDelete === 'magazine') {
      this.removingMagazine = true;
      this.sharedService.removeMagazine(this.idForDelete).subscribe(
        data => {
          this.magazines = this.magazines.filter(mag => mag.issn != data)
          this.removingMagazine = false;
        },
        err => {
          this.removingMagazine = false;
          alert('Doslo je do greske. Pogledaj konzolu')
          console.log(err)
        }
      )
    }

  }

}
