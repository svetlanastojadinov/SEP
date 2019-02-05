import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PaymentService } from './../payment_service/payment.service';
import { SharedService } from '../shared_service/shared.service'
@Component({
    selector: 'app-paypalsucces',
    templateUrl: './paypalsuccesMembership.component.html',
    styleUrls: ['./paypalsuccesMembership.component.css']
})

export class PaypalsuccesMembership implements OnInit {

    constructor(private paymentService: PaymentService,
        private activatedRoute: ActivatedRoute,
        private router: Router, private service: SharedService) { }
    ngOnInit() {
        this.activatedRoute.queryParams.subscribe(params => {
            if (params['paymentId'] !== undefined && params['PayerID'] !== undefined) {
                this.paymentService.completePayment(params['paymentId'], params['PayerID']).subscribe(
                    (data: any) => {
                        console.log(data);
                    });
            }
        })

        this.service.setMembership(localStorage.getItem("issn")).subscribe()
    }

    backToCart() {
        this.router.navigate(['center']);
    }


}
